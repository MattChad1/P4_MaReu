package com.mchadeville.mareu.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.data.FilterRoom;

import java.util.ArrayList;
import java.util.List;

public class FilterRepository {

    private final MutableLiveData<List<FilterRoom>> filterRoomLD = new MutableLiveData<>();

    public FilterRepository () {
        List<FilterRoom> filters = new ArrayList<>();
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
            filters.remove(filter);
        }
        filterRoomLD.setValue(filters);
    }





}
