package com.mchadeville.mareu.data.model;


import com.mchadeville.mareu.data.Room;

import java.util.Calendar;
import java.util.List;

public class Meeting {
    int id;
    String topic;
    Room room;
    List<String> participants;
    String startTime;
    Calendar date;

    public Meeting(int id, String topic, Room room,List<String> participants, String startTime, Calendar date) {
        this.id = id;
        this.topic = topic;
        this.room = room;
        this.participants = participants;
        this.startTime = startTime;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
