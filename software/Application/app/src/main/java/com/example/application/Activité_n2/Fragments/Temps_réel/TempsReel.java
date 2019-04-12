package com.example.application.Activité_n2.Fragments.Temps_réel;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.application.Activité_n2.Fragments.Menu.Menu;
import com.example.application.Activité_n2.Order.ListOrder;
import com.example.application.Activité_n2.Order.TempsReelOrder;
import com.example.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TempsReel extends Fragment {

    static public TempsReel temps_reel = new TempsReel();

    public TempsReel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_temps_reel, container, false);

        EditText text = v.findViewById(R.id.text);

        Button save = v.findViewById(R.id.save_temps_reel);
        Button send = v.findViewById(R.id.send_temps_reel);
        Button charger = v.findViewById(R.id.charger);

        final EditText acceleration = v.findViewById(R.id.AccelerationTempsReel);
        final EditText vitesse = v.findViewById(R.id.VitesseTempsReel);
        final Switch direction = v.findViewById(R.id.DirectionTempsReel);
        final EditText steps = v.findViewById(R.id.StepsTempsReel);
        final Switch choix_rotation = v.findViewById(R.id.choix_rotation_TempsReel);
        final EditText rotation_number = v.findViewById(R.id.Rotation_number_TempsReel);
        final EditText rotation_time = v.findViewById(R.id.Rotation_time_TempsReel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sauvegarder dans la BDD
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                TempsReelOrder tempsReelOrder=new TempsReelOrder(Integer.parseInt(acceleration.getText().toString()),Integer.parseInt(vitesse.getText().toString()),
                        direction.isChecked(),Integer.parseInt(steps.getText().toString()),choix_rotation.isChecked(),
                        Integer.parseInt(rotation_number.getText().toString()),Integer.parseInt(rotation_time.getText().toString()));
                ListOrder.list.add(tempsReelOrder);
                getFragmentManager().beginTransaction().remove(TempsReel.temps_reel).addToBackStack(null).commit();
                Menu.orderAdapter.notifyDataSetChanged();
            }
        });

        return v;

    }

}
