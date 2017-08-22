package com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler;

import com.google.gson.JsonArray;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Arief on 8/20/2017.
 */

public interface RetrofitAPI {



    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody> insertCall(@FieldMap Map<String,Object> map);

    @GET("select.php")
    Call<JsonArray> getCall();


}
