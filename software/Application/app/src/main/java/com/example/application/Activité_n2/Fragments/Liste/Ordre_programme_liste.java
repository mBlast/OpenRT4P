package com.example.application.Activité_n2.Fragments.Liste;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ordre_programme_liste extends Fragment {


    public Ordre_programme_liste() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ordre_programme_liste, container,false);
        return view;
    }

}
