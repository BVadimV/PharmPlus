package ru.bvv.pharmplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.bvv.pharmplus.catalog.CategoryActivity;

public class ProfileActivity extends AppCompatActivity {

    private final String TAG = "UserDetailActivity";

    TextView firstnameField, lastnameField, emailField, phoneField;
    DatabaseReference dbRef;
    FirebaseAuth auth;
    User user;
    LinearLayout actionProfile;
    FirebaseDatabase database;
    ValueEventListener mUserListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstnameField = findViewById(R.id.tv_prof_firstname);
        lastnameField = findViewById(R.id.tv_prof_lastname);
        emailField = findViewById(R.id.tv_prof_email);
        phoneField = findViewById(R.id.tv_prof_phone);

        actionProfile = findViewById(R.id.action_profile);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference()
                .child("Users");

    }

    public void onStart(){
        super.onStart();

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstname = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("firstname").getValue(String.class);
                String lastname = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lastname").getValue(String.class);
                String email = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").getValue(String.class);
                String phone = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").getValue(String.class);

                firstnameField.append(": " + firstname);
                lastnameField.append(": " + lastname);
                emailField.append(": " + email);
                phoneField.append(": " + phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "LoadUser: onCancelled", databaseError.toException());
                Toast.makeText(ProfileActivity.this, "Failed to losd data", Toast.LENGTH_SHORT).show();
            }
        };
        dbRef.addValueEventListener(userListener);

        mUserListener = userListener;
    }

    private void updateUI() {

        String userFirstname = user.getFirstname().toString();
        firstnameField.append(": " + userFirstname);
        lastnameField.append(": " + user.getLastname());
        emailField.setText(user.getEmail());

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
                Intent intentCata = new Intent(this, CategoryActivity.class);
                startActivity(intentCata);
                return true;
            case R.id.action_map:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
            case R.id.action_shopping:
                Intent intentShop = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentShop);
                return true;
            case R.id.action_contacts:
                Intent intentContacts = new Intent(this, ContactsActivity.class);
                startActivity(intentContacts);
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
