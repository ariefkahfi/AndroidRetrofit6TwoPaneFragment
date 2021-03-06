package com.example.arief.belajarandroidretrofit6twopanefragment.AsinkronCall;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.AsyncTaskLoader;

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

public class CustomAsinkronLoader extends AsyncTaskLoader<ArrayList<Person>> {


    public CustomAsinkronLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public ArrayList<Person> loadInBackground() {
        ArrayList<Person> data = new ArrayList<>();

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
    public void deliverResult(ArrayList<Person> data) {
        super.deliverResult(data);
    }


}

