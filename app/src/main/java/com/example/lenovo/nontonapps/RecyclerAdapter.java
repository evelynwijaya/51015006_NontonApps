package com.example.lenovo.nontonapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Lenovo on 26/01/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<Movie> movie;
    private LayoutInflater inflater;
    private Context context;

    public RecyclerAdapter (Context context,List<Movie>listmovie){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.movie = listmovie;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_movie,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie current = movie.get(position);
        holder.setData(current,position);
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_judul, tv_date, tv_rating;
        ImageView img_row;
        int position;
        Movie current;

        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            tv_judul = (TextView)itemView.findViewById(R.id.tv_judul);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
            tv_rating = (TextView)itemView.findViewById(R.id.tv_rating);
            img_row = (ImageView)itemView.findViewById(R.id.img_row);
        }

        public void setData(Movie current, int position) {
            String imageUrl = "http://image.tmdb.org/t/p/w185"+current.getPoster_path();
            Glide.with(context).load(imageUrl).into(img_row);

            Log.d("IMG >>>>>>", imageUrl);

            this.tv_judul.setText(current.getTitle());
            this.tv_date.setText(current.getRelease_date());
            this.tv_rating.setText(current.getPopularity().toString());

            this.current = current;
            this.position = position;
        }

        public void setListeners() {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 FragmentCommunicator fragmentCommunicator = (FragmentCommunicator) context;
                 fragmentCommunicator.displayDetail(current);
                }
            });
        }
    }
}
