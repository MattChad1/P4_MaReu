package com.mchadeville.mareu.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.FilterRoom;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainViewModel extends ViewModel {

    String TAG = "MainViewModel";

    @NonNull
    private final MeetingRepository meetingRepository;

    Map<String, FilterRoom> filtersRoomsCheckboxes = new HashMap<String, FilterRoom>() {
        {
            put("A", FilterRoom.SALLE_A);
            put("B", FilterRoom.SALLE_B);
            put("C", FilterRoom.SALLE_C);
        }
    };

    private MutableLiveData<List<MeetingsViewStateItem>> allMeetingsViewStateItemsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<FilterRoom>> filterRoomLiveData = new MutableLiveData<>();
    public MediatorLiveData<List<MeetingsViewStateItem>> meetingsViewStateItemMediatorLD = new MediatorLiveData<>();

    //public MutableLiveData<List<FilterRoom>> getFilterRoomLiveData() {return filterRoomLiveData;}

    public MediatorLiveData<List<MeetingsViewStateItem>> getMeetingsViewStateItemMediatorLD() {
        return meetingsViewStateItemMediatorLD;
    }

    public MainViewModel(@NonNull MeetingRepository meetingRepository) {

        this.meetingRepository = meetingRepository;

        meetingsViewStateItemMediatorLD.addSource(getAllMeetingsViewStateItemsLiveData(), value -> meetingsViewStateItemMediatorLD.setValue(value));
        meetingsViewStateItemMediatorLD.addSource(filterRoomLiveData, filterRooms -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = allMeetingsViewStateItemsLiveData.getValue();
            List<MeetingsViewStateItem> newMeetings = new ArrayList<>();

            for (MeetingsViewStateItem meeting : meetingsViewStateItems) {
                if (filterRooms.contains(filtersRoomsCheckboxes.get(meeting.getRoom()))) {
                    newMeetings.add(meeting);
                }
            }
            meetingsViewStateItemMediatorLD.setValue(newMeetings);
        });
    } // Fin constructeur

    public void deleteMeetingLiveData(int id) {
        meetingRepository.deleteMeeting(id);
    }

    public LiveData<List<MeetingsViewStateItem>> getAllMeetingsViewStateItemsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();
            for (Meeting meeting : meetings) {

                meetingsViewStateItems.add(new MeetingsViewStateItem(meeting.getId(), meeting.getTopic(), Utils.listToString(meeting.getParticipants()) + "\n" + "A " + meeting.getStartTime(), meeting.getRoom()));
                Log.i("MeetingsViewStateItem", "Add : " + meeting.getId() + " " + meeting.getTopic() + " " + "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getStartTime() + " " + meeting.getRoom());
            }

            allMeetingsViewStateItemsLiveData.setValue(meetingsViewStateItems);
            return meetingsViewStateItems;
        });
    }

    public void filterDatas(List<FilterRoom> filterRoomInput, FilterDate filterDateInput) {
        filterRoomLiveData.setValue(filterRoomInput);
        Log.i(TAG, "filterDatas: " + filterRoomLiveData.getValue().toString());
    }
}
