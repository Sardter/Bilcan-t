package com.badlogic.mygame.models;

import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class Inventory{
    //constants

    //variables
    private ArrayList<Item> items;
    private Json json;
    private Food food;
    private final int LIMIT = 16;
    //constructor
    public Inventory(){
        items = new ArrayList<Item>();

    }

    //methods
    public boolean addTo(Item item){
        if (items.size() <= LIMIT) {
            items.add(item);
            return  true;
        }
        return false;
    }
    public void delete(Item item){
        for (int i = 0; i < items.size(); i++) {
            if(item == items.get(i)){
                items.remove(item);
            }
        }

    }
    public Item get(String ItemName){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(ItemName)){
                return items.get(i);
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {return this.items;}

}