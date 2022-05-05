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


public class QuizScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;
    private Quiz quiz;
    private MinigameCompletionWindow commpletionWindow;
    private int indexGen;
    private int score;
    private String selectedChoice;

    public QuizScreen(BilcantGame game){
        indexGen = 0;
        score = 0;
        selectedChoice = "";
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
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
        /*
        if(getSelected().equals(quiz.getTheTrueChoice(indexGen))){
            indexGen++;
            this.score++;
            if(indexGen > quiz.getQuestionsLenght()){
                Table table2 = new Table();
                table2.setFillParent(true);
                Label end = new Label("" + score, skin1);
                final TextButton exit2 = new TextButton("Cancel", skin2);
                exit2.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.changeScreen(BilcantGame.LOADGAME);
                    }
                });
                table2.addActor(end);
                table2.addActor(exit2);
                table1.add(table2);
            }
            else{

            }
        }
        else{
            this.score = this.score + ((this.score > 0) ? -1 : 0);
            indexGen++;
        }

         */

        }
    private String getSelected(){
        return selectedChoice;
    }
    private void setSelected(String str){
        selectedChoice = str;
    }
    private void showChoices(byte index, final Table table, Stage stage){
        final Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        final Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        final Label question = new Label(quiz.getQuestionExplanation(index), skin1);
        final TextButton first = new TextButton(quiz.getQuestionChoices(index)[0], skin1);
        first.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) first.getText());
                if(getSelected().equals(quiz.getTheTrueChoice(indexGen))){
                    indexGen++;
                    score++;
                    if(indexGen > quiz.getQuestionsLenght()){
                        Table table2 = new Table();
                        table2.setFillParent(true);
                        Label end = new Label("" + score, skin1);
                        final TextButton exit = new TextButton("Cancel", skin2);
                        exit.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                game.changeScreen(BilcantGame.LOADGAME);
                            }
                        });
                        table2.addActor(end);
                        table2.addActor(exit);
                        table.add(table2);
                    }
                    else{

                    }
                }
                else{
                    score = score + ((score > 0) ? -1 : 0);
                    indexGen++;
                }
            }
        });

        final TextButton second = new TextButton(quiz.getQuestionChoices(index)[1], skin1);
        second.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) second.getText());
                if(getSelected().equals(quiz.getTheTrueChoice(indexGen))){
                    indexGen++;
                    score++;
                    if(indexGen > quiz.getQuestionsLenght()){
                        Table table2 = new Table();
                        table2.setFillParent(true);
                        Label end = new Label("" + score, skin1);
                        final TextButton exit = new TextButton("Cancel", skin2);
                        exit.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                game.changeScreen(BilcantGame.LOADGAME);
                            }
                        });
                        table2.addActor(end);
                        table2.addActor(exit);
                        table.add(table2);
                    }
                }
                else{
                    score = score + ((score > 0) ? -1 : 0);
                    indexGen++;
                }
            }
        });

        final TextButton third = new TextButton(quiz.getQuestionChoices(index)[2], skin1);
        third.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) third.getText());
                if(getSelected().equals(quiz.getTheTrueChoice(indexGen))){
                    indexGen++;
                    score++;
                    if(indexGen > quiz.getQuestionsLenght()){
                        Table table2 = new Table();
                        table2.setFillParent(true);
                        Label end = new Label("" + score, skin1);
                        final TextButton exit = new TextButton("Cancel", skin2);
                        exit.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                game.changeScreen(BilcantGame.LOADGAME);
                            }
                        });
                        table2.addActor(end);
                        table2.addActor(exit);
                        table.add(table2);
                    }
                }
                else{
                    score = score + ((score > 0) ? -1 : 0);
                    indexGen++;
                }
            }
        });

        final TextButton fourth = new TextButton(quiz.getQuestionChoices(index)[3], skin1);
        fourth.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSelected((String) fourth.getText());
                if(getSelected().equals(quiz.getTheTrueChoice(indexGen))){
                    indexGen++;
                    score++;
                    if(indexGen > quiz.getQuestionsLenght()){
                        Table table2 = new Table();
                        table2.setFillParent(true);
                        Label end = new Label("" + score, skin1);
                        final TextButton exit = new TextButton("Cancel", skin2);
                        exit.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                game.changeScreen(BilcantGame.LOADGAME);
                            }
                        });
                        table2.addActor(end);
                        table2.addActor(exit);
                        table.add(table2);
                    }
                }
                else{
                    score = score + ((score > 0) ? -1 : 0);
                    indexGen++;
                }
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
