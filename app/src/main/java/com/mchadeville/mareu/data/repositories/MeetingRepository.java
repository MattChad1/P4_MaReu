package com.mchadeville.mareu.data.repositories;


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

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        if (buildConfigResolver.isDebug()) {
            generateRandomMeetings();
        }
    }


    public void addMeeting (
            String topic,
            String room,
            String participants,
            String startTime
    ) {

        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) {
            meetings = new ArrayList<>();
        }

        meetings.add(
                new Meeting(
                        topic,
                        room,
                        participants,
                        startTime
                       )
        );
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
                "13:00"
        );
        addMeeting(
                "Pour ou contre les PowerPoints",
                "B",
                "Lui et moi",
                "16:00"
        );
        addMeeting(
                "Préparer la prochaine réunion",
                "C",
                "Qui veut",
                "09:00"
        );
        addMeeting(
                "Au fait, c'est qui le patron ici?",
                "A",
                "Tout le monde",
                "08:00"
        );

    }


}
