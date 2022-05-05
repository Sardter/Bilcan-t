package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.missions.Quiz;
import com.badlogic.mygame.views.windows.MinigameCompletionWindow;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

//TODO actionListeners update the score  and the show öethod just prints the current state. (the Quiz.java can have a update method)
public class QuizScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;
    private Quiz quiz;
    private int indexGen;
    private int score;


    public int getIndexGen() {
        return indexGen;
    }

    public void setIndexGen(int indexGen) {
        this.indexGen = indexGen;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    private String selectedChoice;

    public QuizScreen(BilcantGame game){
        indexGen = 0;
        score = 0;
        selectedChoice = "";
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    public int getCurrentQuestion(){
        return indexGen;
    }
    @Override
    public void show() {
        Table table1 = new Table();
        table1.setFillParent(true);
        stage.addActor(table1);

        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        Label score =  new Label("" + this.score, skin1);
        stage.addActor(score);

        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("back2.jpeg"))));
        table1.setBackground(textureRegionDrawableBg);

        quiz = new Quiz("Shiny shiny");

        final TextButton exit = new TextButton("Cancel", skin2);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.LOADGAME);
            }
        });
        table1.addActor(exit);

        showChoices((byte)indexGen, table1, stage);
        }

    public String getSelected(){
        return selectedChoice;
    }
    private void setSelected(String str){
        selectedChoice = str;
    }

    private void showChoices(byte index, final Table table, final Stage stage){
        final Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        final Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        final Label question = new Label(quiz.getQuestionExplanation(index), skin1);
        final TextButton first = new TextButton(quiz.getQuestionChoices(index)[0], skin1);
        first.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected(first.getText().toString());
                if(quiz.getTheTrueChoice(indexGen).equals(selectedChoice)){
                    if(indexGen >= quiz.getQuestionsLenght() - 1){
                        table.reset();
                        final Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
                        final Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
                        Table end = new Table();
                        end.setFillParent(true);

                        Label endLab = new Label("" + score, skin1);

                        final TextButton exit = new TextButton("Cancel", skin2);
                        exit.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                game.changeScreen(BilcantGame.LOADGAME);
                            }
                        });

                        end.addActor(exit);
                        end.addActor(endLab);
                        table.addActor(end);
                    }
                    else {
                        table.reset();
                        score--;
                        indexGen++;
                        show();
                    }
                }
                else{
                    if(indexGen > quiz.getQuestionsLenght()){
                        endScreen(table);
                    }
                    else {
                        score++;
                        table.reset();
                        indexGen++;
                        show();
                    }
                }
            }
        });

        final TextButton second = new TextButton(quiz.getQuestionChoices(index)[1], skin1);
        second.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) second.getText());
            }
        });

        final TextButton third = new TextButton(quiz.getQuestionChoices(index)[2], skin1);
        third.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) third.getText());

            }
        });

        final TextButton fourth = new TextButton(quiz.getQuestionChoices(index)[3], skin1);
        fourth.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) fourth.getText());
            }
        });

        table.add(question);
        table.row().pad(20, 0, 10, 0);
        table.add(first).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(second).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(third).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(fourth).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
    }
    private void endScreen(Table table){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
