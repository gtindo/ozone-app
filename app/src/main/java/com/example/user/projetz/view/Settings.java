package com.example.user.projetz.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.projetz.R;

public class Settings extends AppCompatActivity {
    private static final String STATE_ITEMS = "items";
    private static final String STATE_IMAGES = "images";
    String a,b,c,d;
    String[] item;
    int[] img;

    @Override
    public Resources getResources() {
        return super.getResources();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        item= new String[]{getString(R.string.changeoperator), getResources().getString(R.string.notificate), getResources().getString(R.string.help), getString(R.string.aboutapp)};
        img= new int[]{
                R.drawable.sim,
                R.drawable.notification,
                R.drawable.help,
                R.drawable.about

        };
        super.onCreate(savedInstanceState);

        // If we have a saved state then we can restore it now
        if (savedInstanceState != null) {
            item = savedInstanceState.getStringArray(STATE_ITEMS);
            img = savedInstanceState.getIntArray(STATE_IMAGES);
        }
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView l= (ListView) findViewById(R.id.list);
        l.setAdapter(new CustomListAdapter(this,item,img));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), SimConfigure.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), NotificationSet.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), HelpSet.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), AboutApp.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putSerializable(STATE_ITEMS, item);
        outState.putSerializable(STATE_IMAGES, img);
    }
}
