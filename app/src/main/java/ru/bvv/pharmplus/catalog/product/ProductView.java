package ru.bvv.pharmplus.catalog.product;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.bvv.pharmplus.DBHelper;
import ru.bvv.pharmplus.R;

public class ProductView extends AppCompatActivity {

    private Button buttonBuy;
    public static final String EXTRA_MEDICINES_ID = "medicinesId";
    private int currentId;
    private ImageView imageView;
    private TextView cost, name,characteristics, composition, pharma,
            indication, contraind, sideEffect, modeofapp, releaseForm, shelfLife;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        currentId = intent.getIntExtra(EXTRA_MEDICINES_ID, 0);

        SQLiteOpenHelper pharmaplusDBHelper = new DBHelper(this);
        try{
            SQLiteDatabase db = pharmaplusDBHelper.getReadableDatabase();
            Cursor cursor = db.query("medicines", new String[]{"NAME", "CHARACTERISTIC", "COMPOSITION",
                            "PHARMACOLOGY_EFFECT", "INDICATIONS_FOR_USE", "CONTRAINDICATIONS", "SIDE_EFFECTS",
                            "MODE_OF_APPLICATION", "RELEASE_FORM", "SHELF_LIFE", "COST", "IMAGE"},
                    "_ID = ?", new String[]{String.valueOf(currentId)}, null, null, null);

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
                String photoText = cursor.getString(11);

                imageView = (ImageView) findViewById(R.id.iv_medicines);
                int resId = getResources().getIdentifier(photoText, "drawable", getPackageName());
                imageView.setImageResource(resId);
                imageView.setContentDescription(nameText);

                cost = (TextView) findViewById(R.id.tv_cost);
                cost.setText(String.valueOf(costInt) + " тг.");

                name = (TextView) findViewById(R.id.tv_medicines_name);
                name.setText(nameText);

                characteristics = (TextView) findViewById(R.id.tv_characteristics);
                characteristics.setText(characteristicText);

                composition = (TextView) findViewById(R.id.tv_composition);
                composition.setText(compositionText);

                pharma = findViewById(R.id.tv_tv_pharmacology);
                pharma.setText(pharmaText);

                indication = findViewById(R.id.tv_indication);
                indication.setText(indicationsText);

                contraind = findViewById(R.id.tv_contraindication);
                contraind.setText(contraindicationsText);

                sideEffect = findViewById(R.id.tv_side_effect);
                sideEffect.setText(sideEffectsText);

                modeofapp = findViewById(R.id.tv_mode_of_app);
                modeofapp.setText(modeOfApplicText);

                releaseForm = findViewById(R.id.tv_release_form);
                releaseForm.setText(releaseFormText);

                shelfLife = findViewById(R.id.tv_shelf_life);
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