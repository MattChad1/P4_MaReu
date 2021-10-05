package com.mchadeville.mareu;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel;
import com.mchadeville.mareu.ui.main.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                            new MeetingRepository(
                                    new BuildConfigResolver()
                            )
                    );
                }
            }
        }
       return factory;
    }

    // This field inherit the singleton property from the ViewModelFactory : it is scoped to the ViewModelFactory
    // Ask your mentor about DI scopes (Singleton, ViewModelScope, ActivityScope)
    @NonNull
    private final MeetingRepository meetingRepository;

    private ViewModelFactory(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(
                    meetingRepository
            );
        }
        else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(
                    meetingRepository
            );
        }
        else throw new IllegalArgumentException("Unknown ViewModel class");
    }
}