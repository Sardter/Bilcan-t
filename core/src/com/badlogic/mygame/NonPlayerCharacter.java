package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;
import java.awt.Point;


public class NonPlayerCharacter extends GameObject{
    private boolean isImportant;
    private boolean first = true;
    private int[] route;
    private int a = 0;
    private int b = 0;
    private int initialX;
    private int initialY;
    private int speed;
    //protected int x;
    int nextX = MathUtils.random(0,1);
    int nextY = MathUtils.random(0,1);
    private static long moveTimeX;
    private static long moveTimeY;

    public NonPlayerCharacter(boolean isImportant){
        super(-1,"bucket.png", 64, 64);
        this.isImportant = isImportant;
        //this.doesFlee = flee;

    }
    /**
    enter fist x and then y and so on
     */
    public NonPlayerCharacter(boolean isImportant, int a, int b, int c, int d, int speed, int x, int y){
        super(-1,"bucket.png", 64, 64, x, y);
        this.isImportant = isImportant;
        this.initialX = x;
        this.initialY = y;
        route = new int[4];
        route[0] = a; route[1] = b; route[2] = c; route[3] = d;
        this.speed = speed;
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
                if(first){
                    initialize();
                    first = false;
                }
                if((TimeUtils.nanoTime() - moveTimeX) > 5000000){
                    nextX = MathUtils.random(0,1);
                    initializeX();
                }
                if((TimeUtils.nanoTime() - moveTimeY) > 5000000){
                    nextY = MathUtils.random(0,1);
                    initializeY();
                }

                if(nextX == 0){
                    this.x -= (100 * Gdx.graphics.getDeltaTime());
                }
                else if(nextX == 1){
                    this.x += (100 * Gdx.graphics.getDeltaTime());
                }
                if(nextY == 0){
                    this.y -= (100 * Gdx.graphics.getDeltaTime());
                }
                else if(nextY == 1){
                    this.y += (100 * Gdx.graphics.getDeltaTime());
                }

             if(!(this.x < Gdx.graphics.getWidth()) || !(this.x > 0) || !(this.y > 0) || !(this.y < 200)){
                 int outOfBounds = MathUtils.random(0, (int) (Gdx.graphics.getWidth() - 1));
                 this.x = outOfBounds;
                 this.y = 100;
             }
        }
    }

    /**
     * this method is to make an NPC move to a certain place
     * for a mission for example
     */
    public void moveBySpecificIndex(){
        if (a > route[b]) {
            if (b < 3) {
                b++;
                a = 0;
            } else {
                b = 0;
                a = 0;
            }
        } else {
            if (b % 2 == 0) {
                this.x += speed;
                a += speed;
            } else {
                this.y += speed;
                a += speed;
            }
        }
    }

    /**
     * to make an NPC move in a specific loop
     * there is a speed variable to make them look more random and realistic
     */
    public void moveBySpecificIndexLoop(){
        /**
         * make a better out of bounds exception
         */

        if(!this.isImportant) {
            if (a > route[b]) {
                if (b < 3) {
                    b++;
                    a = 0;
                } else {
                    this.x = initialX;
                    this.y = initialY;
                    b = 0;
                    a = 0;
                }
            } else {
                if (b % 2 == 0) {
                    this.x += speed;
                    a += speed;
                } else {
                    this.y += speed;
                    a += speed;
                }
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

    /**
     * initialize final moves that are basically left right and up down
     * the odd numbered variables do x
     * th even numbered ones do y
     */

    public void interact(NonPlayerCharacter n){
        if(n.isImportant){
            
        }
    }
}
