package com.badlogic.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.mygame.models.missions.FirstMission;
import com.badlogic.mygame.models.missions.MainStoryMissionLesson;
import com.badlogic.mygame.models.missions.MinigameMissions;
import com.badlogic.mygame.models.missions.Mission;
import com.badlogic.mygame.models.missions.MissionRouter;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.AvatarSelectionScreen;
import com.badlogic.mygame.views.screens.EndScreen;
import com.badlogic.mygame.views.screens.EscapeTheBeesMinigameScreen;
import com.badlogic.mygame.views.screens.FindTheTableScreen;
import com.badlogic.mygame.views.screens.InfoScreen;
import com.badlogic.mygame.views.screens.InventoryScreen;
import com.badlogic.mygame.views.screens.LoadingScreen;
import com.badlogic.mygame.views.screens.MainScreen;
import com.badlogic.mygame.views.screens.MenuScreen;
import com.badlogic.mygame.views.screens.MissionScreen;
import com.badlogic.mygame.views.screens.PlayerDetailScreen;
import com.badlogic.mygame.views.screens.PreferencesScreen;
import com.badlogic.mygame.views.screens.QuizScreen;
import com.badlogic.mygame.views.screens.WinScreen;

/**
        The main class where ability to switch between screens takes place with changeScreen(int screen) method.
        Also, we have getters for; Preferences, Player, selectedMission, MissionRouter
        Method for initializing the missions, that is, initializeMissions() .
*/




public class BilcantGame extends Game {
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private AppPreferences preferences;
    private MissionScreen missionScreen;
    private PlayerDetailScreen playerDetailScreen;
    private InventoryScreen inventoryScreen;
    private InfoScreen infoScreen;
    private EscapeTheBeesMinigameScreen escapeTheBeesMinigameScreen;
    private FindTheTableScreen findTheTableScreen;
    private QuizScreen quizScreen;
    private AvatarSelectionScreen avatarSelectionScreen;
    private WinScreen winScreen;
    private Player player;
    private MissionRouter missionRouter;

    public BilcantGame() {
        preferences = new AppPreferences();
        //this.player = new Player("character.png", 32,32, 400, 240);
    }

    public AppPreferences getPreferences() {return this.preferences;}
    public Player getPlayer() {return  this.player;}
    public void setPlayer(Player player) {this.player = player;}
    public int getSelectedMission() {return missionRouter.getIndex();}
    public void setSelectedMission(int mission) {missionRouter.setIndex(mission);}

    public MissionRouter getMissionRouter() {
        return missionRouter;
    }

    public void initializeMissions() {
        missionRouter = new MissionRouter(new Mission[]{
                new MainStoryMissionLesson(player),
                new FirstMission(player),
                new MinigameMissions(player)
        });
        System.out.println(missionRouter);
    }

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int LOADGAME = 4;
    public final static int MISSIONS = 6;
    public final static int DETAIL = 5;
    public final static int INVENTORY = 7;
    public final static int INFO = 8;
    public final static int ESCAPE_THE_BEES = 9;
    public final static int FIND_THE_TABLE = 10;
    public final static int QUIZ = 11;
    public final static int AVATAR_SELECTION = 12;
    public final static int WIN_SCREEN = 13;

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
            case LOADGAME:
                mainScreen = new MainScreen(this, true);
                this.setScreen(mainScreen);
                break;
            case MISSIONS:
                missionScreen = new MissionScreen(this);
                this.setScreen(missionScreen);
                break;
            case DETAIL:
                playerDetailScreen = new PlayerDetailScreen(this);
                this.setScreen(playerDetailScreen);
                break;
            case INVENTORY:
                inventoryScreen = new InventoryScreen(this);
                this.setScreen(inventoryScreen);
                break;
            case INFO:
                infoScreen = new InfoScreen(this);
                this.setScreen(infoScreen);
                break;
            case ESCAPE_THE_BEES:
                escapeTheBeesMinigameScreen = new EscapeTheBeesMinigameScreen(this);
                this.setScreen(escapeTheBeesMinigameScreen);
                break;
            case FIND_THE_TABLE:
                findTheTableScreen = new FindTheTableScreen(this);
                this.setScreen(findTheTableScreen);
                break;
            case QUIZ:
                quizScreen = new QuizScreen(this) {

                };
                this.setScreen(quizScreen);
                break;
            case AVATAR_SELECTION:
                avatarSelectionScreen = new AvatarSelectionScreen(this);
                this.setScreen(avatarSelectionScreen);
                break;
            case WIN_SCREEN:
                winScreen = new WinScreen(this);
                this.setScreen(winScreen);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + screen);
        }
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }
}
