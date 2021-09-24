package com.mchadeville.mareu.data.repositories;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.model.Meeting;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {


    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();
private String TAG = "Meeting Repository";

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        // At startup, when creating repo, if we're in debug mode, add random Meetings
        if (buildConfigResolver.isDebug()) {
            Log.i(TAG, "appel generateRandomMeetings");
            generateRandomMeetings();
            Log.i(TAG, meetingsLiveData.toString());
        }
    }


    public void addMeeting (
            String place,
            String topic,
            String participants,
            String time
    ) {

        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) {
            meetings = new ArrayList<>();
        }

        meetings.add(
                new Meeting(
                        place,
                        topic,
                        participants,
                        time
                       )
        );

        Log.i(TAG, "add meeting : " + topic);
        meetingsLiveData.setValue(meetings);
    }

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }


//    public LiveData<Meeting> getMeetingLiveData() {
//
//    }






    private void generateRandomMeetings() {
        addMeeting(
                "Room blue",
                "Faut-il changer la machine à café?",
                "Moi et toi",
                "13:00"
        );
        addMeeting(
                "Room red",
                "Pour ou contre les PowerPoints",
                "Lui et moi",
                "16:00"
        );

    }


}
