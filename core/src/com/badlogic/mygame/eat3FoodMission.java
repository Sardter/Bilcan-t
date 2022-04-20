package com.badlogic.mygame;

public class eat3FoodMission extends Mission{
    private String name;
    private String explanation;
    public eat3FoodMission(Player player){
        super(player);
        name = "eating food";
        explanation = "eat 3 foods to complete the mission";
    }
    public void Ifcompleted(){
        player.restoreEnergy();
        GetExperience();
        Items newLock = new Lock();
        player.getInventory().addTo(newLock);
    }
    public boolean CheckIfCompleted(){
        if(player.getEatCount() >= 3){
            return true;
        }
        return false;
    }
    public void GetExperience(){
        player.setExperience(1);
    }
    public String getQuestExplanation(){
        return explanation;
    }
    public String getName(){
        return name;
    }
}
