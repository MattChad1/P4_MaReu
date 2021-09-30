package com.mchadeville.mareu.ui.main;


import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    public MediatorLiveData<List<MeetingsViewStateItem>> med = new MediatorLiveData<>();
    public LiveData<List<MeetingsViewStateItem>> allMeetings;




    @RequiresApi(api = Build.VERSION_CODES.N)
    public MainViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;


        /* Essai avec MediatorLiveData (ne marche pas) */
//        allMeetings = Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
//            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();
//
//            for (Meeting meeting : meetings) {
//                meetingsViewStateItems.add(
//                        new MeetingsViewStateItem(
//                                meeting.getTopic(),
//                                "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getStartTime(),
//                                meeting.getRoom()
//                        )
//                );
//            }
//            return meetingsViewStateItems;
//        });
//
//        med.addSource(allMeetings, value -> value.stream().filter(mt -> mt.getTitle().contains("Faut-il")));
    }



//                new Observer<MeetingsViewStateItem>() {
//
//            @Override
//            public void onChanged(MeetingsViewStateItem meetingsViewStateItem) {
//                if (meetingsViewStateItem.getTitle().contains("Faut-il")) med.postValue(meetingsViewStateItem);
//            }
//
//            @Override
//            public void onChanged() {
//                for (mt: meetingsViewStateItems); {
//
//                }
//
//                med.setValue(meetingsViewStateItems);
//            }
//        } -> {
//            if(filterData != null) {
//                MeetingsViewStateItem myItemList = med.getValue();
//                if (myItemList == null) return
//
//                        //here add logic for update myItemList depend On filterData
//
//                        result.setValue(myItemList)
//            }
//        });


    public MediatorLiveData<List<MeetingsViewStateItem>> getMed() {
        return med;
    }

    public LiveData<List<MeetingsViewStateItem>> getAllMeetings() {
        return allMeetings;
    }


    public void deleteMeetingLiveData(int id) {
        meetingRepository.deleteMeeting(id);
    }



    public LiveData<List<MeetingsViewStateItem>> getMeetingViewStateItemsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();
            for (Meeting meeting : meetings) {
                meetingsViewStateItems.add(
                        new MeetingsViewStateItem(
                                meeting.getId(),
                                meeting.getTopic(),
                                "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getStartTime(),
                                meeting.getRoom()
                        )
                );
                Log.i("MeetingsViewStateItem", "Add : " + meeting.getId() + " " + meeting.getTopic() + " "+ "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getStartTime() + " "+ meeting.getRoom());
            }
            return meetingsViewStateItems;
        });
    }


    public LiveData<List<MeetingsViewStateItem>> getMeetingsFiltered() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();
            for (Meeting meeting : meetings) {
                if (meeting.getRoom().equals("A")) {
                    meetingsViewStateItems.add(
                            new MeetingsViewStateItem(
                                    meeting.getId(),
                                    meeting.getTopic(),
                                    "Avec : " + meeting.getParticipants() + "\n" + "A " + meeting.getStartTime(),
                                    meeting.getRoom()
                            )
                    );
                }
            }
            return meetingsViewStateItems;
        });
    }


}
