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
                    "Faut-il changer la machine à café?",
                    "A",
                    "Moi et toi",
                    "13:00",
                    "30/09/2021"
            ),
            new Meeting(
                    2,
                    "Pour ou contre les PowerPoints",
                    "B",
                    "Lui et moi",
                    "16:00",
                    "30/09/2021"
            ),
            new Meeting(
                    3,
                    "Préparer la prochaine réunion",
                    "C",
                    "Qui veut",
                    "09:00",
                    "30/09/2021"
            ),
            new Meeting(
                    4,
                    "Au fait, c'est qui le patron ici?",
                    "A",
                    "Tout le monde",
                    "08:00",
                    "30/09/2021"
            )
    );
}
