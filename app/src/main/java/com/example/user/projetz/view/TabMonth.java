package com.example.user.projetz.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.utils.Item;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.projetz.model.ListeForfaits.getForfaitMoisOperatorActiv;

/**
 * Created by Admin on 12/02/2018.
 */ public class TabMonth extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener{
    private SearchView searchView;
    private MyAdapter adapter;
    private RecyclerView r;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.tab4content, container, false);
        r= (RecyclerView) view.findViewById(R.id.recycler3);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity());
        r.setLayoutManager(layoutManager);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        Activity context= getActivity();
        List<Item> list = new ArrayList<>();
        ArrayList<Forfait> b= getForfaitMoisOperatorActiv(context);
        for(Forfait s :b ){
            list.add(new Item(s ,s.getImage()));}
        adapter = new MyAdapter(context,list);
        r.setAdapter(adapter);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main2, menu);
        final MenuItem search= menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(search,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        Activity context= getActivity();
                        List<Item> list = new ArrayList<>();
                        ArrayList<Forfait> a= getForfaitMoisOperatorActiv(context);


                        for(Forfait s :a ){
                            list.add(new Item(s ,s.getImage()));
                        }

                        adapter.setFilter(list);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

            return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Activity context= getActivity();
        List<Item> list = new ArrayList<>();
        ArrayList<Forfait> a= getForfaitMoisOperatorActiv(context);


        for(Forfait s :a ){
            list.add(new Item(s ,s.getImage()));
        }

        final List<Item> filteredModelList = filter(list, newText);
        r.setAdapter(new MyAdapter(context,filteredModelList));
        return true;
    }
    private List<Item> filter(List<Item> p, String query){
        query=query.toLowerCase();
        final List<Item> filterlist= new ArrayList<>();
        for (Item model:p ){
            final String text= model.getForfait().getNom().toLowerCase(); ;
            if(text.contains(query)){
                filterlist.add(model);
            }
        }
        return filterlist;
    }
}
