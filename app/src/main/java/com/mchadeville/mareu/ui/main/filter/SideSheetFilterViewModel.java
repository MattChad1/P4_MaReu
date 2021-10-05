package com.mchadeville.mareu.ui.main.filter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.FilterRoom;
import com.mchadeville.mareu.data.repositories.FilterRepository;

import java.util.List;

public class SideSheetFilterViewModel extends ViewModel {

    String TAG = "SideSheetFilterViewModel";

    @NonNull
    private final FilterRepository filterRepository;

    private MutableLiveData<List<FilterRoom>> filterRoomLiveData = new MutableLiveData<>();

    public SideSheetFilterViewModel(@NonNull FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

//    public LiveData<List<FilterRoom>> getFilterRoomLiveData() {
//        return Transformations.map(filterRepository.getFilterRoomLD(), filters -> {
//
//        });
//    }

    public void addFilter(FilterRoom filter) {
        filterRepository.addFilter(filter);
        Log.i(TAG, "filterRepository.addFilter" + filter.toString());
    }

    public void deleteFilter(FilterRoom filter) {
        filterRepository.deleteFilter(filter);
        Log.i(TAG, "filterRepository.deleteFilter" + filter.toString());
    }
}
