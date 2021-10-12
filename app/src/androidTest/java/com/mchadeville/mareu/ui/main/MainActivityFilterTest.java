package com.mchadeville.mareu.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.mchadeville.mareu.util.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.core.app.ActivityScenario;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.data.repositories.GenerateMeetings;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MainActivityFilterTest {

    private MainActivity activityRef;
    List<Meeting> meetings = GenerateMeetings.FAKE_MEETINGS;
    int numRows = meetings.size();
    int meetingsInA = 0;

    @Before
    public void setUp() throws Exception {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> activityRef = activity);

        for (Meeting m : meetings) {if (m.getRoom() == Room.SALLE_A) meetingsInA++;}
    }


    /* Vérification des filtres de salle */
    @Test
    public void filterRowsByRoom() {

        // Désactivation des réunions dans la première salle
        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isNotChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows - meetingsInA));

        // Réactivation des réunions dans la première salle, on retrouve numRows
        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }

    /* Vérification des filtres de date */
    @Test
    public void filterRowsByDate() {

        // Sélection des réunions du jour
        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_date3)).perform(click());
        onView(withId(R.id.filter_date1)).perform(click());
        onView(withId(R.id.filter_date1)).check(matches(isChecked()));

        onView(withId(R.id.filter_date1)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(3));

        // Réactivation des réunions dans la première salle, on retrouve numRows
        onView(withId(R.id.btn_filter)).perform(click());
        onView(withId(R.id.filter_date2)).perform(click());
        onView(withId(R.id.filter_date4)).perform(click());
        onView(withId(R.id.filter_date1)).check(matches(isNotChecked()));

        onView(withId(R.id.filter_date4)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }
}
