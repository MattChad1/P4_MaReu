package com.mchadeville.mareu.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Filter;
import com.mchadeville.mareu.data.model.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterRepository {

    String TAG = "FilterRepository";

    private final MutableLiveData<Filter> filterLiveData = new MutableLiveData<>();

    public LiveData<Filter> getFilterLiveData() {
        return filterLiveData;
    }

    public FilterRepository () {
        Filter filter = new Filter(new ArrayList<>(Arrays.asList(Room.values())), FilterDate.DATE_ALL);
        filterLiveData.setValue(filter);
    }


    public void addFilterRoom(Room newFilterRoom) {
        Filter filter = filterLiveData.getValue();
        List<Room> filtersRooms = filter.getFiltersRooms();
        filtersRooms.add(newFilterRoom);
        filterLiveData.setValue(new Filter(filtersRooms, filter.getFilterDate()));
    }

    public void deleteFilterRoom(Room filterRoomToDelete) {
        Filter filter = filterLiveData.getValue();
        List<Room> filtersRooms = new ArrayList<>();
        filtersRooms = filter.getFiltersRooms();
        Log.i(TAG, "filtersRooms: " + filtersRooms.toString());

        filtersRooms.remove(filterRoomToDelete);
        filterLiveData.setValue(new Filter(filtersRooms, filter.getFilterDate()));

        Log.i(TAG, "filterLiveData: " + filterLiveData.getValue().getFiltersRooms().toString());
    }

    public void updateFilterDate(FilterDate newFilterDate) {
        Filter filter = filterLiveData.getValue();
        filterLiveData.setValue(new Filter(filter.getFiltersRooms(), newFilterDate));
    }



}
