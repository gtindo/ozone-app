package com.example.user.projetz.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.projetz.R;

public class HelpSet extends AppCompatActivity {
    String[] item;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        item=new String[]{getString(R.string.askanswer),getString(R.string.contactus),getString(R.string.confidentiality)};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_set);
        list=(ListView) findViewById(R.id.helplist);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, item);
        adapter.setDropDownViewResource(android.R.layout.activity_list_item);
        list.setAdapter(adapter);
    }
}
