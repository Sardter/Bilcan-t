package com.badlogic.mygame.models.minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.Food;
import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.EscapeTheBeesMinigameScreen;

import java.util.ArrayList;
import java.util.Random;

public class EscapeTheBeesMinigame extends Minigame {
    public class MinigameObject {
        final Texture texture;

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        int y, x;

        public MinigameObject(String textureUrl, int y, int x) {
            this.texture = new Texture(textureUrl);
            this.y = y;
            this.x = x;
        }

        public Texture getTexture() {
            return texture;
        }
    }

    private class Collectable extends MinigameObject {
        final Item reward;

        public Collectable(String texture, int y, int x, Item reward) {
            super(texture, y, x);
            this.reward = reward;
        }
    }

    private class Bee extends MinigameObject {
        public Bee(String texture, int y, int x) {
            super(texture, y, x);
        }
    }

    public class MainCharacter extends MinigameObject {
        final ArrayList<Collectable> collectables;
        public MainCharacter(String texture, int y, int x) {
            super(texture, y, x);
            collectables = new ArrayList<>();
        }

        public ArrayList<Item> getItems() {
            ArrayList<Item> items = new ArrayList<>();
            for (Collectable collectable : collectables) {
                items.add(collectable.reward);
            }
            return items;
        }
    }

    private class WinGate extends MinigameObject {
        public WinGate(String texture, int y, int x) {
            super(texture, y, x);
        }
    }

    private final MinigameObject[][] positions;
    private final ArrayList<Bee> bees;
    private MainCharacter mainCharacter;

    public MinigameObject[][] getPositions() {
        return positions;
    }

    public EscapeTheBeesMinigame(Player player, EscapeTheBeesMinigameScreen screen, int size) {
        super(player, screen);

        positions = new MinigameObject[size][];
        for (int i = 0; i < size; i++)
            positions[i] = new MinigameObject[size];
        bees = new ArrayList<>();
        initializeGame();
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    private void initializeGame() {
        Random random = new Random();
        initializeWinGate(random);
        initializePlayer(random);
        initializeCollectables(random);
        initializeBees(random);
    }

    private void initializeWinGate(Random random) {
        int x, y;
        do {
            x = random.nextInt(positions.length-1);
            y = random.nextInt(positions.length-1);
        } while (positions[x][y] != null);

        WinGate winGate = new WinGate("drop.png", x, y);
        positions[x][y] = winGate;
        winGate.x = x;
        winGate.y = y;
    }

    private void initializePlayer(Random random) {
        int x, y;
        do {
            x = random.nextInt(positions.length-1);
            y = random.nextInt(positions.length-1);
        } while (positions[x][y] != null);

        MainCharacter mainCharacter = new MainCharacter("character.png", x, y);
        this.mainCharacter = mainCharacter;
        positions[x][y] = mainCharacter;
        mainCharacter.x = x;
        mainCharacter.y = y;
    }

    private void initializeCollectables(Random random) {
        final int LIMIT = positions.length / 4;
        for (int i = 0; i < LIMIT; i++) {
            int x, y;
            do {
                x = random.nextInt(positions.length-1);
                y = random.nextInt(positions.length-1);
            } while (positions[x][y] != null);

            Collectable collectable = new Collectable("bucket.png", x, y,
                    new Food("Reward food" + i, "asdsd", "drop.png"));
            positions[x][y] = collectable;
            collectable.x = x;
            collectable.y = y;
        }
    }

    private void initializeBees(Random random) {
        final int LIMIT = positions.length / 4;
        for (int i = 0; i < LIMIT; i++) {
            int x, y;
            do {
                x = random.nextInt(positions.length-1);
                y = random.nextInt(positions.length-1);
            } while (positions[x][y] != null);

            Bee bee = new Bee("rectext.png", x, y);
            bees.add(bee);
            positions[x][y] = bee;
            bee.x = x;
            bee.y = y;
        }
    }

    public void onWinGate() {
        this.player.getInventory().addAll(mainCharacter.getItems());
        won = true;
    }

    public void onCollectable(Collectable collectable) {
        mainCharacter.collectables.add(collectable);
        positions[collectable.x][collectable.y] = null;
    }

    public void onBee() {
        lost = true;
    }

    public void playerTraverse(MainCharacter object, int x, int y) {
        if (positions[x][y] == null) {
            System.out.println(object.y);
            System.out.println(y);
            positions[x][y] = object;
            positions[object.x][object.y] = null;
            object.x = x;
            object.y = y;
            return;
        }
        if (positions[x][y] instanceof Bee) {
            onBee();
            return;
        } else if (positions[x][y] instanceof WinGate) {
            onWinGate();
            return;
        } else if (positions[x][y] instanceof Collectable) {
            onCollectable((Collectable) positions[x][y]);
            positions[x][y] = object;
            positions[object.x][object.y] = null;
            object.x = x;
            object.y = y;
        }
    }

    public void beeTraverse(Bee object, int x, int y) {
        if (positions[x][y] == null) {
            positions[x][y] = object;
            positions[object.x][object.y] = null;
            object.x = x;
            object.y = y;
            return;
        }
        if (positions[x][y] instanceof MainCharacter) {
            onBee();
            return;
        } else if (positions[x][y] instanceof WinGate) {

        } else {
            positions[x][y] = object;
            positions[object.x][object.y] = null;
            object.x = x;
            object.y = y;
        }
    }

    public void beeTurn() {
        for (Bee bee : bees) {
            int x = bee.x, y = bee.y;
            if (bee.x != mainCharacter.x) {
                if (bee.x < mainCharacter.x) {
                    x++;
                } else if (bee.x > mainCharacter.x) {
                    x--;
                }
                beeTraverse(bee, x, y);
                return;
            } else if (bee.y != mainCharacter.y) {
                if (bee.y < mainCharacter.y) {
                    y++;
                } else if (bee.y > mainCharacter.y) {
                    y--;
                }
                beeTraverse(bee,x,y);
                return;
            }
        }
    }

    @Override
    public void onWin() {
        player.addXP(100);
        player.getInventory().addAll(mainCharacter.getItems());
        String title = "Won!";
        String desc = "Items: \n";
        for (Item item : player.getInventory().getItems()) {
            //System.out.println(item.getName());
            desc += " . " + item.getName() + "\n";
        }

        String[] windowItems = {title, desc};
        screen.getCompletionWindow().setObject(windowItems);
        screen.getCompletionWindow().setVisible(true);
    }

    @Override
    public void onLose() {
        player.setEnergy(player.getEnergy()-0.1f);
        player.setPopularity(player.getPopularity()-0.05f);
        String[] windowItems = {"Lost!", "Bee got you first!"};
        screen.getCompletionWindow().setObject(windowItems);
        screen.getCompletionWindow().setVisible(true);
    }

    private boolean validMove(int x, int y) {
        return 0 <= x && x < positions.length && 0 <= y && y < positions[0].length;
    }

    @Override
    public void play(int[] input) {
        if (!validMove(mainCharacter.x + input[0], mainCharacter.y + input[1])) return;
        if (!isCompleted()) {
            playerTraverse(mainCharacter, mainCharacter.x + input[0], mainCharacter.y + input[1]);

            beeTurn();
        }

        if (isCompleted()) {
            if (won) onWin();
            else onLose();
        }
    }
}
