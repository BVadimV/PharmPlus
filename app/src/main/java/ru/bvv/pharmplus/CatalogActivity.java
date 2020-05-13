package ru.bvv.pharmplus;

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

import ru.bvv.pharmplus.catalog.CatalogMedicinesActivity;
import ru.bvv.pharmplus.catalog.HygieneActivity;
import ru.bvv.pharmplus.catalog.LensesActivity;
import ru.bvv.pharmplus.catalog.MedGoodsActivity;
import ru.bvv.pharmplus.catalog.VitaminsActivity;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listCatalog = findViewById(R.id.list_item_catalog);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent (CatalogActivity.this, CatalogMedicinesActivity.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent (CatalogActivity.this, VitaminsActivity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent (CatalogActivity.this, HygieneActivity.class);
                    startActivity(intent);
                }
//                if(position == 3){
//                    Intent intent = new Intent (CatalogActivity.this, IntimActivity.class);
//                    startActivity(intent);
//                }
//                if(position == 4){
//                    Intent intent = new Intent (CatalogActivity.this, BeautyActivity.class);
//                    startActivity(intent);
//                }
                if(position == 5){
                    Intent intent = new Intent (CatalogActivity.this, LensesActivity.class);
                    startActivity(intent);
                }
//                if(position == 6){
//                    Intent intent = new Intent (CatalogActivity.this, MedTechActivity.class);
//                    startActivity(intent);
//                }
                if(position == 7){
                    Intent intent = new Intent (CatalogActivity.this, MedGoodsActivity.class);
                    startActivity(intent);
                }
//                switch(position){
//                    case(0):
//                        Intent intentMed = new Intent (CatalogActivity.this, CatalogMedicinesActivity.class);
//                        startActivity(intentMed);
//                        break;
//                    case(1):
//                        Intent intentVit = new Intent (CatalogActivity.this, CatalogMedicinesActivity.class);
//                        startActivity(intentVit);
//                        break;
//                }
            }
        };

        listCatalog.setOnItemClickListener(itemClickListener);
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
