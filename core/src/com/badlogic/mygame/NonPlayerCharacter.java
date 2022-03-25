package com.badlogic.mygame;

import java.util.Random;

public class NonPlayerCharacter extends GameObject{
    private boolean isImportant;
    Random rand = new Random();
    //private boolean doesFlee;
    public NonPlayerCharacter(boolean isImportant){
        super(-1,"rectext.png", 64, 64, 200, 200);
        this.isImportant = isImportant;
        //this.doesFlee = flee;
    }
    public NonPlayerCharacter(){
        super(-1,"rectext.png", 64, 64, 200, 200);
    }
    /**
     * takes a boolean
     * if a event happens call this function to make the NPC s
     * flee the scene
     */
    public void getOut(boolean doesFlee){
        if(!this.isImportant && doesFlee){
            if(this.getX() < this.getWidth()/2){
                while(this.getX() > 0)
                    this.setX(this.getX()-1);
            }
            else{
                while(this.getX() < this.getWidth())
                    this.setX(this.getX()+1);
            }
        }
        else{
            System.out.println("should not flee");
        }
    }

    /**
     * Because the java version is lower than 17 I cannot set a (int,int)  bound
     * hence if the result is 0 nextX is assigned -1 and 1 if atherwise [same for y]
     */
    public void move(){
        if(!this.isImportant){
             int outOfBounds = rand.nextInt((int) (this.getWidth() + 1));
             int nextX = rand.nextInt(2);
             int nextY = rand.nextInt(2);
                 if(nextX == 0){
                     nextX = -1;
                 }
                 else{
                     nextX = 1;
                 }
                 if(nextY == 0){
                     nextY = -1;
                 }
                 else{
                     nextY = 1;
                 }
             this.setX(this.getX() + nextX);
             this.setY(this.getY() + nextY);
             if(!(this.getX() < this.getWidth()) && !(this.getX() > 0) && !(this.getY() > 0) && !(this.getY() < this.getHeight())){
                this.setX(outOfBounds);
                this.setY(0);
             }
        }
    }
    /*public NonPlayerCharacter createACharecter(boolean isImportant){
        NonPlayerCharacter temp = new NonPlayerCharacter(isImportant);
        return temp;
    }*/

}
