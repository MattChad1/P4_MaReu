package com.mchadeville.mareu.ui.addMeeting;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;

import java.util.List;

public class AddMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    public AddMeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }




}
