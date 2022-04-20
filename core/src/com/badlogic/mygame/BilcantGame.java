package com.badlogic.mygame;

import com.badlogic.gdx.Game;



public class BilcantGame extends Game {
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private AppPreferences preferences;
    private MissionScreen2 missionScreen2;
    private PlayerDetailScreen playerDetailScreen;

    public BilcantGame() {
        preferences = new AppPreferences();
    }

    public  AppPreferences getPreferences() {return this.preferences;}

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int LOADGAME = 4;
    public final static int MISSIONS = 6;
    public final static int DETAIL = 5;

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
                missionScreen2 = new MissionScreen2(this, true);
                this.setScreen(mainScreen);
                break;

            case DETAIL:
                playerDetailScreen = new PlayerDetailScreen(this);
                this.setScreen(playerDetailScreen);
                break;
        }
    }

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }
}
