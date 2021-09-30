package com.mchadeville.mareu.data.model;


public class Meeting {
    int id;
    String topic;
    String room;
    String participants;
    String startTime;
    String date;

    public Meeting(int id, String topic, String room,String participants, String startTime, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
