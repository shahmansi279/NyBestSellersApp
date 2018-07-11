package com.bestreads.rest;

import com.bestreads.NyBsClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mansshah on 6/24/18.
 */

public class GBApiClient {

        public static final String BASE_URL = "https://www.googleapis.com/books/";
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


        public static String getGAPIIdentifier(){
            return NyBsClient.getInstance().getGIdentitifer();
        }
}
