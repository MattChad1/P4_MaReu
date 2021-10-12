package com.mchadeville.mareu.data;

import java.util.ArrayList;
import java.util.List;

public enum Room {
    SALLE_A("Salle A"), SALLE_B("Salle B"), SALLE_C("Salle C");

    private String name;

    Room(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public static Room fromString(String str) {
        for (Room r : Room.values()) {
            if (r.getName().equals(str)) {
                return r;
            }
        }
        return null;
    }

    public static List<String> getAllNames() {
        List<String> re = new ArrayList<>();
        for (Room r : Room.values()) {
            re.add(r.getName());
            }
        return re;
    }
    }
