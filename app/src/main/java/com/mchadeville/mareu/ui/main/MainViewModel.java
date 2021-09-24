package com.mchadeville.mareu.ui.main;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    public MainViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public LiveData<List<MeetingsViewStateItem>> getMeetingViewStateItemsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();

            // This is called mapping !
            // Ask your mentor why it is important to separate "data" models (like Neighbour class)
            // and "view" models (like NeighboursViewStateItem)
            for (Meeting meeting : meetings) {
                    meetingsViewStateItems.add(
                            new MeetingsViewStateItem(
                                    meeting.getTopic(),
                                    "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getBeginningTime(),
                                    meeting.getPlace()

                            )
                    );
                }

            return meetingsViewStateItems;

        });
    }





}
