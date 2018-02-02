package com.example.lenovo.nontonapps;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lenovo on 26/01/2018.
 */

public class MovieData {
    public static String jsondata;

    public void  setJsondata(String json){
        jsondata = json;
        Log.i("data",jsondata);
    }

    public static ArrayList<Movie> getListMovie(){
        String response = null;
        Movie movie = null;

        //Baca data
        FetchMovieData conn = (FetchMovieData) new FetchMovieData();
        conn.execute();
        try {
            jsondata = conn.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //MASUKKAN KE ARRAY
        ArrayList<Movie> list = new ArrayList<>();
        try {
            JSONObject jsonArray = new JSONObject(jsondata);
            JSONArray data = jsonArray.getJSONArray("results");
            Gson gson = new Gson();
            int i =0;
            while (i < data.length()){
                list.add(gson.fromJson(data.getJSONObject(i).toString(),Movie.class));
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
