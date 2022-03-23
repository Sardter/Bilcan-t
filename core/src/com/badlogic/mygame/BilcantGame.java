package com.badlogic.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class BilcantGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Player character;
	private GameMap map;
	private ArrayList<GameObject> objects;
	private Stage stage;
	private Table table;

	final static int BOUNDRY_X = 800, BOUNDRY_Y = 480;
	final static int STARTING_POSITION_X = BOUNDRY_X / 2, STARTING_POSITION_Y = BOUNDRY_Y / 2;

	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.position.x = STARTING_POSITION_X;
		camera.position.y = STARTING_POSITION_Y;

		batch = new SpriteBatch();

		character = new Player("rectext.png", 32, 32, STARTING_POSITION_X, STARTING_POSITION_Y);
		map = new GameMap("map.png", 800, 480, - BOUNDRY_X,  -BOUNDRY_Y);

		GameObject object1 = new GameObject(0,"rectext.png", 64, 64, 200, 200);
		GameObject object2 = new GameObject(1,"rectext.png", 64, 64, 360, 360);
		objects = new ArrayList<>();
		objects.add(object1);
		objects.add(object2);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		table.setDebug(true);
		TextureRegion upRegion = new TextureRegion();
		upRegion.setTexture(new Texture(Gdx.files.internal("rectext.png")));
		TextureRegion downRegion = new TextureRegion();
		downRegion.setTexture(new Texture(Gdx.files.internal("rectext.png")));
		BitmapFont buttonFont = new BitmapFont();

		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.up = new TextureRegionDrawable(upRegion);
		style.down = new TextureRegionDrawable(downRegion);
		style.font = buttonFont;

		TextButton button1 = new TextButton("Action", style);
		table.add(button1);

		TextButton button2 = new TextButton("Button 2", style);
		//table.add(button2);

		button1.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				interactOnVicinity();
			}
		});
	}

	public void interactOnVicinity() {
		for ( GameObject object : objects) {
			if (object.getOnVicinity()) {
				object.interact();
				break;
			}
		}
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {


		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(map.getTexture(), map.x, map.y);

		batch.draw(character.getTexture(), character.x, character.y);
		for (GameObject object: objects) {
			batch.draw(object.getTexture(), object.x, object.y);
		}
		batch.end();



		Controller.move(character, camera ,objects, stage);



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}
}
