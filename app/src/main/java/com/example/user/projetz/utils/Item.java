package com.example.user.projetz.utils;

import com.example.user.projetz.model.Forfait;

/**
 * Created by Admin on 21/02/2018.
 */

public class Item {
    Forfait item;
    int img;

    public Item(Forfait item, int img){
        this.item=item;
        this.img=img;
    }

    public Item(Forfait item){
            this.item=item;
        }

    public Forfait getForfait(){
    return this.item;
    }

    public void setForfait(Forfait f) {
        this.item=f;
    }

    public int getImg() {
        return this.img;
    }
}
