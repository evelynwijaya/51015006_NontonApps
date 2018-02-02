package com.example.lenovo.nontonapps;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getIntent().getParcelableExtra("movie");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentDetail detailFragment = (FragmentDetail)fragmentManager.findFragmentById(R.id.fragmentD);
        detailFragment.displayData(movie);
    }
}
