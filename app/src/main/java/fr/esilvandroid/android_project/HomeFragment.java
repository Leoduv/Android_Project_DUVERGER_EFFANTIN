package fr.esilvandroid.android_project;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Get context and database
        Context ctx = getActivity();
        SQLiteHelper sqliteHelper = new SQLiteHelper(ctx);

        //Get user id
        String user_id = UserHelper.getUserId();
        //Create list
        List<OffersModel> offerList;
        offerList = sqliteHelper.getOfferList(user_id);

        //Get reference to recyclerView
        RecyclerView offerRecyclerView = rootView.findViewById(R.id.recviewOffer);

        //Set adapter
        OfferRecAdapter adapter = new OfferRecAdapter(offerList);

        ((SimpleItemAnimator) offerRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        offerRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        offerRecyclerView.setAdapter(adapter);
        offerRecyclerView.setHasFixedSize(true);
        Bundle home_bundle = new Bundle();
        home_bundle.putString("user_id", user_id);
        setArguments(home_bundle);
        return rootView;
    }

}

