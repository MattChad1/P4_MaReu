package com.mchadeville.mareu.ui.addMeeting;


import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.repositories.MeetingRepository;

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
    public MutableLiveData<Boolean> validDate = new MutableLiveData<>();
    public MutableLiveData<Boolean> validGeneral = new MutableLiveData<>();

    public MutableLiveData<Boolean> getValidTopic() {return validTopic;}
    public MutableLiveData<Boolean> getValidPlace() {return validPlace;}
    public MutableLiveData<Boolean> getValidParticipants() {return validPartipants;}
    public MutableLiveData<Boolean> getValidTime() {return validTime;}
    public MutableLiveData<Boolean> getValidGeneral() {return validGeneral;}


    public Boolean validForm (String topic, String place, String participants, String beginningTime, String date) {
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
//        else if (!Patterns.EMAIL_ADDRESS.matcher(participants).matches()) {
//            validPartipants.setValue(false);
//            valid = false;
//        }
        else validPartipants.setValue(true);
        if (beginningTime == null || beginningTime.isEmpty()){
            validTime.setValue(false);
            valid = false;
        }
        else validTime.setValue(true);
        if (date == null || date.isEmpty()){
            validDate.setValue(false);
            valid = false;
        }
        else validDate.setValue(true);
        validGeneral.setValue(valid);
        return valid;

    }


    public void addMeetingLiveData(String topic, String place, String participants, String beginningTime, String date) {
        if (validForm(topic, place, participants, beginningTime, date)) meetingRepository.addMeeting(topic, place, participants, beginningTime, date);
    }









}
