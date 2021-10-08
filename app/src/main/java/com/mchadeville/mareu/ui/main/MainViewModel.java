package com.mchadeville.mareu.ui.main;

import static com.mchadeville.mareu.util.Utils.daysBetween;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Filter;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.FilterRepository;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

public class MainViewModel extends ViewModel {

    String TAG = "MainViewModel";

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final FilterRepository filterRepository;

    private MutableLiveData<List<MeetingsViewStateItem>> allMeetingsViewStateItemsLiveData = new MutableLiveData<>();
    private MutableLiveData<Filter> filterLiveData = new MutableLiveData<>();
    public MediatorLiveData<List<MeetingsViewStateItem>> meetingsViewStateItemMediatorLD = new MediatorLiveData<>();

    public LiveData<Filter> getFilterLiveData() {
        return filterRepository.getFilterLiveData();
    }
    public MediatorLiveData<List<MeetingsViewStateItem>> getMeetingsViewStateItemMediatorLD() {
        return meetingsViewStateItemMediatorLD;
    }

    public MainViewModel(@NonNull MeetingRepository meetingRepository, @NonNull FilterRepository filterRepository) {

        this.meetingRepository = meetingRepository;
        this.filterRepository = filterRepository;

        /* Mediator : meetings à afficher selon les filtres */
        meetingsViewStateItemMediatorLD.addSource(getAllMeetingsViewStateItemsLiveData(), value -> meetingsViewStateItemMediatorLD.setValue(value));
        meetingsViewStateItemMediatorLD.addSource(getFilterLiveData(), filter -> {
            Log.i(TAG, "MainViewModel: getFilterLiveData MAJ");
            List<MeetingsViewStateItem> meetingsViewStateItems = allMeetingsViewStateItemsLiveData.getValue();
            List<MeetingsViewStateItem> newMeetings = new ArrayList<>();

            if (meetingsViewStateItems!= null && !meetingsViewStateItems.isEmpty()) {
            for (MeetingsViewStateItem meeting : meetingsViewStateItems) {
                Log.i("MediatorLD", "filter :" + filter.toString());

                if (filter.getFiltersRooms().contains(meeting.getRoom())) {

                    // Si Salle ok, vérification du filtre date
                    Calendar dateNow = Calendar.getInstance(Locale.FRANCE);
                    Calendar dateMeeting = meeting.getDate();

                    Log.i(TAG, "MainViewModel: dateMeeting" + dateMeeting.toString());
                    Log.i(TAG, "MainViewModel: dateNow" + dateNow.toString());

                    long daysBetween = daysBetween(dateMeeting, dateNow);

                    if (daysBetween >= 0 && daysBetween <= filter.getFilterDate().getMaxDays()) {
                        newMeetings.add(meeting);
                    }
                }
                meetingsViewStateItemMediatorLD.setValue(newMeetings);
            }
        }
    });
    }// Fin constructeur

    public void deleteMeetingLiveData(int id) {
        meetingRepository.deleteMeeting(id);
    }

    /* Live Data All Meetings => récupération des données du repository*/
    public LiveData<List<MeetingsViewStateItem>> getAllMeetingsViewStateItemsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();
            for (Meeting meeting : meetings) {
                meetingsViewStateItems.add(new MeetingsViewStateItem(meeting.getId(), meeting.getTopic(), meeting.getRoom(), meeting.getParticipants(), meeting.getDate(), meeting.getStartTime()));
                Log.i("MeetingsViewStateItem", "addMeeting : id : " + meeting.getId() + " /topic : " + meeting.getTopic());
            }

            allMeetingsViewStateItemsLiveData.setValue(meetingsViewStateItems);
            return meetingsViewStateItems;
        });
    }



}
