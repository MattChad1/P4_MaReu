package com.mchadeville.mareu.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.mchadeville.mareu.util.RecyclerViewItemCountAssertion.withItemCount;
import static com.mchadeville.mareu.util.TestUtils.clickInItemView;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.data.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivityFilterTest {

    private MainActivity activityRef;
    //List<Meeting> meetings;
    List<MeetingsViewStateItem> meetings;
    MainViewModel vm;
    int numRows;
    int meetingsInA = 0;
    int meetingsToday = 0;
    Calendar today = Calendar.getInstance(Locale.FRANCE);

    @Before
    public void setUp() throws Exception {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> activityRef = activity);
        //meetings = GenerateMeetings.FAKE_MEETINGS;
        vm = new ViewModelProvider(activityRef, ViewModelFactory.getInstance()).get(MainViewModel.class);
        meetings = vm.getMeetingsViewStateItemMediatorLD().getValue();
        numRows = meetings.size();
        for (MeetingsViewStateItem m : meetings) {if (m.getRoom() == Room.SALLE_A) meetingsInA++;}
        for (MeetingsViewStateItem m : meetings) {
            if (m.getDate().get(Calendar.DATE) == Calendar.getInstance(Locale.FRANCE).get(Calendar.DATE) && m.getDate().get(Calendar.MONTH) == Calendar.getInstance(Locale.FRANCE).get(Calendar.MONTH) && m.getDate().get(Calendar.YEAR) == Calendar.getInstance(Locale.FRANCE).get(Calendar.YEAR))
                meetingsToday++;
        }
    }

    @After
    public void tearDown() {
        activityRef = null;
    }

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

    /* Vérification des filtres de salle */
    @Test
    public void filterRowsByRoom() throws InterruptedException {

        // Désactivation des réunions dans la première salle
        onView(withId(R.id.btn_filter)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isNotChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows - meetingsInA));

        // Réactivation des réunions dans la première salle, on retrouve numRows
        onView(withId(R.id.btn_filter)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.filter_salle_A)).perform(click());
        onView(withId(R.id.filter_salle_A)).check(matches(isChecked()));

        onView(withId(R.id.filter_salle_A)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }

    /* Vérification des filtres de date */
    @Test
    public void filterRowsByDate() throws InterruptedException {

        // Sélection des réunions du jour
        onView(withId(R.id.btn_filter)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.filter_date1)).perform(click());
        onView(withId(R.id.filter_date1)).check(matches(isChecked()));

        onView(withId(R.id.filter_date1)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(meetingsToday));

        // Réactivation des réunions dans la première salle, on retrouve numRows
        onView(withId(R.id.btn_filter)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.filter_date4)).perform(click());
        onView(withId(R.id.filter_date1)).check(matches(isNotChecked()));

        onView(withId(R.id.filter_date4)).perform(pressBack());
        onView(withId(R.id.liste_meetings)).check(withItemCount(numRows));
    }
}
