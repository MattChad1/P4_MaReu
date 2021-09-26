package com.mchadeville.mareu.ui.addMeeting;


import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;
    private String TAG = "AddMeetingViewModel";

    public AddMeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public MutableLiveData<String> topicLiveData = new MutableLiveData<>();
    public MutableLiveData<String> placeLiveData = new MutableLiveData<>();

    public Boolean testTopic (String value) {
        return value != null && !value.isEmpty();
    }

    public Boolean testPlace (String value) {
        return value != null && !value.isEmpty();
    }



    public void addMeetingLiveData(String topic, String place, String participants, String beginningTime) {
        if (testPlace(place) && testTopic(topic)) {
            meetingRepository.addMeeting(topic, place, participants, beginningTime );
        }
    }




    public void onClick(View view) {
        Log.i(TAG, "onClick: " + topicLiveData.getValue());
        //LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());
        //userMutableLiveData.setValue(loginUser);



    }





}
