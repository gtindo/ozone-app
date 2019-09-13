package com.example.user.projetz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.utils.Param;

import java.util.ArrayList;
import java.util.List;

public class ChooseOperatorActivity extends AppCompatActivity {
   public static Spinner spinner;
    public static ArrayList<Forfait> monthlist;
    public static ArrayList<Forfait> daylist ;
    public static ArrayList<Forfait> weeklist;
    private static Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_operator);
        // recupation du spinner et creation de la liste d'operateur
        spinner = (Spinner) findViewById(R.id.spinner);
        List operateur = new ArrayList();
        operateur.add("ORANGE");
        operateur.add("MTN");
        operateur.add("NEXTTEL");
        operateur.add("CAMTEL");
        operateur.add("YOOMEE");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operateur);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void LoadingFirstInterface(View view){
        setOperatorActiv();
        Intent upIntent = new Intent(this, Main2Activity.class);
        startActivity(upIntent);
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setOperatorActiv(){
        int i =spinner.getSelectedItemPosition();
        Param p = new Param(this);
        if(i==0) p.setOperatorName("orange");
        if(i==1) p.setOperatorName("mtn");
        if(i==2) p.setOperatorName("nexttel");
        if(i==3) p.setOperatorName("camtel");
        if(i==4) p.setOperatorName("yoomee");
        p.saveParam(this);


    }

}


