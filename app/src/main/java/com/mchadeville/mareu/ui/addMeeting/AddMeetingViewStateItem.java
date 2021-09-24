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
}
