package com.example.user.projetz.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.user.projetz.R;
import com.example.user.projetz.model.Historique;
import com.example.user.projetz.model.Souscription;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity context= this;
        String[] list = new String[]{};
        SimpleDateFormat formatDate= new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String[] dateHist = new String[]{};
        Historique hist = new Historique(this);
        ArrayList<Souscription> b= hist.getHistory();

        if(b != null){
            int n = b.size() ;
            list = new String[n];
            dateHist = new String[n];

            for(int i = 0 ; i < n ; i++){
                list[i] = b.get(i).getForfait().getNom();
                dateHist[i] = formatDate.format(b.get(i).getDateDebut());
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView r= (ListView) findViewById(R.id.histlist);
        r.setAdapter(new CustomListNotAdapter(this,list,dateHist));
    }

}
