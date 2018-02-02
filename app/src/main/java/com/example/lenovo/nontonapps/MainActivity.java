package com.example.lenovo.nontonapps;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  FragmentCommunicator{

    private boolean netOk;
    private boolean isDualPane = false;
    private FragmentDetail fragmentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
