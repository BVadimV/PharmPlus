package ru.bvv.pharmplus.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import ru.bvv.pharmplus.CatalogActivity;
import ru.bvv.pharmplus.MainActivity;
import ru.bvv.pharmplus.ProfileActivity;
import ru.bvv.pharmplus.R;
import ru.bvv.pharmplus.ShoppingCartActivity;

public class LensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenses);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

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
