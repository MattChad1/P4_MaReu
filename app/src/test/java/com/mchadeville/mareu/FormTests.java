package com.mchadeville.mareu;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormTests {


    private AddMeetingViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();
//
    @Mock
    private BuildConfigResolver buildConfigResolver;

    private MeetingRepository meetingRepository;

    @Before
    public void setUp() {
        //given(buildConfigResolver.isDebug()).willReturn(true);
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(false);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        //viewModel = new AddMeetingViewModel(new MeetingRepository(new BuildConfigResolver()));
        viewModel = new AddMeetingViewModel(meetingRepository);
    }

    @Test
    public void testTopicEntry_withEmptyField() {
        String topic = "";
        String room = "Room Red";
        int participants = 4;
        String startTime = "12:00";
        String date = "30/09/2021";

        assertFalse(viewModel.validForm(topic, room, participants, startTime, date));
    }

    @Test
    public void testTopicEntry_withCorrectFields() {
        String topic = "Topic de la réunion";
        String room = "Room Red";
        int participants = 4;
        String startTime = "12:00";
        String date = "30/09/2021";

        assertTrue(viewModel.validForm(topic, room, participants, startTime, date));
    }


}
