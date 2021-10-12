package com.mchadeville.mareu.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.mchadeville.mareu.util.RecyclerViewItemCountAssertion.withItemCount;
import static com.mchadeville.mareu.util.TestUtils.clickInItemView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.GenerateMeetings;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MainActivityOtherTest {

    private MainActivity activityRef;
    List<Meeting> meetings = GenerateMeetings.FAKE_MEETINGS;
    int numRows = meetings.size();
    int meetingsInA = 0;

    @Before
    public void setUp()  {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> activityRef = activity);
    }

    /* Vérifie le nombre de réunions affichées */
    @Test
    public void checkNumRows_InRecyclerView() {
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));

    }

    /* Vérifie la suppression d'une réunion avec l'icône corbeille */
    @Test
    public void deleteRowTest() {
        onView(withId(R.id.liste_meetings)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickInItemView(R.id.item_btn_delete)));
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows - 1));
    }


}