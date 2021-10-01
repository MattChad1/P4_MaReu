package com.mchadeville.mareu.ui.main;




public class MeetingsViewStateItem {

    private final int id;
    private final String title;
    private final String description;
    private final String room;

    public MeetingsViewStateItem(int id, String title, String description, String room) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRoom() {
        return room;
    }
}
