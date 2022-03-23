package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Controller {
    final static float SPEED = 4;
    static boolean moveOnMouse = false;

    static  float newX = 0, newY = 0;

    public static  void move(Rectangle character, OrthographicCamera camera, ArrayList<GameObject> objects, Stage stage) {
        final Input input = Gdx.input;

        float prevX = character.x, prevY = character.y;

        if(input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            moveOnMouse = true;
            newX = touchPos.x - 64 / 2;
            newY = touchPos.y - 64 / 2;
        }

        if (moveOnMouse) {

            if (newX > prevX) {
                character.x += SPEED;
                camera.position.x += SPEED;
            } else if (newX < prevX) {
                character.x -= SPEED;
                camera.position.x -= SPEED;
            }

            if (newY > prevY) {
                character.y += SPEED;
                camera.position.y += SPEED;
            } else if (newY < prevY) {
                character.y -= SPEED;
                camera.position.y -= SPEED;
            }

            if (prevX < newX + 5 && prevX > newX - 5 && prevY < newY + 5 && prevY > newY - 5) {
                moveOnMouse = false;
            }
        }

        if (input.isKeyPressed(Input.Keys.ANY_KEY)) {
            moveOnMouse = false;
        }
        if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.A)) {
            character.x -= SPEED;
            camera.position.x -= SPEED;
        }
        if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.D)) {
            character.x += SPEED;
            camera.position.x += SPEED;
        }
        if (input.isKeyPressed(Input.Keys.UP) || input.isKeyPressed(Input.Keys.W)) {
            character.y += SPEED;
            camera.position.y += SPEED;
        }
        if (input.isKeyPressed(Input.Keys.DOWN) || input.isKeyPressed(Input.Keys.S)) {
            character.y -= SPEED;
            camera.position.y -= SPEED;
        }


        if(character.x < 0) {
            character.x = 0;
            camera.position.x = 0;
            moveOnMouse = false;
        }
        if(character.x > 800 - 64) {
            character.x = 800 - 64;
            camera.position.x = 800 -64;
            moveOnMouse = false;
        }
        if(character.y < 0) {
            character.y = 0;
            camera.position.y = 0;
            moveOnMouse = false;
        }
        if(character.y > 400 + 16) {
            camera.position.y = 400 + 16;
            character.y = 400 + 16;
            moveOnMouse = false;
        }

        for (GameObject object : objects) {
            if (onCollision(character, camera,object, prevX, prevY)) {
                //System.out.println(object.getId());
                moveOnMouse = false;
            }

            if (onVicinity(character, camera, object)) {
                //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //System.out.println(object.getId());

                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
            };
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

    private static boolean onVicinity(Rectangle character, OrthographicCamera camera , GameObject object) {
        Vector2 characterVec = new Vector2(character.x, character.y);
        Vector2 objectVec = new Vector2(object.x, object.y);

        characterVec = character.getCenter(characterVec);
        objectVec = object.getCenter(objectVec);

        float currCharX = characterVec.x, currCharY = characterVec.y;
        float currObjX = object.x, currObjY = objectVec.y;




        if (currCharX <= currObjX + 100 && currCharX >= currObjX - 100 && currCharY <= currObjY + 100 && currCharY >= currObjY - 100) {
            //System.out.println(currCharX);
            if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                object.interact();
            }
            object.setOnVicinity(true);
            return true;
        }
        object.setOnVicinity(false);
        return  false;
    }

}
