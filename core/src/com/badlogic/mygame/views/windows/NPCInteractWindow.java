package com.badlogic.mygame.views.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.mygame.models.npc.DialogOption;
import com.badlogic.mygame.models.npc.NonPlayerCharacter;
/*
            Window where the Player object is able to see the possible interactions with the NPC.
*/
public class NPCInteractWindow extends InteractiveWindow {
    private NonPlayerCharacter npc;
    private Label dialog;

    public NPCInteractWindow(String title, WindowStyle style) {
        super(title, style);
        verticalGroup = new VerticalGroup();
    }

    public void setObject(Object object) {
        if (!(object instanceof NonPlayerCharacter)) return;
        final NonPlayerCharacter npc = (NonPlayerCharacter) object;
        Skin skin1 = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        final Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.npc = npc;
        this.name = new Label(npc.getName(), skin2);
        this.dialog = new Label(npc.getDialog().getCurrentDialogResult(), skin2);
        //this.description = new Label(npc.getDescription(), skin2);
        dialog.setFontScale(0.7f);


        verticalGroup = new VerticalGroup();
        verticalGroup.addActor(name);
        verticalGroup.addActor(dialog);
        verticalGroup.addActor(new Label(" ", skin2));

        DialogOption[] options = npc.getDialog().getOptions();
        if (options != null) {
            HorizontalGroup responsesContainer = new HorizontalGroup();
            for (final DialogOption option : options) {
                TextButton button = new TextButton(option.getOption(), skin1);
                button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        npc.getDialog().setIndex(option.getResponse());
                        setObject(npc);
                    }
                });
                responsesContainer.addActor(new Label(" ", skin2));
                responsesContainer.addActor(button);
            }
            verticalGroup.addActor(responsesContainer);
            verticalGroup.addActor(new Label(" ", skin2));
        }
        setCloseButton();
        addVerticalGroup();
    }

}
