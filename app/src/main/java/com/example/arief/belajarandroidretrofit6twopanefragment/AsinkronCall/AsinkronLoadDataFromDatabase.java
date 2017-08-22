package com.example.arief.belajarandroidretrofit6twopanefragment.AsinkronCall;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.arief.belajarandroidretrofit6twopanefragment.adapters.RecViewAdapter;
import com.example.arief.belajarandroidretrofit6twopanefragment.model.Person;
import com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler.RetrofitAPI;
import com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Arief on 8/22/2017.
 */

public class AsinkronLoadDataFromDatabase extends AsyncTask<Void,Integer,ArrayList<Person>> {

    private RecyclerView recyclerView;

    public AsinkronLoadDataFromDatabase() {
    }

    public AsinkronLoadDataFromDatabase(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected ArrayList<Person> doInBackground(Void... params) {

        ArrayList<Person> data  = new ArrayList<>();

        Call<JsonArray> arr = RetrofitClient.getInstance()
                .create(RetrofitAPI.class)
                .getCall();
        Response<JsonArray> response = null;


        try {
            response = arr.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(JsonElement el : response.body()){
            int id = el.getAsJsonObject().get("id").getAsInt();
            String nama = el.getAsJsonObject().get("nama").getAsString();

            data.add(new Person(id,nama));

        }
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Person> persons) {
        super.onPostExecute(persons);

        RecViewAdapter adapter = new RecViewAdapter(persons);
        recyclerView.setAdapter(adapter);

    }
}
