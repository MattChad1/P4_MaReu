package com.mchadeville.mareu.ui.addMeeting;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.FilterRepository;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

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
    public MutableLiveData<Boolean> validDate = new MutableLiveData<>();
    public MutableLiveData<Boolean> validGeneral = new MutableLiveData<>();
    public MutableLiveData<List<String>> liveDataListeEmails = new MutableLiveData<>();
    public MutableLiveData<List<String>> liveDataAllEmails = new MutableLiveData<>();

    public MutableLiveData<Boolean> getValidTopic() {
        return validTopic;
    }

    public MutableLiveData<Boolean> getValidPlace() {
        return validPlace;
    }

    public MutableLiveData<Boolean> getValidParticipants() {
        return validPartipants;
    }

    public MutableLiveData<Boolean> getValidTime() {
        return validTime;
    }

    public MutableLiveData<Boolean> getValidGeneral() {
        return validGeneral;
    }

    public MutableLiveData<List<String>> getLiveDataListeEmails() {
        return liveDataListeEmails;
    }

    public MutableLiveData<List<String>> getLiveDataAllEmails() {

        List<String> allEmails = new ArrayList<>();
        List<Meeting> allMeetings = meetingRepository.getMeetingsLiveData().getValue();
        if (allMeetings != null) {
            for (Meeting meeting : allMeetings) {
                for (String email : meeting.getParticipants()) {
                    if (!allEmails.contains(email)) allEmails.add(email);
                }
            }
        }
            liveDataAllEmails.setValue(allEmails);
        return liveDataAllEmails;
    }

    public void addParticipantToTextView(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            validPartipants.setValue(false);
            Log.i(TAG, "addParticipantToTextView: ");
        } else {
            Log.i(TAG, "addParticipantToTextView: email valid");
            List<String> listeEmails = getLiveDataListeEmails().getValue();
            if (listeEmails == null) listeEmails = new ArrayList<>();
            listeEmails.add(email);
            liveDataListeEmails.setValue(listeEmails);
        }
    }

    public void deleteLastParticipantToTextView() {
        List<String> listeEmails = getLiveDataListeEmails().getValue();
        if (listeEmails != null) {
            listeEmails.remove(listeEmails.size() - 1);
            liveDataListeEmails.setValue(listeEmails);
        }
    }

    public Boolean validForm(String topic, String place, int participants, String beginningTime, String date) {
        boolean valid = true;
        if (topic == null || topic.isEmpty()) {
            validTopic.setValue(false);
            valid = false;
        } else validTopic.setValue(true);
        if (place == null || place.isEmpty()) {
            validPlace.setValue(false);
            valid = false;
        } else validPlace.setValue(true);
        if (participants < 1) {
            validPartipants.setValue(false);
            valid = false;
        }
        else validPartipants.setValue(true);
        if (beginningTime == null || beginningTime.isEmpty()) {
            validTime.setValue(false);
            valid = false;
        } else validTime.setValue(true);
        if (date == null || date.isEmpty()) {
            validDate.setValue(false);
            valid = false;
        } else validDate.setValue(true);
        validGeneral.setValue(valid);
        return valid;
    }

    public void addMeetingLiveData(String topic, String place, String beginningTime, String date) {
        List<String> participants = getLiveDataListeEmails().getValue();
        if (validForm(topic, place, participants.size(), beginningTime, date))
            meetingRepository.addMeeting(topic, place, participants, beginningTime, date);
    }
}
