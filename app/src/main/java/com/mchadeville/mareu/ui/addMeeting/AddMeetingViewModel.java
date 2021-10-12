package com.mchadeville.mareu.ui.addMeeting;

import static com.mchadeville.mareu.util.Utils.dateStringToCalendar;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final Application application;


    private String TAG = "AddMeetingViewModel";
    AddMeetingViewStateItem addMeetingViewState;
    private final MutableLiveData<AddMeetingViewStateItem> viewStateLiveData = new MutableLiveData();
    private final MutableLiveData<List<String>> liveDataListeEmails = new MutableLiveData();
    private final MutableLiveData<List<String>> liveDataAllEmails = new MutableLiveData(); //Mails de toutes les réunions pour AutocompleteTextView


    public AddMeetingViewModel(@NonNull Application application, @NonNull MeetingRepository meetingRepository) {
        this.application = application;
        this.meetingRepository = meetingRepository;
    }

    public void addParticipantToTextView(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // error
        }
        else {
            List<String> listeEmails = getLiveDataListeEmails().getValue();
            if (listeEmails == null) listeEmails = new ArrayList<>();
            if (!listeEmails.contains(email)) listeEmails.add(email);
            liveDataListeEmails.setValue(listeEmails);
        }
    }

    public void deleteLastParticipantToTextView() {
        List<String> listeEmails = getLiveDataListeEmails().getValue();
        if (listeEmails != null && !listeEmails.isEmpty()) {
            listeEmails.remove(listeEmails.size() - 1);
            liveDataListeEmails.setValue(listeEmails);
        }
    }

    private String validTopic(String topic) {
        if (topic == null || topic.isEmpty()) {
            return application.getString(R.string.error_empty_topic);
        }
        else return null;
    }

    private String validRoom(String roomString) {
        if (roomString == null || roomString.isEmpty()) {
            return application.getString(R.string.error_editRoom_empty);
        }
        else return null;
    }

    private String validParticipants(List<String> participants) {
        if (participants.isEmpty()) {
            return application.getString(R.string.error_editParticipants_empty);
        }
        else return null;
    }

    private String validDate(String date) {
        if (date == null || date.isEmpty()) {
            return application.getString(R.string.error_editDate_empty);
        }
        else return null;
    }

    private String validTime(String time) {
        if (time == null || time.isEmpty()) {
            return application.getString(R.string.error_editTime_empty);
        }
        else return null;
    }



    public void addMeetingLiveData(String topic, String roomString, List<String> participants, String date, String time) {
        boolean validGeneral = false;
        Room room = null;
        Calendar dateFormatted;

        if (validTopic(topic)==null && validRoom(roomString)==null && validParticipants(participants)==null && validDate(date)==null && validTime(time)==null) validGeneral=true;

        if (validRoom(roomString)== null)  room = Room.fromString(roomString);
        if (validDate(date) == null) dateFormatted = dateStringToCalendar(date);
        else dateFormatted=null;

        addMeetingViewState = new AddMeetingViewStateItem(
                topic,
                room,
                participants,
                dateFormatted,
                time,
                validTopic(topic),
                validRoom(roomString),
                validParticipants(participants),
                validDate(date),
                validTime(time),
                validGeneral
        );
        viewStateLiveData.setValue(addMeetingViewState);

        if (validGeneral) meetingRepository.addMeeting(topic, room, participants, time, dateFormatted);
    }

    public MutableLiveData<List<String>> getLiveDataListeEmails() {return liveDataListeEmails;}
    public MutableLiveData<AddMeetingViewStateItem> getViewStateLiveData() {return viewStateLiveData;}

    public MutableLiveData<List<String>> getLiveDataAllEmails() {
        List<String> allEmails = new ArrayList<>(meetingRepository.getAllEmails());
        liveDataAllEmails.setValue(allEmails);
        return liveDataAllEmails;
    }
}
