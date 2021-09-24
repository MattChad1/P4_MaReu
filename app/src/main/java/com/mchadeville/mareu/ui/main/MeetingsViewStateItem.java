package com.mchadeville.mareu.ui.main;




public class MeetingsViewStateItem {

    private final String title;
    private final String description;
    private final String place;



    public MeetingsViewStateItem(String title, String description, String place) {
        this.title = title;
        this.description = description;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }
}
