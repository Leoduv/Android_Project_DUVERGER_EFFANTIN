package fr.esilvandroid.android_project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

//Specify the request type and pass the relative URL//

    @GET("/todos")

//Wrap the response in a Call object with the type of the expected result//
    Call<List<RetroTodos>> getAllTodos();
}
