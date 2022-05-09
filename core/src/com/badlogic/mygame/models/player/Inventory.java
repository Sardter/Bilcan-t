package com.badlogic.mygame.models.player;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.mygame.models.items.Food;
import com.badlogic.mygame.models.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
/*
            Has a field that is an arraylist of Item objects, and essentially stores them.
            Saves the current state of the Inventory via JSON.
            Has a limit of maximum holding 16 objects.
 */
public class Inventory{
    //constants

    //variables
    private ArrayList<Item> items;
    private Json json;
    private Food food;
    private final int LIMIT = 16;
    //constructor
    public Inventory(){
        json = new Json();
        items = new ArrayList<Item>();
    }

    //methods
    public boolean addItem(Item item){
        if (items.size() <= LIMIT) {
            items.add(item);
            return  true;
        }
        return false;
    }

    public boolean addAll(ArrayList<Item> items) {
        for (Item item : items) {
            if (!addItem(item)) return false;
        }
        return true;
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

    public String toJson() {
        //items.add(new Food("asd","sad", "rectext.png"));
        ArrayList<HashMap<String,String>> maps = new ArrayList<>();
        for (Item item : items) {
            HashMap<String, String> map = new HashMap<>();
            map.put("class", item.getClass().toString());
            map.put("name", item.getName());
            map.put("description", item.getDescription());
            map.put("textureUrl", item.getTextureUrl());
            maps.add(map);
        }
        return json.toJson(maps);
    }

    public void fromJson(String jsonStr) {
        ArrayList<JsonValue> maps = json.fromJson(ArrayList.class , jsonStr);
        ArrayList<Item> newList = new ArrayList<>(); // text: 0, name: 1, desc: 2, class: 3
        System.out.println(maps);
        if (maps == null) return;
        for (JsonValue map : maps) {
            String type = map.get(3).getString(1), name = map.get(1).getString(1),
                    desc = map.get(2).getString(1), textureUrl = map.get(0).getString(1);
            switch (type) {
                case "class com.badlogic.mygame.models.items.Food":
                    newList.add(new Food(name, desc, textureUrl));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
        }

        items = newList;
    }

    public ArrayList<Item> getItems() {return this.items;}

}