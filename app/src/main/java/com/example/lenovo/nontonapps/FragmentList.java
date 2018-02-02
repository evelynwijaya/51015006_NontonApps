package com.example.lenovo.nontonapps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lenovo on 28/01/2018.
 */

public class FragmentList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list,null);
        setupRecyclerView(view);
        return view;
    }


    public void setupRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(),MovieData.getListMovie());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }
}
