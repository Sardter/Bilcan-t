package com.badlogic.mygame.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.mygame.models.Item;
import com.badlogic.mygame.models.Player;
import com.badlogic.mygame.screens.InventoryScreen;

public class ItemWindow extends InteractiveWindow {
    private Player player;
    private Item item;
    private Image image;
    private TextButton useButton;
    private InventoryScreen inventoryScreen;

    public ItemWindow(String name, WindowStyle style, Player player, InventoryScreen inventoryScreen) {
        super(name, style);

        verticalGroup = new VerticalGroup();
        this.player = player;
        this.inventoryScreen = inventoryScreen;
    }

    public void setObject(Object object) {
        if (!(object instanceof Item)) return;
        Item item = (Item) object;
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.item = item;
        this.description = new Label(item.getDescription(), skin2);
        description.setFontScale(0.7f);
        this.name = new Label(item.getName(), skin2);
        this.image = new Image(item.getTexture());

        verticalGroup = new VerticalGroup();

        verticalGroup.addActor(image);
        //row();
        verticalGroup.addActor(name);
        verticalGroup.addActor(description);
        verticalGroup.addActor(new Label(" ", skin2));
        //row();
        setUseButton();
        setCloseButton();
        addVerticalGroup();
    }


    private void setUseButton() {
        if (item.getIsUsable()) {
            Skin skin =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
            useButton = new TextButton("Use", skin);
            useButton.pad(10f);
            verticalGroup.addActor(useButton);
            verticalGroup.addActor(new Label(" ", skin));
            useButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    item.use(player);
                    inventoryScreen.drawItems(new ItemWindow("Item", getStyle(), player, inventoryScreen));
                    verticalGroup = new VerticalGroup();
                    setVisible(false);
                }
            });
        }
    }
}
