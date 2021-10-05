package com.mchadeville.mareu.ui.main;

import java.util.List;

public class MeetingsViewStateItem {

    private final int id;
    private final String topic;
    private final String room;
    private final List<String> participants;
    private final String date;
    private final String time;


    public MeetingsViewStateItem(int id, String topic, String room, List<String> participants, String date, String time) {
        this.id = id;
        this.topic = topic;
        this.room = room;
        this.participants = participants;
        this.date = date;
        this.time = time;
    }



    public List<String> getParticipants() {
        return participants;
    }

    public String getTime() {
        return time;
    }



    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }



    public String getRoom() {
        return room;
    }
}
