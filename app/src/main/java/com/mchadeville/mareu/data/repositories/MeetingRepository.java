package com.mchadeville.mareu.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();
    private int idMax = 1;
    String TAG = "Meeting Repository";

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        if (buildConfigResolver.isDebug()) {
            meetingsLiveData.setValue(GenerateMeetings.generateMeetings());
            idMax = GenerateMeetings.FAKE_MEETINGS.size() + 1;
        }
    }

    public void addMeeting(String topic, Room room, List<String> participants, String startTime, Calendar date) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) {meetings = new ArrayList<>();}

        meetings.add(new Meeting(idMax, topic, room, participants, startTime, date));

        //Log.i(TAG, "addMeeting: " + idMax + " " + topic + " " + room + " " + participants + " " + startTime + " " + date);
        idMax++;
        meetingsLiveData.setValue(meetings);
    }

    public void deleteMeeting(int id) {
        List<Meeting> meetings = meetingsLiveData.getValue();
        Meeting meetingToDelete = null;
        for (Meeting m : meetings) {
            if (m.getId() == id) meetingToDelete = m;
        }
        if (meetingToDelete != null) meetings.remove(meetingToDelete);
        meetingsLiveData.setValue(meetings);
    }

    public List<String> getAllEmails() {
        List<String> allEmails = new ArrayList<>();
        List<Meeting> meetings = meetingsLiveData.getValue();
        for (Meeting meet : meetings) {
            for (String email : meet.getParticipants()) {
                if (!allEmails.contains(email)) allEmails.add(email);
            }
        }
        return allEmails;
    }

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }
}
