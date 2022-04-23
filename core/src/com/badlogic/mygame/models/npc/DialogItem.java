package com.badlogic.mygame.models.npc;

public class DialogItem {
    private static int ID = 0;
    private DialogItem next;
    private DialogOption[] options;
    private String dialog;
    private int id;

    public DialogItem(String dialog, DialogOption[] options) {
        this.options = options;
        this.dialog = dialog;
        this.id = ID;
        ID++;
    }

    public DialogItem getNext() { return next;}
    public void setNext(DialogItem next) { this.next = next; }
    public String getDialog() { return dialog; }
    public DialogOption[] getOptions() { return options; }
    public void setOptions(DialogOption[] options) { this.options = options; }

    public DialogItem selectOption(int index, DialogItem[] items) {
        if (!options[index].getHasNext()) return null;
        //this.next = options[index].getResponse();
        next = items[options[index].getResponse()];
        return next;
    }

    public String[] getDialogOptions() {
        String[] options = new String[this.options.length];
        for (int i = 0; i < this.options.length; i++) {
            options[i] = this.options[i].getOption();
        }
        return options;
    }
}
