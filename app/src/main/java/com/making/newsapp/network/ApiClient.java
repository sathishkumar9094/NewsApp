package com.making.newsapp.network;

import com.making.newsapp.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;


    public static Retrofit getApiClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();

        }

        return retrofit;
    }


}
