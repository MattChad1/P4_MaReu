package com.mchadeville.mareu.ui.addMeeting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.mchadeville.mareu.ui.main.MainActivityTest.childAtPosition;
import static com.mchadeville.mareu.utils.TestUtils.nthChildOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.mchadeville.mareu.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    private AddMeetingActivity activity;

    @Rule
    public ActivityTestRule<AddMeetingActivity> activityRule =
            new ActivityTestRule(AddMeetingActivity.class);



    @Before
    public void setUp() throws Exception {
        activity = activityRule.getActivity();

    }

    @Test
    public void addMeeting_WithFormOK () {
        String topicOK = "Sujet Réunion test";
        String placeOK = "Salle rouge";
        String participantsOK = "ok@gmail.com, oki@live.fr";
        String beginningHourOK = "13:00";


        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_sujet))))
                .perform(replaceText(topicOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_salle))))
                .perform(replaceText(placeOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_participants))))
                .perform(replaceText(participantsOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_heure_debut))))
                .perform(replaceText(beginningHourOK));

        onView(withId(R.id.btn_save)).perform(click());

        onView(allOf(withId(R.id.item_meeting_tv_title), withText(topicOK))).check(matches(isDisplayed()));

    }

    @Test
    public void addMeeting_WithFormIncomplete () {
        String topicOK = "";
        String placeOK = "Salle rouge";
        String participantsOK = "ok@gmail.com, oki@live.fr";
        String beginningHourOK = "13:00";


        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_sujet))))
                .perform(replaceText(topicOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_salle))))
                .perform(replaceText(placeOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_participants))))
                .perform(replaceText(participantsOK));
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.edit_heure_debut))))
                .perform(replaceText(beginningHourOK));

        onView(withId(R.id.btn_save)).perform(click());
        onView(withText(activity.getString(R.string.error_empty_topic))).check(matches(isDisplayed()));

    }
}