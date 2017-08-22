package com.example.arief.belajarandroidretrofit6twopanefragment.tabfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arief.belajarandroidretrofit6twopanefragment.AsinkronCall.AsinkronLoadDataFromDatabase;
import com.example.arief.belajarandroidretrofit6twopanefragment.AsinkronCall.CustomAsinkronLoader;
import com.example.arief.belajarandroidretrofit6twopanefragment.R;
import com.example.arief.belajarandroidretrofit6twopanefragment.adapters.RecViewAdapter;
import com.example.arief.belajarandroidretrofit6twopanefragment.model.Person;
import com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler.RetrofitClient;

import java.util.ArrayList;

/**
 * Created by Arief on 8/20/2017.
 */

public  class TabTwoFragment extends Fragment  implements TabOneFragment.insertListener{


    private RecViewAdapter adapter;

    private RecyclerView recView;

    private ArrayList<Person> data;

    private RecyclerView.LayoutManager lat;

    private  RetrofitClient rc;


    private LoaderManager manager;

    private LoaderManager.LoaderCallbacks<ArrayList<Person>> callbacks = new LoaderManager.LoaderCallbacks<ArrayList<Person>>() {


        @Override
        public Loader<ArrayList<Person>> onCreateLoader(int id, Bundle args) {
            return new CustomAsinkronLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<ArrayList<Person>> loader, ArrayList<Person> data) {
            recView.setAdapter(new RecViewAdapter(data));
        }

        @Override
        public void onLoaderReset(Loader<ArrayList<Person>> loader) {

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_two, container, false);
        Log.e("info view", "onCreateView");
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("info Attach", "attached");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        data = new ArrayList<>();

        recView = (RecyclerView) getActivity().findViewById(R.id.recView);

        lat = new LinearLayoutManager(getActivity());


        Log.e("onCreateActivity", "ACtivity Created and afterLoad true");


        recView.setHasFixedSize(true);
        recView.setLayoutManager(lat);

        adapter  = new RecViewAdapter(data);
        recView.setAdapter(adapter);

        manager = getActivity().getSupportLoaderManager();


        manager.initLoader(0,null,callbacks);


    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("TabTwo","On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TabTwo","onPause");
    }


    @Override
    public void isInsert(boolean b, final Context con) {
       if(b){



           FragmentActivity ac = ((FragmentActivity)con);

                RecyclerView rec = (RecyclerView)ac.findViewById(R.id.recView);

                if(rec!=null){
                    rec.setHasFixedSize(true);
                    rec.setLayoutManager(new LinearLayoutManager(ac));

                    new AsinkronLoadDataFromDatabase(rec).execute();

                }


        }

    }




}


