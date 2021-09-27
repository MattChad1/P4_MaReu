package com.mchadeville.mareu.ui.addMeeting;


import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
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

    public MutableLiveData<Boolean> validTopic = new MutableLiveData<>();
    public MutableLiveData<Boolean> validPlace = new MutableLiveData<>();
    public MutableLiveData<Boolean> validPartipants = new MutableLiveData<>();
    public MutableLiveData<Boolean> validTime = new MutableLiveData<>();
    public MutableLiveData<Boolean> validGeneral = new MutableLiveData<>();

    public MutableLiveData<Boolean> getValidTopic() {return validTopic;}
    public MutableLiveData<Boolean> getValidPlace() {return validPlace;}
    public MutableLiveData<Boolean> getValidParticipants() {return validPartipants;}
    public MutableLiveData<Boolean> getValidTime() {return validTime;}
    public MutableLiveData<Boolean> getValidGeneral() {return validGeneral;}


    public Boolean validForm (String topic, String place, String participants, String beginningTime) {
        boolean valid = true;
        if (topic == null || topic.isEmpty()){
            validTopic.setValue(false);
            valid = false;
        }
        else validTopic.setValue(true);
        if (place == null || place.isEmpty()){
            validPlace.setValue(false);
            valid = false;
        }
        else validPlace.setValue(true);
        if (participants == null || participants.isEmpty()){
            validPartipants.setValue(false);
            valid = false;
        }
        else validPartipants.setValue(true);
        if (beginningTime == null || beginningTime.isEmpty()){
            validTime.setValue(false);
            valid = false;
        }
        else validTime.setValue(true);
        validGeneral.setValue(valid);
        return valid;

    }


//    public Boolean testTopic (String value) {
//        if (value == null || value.isEmpty()){
//            validTopic.setValue(false);
//            return false;
//        }
//        else {
//            validTopic.setValue(true);
//            return true;
//        }
//    }
//
//    public Boolean testPlace (String value) {
//        if (value == null || value.isEmpty()){
//            validPlace.setValue(false);
//            return false;
//        }
//        else {
//            validPlace.setValue(true);
//            return true;
//        }
//    }
//
//    public Boolean testParticipants (String value) {
//        if (value == null || value.isEmpty()){
//            validPartipants.setValue(false);
//            return false;
//        }
//        else {
//            validPartipants.setValue(true);
//            return true;
//        }
//    }
//
//    public Boolean testTime (String value) {
//        if (value == null || value.isEmpty()){
//            validTime.setValue(false);
//            return false;
//        }
//        else {
//            validTime.setValue(true);
//            return true;
//        }
//    }


    public void addMeetingLiveData(String topic, String place, String participants, String beginningTime) {
        if (validForm(topic, place, participants, beginningTime)) meetingRepository.addMeeting(topic, place, participants, beginningTime );
    }







}
