package com.example.lenovo.nontonapps;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  FragmentCommunicator{

    private boolean netOk;
    private boolean isDualPane = false;
    private FragmentDetail fragmentDetail;
    private String tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.hasExtra("Datasaya")) {
            tmp = getIntent().getStringExtra("Datasaya");
        }

        //just info tentang network
        netOk = NetworkHelper.hasNetworkAccess(this);
        Toast.makeText(this,"NetworkOK: "+netOk, Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentDetail = (FragmentDetail)fragmentManager.findFragmentById(R.id.fragmentD);

        View fragmentDView = findViewById(R.id.fragmentD);
        isDualPane = fragmentDView !=null && fragmentDView.getVisibility()==View.VISIBLE;
    }

    @Override
    public void displayDetail(Movie movie) {
        if (isDualPane){
            fragmentDetail.displayData(movie);

        }else {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("movie",movie);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profil:
                Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
                intent.putExtra("email",tmp);
                startActivity(intent);
                return true;
            case R.id.logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
