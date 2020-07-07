package ru.bvv.pharmplus.catalog.medicines;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.google.firebase.auth.FirebaseAuth;

import ru.bvv.pharmplus.CatalogActivity;
import ru.bvv.pharmplus.MainActivity;
import ru.bvv.pharmplus.PharmplusDBHelper;
import ru.bvv.pharmplus.ProfileActivity;
import ru.bvv.pharmplus.R;
import ru.bvv.pharmplus.ShoppingCartActivity;

public class AkusherstvoGynecologyInfectionActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akusherstvo_gynecology_infection);

        ListView listView = (ListView) findViewById(R.id.list_item_gynecology_infection);
        SQLiteOpenHelper pharmplusDBHelper = new PharmplusDBHelper(this);
        try{
            db = pharmplusDBHelper.getReadableDatabase();
            cursor = db.query("MEDICINES", new String[]{"_id", "NAME"}, "CATEGORY = ?", new String[]{"infections"}, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            listView.setAdapter(listAdapter);
        } catch(SQLiteException e){
            e.getMessage();
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listMedicines, View itemView, int position, long id) {
                Intent intent = new Intent(AkusherstvoGynecologyInfectionActivity.this, MedicinesActivity.class);
                intent.putExtra(MedicinesActivity.EXTRA_MEDICINESID, (int) id);
//                intent.putExtra(MedicinesActivity.EXTRA_CATEGORY, "infertility");
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

//    public void onClickOrgalutran(View view) {
//        startActivity(new Intent(getApplicationContext(), MedicinesActivity.class));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_shopping_cart:
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_profile:
                Intent intentProf = new Intent(this, ProfileActivity.class);
                startActivity(intentProf);
                return true;
            case R.id.action_catalog:
                Intent intentCata = new Intent(this, CatalogActivity.class);
                startActivity(intentCata);
                return true;
//            case R.id.action_map:
//                Intent intentMap = new Intent(this, )
            case R.id.action_shopping:
                Intent intentShop = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentShop);
                return true;
//            case R.id.action_contacts:
//                Intent
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
