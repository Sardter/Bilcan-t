package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Random;



public class NonPlayerCharacter extends GameObject{
    private boolean isImportant;
    //protected int x;
    int nextX = MathUtils.random(0,1);
    int nextY = MathUtils.random(0,1);
    private static long moveTimeX;
    private static long moveTimeY;

    public NonPlayerCharacter(boolean isImportant){
        super(-1,"bucket.png", 64, 64);
        this.isImportant = isImportant;
        //this.doesFlee = flee;
        initialize();
    }
    public NonPlayerCharacter(){
        super(-1,"bucket.png", 64, 64, 200, 200);
    }
    /**
     * takes a boolean
     * if a event happens call this function to make the NPC s
     * flee the scene
     */
    /**
     * BUGGY
     * @param doesFlee
     */
    public void getOut(boolean doesFlee){
        if(!this.isImportant && doesFlee){
            if(this.x < (Gdx.graphics.getWidth() /2)){
                while(this.x > 0)
                    this.x--;
            }
            else{
                while(this.x < Gdx.graphics.getWidth())
                    this.x++;
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
    /**
     * works but because the bourdeies of the map  are not set
     * always gets in initialize again in loop
     */
    public void move(){
        if(!this.isImportant){

                if((TimeUtils.nanoTime() - moveTimeX) > 2000000000){
                    nextX = MathUtils.random(0,1);
                    initializeX();
                }
                if((TimeUtils.nanoTime() - moveTimeY) > 1000000000){
                    nextY = MathUtils.random(0,1);
                    initializeY();
                }

                if(nextX == 0){
                    nextX = -(int) (100 * Gdx.graphics.getDeltaTime());
                }
                else if(nextX == 1){
                    nextX = (int) (100 * Gdx.graphics.getDeltaTime());
                }
                if(nextY == 0){
                    nextY = -(int) (100 * Gdx.graphics.getDeltaTime());
                }
                else if(nextY == 1){
                    nextY = (int) (100 * Gdx.graphics.getDeltaTime());
                }
                this.x = this.x + nextX;
                this.y = (int) (this.y + nextY);
             if(!(this.x < Gdx.graphics.getWidth()) || !(this.x > 0) || !(this.y > 0) || !(this.y < 200)){
                 int outOfBounds = MathUtils.random(0, (int) (Gdx.graphics.getWidth() - 1));
                 this.x = 100;
                 this.y = 100;
             }
        }
    }
    public boolean getISImportant(){
        return isImportant;
    }
    private static void initialize(){
        moveTimeX = TimeUtils.nanoTime();
        moveTimeY = TimeUtils.nanoTime();
    }
    private static void initializeX(){
        moveTimeX = TimeUtils.nanoTime();
    }
    private static void initializeY(){
        moveTimeY = TimeUtils.nanoTime();
    }
}
