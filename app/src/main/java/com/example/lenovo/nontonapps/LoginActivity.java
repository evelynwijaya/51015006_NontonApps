package com.example.lenovo.nontonapps;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.nontonapps.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    EditText edtEmail, edtPassword;
    Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);

        btnLogin = findViewById(R.id.btnlogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String femail = edtEmail.getText().toString();
                String fpass = edtPassword.getText().toString();
                if((!"".equals(femail)) || (!"".equals(fpass))) {
                    insertdatauser(femail,fpass);
                    Toast.makeText(getApplicationContext(), "Register Successfullly", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Tidak Lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                cursor = db.rawQuery("Select * From " + DatabaseHelper.Table_User + " Where " + DatabaseHelper.UserCol_1 + " =? AND " + DatabaseHelper.UserCol_2 + "=?", new String[]{email,pass});
                if (cursor != null){
                    if(cursor.getCount() > 0 ){
                        cursor.moveToNext();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Datasaya", edtEmail.getText().toString());
                        startActivity(intent);

                        edtEmail.setText("");
                        edtPassword.setText("");
                    } else{
                        Toast.makeText(getApplicationContext(), "Email atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void insertdatauser(String femail, String fpass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.UserCol_1, femail);
        contentValues.put(DatabaseHelper.UserCol_2, fpass);
        Long id = db.insert(DatabaseHelper.Table_User, null, contentValues);
    }
}
