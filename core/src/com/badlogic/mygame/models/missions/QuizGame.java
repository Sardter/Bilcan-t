package com.badlogic.mygame.models.missions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.maps.Minigame;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.mygame.models.player.Player;

public class QuizGame extends Minigame implements Screen {
    private static Question[] questions;
    private Question currQues;
    private Stage stage;
    private Table table1, question, answers;
    private BilcantGame game;
    private String currAns;
    private byte score;
    private byte currQuesNum;
    public QuizGame(){
        initialize();
    }
    @Override
    public void play() {
        show();
    }

    @Override
    public void loadScreen() {
        table1 = new Table();
        question = new Table();
        answers = new Table();
        table1.setFillParent(true);
        stage.addActor(table1);
        stage.addActor(question);
        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        final TextButton cancel = new TextButton("Cancel", skin1);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.DETAIL);
            }
        });
        table1.add(cancel).pad(10);
        table1.bottom();

        Label questionDesc = new Label(currQues.getQuestion(), skin2);
        question.add(questionDesc).size(800, 500);

        for (byte a = 0; a < currQues.getChoices().length; a++){
            final TextButton choice = new TextButton(currQues.getChoices()[a], skin2);
            choice.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(currQues.getAnswer().equals(choice.getText())){
                        score++;
                    }
                    else{
                        score--;
                    }
                    currQuesNum++;
                }
            });
        }
    }
    private void initialize(){
        stage = new Stage(new ScreenViewport());
        questions = new Question[6];
        Question no1 = new Question(1, " What's Turkey's surface area?", "783,356km2", new String[] {"100000km2", "200000km2", "3000000km2", "783,356km2"});
        Question no2 = new Question(2, " How much is the tabldote menu in Bilcan't", "11tl", new String[] {"10tl", "9tl", "12tl", "13tl"});
        Question no3 = new Question(3, " What is avg. of 2021-22 MATH102", "30", new String[] {"30"});
        Question no4 = new Question(4, " What's Turkey's ", "783,356km2", new String[] {"1", "2"});
        Question no5 = new Question(5, " surface area?", "783,356km2", new String[] {"3"});
        Question no6 = new Question(6, " area?", "783,356km2", new String[] {"7"});

        questions[0] = no1;
        questions[1] = no2;
        questions[2] = no3;
        questions[3] = no4;
        questions[4] = no5;
        questions[5] = no6;
        score = 0;
        currQuesNum = 0;
        currQues = questions[currQuesNum];
    }

    @Override
    public void show() {
        loadScreen();
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

    }

    public class Question {
        private String answer;
        private int questionNo;
        private String question;
        private String[] choices;
        //private String question;

        public Question( int questionNo, String question,String answer, String[] choices){
            this.questionNo = questionNo;
            this.question = question;
            this.answer = answer;
            this.choices = choices;
        }
        public String getAnswer(){
            return answer;
        }
        public int getQuestionNo(){
            return questionNo;
        }
        private String getQuestion(){
            return question;
        }
        public String[] getChoices(){
            return choices;
        }
    }

}
