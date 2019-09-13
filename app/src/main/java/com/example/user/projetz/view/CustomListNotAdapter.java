package com.example.user.projetz.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.projetz.R;

/**
 * Created by Admin on 23/02/2018.
 */

class CustomListNotAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] set;

    public CustomListNotAdapter(Activity context, String[] itemname, String[] set) {
        super(context, R.layout.custom_notificatiolist, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.set =set;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_notificatiolist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text1);
        TextView txtset = (TextView) rowView.findViewById(R.id.text2);


        txtTitle.setText(itemname[position]);
        txtset.setText(set[position]);
        return rowView;

    }
}
