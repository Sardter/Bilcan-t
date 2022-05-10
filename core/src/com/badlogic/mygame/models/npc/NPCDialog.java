package com.badlogic.mygame.models.npc;

import java.util.Random;
/**

        When the Player objects come into the vicinity of a NPC object and decides to interact with it,
        this class allows for the NPC object to respond by opening a window as an answer to the Player object.


 */
public class NPCDialog {
    private final DialogItem[] dialogItems;
    private int index = 0;

    protected String[] defaultPrompts = {
            "Hello",
            "How are you?",
            "Go away"
    };
    private Random random;

    public NPCDialog(DialogItem[] dialogItems) {
        this.dialogItems = dialogItems;
        this.random = new Random();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String defaultPrompt() {
        return defaultPrompts[random.nextInt(defaultPrompts.length)];
    }

    public boolean hasNext() {
        return dialogItems != null && index < dialogItems.length && index != -1;
    }

    public DialogItem[] getDialogItems() {
        return dialogItems;
    }

    public DialogItem getDialogItem() {
        return dialogItems[index];
    }

    public String getCurrentDialogResult() {
        if (!hasNext()) return defaultPrompt();
        return dialogItems[index].getDialog();
    }

    public DialogOption[] getOptions() {
        if (hasNext()) {
            return  dialogItems[index].getOptions();
        }
        return null;
    }

    public boolean next() {
        if (index < dialogItems.length) {
            index++;
            return true;
        }
        return false;
    }
}
