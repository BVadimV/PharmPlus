package ru.bvv.pharmplus.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import ru.bvv.pharmplus.CatalogActivity;
import ru.bvv.pharmplus.MainActivity;
import ru.bvv.pharmplus.ProfileActivity;
import ru.bvv.pharmplus.R;
import ru.bvv.pharmplus.ShoppingCartActivity;
import ru.bvv.pharmplus.catalog.medicines.AkusherstvoGynecologyActivity;

public class CatalogMedicinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_medicines);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listItemMedicines = findViewById(R.id.list_item_medicines);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent (CatalogMedicinesActivity.this, AkusherstvoGynecologyActivity.class);
                    startActivity(intent);
                }
//                if(position == 1){
//                    Intent intent = new Intent (CatalogMedicinesActivity.this, AlergieActivity.class);
//                    startActivity(intent);
//                }
                // и т.д.
            }
        };

        listItemMedicines.setOnItemClickListener(itemClickListener);
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
