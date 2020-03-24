package fr.esilvandroid.android_project;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    //Base URL
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    //Create the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //add converter
                    .addConverterFactory(GsonConverterFactory.create())
                    //build retrofit instance
                    .build();
        }
        return retrofit;
    }

}
