package com.mchadeville.mareu.ui.addMeeting;



public class AddMeetingViewStateItem {
    private String room;
    private String topic;
    private String participants;
    private String startTime;


    public String getRoom() {
        return room;
    }

    public String getTopic() {
        return topic;
    }

    public String getParticipants() {
        return participants;
    }

    public String getStartTime() {
        return startTime;
    }

    public AddMeetingViewStateItem(String room, String topic, String participants, String startTime) {
        this.room = room;
        this.topic = topic;
        this.participants = participants;
        this.startTime = startTime;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
