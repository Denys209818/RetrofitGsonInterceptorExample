package com.example.retrofitexample.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit retrofit;

    private NetworkService()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .callTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JsonPlaceholderApi getRetrofitApi()
    {
        return retrofit.create(JsonPlaceholderApi.class);
    }

    public static NetworkService getInstance()
    {
        if(networkService == null)
            networkService = new NetworkService();

        return networkService;
    }
}
