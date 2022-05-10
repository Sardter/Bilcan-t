package com.badlogic.mygame.models.npc;

/**

        Class of the options of each Dialogue item.
        Player object can choose from different Dialogue options.

 */
public class DialogOption {
    private final String option;
    private final int response;
    private final boolean hasNext;

    public DialogOption(String option, int response, boolean hasNext) {
        this.option = option;
        this.response = response;
        this.hasNext = hasNext;
    }

    public String getOption() {
        return option;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public int getResponse() {
        return response;
    }
}
