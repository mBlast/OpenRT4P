package com.example.application.Activité_n2.AjoutBDD;

import android.os.AsyncTask;

import com.example.application.BDD.DataBaseHelperValeurReel;
import com.example.application.objets.valeurReel;


import java.util.List;

public class ajoutBDDVR extends AsyncTask<valeurReel,Void, Void> {

    @Override
    protected Void doInBackground(valeurReel... strings) {

        List<valeurReel> valeurs = DataBaseHelperValeurReel.getInstance().getValeurReelDAO().getAll();
        valeurs.add(strings[0]);
        DataBaseHelperValeurReel.getInstance().getValeurReelDAO().insertAll(valeurs);
        return null;

    }
}
