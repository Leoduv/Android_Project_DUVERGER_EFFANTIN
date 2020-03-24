package fr.esilvandroid.android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ConseilActivity extends AppCompatActivity implements ConseilAdapter.SelectedConseil{

    RecyclerView recyclerView;

    List<ConseilsModel> conseilsModelList = new ArrayList<>();

    String[] titles = {"CV", "Lettres de motivation", "Entretien"};
    String[] descs = {"Préparation CV", "Préparation Lettres de motivation", "Préparation Entretien"};

    ConseilAdapter conseilAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil);

        recyclerView = findViewById(R.id.recyclerview);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        for (int i=0; i<titles.length; i++){
            ConseilsModel conseilsModel = new ConseilsModel(titles[i], descs[i]);

            conseilsModelList.add(conseilsModel);
        }

        conseilAdapter = new ConseilAdapter(conseilsModelList, this);

        recyclerView.setAdapter(conseilAdapter);
    }

    @Override
    public void selectedConseil(ConseilsModel conseilsModel) {

        startActivity(new Intent(ConseilActivity.this, SelectedConseilActivity.class).putExtra("data", conseilsModel));

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.nav_items, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                conseilAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.search_view){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
