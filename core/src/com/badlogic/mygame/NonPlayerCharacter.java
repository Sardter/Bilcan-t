package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;
import java.awt.Point;

/*

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

 */



public class NonPlayerCharacter extends GameObject{
    private boolean isImportant;
    private boolean first = true;
    private byte route;
    //protected int x;
    private static final ArrayList<float[]> moves = new ArrayList<>();
    int nextX = MathUtils.random(0,1);
    int nextY = MathUtils.random(0,1);
    private static long moveTimeX;
    private static long moveTimeY;

    public NonPlayerCharacter(boolean isImportant){
        super(-1,"bucket.png", 64, 64);
        this.isImportant = isImportant;
        initializeMoves();
        //this.doesFlee = flee;

    }
    public NonPlayerCharacter(){
        super(-1,"bucket.png", 64, 64, 200, 200);
    }
    public NonPlayerCharacter(byte a){
        super(-1,"bucket.png", 64, 64, 200, 200);
        route = a;
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
    public void moveFromArrayList(){
        int a = 0;
        //while(!(moves.get(route)[a] == null)){

        //}
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
    private static void initializeMoves(){
        final float[] FIRST = new float[3]; FIRST[0] = 100 * Gdx.graphics.getDeltaTime(); FIRST[1] = 50 * Gdx.graphics.getDeltaTime(); FIRST[2] = 400 * Gdx.graphics.getDeltaTime();
        final float[] SECOND = new float[3]; FIRST[0] = 200* Gdx.graphics.getDeltaTime(); FIRST[1] = 100* Gdx.graphics.getDeltaTime(); FIRST[2] = 300;
        final float[] THIRD = new float[3]; FIRST[0] = 50* Gdx.graphics.getDeltaTime(); FIRST[1] = 50* Gdx.graphics.getDeltaTime(); FIRST[2] = 450* Gdx.graphics.getDeltaTime();
        final float[] FOURTH = new float[3]; FIRST[0] = 450* Gdx.graphics.getDeltaTime(); FIRST[1] = 150* Gdx.graphics.getDeltaTime(); FIRST[2] = 50* Gdx.graphics.getDeltaTime();

        moves.add(FIRST);
        moves.add(SECOND);
        moves.add(THIRD);
        moves.add(FOURTH);

    }
}
