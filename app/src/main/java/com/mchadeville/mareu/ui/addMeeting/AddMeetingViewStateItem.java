package com.mchadeville.mareu.ui.addMeeting;



public class AddMeetingViewStateItem {
    private String place;
    private String topic;
    private String participants;
    private String beginningTime;


    public String getPlace() {
        return place;
    }

    public String getTopic() {
        return topic;
    }

    public String getParticipants() {
        return participants;
    }

    public String getBeginningTime() {
        return beginningTime;
    }

    public AddMeetingViewStateItem(String place, String topic, String participants, String beginningTime) {
        this.place = place;
        this.topic = topic;
        this.participants = participants;
        this.beginningTime = beginningTime;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setBeginningTime(String beginningTime) {
        this.beginningTime = beginningTime;
    }
}
