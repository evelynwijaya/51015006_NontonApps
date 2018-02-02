package com.example.lenovo.nontonapps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Lenovo on 28/01/2018.
 */

public class FragmentDetail extends Fragment {
    private TextView tv_name, tv_description;
    private ImageView img_item;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail,null);
         img_item = (ImageView)view.findViewById(R.id.img_item);
         tv_name = (TextView)view.findViewById(R.id.tv_name);
         tv_description = (TextView)view.findViewById(R.id.tv_description);
         return view;
    }

    public void displayData(Movie movie){
        String imageUrl = "http://image.tmdb.org/t/p/w185/"+movie.getPoster_path();
        Glide.with(this).load(imageUrl).into(img_item);
        tv_name.setText(movie.getTitle());
        tv_description.setText(movie.getOverview());
    }
}
