package com.example.lenovo.nontonapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {

    TextView tv_username;
    Button bt_logout;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tv_username = (TextView) findViewById(R.id.tv_username);
        bt_logout = (Button) findViewById(R.id.bt_logout);

        Intent intent = getIntent();
        if(intent.hasExtra("email")){
            tv_username.setText(getIntent().getStringExtra("email"));
        }



        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProfilActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}
