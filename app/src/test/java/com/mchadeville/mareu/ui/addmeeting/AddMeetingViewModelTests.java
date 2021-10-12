package com.mchadeville.mareu.ui.addmeeting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewStateItem;
import com.mchadeville.mareu.util.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddMeetingViewModelTests {

    private AddMeetingViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private BuildConfigResolver buildConfigResolver;

    @Mock
    private Application application;

    @Mock
    List<String> participants;

    private static final String ERROR_MESSAGE = "ERROR MESSAGE";

    private MeetingRepository meetingRepository;



    @Before
    public void setUp() {
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(false);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        viewModel = new AddMeetingViewModel(application, meetingRepository);
        doReturn(ERROR_MESSAGE)
                .when(application)
                .getString(anyInt());

    }

    @Test
    public void testTopicEntry_withEmptyField() throws InterruptedException {
        String topic = "";
        String room = Room.SALLE_A.getName();
        participants = new ArrayList<>(Arrays.asList("francis@lamzone.com", "david@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        viewModel.addMeetingLiveData(topic, room, participants, date, startTime);
        AddMeetingViewStateItem viewstate = LiveDataTestUtils.getOrAwaitValue(viewModel.getViewStateLiveData());
        assertFalse(viewstate.getValidGeneral());
    }

    @Test
    public void testTopicEntry_withEmptyField2() throws InterruptedException {
        String topic = "Topic de la réunion";
        String room = "";
        participants = new ArrayList<>(Arrays.asList("francis@lamzone.com", "david@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        viewModel.addMeetingLiveData(topic, room, participants, date, startTime);
        AddMeetingViewStateItem viewstate = LiveDataTestUtils.getOrAwaitValue(viewModel.getViewStateLiveData());
        assertFalse(viewstate.getValidGeneral());
    }

    @Test
    public void testTopicEntry_withCorrectFields() throws InterruptedException {
        String topic = "Topic de la réunion";
        String room = Room.SALLE_A.getName();
        participants = new ArrayList<>(Arrays.asList("francis@lamzone.com", "david@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        viewModel.addMeetingLiveData(topic, room, participants, date, startTime);
        AddMeetingViewStateItem viewstate = LiveDataTestUtils.getOrAwaitValue(viewModel.getViewStateLiveData());
        assertTrue(viewstate.getValidGeneral());
    }

}
