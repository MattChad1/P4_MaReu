package com.mchadeville.mareu.data;

import android.os.Environment;
import android.util.Log;

//public enum Room {
//    SALLE_A,
//    SALLE_B,
//    SALLE_C
//}

public enum Room {
    SALLE_A("Salle A"), SALLE_B("Salle B"), SALLE_C("Salle C");

    private String name;

    Room(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public static Room fromString(String str) {
        Log.i("Room", "fromString: appel");
        for (Room r : Room.values()) {
            if (r.getName().equals(str)) {
                Log.i("Room", "fromString: match" + r.toString());
                return r;
            }
        }
        return null;
    }
    }
