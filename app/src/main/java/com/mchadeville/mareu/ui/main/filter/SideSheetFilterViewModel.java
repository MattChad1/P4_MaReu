package com.mchadeville.mareu.ui.main.filter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.repositories.FilterRepository;

import java.util.List;

public class SideSheetFilterViewModel extends ViewModel {

    String TAG = "SideSheetFilterViewModel";

    @NonNull
    private final FilterRepository filterRepository;

    private MutableLiveData<List<Room>> filterRoomLiveData = new MutableLiveData<>();

    public SideSheetFilterViewModel(@NonNull FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }


    public void addFilter(Room filter) {
        filterRepository.addFilterRoom(filter);
        Log.i(TAG, "SideSheetFilterViewModel.addFilter" + filter.toString());
    }

    public void deleteFilter(Room filter) {
        filterRepository.deleteFilterRoom(filter);
        Log.i(TAG, "SideSheetFilterViewModel.deleteFilter" + filter.toString());
    }

    public void updateFilterDate (FilterDate filter) {
        filterRepository.updateFilterDate(filter);
        Log.i(TAG, "SideSheetFilterViewModel.updateFilterDate" + filter.toString());
    }
}
