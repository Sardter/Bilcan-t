package com.badlogic.mygame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.mygame.windows.InteractiveWindow;
import com.badlogic.mygame.windows.NPCInteractWindow;


public class NonPlayerCharacter extends GameObject{
    private boolean isImportant;
    private boolean first = true;
    private boolean isInCollision;
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
    private ShapeRenderer shapeRenderer;
    private NPCDialog dialog;

    private NPCRouter router;

    public NPCDialog getDialog() {
        return dialog;
    }

    public void setDialog(NPCDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * base constructer
     * @param isImportant
     */
    public NonPlayerCharacter(boolean isImportant){
        super(-1,"bucket.png", 64, 64);
        shapeRenderer = new ShapeRenderer();
        this.isImportant = isImportant;
        //this.doesFlee = flee;
        this.isInCollision = false;
    }

    public void setIsInCollision(boolean condition) {this.isInCollision = condition;}

    /**
     * enter first x and then y and so on
     * @param isImportant
     * @param a
     * @param b
     * @param c
     * @param d
     * @param speed
     * @param x
     * @param y
     */
    public NonPlayerCharacter(boolean isImportant, int a, int b, int c, int d, int speed,
                              int x, int y){
        super("bucket.png", "npc","npc desc", 64, 64, x, y);
        shapeRenderer = new ShapeRenderer();
        this.isImportant = isImportant;
        this.initialX = x;
        this.initialY = y;
        route = new int[4];
        route[0] = a; route[1] = b; route[2] = c; route[3] = d;
        this.speed = speed;
        this.dialog = new NPCDialog(null);
    }

    public NonPlayerCharacter(String textureUrl, String name, String description,
                              boolean isImportant, int posX, int posY, NPCDialog dialog) {
            super(textureUrl, name, description, 64, 64, posX, posY);
            this.isImportant = isImportant;
            this.speed = 2;
            this.dialog = dialog;
    }

    public void setRouter(NPCRouter router) {
        this.router = router;
    }

    public boolean isInCollision() {
        return isInCollision;
    }

    public NonPlayerCharacter(){
        super("bucket.png", "npc", "npc desc",64, 64, 200, 200);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void interact(InteractiveWindow interactWindow) {
        if (!(interactWindow instanceof NPCInteractWindow)) return;
        System.out.println(this);
        interactWindow.setObject(this);
        interactWindow.setVisible(true);
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
        if(!this.isImportant && !isInCollision){
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

    public void traverse() {
        router.traverse(this.speed);
    }

    /**
     * to make an NPC move in a specific loop
     * there is a speed variable to make them look more random and realistic
     */
    public void moveBySpecificIndexLoop(){
        /**
         * make a better out of bounds exception
         */

        if(!isInCollision && !this.isImportant) {
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

    /**
     * use only to a important NPC and enter a string to be displayed
     * @param n
     * @param s
     * @param waitTime
     * @param stage
     */
    public void interact(NonPlayerCharacter n, String s, int waitTime, Stage stage){
        try{
            Thread.sleep(waitTime);
        }
        catch(InterruptedException ex){
            java.lang.Thread.currentThread().interrupt();
        }
        String message = s;
        if(n.isImportant){
            this.y -= ((this.y > 20) ? 20 : -10);
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(this.x + 10, this.y - 5, 10, 10);
            Label.LabelStyle style = new Label.LabelStyle();
            BitmapFont font = new BitmapFont();
            style.font = font;
            Label label = new Label(s, style);
            label.setBounds(this.x, this.y + 1, 10, 10);
            label.setFontScale(1f,1f);
            stage.addActor(label);
        }
        shapeRenderer.end();
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
}
