package ru.bvv.pharmplus.catalog.product;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import ru.bvv.pharmplus.ContactsActivity;
import ru.bvv.pharmplus.DBHelper;
import ru.bvv.pharmplus.MainActivity;
import ru.bvv.pharmplus.MapActivity;
import ru.bvv.pharmplus.ProfileActivity;
import ru.bvv.pharmplus.R;
import ru.bvv.pharmplus.ShoppingCartActivity;
import ru.bvv.pharmplus.catalog.CategoryActivity;
import ru.bvv.pharmplus.catalog.ProductItem;

public class ProductList extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "category";
    private SQLiteOpenHelper pharmaplusDBHelper;
    private String currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        currentCategory = intent.getStringExtra(EXTRA_CATEGORY);

//        pharmaplusDBHelper = new PharmplusDBHelper(this);
        pharmaplusDBHelper = new DBHelper(this);
// Create list for Recycler View.
        List<ProductItem> productList = generateNewValues();
        ProductAdapter adapter = new ProductAdapter(productList);

        RecyclerView listItem = findViewById(R.id.list_item_products);
        listItem.setLayoutManager(new LinearLayoutManager(this));
        listItem.setHasFixedSize(true);
        listItem.setAdapter(adapter);
    }

    // Создание списка объектов с Наименованиями и ценами товара
    private List<ProductItem> generateNewValues() {
        List<ProductItem> productList = new ArrayList<>();
        try {
            SQLiteDatabase db = pharmaplusDBHelper.getReadableDatabase();
            Cursor cursor = db.query("medicines", new String[]{"_ID", "NAME", "COST", "CATEGORY"},
                    "CATEGORY = ?", new String[]{currentCategory}, null, null, "NAME ASC");
            if (cursor.moveToFirst()) {// moveToFirst
                do {
                    String titleText = cursor.getString(1);
                    String costInt = cursor.getInt(2) + " тенге";

                    productList.add(new ProductItem(titleText, costInt));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            e.getMessage();
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        Log.d("SQLite", "title: " + productList.toString());

        return productList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_profile:
                Intent intentProfile = new Intent(this, ProfileActivity.class);
                startActivity(intentProfile);
                return true;
            case R.id.action_catalog:
                Intent intentCata = new Intent(this, CategoryActivity.class);
                startActivity(intentCata);
                return true;
            case R.id.action_map:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                return true;
            case R.id.action_shopping_cart:
                Intent intentShop = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentShop);
                return true;
            case R.id.action_contacts:
                Intent intentContacts = new Intent(this, ContactsActivity.class);
                startActivity(intentContacts);
                return true;
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
