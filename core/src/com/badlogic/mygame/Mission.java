package com.badlogic.mygame;

public abstract class Mission {
    //variables
    protected Player player;

    public Mission(Player aplayer){
        player = aplayer;
    }
    //public abstract void display();
    public abstract void Ifcompleted();
    public abstract void GetExperience();
    public abstract String getQuestExplanation();
}
