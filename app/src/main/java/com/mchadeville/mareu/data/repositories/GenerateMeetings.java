package com.mchadeville.mareu.data.repositories;

import static com.mchadeville.mareu.util.Utils.dateStringToCalendar;

import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

abstract public class GenerateMeetings {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(
                    1,
                    "Avantages du Kotlin",
                    Room.SALLE_A,
                    new ArrayList<>(Arrays.asList("arthur@lamzone.com", "bertrand@gmail.com", "claire@lamzone.com", "damien@live.fr", "jean.peuplus@gmail.com", "cecile.ourkessa@lamzone.com")),
                    "08:00",
                    dateStringToCalendar("08/10/2021")
            ),
            new Meeting(
                    2,
                    "Avantages du Java",
                    Room.SALLE_A,
                    new ArrayList<>(Arrays.asList("francis@lamzone.com")),
                    "16:00",
                    dateStringToCalendar("08/10/2021")
            ),
            new Meeting(
                    4,
                    "Progrès de l'application Réunion ",
                    Room.SALLE_B,
                    new ArrayList<>(Arrays.asList("matthieu@lamzone.com", "francis@lamzone.com", "cecile@gmail.com", "lea@lamzone.com")),
                    "09:00",
                    dateStringToCalendar("20/10/2021")
            ),
            new Meeting(
                    5,
                    "Réunion pour préparer la prochaine réunion",
                    Room.SALLE_B,
                    new ArrayList<>(Arrays.asList("sarah.fraichi@lamzone.com", "jacques.selere@gmail.com","claire@lamzone.com", "karim@lamzone.com", "oscar@lamzone.com")),
                    "09:00",
                    dateStringToCalendar("22/10/2021")
            ),
            new Meeting(
                    6,
                    "Faisons-nous trop de réunions?",
                    Room.SALLE_C,
                    new ArrayList<>(Arrays.asList("francis@lamzone.com", "lesmentors@oc.com", "matthieu.nebra@oc.com")),
                    "08:00",
                    dateStringToCalendar("12/10/2021")
            )
    );
}
