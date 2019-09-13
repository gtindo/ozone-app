package com.example.user.projetz.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.projetz.R;
import com.example.user.projetz.utils.Notificateur;
import com.example.user.projetz.utils.Param;

import java.util.ArrayList;
import java.util.List;

public class SimConfigure extends AppCompatActivity {
Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_configure);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        List operateur = new ArrayList();
        operateur.add("ORANGE");
        operateur.add("MTN");
        operateur.add("NEXTTEL");
        operateur.add("CAMTEL");
        operateur.add("YOOMEE");

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operateur);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);



    }
    public void ConfirmChanges(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertDialogBuilder.setMessage(R.string.messageBox);
        alertDialogBuilder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i =spinner1.getSelectedItemPosition();
                setOperatorActiv(i);
                deleteForfait();
            }
        });

        alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();


    }
    public  void setOperatorActiv(int i){
        Param p = new Param(this);
        if(i==0) p.setOperatorName("orange");
        if(i==1) p.setOperatorName("mtn");
        if(i==2) p.setOperatorName("nexttel");
        if(i==3) p.setOperatorName("camtel");
        if(i==4) p.setOperatorName("yoomee");
        p.saveParam(this);
    }

    public void deleteForfait(){
        Param p = new Param(this);
        try{
            Notificateur.annulerForfait(this, p.getActiveSubscription().getForfait());
        }catch (Exception e){
            e.printStackTrace();
        }
        p.setActiveSubscription(null);
        p.setNotification50Pushed(0);
        p.setNotification80Pushed(0);
        p.setNotificatitonFinPushed(0);
        p.saveParam(this);
    }

}
