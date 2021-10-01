package com.mchadeville.mareu.data.repositories;

import com.mchadeville.mareu.data.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class GenerateMeetings {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(
                    1,
                    "Avantages du Kotlin",
                    "A",
                    new ArrayList<>(Arrays.asList("arthur@lamzone.com", "bertrand@gmail.com", "claire@lamzone.com", "damien@live.fr", "jean.peuplus@gmail.com", "cecile.ourkessa@lamzone.com")),
                    "13:00",
                    "30/09/2021"
            ),
            new Meeting(
                    2,
                    "Avantages du Java",
                    "B",
                    new ArrayList<>(Arrays.asList("francis@lamzone.com")),
                    "16:00",
                    "30/09/2021"
            ),
            new Meeting(
                    4,
                    "Application Réunion ",
                    "C",
                    new ArrayList<>(Arrays.asList("matthieu@lamzone.com", "francis@lamzone.com", "cecile@gmail.com", "lea@lamzone.com")),
                    "09:00",
                    "30/09/2021"
            ),
            new Meeting(
                    5,
                    "Préparer la prochaine réunion",
                    "C",
                    new ArrayList<>(Arrays.asList("claire@lamzone.com", "karim@lamzone.com", "oscar@lamzone.com")),
                    "09:00",
                    "30/09/2021"
            ),
            new Meeting(
                    6,
                    "Au fait, c'est qui le patron ici?",
                    "A",
                    new ArrayList<>(Arrays.asList("francis@lamzone.com", "lesmentors@oc.com", "matthieu.nebra@oc.com")),
                    "08:00",
                    "30/09/2021"
            )
    );
}
