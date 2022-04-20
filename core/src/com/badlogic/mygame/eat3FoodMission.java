package com.badlogic.mygame;

public class eat3FoodMission extends Mission{
    private String name;
    private String explanation;
    public eat3FoodMission(Player aplayer){
        super(aplayer);
        name = "eating food";
        explanation = "eat 3 foods to complete the mission";
    }
    public void Ifcompleted(){
        player.restoreEnergy();
        GetExperience();
    }
    public boolean CheckIfCompleted(){

    }
    public void GetExperience(){
        player.setExperience(1);
    }
    public String getQuestExplanation(){

    }
    public String getName(){
        return name;
    }
}
