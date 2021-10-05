package com.mchadeville.mareu.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.data.FilterRoom;
import com.mchadeville.mareu.data.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterRepository {

    private final MutableLiveData<List<FilterRoom>> filterRoomLD = new MutableLiveData<>();

    public LiveData<List<FilterRoom>> getFilterRoomLD() {
        return filterRoomLD;
    }

    public FilterRepository () {
        List<FilterRoom> filters = new ArrayList<>();
        filters.addAll(Arrays.asList(FilterRoom.values()));
        filterRoomLD.setValue(filters);
    }


    public void addFilter(FilterRoom filter) {
        List<FilterRoom> filters = filterRoomLD.getValue();
            filters.add(filter);
        filterRoomLD.setValue(filters);
    }

    public void deleteFilter(FilterRoom filter) {
        List<FilterRoom> filters = filterRoomLD.getValue();


        if (filters != null) {
            Log.i("deleteFilter()", "filter " + filter.toString());
            Log.i("deleteFilter()", "filterRoomLD.getValue() " + filterRoomLD.getValue());
            filters.remove(filter);
        }
        filterRoomLD.setValue(filters);
    }

//    public void deleteMeeting(int id) {
//        List<Meeting> meetings = meetingsLiveData.getValue();
//        Meeting meetingToDelete = null;
//        for (Meeting m : meetings) {
//            if (m.getId() == id) meetingToDelete = m;
//        }
//        if (meetingToDelete != null) meetings.remove(meetingToDelete);
//        meetingsLiveData.setValue(meetings);
//    }





}
