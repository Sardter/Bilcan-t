package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.IdCard;
import com.badlogic.mygame.models.player.Player;

/* This class contains the questions, true answers and needs a UI to be displayed properly
 * */
public class Quiz {
    private String Quizexplanation;
    private Question[] questions;
    private final Player player;
    private IdCard id;


    private String[] choices1 = {"Bees","Lunch queue","FZ","all of above"};
    private String[] choices2 = {"B building","G building","EE building's toilet","SA Building"};
    private String[] choices3 = {"I have masochistic tendencies","perfect education","It is reputable","to have fun!"};

    private Question question1 = new Question("What is the worst nightmare of an average Bilcan-t student",
            choices1, 4);
    private Question question2 = new Question("Which building is the most popular among engineering students", choices2,
            3);
    private Question question3 = new Question("Why did you select Bilcan-t", choices3,
            1);


    public Quiz(String aQuizExplanation){
        this.Quizexplanation = aQuizExplanation;
        this.player = null;
        this.id = new IdCard("Alara", "1");
        questions = new Question[3];
        questions[0] = question1;
        questions[1] = question2;
        questions[2] = question3;
    }
    public Quiz(String aQuizExplanation, Player player){
        this.Quizexplanation = aQuizExplanation;
        this.player = player;
        this.id = new IdCard("Alara", "1");
        questions = new Question[3];
        questions[0] = question1;
        questions[1] = question2;
        questions[2] = question3;
    }

    //inner class for the question and its properties
    public class Question{
        private String QuestionExplanation;
        private String[] choices;
        private final int trueChoice;

        public Question(String aQuestionExplanation, String[] theChoices, int theTruechoice){
            this.QuestionExplanation = aQuestionExplanation;
            this.choices = theChoices;
            this.trueChoice = theTruechoice;
        }

        private String getTheAnswer(){
            return choices[trueChoice - 1];
        }
    }


    //all the indexes are used to select the necessary Question from the questions array
    public String getQuestionExplanation(int index){
        return questions[index].QuestionExplanation;
    }

    public String[] getQuestionChoices(int index){
        return questions[index].choices;
    }

    public String getQuizExplanation(){
        return Quizexplanation;
    }
    public String getTheTrueChoice(int index){
        return questions[index].getTheAnswer();
    }

    public Question constructQuestion(String aQuestionExplanation, String[] theChoices, int theTruechoice){
        return new Question(aQuestionExplanation, theChoices, theTruechoice);
    }
    public int getQuestionsLenght(){
        return questions.length;
    }
    public void onWin(BilcantGame game){
        if(game.getMissionRouter().getCurrentMission().getName().equals("First Mission")
                && game.getMissionRouter().getCurrentMission().getTaskIndex() == 0
                && !game.getMissionRouter().getCurrentMission().getCurrentTask().getBoolean()){

            game.getMainScreen().saveGame();
            id.use(player);
            FirstMission currentMission = (FirstMission) game.getMissionRouter().getCurrentMission();
            currentMission.getCurrentTask().isCompleted();
            game.getMainScreen().saveGame();
            //game.getMainScreen().loadGame();
        }
        player.addXP(100);
    }
    public void onLose(){
        player.addXP(-5);
    }
}

