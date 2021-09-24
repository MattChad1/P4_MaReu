package com.mchadeville.mareu.data.model;


public class Meeting {

    String place;
    String topic;
    String participants;
    String beginningTime;

    public Meeting(String lieu, String topic, String participants, String heureDebut) {
        this.place = lieu;
        this.topic = topic;
        this.participants = participants;
        this.beginningTime = heureDebut;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String sujet) {
        this.topic = sujet;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(String beginningTime) {
        this.beginningTime = beginningTime;
    }
}
