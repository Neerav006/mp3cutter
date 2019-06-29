package com.codefuelindia.ringtonemaker.common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String baseUrl) {

       // HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
       // httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
            final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS);

            //okHttpClient.addInterceptor(httpLoggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;


    }



    public static Retrofit getClient22(String baseUrl) {

       // HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
       // httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS);

           // okHttpClient.addInterceptor(httpLoggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        return retrofit;


    }


    public static Retrofit getPdfClient(String baseUrl) {

      //  HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
       // httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

       // okHttpClient.addInterceptor(httpLoggingInterceptor);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;


    }

}