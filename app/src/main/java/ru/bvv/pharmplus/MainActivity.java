package ru.bvv.pharmplus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    TextView profile, catalog, map, settings;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    LinearLayout mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        profile = findViewById(R.id.tv_profile);
        catalog = findViewById(R.id.tv_catalog);
        map = findViewById(R.id.tv_map);

        mainActivity = findViewById(R.id.main_activity);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");


    }


//    profile.setOnClickListener(new View.OnClickListener)

    public void onClickProfile(View view) {
//        Intent intent = new Intent(getApplicationContext(), AuthorizationActivity.class);
//        startActivity(intent);
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(this, ProfileActivity.class));
        } else {
            showSignInWindow();
        }
    }

    public void onClickCatalog(View view) {
              //создать ссылку на каталог!!!
        Intent intent = new Intent(getApplicationContext(), CatalogActivity.class);
        startActivity(intent);
    }

    public void onClickMap(View view) {
            // Создать ссылку на карту которая будет сразу показывать аптеки!!!
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }

    public void onClickContacts(View view) {
        //Ссылка на страницу контакты
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
//                Intent intentProf = new Intent(this, AuthorizationActivity.class);
//                startActivity(intentProf);
                showSignInWindow();
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

    private void showSignInWindow(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog. setView(sign_in_window);

        final MaterialEditText email = sign_in_window.findViewById(R.id.email_field);
        final MaterialEditText password = sign_in_window.findViewById(R.id.password_field);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(mainActivity, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().length() < 5){
                    Snackbar.make(mainActivity, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //Добавить действие которое будет происходить при успешной авторизации
                                Snackbar.make(mainActivity, "Успешная авторизация", Snackbar.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mainActivity, "Ошибка авторизации" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.setNeutralButton("Регистрация", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                showRegistrationWindow();
            }
        });

                dialog.show();
    }

    private void showRegistrationWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Веведите данные");

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);// check this parameter
        View registration_window = inflater.inflate(R.layout.registration_window, null);
        dialog.setView(registration_window);

        final MaterialEditText email = registration_window.findViewById(R.id.email_field);
        final MaterialEditText phone = registration_window.findViewById(R.id.phone_field);
        final MaterialEditText firstname = registration_window.findViewById(R.id.firstname_field);
        final MaterialEditText lastname = registration_window.findViewById(R.id.lastname_field);
        final MaterialEditText password = registration_window.findViewById(R.id.password_field);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Registration", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(mainActivity, "Введите почту!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(firstname.getText().toString())){
                    Snackbar.make(mainActivity, "Введите ваше имя!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname.getText().toString())){
                    Snackbar.make(mainActivity, "Введите вашу фамилию!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(mainActivity, "Введите ваш телефон!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Snackbar.make(mainActivity, "Введите пароль!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Регистрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setFirstname(firstname.getText().toString());
                                user.setLastname(lastname.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setPassword(password.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(mainActivity, "Пользователь добавлен.", Snackbar.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(mainActivity, "Ошибка регистрации! " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });

        dialog.show();
    }
}
