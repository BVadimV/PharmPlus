package ru.bvv.pharmplus.catalog.medicines;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ru.bvv.pharmplus.PharmplusDBHelper;
import ru.bvv.pharmplus.R;

public class MedicinesActivity extends AppCompatActivity {

    Button buttonBuy;
    public static final String EXTRA_MEDICINESID = "medicinesId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        int medicinesId = (Integer)getIntent().getExtras().get(EXTRA_MEDICINESID);

        SQLiteOpenHelper pharmaplusDBHelper = new PharmplusDBHelper(this);
        try{
            SQLiteDatabase db = pharmaplusDBHelper.getReadableDatabase();
            Cursor cursor = db.query("MEDICINES", new String[]{"NAME", "CHARACTERISTIC", "COMPOSITION", "PHARMACOLOGY_EFFECT", "INDICATION_FOR_USE", "CONTRAINDICATIONS", "SIDE_EFFECTS", "MODE_OF_APPLICATION", "RELEASE_FORM", "SHELF_LIFE", "COST", "IMAGE_RESOURCE_ID"}, "_id = ?", new String[]{String.valueOf(medicinesId)}, null, null, null);

            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String characteristicText = cursor.getString(1);
                String compositionText = cursor.getString(2);
                String pharmaText = cursor.getString(3);
                String indicationsText = cursor.getString(4);
                String contraindicationsText = cursor.getString(5);
                String sideEffectsText = cursor.getString(6);
                String modeOfApplicText = cursor.getString(7);
                String releaseFormText = cursor.getString(8);
                String shelfLifeText = cursor.getString(9);
                int costInt = cursor.getInt(10);
                int photoId = cursor.getInt(11);

                ImageView imageView = (ImageView) findViewById(R.id.iv_medicines);
                imageView.setImageResource(photoId);
                imageView.setContentDescription(nameText);

                TextView cost = (TextView) findViewById(R.id.tv_cost);
                cost.setText(String.valueOf(costInt));

                TextView name = (TextView) findViewById(R.id.tv_medicines_name);
                name.setText(nameText);

                TextView characteristics = (TextView) findViewById(R.id.tv_characteristics);
                characteristics.setText(characteristicText);

                TextView composition = (TextView) findViewById(R.id.tv_composition);
                composition.setText(compositionText);

                TextView pharma = findViewById(R.id.tv_tv_pharmacology);
                pharma.setText(pharmaText);

                TextView indication = findViewById(R.id.tv_indication);
                indication.setText(indicationsText);

                TextView contraind = findViewById(R.id.tv_contraindication);
                contraind.setText(contraindicationsText);

                TextView sideEffect = findViewById(R.id.tv_side_effect);
                sideEffect.setText(sideEffectsText);

                TextView modeofapp = findViewById(R.id.tv_mode_of_app);
                modeofapp.setText(modeOfApplicText);

                TextView releaseForm = findViewById(R.id.tv_release_form);
                releaseForm.setText(releaseFormText);

                TextView shelfLife = findViewById(R.id.tv_shelf_life);
                shelfLife.setText(shelfLifeText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            e.getMessage();
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        buttonBuy = findViewById(R.id.btn_buy1);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
