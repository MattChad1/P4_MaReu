package com.mchadeville.mareu;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.FilterRepository;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel;
import com.mchadeville.mareu.ui.main.MainViewModel;
import com.mchadeville.mareu.ui.main.filter.SideSheetFilterViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(new MeetingRepository(new BuildConfigResolver()), new FilterRepository());
                }
            }
        }
        return factory;
    }

    // This field inherit the singleton property from the ViewModelFactory : it is scoped to the ViewModelFactory
    // Ask your mentor about DI scopes (Singleton, ViewModelScope, ActivityScope)
    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final FilterRepository filterRepository;

    private ViewModelFactory(@NonNull MeetingRepository meetingRepository, @NonNull FilterRepository filterRepository) {
        this.meetingRepository = meetingRepository;
        this.filterRepository = filterRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(meetingRepository, filterRepository);
        }
        else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(meetingRepository);
        }

        else if (modelClass.isAssignableFrom(SideSheetFilterViewModel.class)) {
            return (T) new SideSheetFilterViewModel(filterRepository);
        }
        else throw new IllegalArgumentException("Unknown ViewModel class");
    }
}