package fr.esilvandroid.android_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    Button btnconseil;
    Button btnajouter;
    Button btntodolist;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        btnconseil = rootView.findViewById(R.id.button_conseil);
        btnajouter = rootView.findViewById(R.id.button_add);
        btntodolist = rootView.findViewById(R.id.button_todolist);

        btnconseil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ConseilActivity.class);
                startActivity(intent);
            }
        });

        btnajouter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddOfferActivity.class);
                startActivity(intent);
            }
        });

        btntodolist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), RetrofitActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
