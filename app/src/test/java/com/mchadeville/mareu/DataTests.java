package com.mchadeville.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.GenerateMeetings;
import com.mchadeville.mareu.data.repositories.MeetingRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DataTests {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private BuildConfigResolver buildConfigResolver;

    private MeetingRepository meetingRepository;
    int repoSize;



    @Before
    public void setUp() {
        Mockito.when(buildConfigResolver.isDebug()).thenReturn(true);
        meetingRepository = new MeetingRepository(buildConfigResolver);
        repoSize = GenerateMeetings.FAKE_MEETINGS.size();


    }


    @Test
    public void addMeetingTest () {
        String topic = "";
        String room = "Salle A";
        List<String> participants = Arrays.asList("francis@lamzone.com", "jean.aymar@gmail.com");
        String startTime = "12:00";
        String date = "30/09/2021";

        meetingRepository.addMeeting(topic, room, participants, startTime, date);
        assertEquals(meetingRepository.getMeetingsLiveData().getValue().size(), (repoSize + 1));
    }

    @Test
    public void deleteMeetingTest () {
        int idMeetingToDelete = GenerateMeetings.FAKE_MEETINGS.get(0).getId();
        meetingRepository.deleteMeeting(idMeetingToDelete);
        assertEquals(meetingRepository.getMeetingsLiveData().getValue().size(), (repoSize - 1));
    }

//    @Test
//    public void getAllEmailsTest () {
//        meetingRepository.
//    }



}
