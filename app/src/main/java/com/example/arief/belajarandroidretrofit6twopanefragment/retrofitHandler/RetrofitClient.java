package com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.arief.belajarandroidretrofit6twopanefragment.adapters.RecViewAdapter;
import com.example.arief.belajarandroidretrofit6twopanefragment.model.Person;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arief on 8/20/2017.
 */

public class RetrofitClient {


    private static final String BASE_URL = "http://192.168.1.101/AndroidRetrofit5/";


    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Context con;

    public RetrofitClient() {
    }

    public RetrofitClient(Context con) {
        this.con = con;
    }

    public void insertData(int id, String nama){
        Map<String,Object> map = new HashMap<>();


        map.put("id",id);
        map.put("nama",nama);

        RetrofitClient
                .getInstance()
                .create(RetrofitAPI.class)
                .insertCall(map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Error callInsert",t.getMessage());
                        t.printStackTrace();
                    }
                });

    }


    public void clearAndLoadAll(final RecyclerView rec , RecyclerView.LayoutManager lat ){

        rec.setHasFixedSize(true);

        rec.setLayoutManager(lat);



            RetrofitClient.getInstance()
                    .create(RetrofitAPI.class)
                    .getCall()
                    .enqueue(new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            ArrayList<Person> data = new ArrayList<Person>();
                            for(JsonElement el : response.body()){
                                int id = el.getAsJsonObject().get("id").getAsInt();
                                String nama = el.getAsJsonObject().get("nama").getAsString();

                                data.add(new Person(id,nama));
                            }
                            rec.setAdapter(new RecViewAdapter(data));
                        }

                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {
                            Log.e("err on ClearAndLoad",t.getMessage());
                            t.printStackTrace();
                        }
                    });
    }

    public void loadAllData(final RecyclerView rec , final RecViewAdapter adapter , RecyclerView.LayoutManager lat){

        rec.setHasFixedSize(true);
        rec.setLayoutManager(lat);

        rec.setAdapter(adapter);

        RetrofitClient.getInstance()
                .create(RetrofitAPI.class)
                .getCall()
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        adapter.getData().clear();

                        for(JsonElement el : response.body()){
                            int id = el.getAsJsonObject().get("id").getAsInt();
                            String nama = el.getAsJsonObject().get("nama").getAsString();
                            adapter.getData().add(new Person(id,nama));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e("Error loadData",t.getMessage());
                        t.printStackTrace();
                    }
                });

    }

}
