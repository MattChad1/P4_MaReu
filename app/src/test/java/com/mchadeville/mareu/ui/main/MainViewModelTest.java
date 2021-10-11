package com.mchadeville.mareu.ui.main;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.FilterRepository;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MainViewModelTest {

    private MainViewModel viewModel;
    private MeetingRepository meetingRepository;
    private FilterRepository filterRepository;

    @Mock
    private BuildConfigResolver buildConfigResolver;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(false);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        filterRepository = new FilterRepository();
        viewModel = new MainViewModel(meetingRepository, filterRepository);
    }



    @Test
    public void getMeetingsViewStateItemMediatorLD() {
    }

    @Test
    public void deleteMeetingLiveData() {


    }

    @Test
    public void getAllMeetingsViewStateItemsLiveData() {


    }
}