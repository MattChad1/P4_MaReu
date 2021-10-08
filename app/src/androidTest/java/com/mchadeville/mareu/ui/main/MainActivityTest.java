package com.mchadeville.mareu.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.mchadeville.mareu.util.RecyclerViewItemCountAssertion.withItemCount;
import static org.junit.Assert.*;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.GenerateMeetings;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class MainActivityTest {

    List<Meeting> meetings = GenerateMeetings.FAKE_MEETINGS;
    int numRows = meetings.size();
    int meetingsInA = 0;


    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        for(Meeting m: meetings) {if (m.getRoom()=="A") meetingsInA++;}

    }

    @Test
    public void checkNumRows_InRecyclerView () {
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }

    @Test
    public void deleteRowTest() {
        //onView(withId(R.id.liste_meetings)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ))
    }

    @Test
    public void filterRows() {
        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isNotChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows - meetingsInA));

        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }


}