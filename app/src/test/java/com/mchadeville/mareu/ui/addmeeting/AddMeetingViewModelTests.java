package com.mchadeville.mareu.ui.addmeeting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

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

    private com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private BuildConfigResolver buildConfigResolver;

    private MeetingRepository meetingRepository;

    @Before
    public void setUp() {
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(false);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        viewModel = new com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel(meetingRepository);
    }

    @Test
    public void testTopicEntry_withEmptyField() {
        String topic = "";
        String room = "Salle A";
        List<String> participants = new ArrayList<>(Arrays.asList("francis@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        assertFalse(viewModel.validForm(topic, room, participants, startTime, date));
    }

    @Test
    public void testTopicEntry_withEmptyField2() {
        String topic = "Topic de la réunion";
        String room = "";
        List<String> participants = new ArrayList<>(Arrays.asList("francis@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        assertFalse(viewModel.validForm(topic, room, participants, startTime, date));
    }

    @Test
    public void testTopicEntry_withCorrectFields() {
        String topic = "Topic de la réunion";
        String room = "Salle A";
        List<String> participants = new ArrayList<>(Arrays.asList("francis@lamzone.com"));
        String startTime = "12:00";
        String date = "30/09/2021";

        assertTrue(viewModel.validForm(topic, room, participants, startTime, date));
    }
}
