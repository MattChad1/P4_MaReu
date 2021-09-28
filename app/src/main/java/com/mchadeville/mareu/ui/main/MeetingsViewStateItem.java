package com.mchadeville.mareu.ui.main;




public class MeetingsViewStateItem {

    private final String title;
    private final String description;
    private final String room;




    public MeetingsViewStateItem(String title, String description, String room) {
        this.title = title;
        this.description = description;
        this.room = room;
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
