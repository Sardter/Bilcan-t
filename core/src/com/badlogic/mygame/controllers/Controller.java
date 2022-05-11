package com.badlogic.mygame.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.models.npc.NonPlayerCharacter;
import com.badlogic.mygame.views.screens.MainScreen;
import com.badlogic.mygame.views.windows.InteractWindow;

public class Controller {
    final static float SPEED = 4;

    static  float newX = 0, newY = 0;

    public static  void move(MainScreen mainScreen) {
        final Input input = Gdx.input;

        float prevX = mainScreen.getCharacter().x, prevY = mainScreen.getCharacter().y;

        if (mainScreen.getTouchpad().isTouched()) {
            float newX = mainScreen.getTouchpad().getKnobPercentX();
            float newY = mainScreen.getTouchpad().getKnobPercentY();
            if (newX < 0) {
                mainScreen.getCharacter().x -= SPEED;
                mainScreen.getCamera().position.x -= SPEED;
            } else if (newX > 0) {
                mainScreen.getCharacter().x += SPEED;
                mainScreen.getCamera().position.x += SPEED;
            }

            if (newY < 0) {
                mainScreen.getCharacter().y -= SPEED;
                mainScreen.getCamera().position.y -= SPEED;
            } else if (newY > 0) {
                mainScreen.getCharacter().y += SPEED;
                mainScreen.getCamera().position.y += SPEED;
            }
        }

        if (input.isKeyPressed(Input.Keys.ANY_KEY)) {
            mainScreen.setMoveOnMouse(false);
        }
        if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.A)) {
            mainScreen.getCharacter().x -= SPEED;
            mainScreen.getCamera().position.x -= SPEED;
        }
        if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.D)) {
            mainScreen.getCharacter().x += SPEED;
            mainScreen.getCamera().position.x += SPEED;
        }
        if (input.isKeyPressed(Input.Keys.UP) || input.isKeyPressed(Input.Keys.W)) {
            mainScreen.getCharacter().y += SPEED;
            mainScreen.getCamera().position.y += SPEED;
        }
        if (input.isKeyPressed(Input.Keys.DOWN) || input.isKeyPressed(Input.Keys.S)) {
            mainScreen.getCharacter().y -= SPEED;
            mainScreen.getCamera().position.y -= SPEED;
        }


        /* if(mainScreen.getCharacter().x < 0) {
            mainScreen.getCharacter().x = 0;
            mainScreen.getCamera().position.x = 0;
            mainScreen.setMoveOnMouse(false);

        }
        if(mainScreen.getCharacter().x > 800 - 64) {
            mainScreen.getCharacter().x = 800 - 64;
            mainScreen.getCamera().position.x = 800 -64;
            mainScreen.setMoveOnMouse(false);
        }
        if(mainScreen.getCharacter().y < 0) {
            mainScreen.getCharacter().y = 0;
            mainScreen.getCamera().position.y = 0;
            mainScreen.setMoveOnMouse(false);
        }
        if(mainScreen.getCharacter().y > 400 + 16) {
            mainScreen.getCamera().position.y = 400 + 16;
            mainScreen.getCharacter().y = 400 + 16;
            mainScreen.setMoveOnMouse(false);
        } */

        boolean onAnyVicinity = false;
        for (GameObject object : mainScreen.objects()) {

            if (onCollision(mainScreen.getCharacter(), mainScreen.getCamera(),object, prevX, prevY)) {
                //System.out.println(object.getId());
                mainScreen.setMoveOnMouse(false);
                if (object instanceof NonPlayerCharacter) {
                    ((NonPlayerCharacter) object).setIsInCollision(true);
                }
            } else {
                if (object instanceof NonPlayerCharacter) {
                    ((NonPlayerCharacter) object).setIsInCollision(false);
                }
            }


            if (onVicinity(mainScreen.getCharacter(), mainScreen.getCamera(), object, mainScreen.getInteractWindow())) {
                mainScreen.getInteractButton().setVisible(true);
                onAnyVicinity = true;
            } else {
                if (!onAnyVicinity) {
                    mainScreen.getInteractButton().setVisible(false);
                    onAnyVicinity = true;
                }
            }
        }

    }


    private static boolean onCollision(Rectangle character, OrthographicCamera camera , GameObject object, float prevX, float prevY) {
        Vector2 characterVec = new Vector2(character.x, character.y);
        Vector2 objectVec = new Vector2(object.x, object.y);

        characterVec = character.getCenter(characterVec);
        objectVec = object.getCenter(objectVec);

        float currCharX = characterVec.x, currCharY = characterVec.y;
        float currObjX = object.x, currObjY = objectVec.y;

        float charWidth = character.width / 2,  charHeight = character.height / 2;
        float objWidth = object.width / 2,  objHeight = object.height / 2;

        float charHeightPos = currCharY + charHeight, charHeightNeg = currCharY - charHeight;
        float charWidthPos = currCharX + charWidth, charWidthNeg = currCharX - charWidth;
        float objHeightPos = currObjY + objHeight, objHeightNeg = currObjY - objHeight;
        float objWidthPos = currObjX + objWidth, objWidthNeg = currObjX - objWidth;

        boolean result = false;

        if (charHeightPos <= objHeightPos && charHeightPos >= objHeightNeg - objHeight
                || charHeightNeg <= objHeightPos && charHeightNeg >= objHeightNeg - objHeight) {
            if (charWidthPos <= objWidthPos + objWidth && charWidthPos >= objWidthNeg
                    || charWidthNeg <= objWidthPos + objWidth && charWidthPos >= objWidthNeg) {
                character.x = prevX;
                camera.position.x = prevX;
                result = true;
            }
        }

        if (charWidthPos <= objWidthPos + objWidth && charWidthPos >= objWidthNeg
                || charWidthNeg <= objWidthPos + objWidth && charWidthPos >= objWidthNeg) {
            if (charHeightPos <= objHeightPos && charHeightPos >= objHeightNeg - objHeight
                    || charHeightNeg <= objHeightPos && charHeightNeg >= objHeightNeg - objHeight) {
                character.y = prevY;
                camera.position.y = prevY;
                result = true;
            }
        }

        return  result;
    }

    private static boolean onVicinity(Rectangle character, OrthographicCamera camera ,
                                      GameObject object, InteractWindow interactWindow) {
        Vector2 characterVec = new Vector2(character.x, character.y);
        Vector2 objectVec = new Vector2(object.x, object.y);

        characterVec = character.getCenter(characterVec);
        objectVec = object.getCenter(objectVec);

        float currCharX = characterVec.x, currCharY = characterVec.y;
        float currObjX = object.x, currObjY = objectVec.y;




        if (currCharX <= currObjX + 100 && currCharX >= currObjX - 100
                && currCharY <= currObjY + 100 && currCharY >= currObjY - 100) {
            //System.out.println(currCharX);
            if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                object.interact(interactWindow);
            }
            object.setOnVicinity(true);
            return true;
        }
        object.setOnVicinity(false);
        return  false;
    }

}
