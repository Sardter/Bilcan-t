package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;

import jdk.nashorn.internal.parser.JSONParser;

public class Inventory{
    //constants

    //variables
    private ArrayList<Items> items;
    private Json json;

    //constructor
    public Inventory(){
        json = new Json();
        FileHandle file = Gdx.files.local("items");


    }

    //methods
    public void addTo(Items item){

    }


}