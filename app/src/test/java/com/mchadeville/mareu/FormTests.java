package com.mchadeville.mareu;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mchadeville.mareu.config.BuildConfigResolver;
import com.mchadeville.mareu.data.repositories.MeetingRepository;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FormTests {

    private AddMeetingViewModel viewModel;

    @Before
    public void setUp() {
       viewModel = new AddMeetingViewModel(new MeetingRepository(new BuildConfigResolver()));
    }

    @Test
    public void testTopicEntry() {
        String value = "";
        assertFalse(viewModel.testTopic(value));



    }
}
