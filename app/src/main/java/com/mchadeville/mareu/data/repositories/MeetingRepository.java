package com.mchadeville.mareu.data.repositories;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();
    private String TAG = "Meeting Repository";
    private int idMax = 1;

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        if (buildConfigResolver.isDebug()) {
            meetingsLiveData.setValue(GenerateMeetings.generateMeetings());
            idMax = GenerateMeetings.FAKE_MEETINGS.size()+1;
        }
    }


    public void addMeeting (
            String topic,
            String room,
            String participants,
            String startTime,
            String date
    ) {
        List<Meeting> meetings = meetingsLiveData.getValue();


        if (meetings == null) {
            meetings = new ArrayList<>();
        }



        meetings.add(
                new Meeting(
                        idMax,
                        topic,
                        room,
                        participants,
                        startTime,
                        date
                       )
        );
        Log.i(TAG, "addMeeting: "+ idMax + " " + topic + " "+ room + " "+ participants + " "+ startTime + " "+ date);
        idMax ++;
        meetingsLiveData.setValue(meetings);
    }


    public void deleteMeeting (int id) {

        List<Meeting> meetings = meetingsLiveData.getValue();
        Meeting meetingToDelete = null;
        for (Meeting m : meetings) {
            if (m.getId()==id) meetingToDelete = m;
        }
        if (meetingToDelete != null) meetings.remove(meetingToDelete);
        meetingsLiveData.setValue(meetings);
    }

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }



    private void generateRandomMeetings() {
        addMeeting(
                "Faut-il changer la machine à café?",
                "A",
                "Moi et toi",
                "13:00",
                "30/09/2021"
        );
        addMeeting(
                "Pour ou contre les PowerPoints",
                "B",
                "Lui et moi",
                "16:00",
                "30/09/2021"
        );
        addMeeting(
                "Préparer la prochaine réunion",
                "C",
                "Qui veut",
                "09:00",
                "30/09/2021"
        );
        addMeeting(
                "Au fait, c'est qui le patron ici?",
                "A",
                "Tout le monde",
                "08:00",
                "30/09/2021"
        );

    }


}
