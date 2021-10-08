package com.mchadeville.mareu.ui.main;

import com.mchadeville.mareu.data.Room;

import java.util.Calendar;
import java.util.List;

public class MeetingsViewStateItem {

    private final int id;
    private final String topic;
    private final Room room;
    private final List<String> participants;
    private final Calendar date;
    private final String time;

    public MeetingsViewStateItem(int id, String topic, Room room, List<String> participants, Calendar date, String time) {
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

    public Calendar getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }
}
