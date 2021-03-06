package fr.esilvandroid.android_project;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerView;
    private List<Item> listItem;
    private Context mContext;



    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        myrecyclerView = (RecyclerView) v.findViewById(R.id.item_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listItem);
        myrecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        myrecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStats){
        super.onCreate(savedInstanceStats);

        listItem = new ArrayList<>();
        listItem.add(new Item("LinkedIn", R.drawable.linkedin, "https://www.linkedin.com/feed/"));
        listItem.add(new Item("Indeed", R.drawable.indeed_log, "https://www.indeed.fr"));
        listItem.add(new Item("Monster", R.drawable.monster, "https://www.indeed.fr"));
        listItem.add(new Item("Météo Job", R.drawable.meteojob, "https://www.meteojob.com"));

    }


}
