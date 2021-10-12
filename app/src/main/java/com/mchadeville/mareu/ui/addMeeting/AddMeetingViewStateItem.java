package com.mchadeville.mareu.ui.addMeeting;

import androidx.annotation.Nullable;

import com.mchadeville.mareu.data.Room;

import java.util.Calendar;
import java.util.List;

public class AddMeetingViewStateItem {
    private String topic;
    private Room room;
    private List<String> participants;
    private Calendar date;
    private String startTime;

    @Nullable
    private String topicError;
    @Nullable
    private String roomError;
    @Nullable
    private String participantsError;
    @Nullable
    private String timeError;
    @Nullable
    private String dateError;

    private Boolean validGeneral;

    public AddMeetingViewStateItem(String topic, Room room, List<String> participants, Calendar date, String time, @Nullable String topicError, @Nullable String roomError, @Nullable String participantsError, @Nullable String dateError, @Nullable String timeError, Boolean validGeneral) {
        this.topic = topic;
        this.room = room;
        this.participants = participants;
        this.date = date;
        this.startTime = time;
        this.topicError = topicError;
        this.roomError = roomError;
        this.participantsError = participantsError;
        this.timeError = timeError;
        this.dateError = dateError;
        this.validGeneral = validGeneral;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Nullable
    public String getTopicError() {
        return topicError;
    }

    @Nullable
    public String getRoomError() {
        return roomError;
    }

    @Nullable
    public String getParticipantsError() {
        return participantsError;
    }

    @Nullable
    public String getTimeError() {
        return timeError;
    }

    @Nullable
    public String getDateError() {
        return dateError;
    }

    public String getTopic() {
        return topic;
    }

    public Calendar getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public Boolean getValidGeneral() {
        return validGeneral;
    }
}
