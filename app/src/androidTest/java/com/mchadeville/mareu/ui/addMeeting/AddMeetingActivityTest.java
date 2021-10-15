package com.mchadeville.mareu.ui.addMeeting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.core.app.ActivityScenario;
import androidx.test.runner.AndroidJUnit4;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.ui.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    private MainActivity activityRef;


    @Before
    public void setUp() throws Exception {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> activityRef = activity);
        onView(withId(R.id.fab_main)).perform(click());
    }

    @After
    public void tearDown() {
        activityRef = null;
    }

    @Test
    public void addMeeting_WithFormOK() {
        String topicOK = "Sujet Réunion test";
        String placeOK = Room.SALLE_A.getName();
        String participantsOK = "ok@gmail.com";
        String dateOK = "01/11/2021";
        String beginningHourOK = "13:00";

        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_topic)))).perform(replaceText(topicOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_room)))).perform(replaceText(placeOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_participants)))).perform(replaceText(participantsOK));
        onView(withId(R.id.btn_add_participant)).perform(click());
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_date)))).perform(replaceText(dateOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_start_time)))).perform(replaceText(beginningHourOK));

        onView(withId(R.id.btn_save)).perform(click());
        onView(allOf(withId(R.id.item_meeting_tv_title), withText(topicOK))).check(matches(isDisplayed()));
    }

    @Test
    public void addMeeting_WithFormIncomplete1() {
        String topicOK = "";
        String placeOK = "Salle rouge";
        String participantsOK = "ok@gmail.com, oki@live.fr";
        String dateOK = "01/11/2021";
        String beginningHourOK = "13:00";

        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_topic)))).perform(replaceText(topicOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_room)))).perform(replaceText(placeOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_participants)))).perform(replaceText(participantsOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_date)))).perform(replaceText(dateOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_start_time)))).perform(replaceText(beginningHourOK));

        onView(withId(R.id.btn_save)).perform(click());
        onView(withText(activityRef.getString(R.string.error_empty_topic))).check(matches(isDisplayed()));
    }

    /* Test si tous les messages d'erreurs sont présents*/
    @Test
    public void addMeeting_WithFormIncomplete2() {
        onView(withId(R.id.btn_save)).perform(click());
        onView(withText(activityRef.getString(R.string.error_empty_topic))).check(matches(isDisplayed()));
        onView(withText(activityRef.getString(R.string.error_editParticipants_empty))).check(matches(isDisplayed()));
        onView(withText(activityRef.getString(R.string.error_editRoom_empty))).check(matches(isDisplayed()));
        onView(withText(activityRef.getString(R.string.error_editDate_empty))).check(matches(isDisplayed()));
        onView(withText(activityRef.getString(R.string.error_editTime_empty))).check(matches(isDisplayed()));
    }
}