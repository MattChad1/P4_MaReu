package com.mchadeville.mareu.ui.main;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.FilterRepository;
import com.mchadeville.mareu.data.repositories.GenerateMeetings;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.util.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    private MainViewModel viewModel;

    private MeetingRepository meetingRepository;
    private FilterRepository filterRepository;
    int meetingsInit;

    @Mock
    private BuildConfigResolver buildConfigResolver;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(true);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        filterRepository = new FilterRepository();
        viewModel = new MainViewModel(meetingRepository, filterRepository);
        meetingsInit = GenerateMeetings.FAKE_MEETINGS.size();
    }


    @Test
    public void getAllMeetingsViewStateItemsLiveDataTest() throws InterruptedException {

        List<MeetingsViewStateItem> meetingsLiveDataValues;
        meetingsLiveDataValues = LiveDataTestUtils.getOrAwaitValue(viewModel.getAllMeetingsViewStateItemsLiveData());
        assertEquals (meetingsInit, meetingsLiveDataValues != null ? meetingsLiveDataValues.size() : 0);
    }

    @Test
    public void getMeetingsViewStateItemMediatorLDTest() throws InterruptedException {
        List<MeetingsViewStateItem> meetingsLiveDataValues;
        meetingsLiveDataValues = LiveDataTestUtils.getOrAwaitValue(viewModel.getMeetingsViewStateItemMediatorLD());
        assertEquals (meetingsInit, meetingsLiveDataValues != null ? meetingsLiveDataValues.size() : 0);
    }
}