package com.bestreads.rest;

/**
 * Created by Mansi on 1/2/18.
 */

import com.bestreads.NyBsClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class BSApiClient {

    public static final String BASE_URL = "https://api.nytimes.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getBSAPIIdentifier(){
        return NyBsClient.getInstance().getBSIdentifier();
    }

}
