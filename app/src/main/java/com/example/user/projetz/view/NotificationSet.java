package com.example.user.projetz.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.projetz.R;
import com.example.user.projetz.utils.Param;

public class NotificationSet extends AppCompatActivity {
    String[] set;
    String[] item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String notificationTones = "";
        String vibration = "" ;
        String popupNotification = "" ;
        String indicatorLight = "" ;

        final Param param = new Param(NotificationSet.this);

        if(param.getRingNotification() == 1){
            notificationTones = getString(R.string.yes);
        }else{
            notificationTones = getString(R.string.no);
        }

        if(param.getVibratingNotification() == 1){
            vibration = getString(R.string.yes);
        }else{
            vibration = getString(R.string.no);
        }

        if(param.getLightNotification() == 1){
            indicatorLight = getString(R.string.yes);
        }else{
            indicatorLight = getString(R.string.no);
        }

        if(param.getPopupNotification() == 1){
            popupNotification = getString(R.string.yes);
        }else{
            popupNotification = getString(R.string.no);
        }

        set=new String[]{notificationTones,vibration,popupNotification,indicatorLight};
        item=new String[]{getString(R.string.notificationtones),getString(R.string.vibration),getString(R.string.popupnotify),getString(R.string.indicatorlight)};

        setContentView(R.layout.activity_notification_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView r= (ListView) findViewById(R.id.notlist);
        r.setAdapter(new CustomListNotAdapter(this,item,set));

        r.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(NotificationSet.this);
                dialog.setContentView(R.layout.dialog_notification);
                dialog.setCancelable(true);

                final RadioGroup group = (RadioGroup)dialog.findViewById(R.id.groupPopupNotif);
                final TextView text = (TextView)dialog.findViewById(R.id.textPopupNotif);
                final RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.yes_notif);
                final RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.no_notif);
                final Button btn = (Button)dialog.findViewById(R.id.button_notif);
                text.setText(item[position]);

                switch(position){
                    case 0 :
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = group.getCheckedRadioButtonId() ;
                                if(choice == R.id.yes_notif){
                                    set[position] = getString(R.string.yes);
                                    param.setRingNotification(1);
                                }
                                if(choice == R.id.no_notif){
                                    set[position] = getString(R.string.no);
                                    param.setRingNotification(0);
                                }
                                param.saveParam(NotificationSet.this);
                                r.setAdapter(new CustomListNotAdapter(NotificationSet.this,item,set));
                                dialog.cancel();
                            }
                        });
                        break ;

                    case 1 :
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = group.getCheckedRadioButtonId() ;
                                if(choice == R.id.yes_notif){
                                    set[position] = getString(R.string.yes);
                                    param.setVibratingNotification(1);
                                }
                                if(choice == R.id.no_notif){
                                    set[position] = getString(R.string.no);
                                    param.setVibratingNotification(0);
                                }
                                param.saveParam(NotificationSet.this);
                                r.setAdapter(new CustomListNotAdapter(NotificationSet.this,item,set));
                                dialog.cancel();
                            }
                        });
                        break ;

                    case 2 :
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = group.getCheckedRadioButtonId() ;
                                if(choice == R.id.yes_notif){
                                    set[position] = getString(R.string.yes);
                                    param.setPopupNotification(1);
                                }
                                if(choice == R.id.no_notif){
                                    set[position] = getString(R.string.no);
                                    param.setPopupNotification(0);
                                }
                                param.saveParam(NotificationSet.this);
                                r.setAdapter(new CustomListNotAdapter(NotificationSet.this,item,set));
                                dialog.cancel();
                            }
                        });
                        break ;

                    case 3 :
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = group.getCheckedRadioButtonId() ;
                                if(choice == R.id.yes_notif){
                                    set[position] = getString(R.string.yes);
                                    param.setLightNotification(1);
                                }
                                if(choice == R.id.no_notif){
                                    set[position] = getString(R.string.no);
                                    param.setLightNotification(0);
                                }
                                param.saveParam(NotificationSet.this);
                                r.setAdapter(new CustomListNotAdapter(NotificationSet.this,item,set));
                                dialog.cancel();
                            }
                        });
                        break ;
                }

                dialog.show();
            }
        });
    }
}
