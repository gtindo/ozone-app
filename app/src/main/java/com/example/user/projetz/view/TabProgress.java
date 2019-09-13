package com.example.user.projetz.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.model.ListeForfaits;
import com.example.user.projetz.model.Souscription;
import com.example.user.projetz.utils.Notificateur;
import com.example.user.projetz.utils.Param;
import com.example.user.projetz.utils.Ussd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Admin on 12/02/2018.
 */
public class TabProgress extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener {

    private Handler mHandler = new Handler();
    TextView nomForfait ;
    ProgressBar progression ;
    TextView resteForfait ;
    TextView dataRestantes ;
    TextView dataUtilise ;
    TextView dateExpiration ;
    TextView valueRestante ;
    TextView valueUtilise ;
    TextView valueExpiration ;
    TextView messageSouscription ;
    Activity main ;
    FloatingActionButton buttonAdd ;
    FloatingActionButton buttonDelete ;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1content, container, false);

        main = getActivity() ;


        //WebView publicite = (WebView)rootView.findViewById(R.id.pub1);
        //int width = publicite.getWidth();
        //int height = publicite.getHeight();

        //String image = "<html><body><img style=\"width:100%px;height:100%\" src=\"https://static.boredpanda.com/blog/wp-content/uploads/2017/01/Clear-Dirty-586ce799500df-png__880.jpg\" /> </body></html>" ;
        //publicite.loadData(image, "text/html", null);
        //publicite.loadUrl("https://static.boredpanda.com/blog/wp-content/uploads/2017/01/Clear-Dirty-586ce799500df-png__880.jpg");

        buttonAdd = (FloatingActionButton)rootView.findViewById(R.id.addButton);
        buttonDelete = (FloatingActionButton)rootView.findViewById(R.id.buttonDelete);
        nomForfait = (TextView)rootView.findViewById(R.id.nomForfait);
        progression = (ProgressBar) rootView.findViewById(R.id.progressionForfait);
        resteForfait = (TextView)rootView.findViewById(R.id.resteForfait);
        dataRestantes = (TextView)rootView.findViewById(R.id.dataRestantes);
        dataUtilise = (TextView)rootView.findViewById(R.id.dataUtilise);
        dateExpiration = (TextView)rootView.findViewById(R.id.dateExpiration);
        valueRestante = (TextView)rootView.findViewById(R.id.valueRestante);
        valueUtilise = (TextView)rootView.findViewById(R.id.valueUtilise);
        valueExpiration = (TextView)rootView.findViewById(R.id.valueExpiration);

        messageSouscription = (TextView)rootView.findViewById(R.id.messageSouscription);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(main, R.style.MyDialogTheme);
                alertDialogBuilder.setMessage(R.string.waitFeature);
                alertDialogBuilder.show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(main, R.style.MyDialogTheme);
                alertDialogBuilder.setMessage(R.string.deleteSubscription);
                alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Param param = new Param(main);
                        Notificateur.annulerForfait(main, param.getActiveSubscription().getForfait());
                        param.setActiveSubscription(null);
                        param.setNotificatitonFinPushed(0);
                        param.setNotification50Pushed(0);
                        param.setNotification80Pushed(0);
                        param.saveParam(main);
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialogBuilder.show();
            }
        });

        affichage();

        mHandler.postDelayed(m_Runnable, 5000);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);
        final MenuItem search= menu.findItem(R.id.app_bar_search);
        search.setVisible(false);
    }


    public void affichage(){
        //final Activity main = TabProgress.this.getActivity() ;
        Param param = new Param(main);
        Souscription souscription = param.getActiveSubscription();

        if(souscription == null){
            messageSouscription.setText(R.string.any_subscription);
            messageSouscription.setVisibility(View.VISIBLE);
            nomForfait.setVisibility(View.GONE);
            progression.setVisibility(View.GONE);
            resteForfait.setVisibility(View.GONE);
            dataRestantes.setVisibility(View.GONE);
            dataUtilise.setVisibility(View.GONE);
            dateExpiration.setVisibility(View.GONE);
            valueRestante.setVisibility(View.GONE);
            valueUtilise.setVisibility(View.GONE);
            valueExpiration.setVisibility(View.GONE);
            buttonAdd.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);

        }else{
            long reste = (long)param.getActiveSubscription().getDataRestante() / 1024 / 1024  ;
            long initial = (long)param.getActiveSubscription().getForfait().getNbOctets() / 1024 /1024  ;
            long utilise = initial - reste ;
            long niveau = (100 * reste) / initial ;
            progression.setProgress((int)niveau);
            String texte = reste +" / "+initial ;
            //progression.setText(texte);

            String texteReste = reste +" / "+initial+ " Mo." ;
            String textValueUtilise = utilise + " Mo." ;
            String textValueRestante = reste + " Mo." ;

            SimpleDateFormat formatDate= new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");

            String dayExpiration = formatDate.format(souscription.getDateFin());

            nomForfait.setText(souscription.getForfait().getNom());
            resteForfait.setText(texteReste);
            dataRestantes.setText(R.string.valueRestante);
            dataUtilise.setText(R.string.valueUtilise);
            dateExpiration.setText(R.string.valueExpiration);
            valueRestante.setText(textValueRestante);
            valueUtilise.setText(textValueUtilise);
            valueExpiration.setText(dayExpiration);
            //valueExpiration.setText(souscription.getDateFin().toString());

            buttonAdd.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);

            nomForfait.setVisibility(View.VISIBLE);
            progression.setVisibility(View.VISIBLE);
            resteForfait.setVisibility(View.VISIBLE);
            dataRestantes.setVisibility(View.VISIBLE);
            dataUtilise.setVisibility(View.VISIBLE);
            dateExpiration.setVisibility(View.VISIBLE);
            valueRestante.setVisibility(View.VISIBLE);
            valueUtilise.setVisibility(View.VISIBLE);
            valueExpiration.setVisibility(View.VISIBLE);
            messageSouscription.setVisibility(View.GONE);
        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            affichage();
            mHandler.postDelayed(m_Runnable, 5000);
        }

    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
