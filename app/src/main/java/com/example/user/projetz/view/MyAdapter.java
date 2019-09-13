package com.example.user.projetz.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.utils.Item;
import com.example.user.projetz.utils.Param;
import com.example.user.projetz.utils.Ussd;

import java.util.List;

/**
 * Created by Admin on 17/02/2018.
 */

public class MyAdapter extends RecyclerView.Adapter{
    private final Activity activity;
    private final  List<Item> list;

    public MyAdapter(Activity activity, List<Item> list1) {
        this.activity = activity;
        this.list = list1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler,parent,false);
        Activity ctx= (Activity)parent.getContext();
        return new SimpleViewHolder(v, ctx);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SimpleViewHolder) holder).BindView(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        Activity activity ;
        Forfait forfait ;
        SimpleViewHolder(View itemView,Activity activity) {
            super(itemView);
            this.activity=activity;
            textView = (TextView) itemView.findViewById(R.id.forfait);
            imageView= (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        public void BindView(int position){
            textView.setText(list.get(position).getForfait().getNom());
            imageView.setImageResource(list.get(position).getImg());
            forfait = list.get(position).getForfait();
        }

        @Override
        public void onClick(View v) {
            Param p = new Param(activity) ;
            if(p.getActiveSubscription() != null){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
                alertDialogBuilder.setMessage(R.string.goToCancelBefore);

                alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.show();
            }else{
                Ussd.sendUssd(forfait, activity, p.getOperatorName());
            }
        }}

    public void setFilter(List<Item> models){
        list.addAll(models);
        notifyDataSetChanged();
    }
}


