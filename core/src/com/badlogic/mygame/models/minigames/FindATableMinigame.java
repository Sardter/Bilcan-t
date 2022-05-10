package com.badlogic.mygame.models.minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.FindTheTableScreen;

import java.awt.geom.Point2D;
import java.util.Random;

public class FindATableMinigame extends Minigame {
    private final DinningTable[][] dinningTables;
    private Character character;
    private final int maxTries;
    private int turnCount;

    public class DinningTable {
        public final Texture texture;
        public final int x, y;
        public boolean isFull = false;

        public DinningTable(String textureUrl, int x, int y) {
            this.x = x;
            this.texture = new Texture(textureUrl);
            this.y = y;
        }
    }

    public class Character {
        public final Texture texture;
        public int x, y;
        public Distance[][] distances;

        public Character(String textureUrl, int x, int y) {
            this.texture = new Texture(textureUrl);
            this.x = x;
            this.y = y;
            distances = new Distance[dinningTables.length][dinningTables.length];
        }
    }

    public class Distance {
        public final double distance;
        public final DinningTable dinningTable;
        public double successPercentage;

        public Distance(double distance, DinningTable dinningTable) {
            this.distance = distance;
            this.dinningTable = dinningTable;
        }
    }

    public Character getCharacter() {
        return character;
    }

    public DinningTable[][] getTables() {
        return dinningTables;
    }

    public FindATableMinigame(Player player, FindTheTableScreen screen, int tableSize) {
        super(player, screen);
        dinningTables = new DinningTable[tableSize][];
        for (int i = 0; i < dinningTables.length; i++) {
            dinningTables[i] = new DinningTable[tableSize];
        }
        maxTries = tableSize;
        turnCount = 0;
        initializeGame();
    }

    public void initializeGame() {
        character = new Character(((FileTextureData)player.getTexture()
                .getTextureData()).getFileHandle().path(),-1,-1);
        for (int i = 0; i < dinningTables.length; i++) {
            DinningTable[] column = dinningTables[i];
            for (int j = 0; j < column.length; j++) {
                DinningTable dinningTable = new DinningTable("rectext.png", i, j);
                dinningTables[i][j] = dinningTable;
            }
        }
        calculateDistances(character);

    }

    private void calcSuccessPercentage() {
        for (int i = 0; i < dinningTables.length; i++) {
            for (int j = 0; j < dinningTables[i].length; j++) {
                setSuccessPercentage(character, character.distances[i][j]);
            }
        }
    }

    public void calculateDistances(Character character) {
        Distance[][] distances = new Distance[dinningTables.length][dinningTables[0].length];
        for (int i = 0; i < dinningTables.length; i++) {
            for (int j = 0; j < dinningTables[i].length; j++) {
                double distance = distanceBetweenTwoPoints(character.x, dinningTables[i][j].x,
                        character.y, dinningTables[i][j].y);
                distances[i][j] = new Distance(distance, dinningTables[i][j]);
            }
        }
        character.distances = distances;
        calcSuccessPercentage();
    }

    private double distanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        return Point2D.distance(x1,y1,x2,y2);
    }

    public double getMax(Character character) {
        double max = Double.MIN_VALUE;
        for (Distance[] column: character.distances) {
            for (Distance distance : column) {
                if (distance.distance > max) max = distance.distance;
            }
        }
        return max;
    }

    public void setSuccessPercentage(Character character, Distance distanceObject) {
        Random random = new Random();
        double distanceFactor = 100 - (distanceObject.distance / getMax(character) * 100);
        double rngFactor = random.nextInt(100);
        distanceObject.successPercentage = Math.floor((distanceFactor + rngFactor) / 5);
    }

    public double getSuccessPercentage(int x, int y) {
        return character.distances[x][y].successPercentage;
    }

    public boolean canGetTable(Distance distanceObject) {
        Random random = new Random();
        int randomDouble = random.nextInt(100);
        return distanceObject.successPercentage > randomDouble;
    }

    private boolean isInputValid(int x, int y) {
        return x >= 0 && x < dinningTables.length && y >= 0 && y < dinningTables[0].length;
    }

    private boolean isTableFull(int x, int y) {
        return dinningTables[x][y].isFull;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getMaxTries() {
        return maxTries;
    }

    @Override
    public void play(int[] input) {
        int x = input[0], y = input[1];
        if (!isInputValid(x,y) || isTableFull(x,y)) return;

        if (!isCompleted()) {
            if (canGetTable(character.distances[x][y])) {
                won = true;
            } else {
                dinningTables[x][y].isFull = true;
                character.x = x;
                character.y = y;
                calculateDistances(character);
                turnCount++;
                if (turnCount == maxTries) lost = true;
            }
        }

        if (isCompleted()) {
            if (won) onWin();
            else onLose();
        }
    }

    @Override
    public void onWin() {
        String[] windowItems = {"Won!", "Found a table!"};
        ((FindTheTableScreen) screen).getCompletionWindow().setObject(windowItems);
        ((FindTheTableScreen) screen).getCompletionWindow().setVisible(true);
    }

    @Override
    public void onLose() {
        String[] windowItems = {"Lost!", "You will starve today!"};
        ((FindTheTableScreen) screen).getCompletionWindow().setObject(windowItems);
        ((FindTheTableScreen) screen).getCompletionWindow().setVisible(true);
    }
}
