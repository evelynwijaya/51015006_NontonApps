package com.example.lenovo.nontonapps;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText et_username,et_password;
    Button bt_login, bt_regis;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("users");

    SharedPreferences mylocaldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        bt_login = (Button)findViewById(R.id.bt_login);
        bt_regis = (Button)findViewById(R.id.bt_regis);
        

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();

                //pemanggilan data pada firebase berdasarkan email

                userRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mylocaldata = getSharedPreferences("mylocaldata",MODE_PRIVATE);
                        User user = new User();
                        if (dataSnapshot.exists()){
                            user.setUsername(dataSnapshot.child("username").getValue(String.class));
                            user.setPassword(dataSnapshot.child("password").getValue(String.class));

                            SharedPreferences.Editor editor = mylocaldata.edit();
                            editor.putString("uid",user.getUsername());
                            editor.apply();

                            //pindah ke main activity
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("Datasaya", et_username.getText().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            Context context = getApplicationContext();
                            CharSequence text = "User tidak ditemukan";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context,text,duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        bt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
