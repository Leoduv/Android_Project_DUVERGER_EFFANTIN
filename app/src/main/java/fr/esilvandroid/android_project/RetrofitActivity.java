package fr.esilvandroid.android_project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private MyAdapterRetrofit myAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //Create a handler for the RetrofitInstance interface//

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<List<RetroTodos>> call = service.getAllTodos();

        //Execute the request asynchronously//

        call.enqueue(new Callback<List<RetroTodos>>() {
            @Override
            //Handle a successful response//
            public void onResponse(Call<List<RetroTodos>> call, Response<List<RetroTodos>> response) {
                loadDataList(response.body());
            }
            @Override
            //Handle execution failures//
            public void onFailure(Call<List<RetroTodos>> call, Throwable throwable) {
            //If the request fails, then display the following toast//
                Toast.makeText(RetrofitActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Display the retrieved data as a list//
    private void loadDataList(List<RetroTodos> todosList) {

        //Get a reference to the RecyclerView//
        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapterRetrofit(todosList);

        //Use a LinearLayoutManager with default vertical orientation//
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView//
        myRecyclerView.setAdapter(myAdapter);
    }
}
