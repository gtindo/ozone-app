package com.example.user.projetz.view;

import android.app.Activity;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.user.projetz.MainService;
import com.example.user.projetz.R;
import com.example.user.projetz.model.ListeForfaits;
import com.example.user.projetz.utils.Param;

public class MainActivity extends Activity {


    private ProgressBar progressAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressAccueil = (ProgressBar) findViewById(R.id.progressBar);
        Progression calcul = new Progression();

        calcul.execute();
    }

private class Progression extends AsyncTask<Void, Integer, Void>
{

    @Override
    protected Void doInBackground(Void... voids) {
        int progress;
        for (progress=0;progress<=6000;progress++)
        {
            for (int i=0; i<6000; i++){}
            publishProgress(progress);
            progress++;
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        progressAccueil.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void result) {
        Param param = new Param(MainActivity.this);

        if(param.getFirstOpening() == 0){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }else{
            ListeForfaits.initForfaits(MainActivity.this);
            param.setFirstOpening(0);
            param.saveParam(MainActivity.this);

            Intent activity =new Intent(MainActivity.this,ChooseOperatorActivity.class);
            startActivity(activity);
        }
        finish();
    }
}}