package com.musicalcoder.authentication;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_Client {
    private static final String BASE_URL = "https://laicare.herokuapp.com/api/v2/laicare/";
    private  static Api_Client aInstance;
    private Retrofit retrofit;

    private Api_Client(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Api_Client getInstance(){
        if(aInstance == null){
            aInstance = new Api_Client();
        }
        return aInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
