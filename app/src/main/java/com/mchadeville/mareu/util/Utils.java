package com.mchadeville.mareu.util;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class Utils {

    public static String textFromTextInputLayout(TextInputLayout til) {
        return Objects.requireNonNull(til.getEditText()).getText().toString();
    }

    public static String listToStringRevert(List<String> list) {
        String re = "";
        for (int i= list.size() -1 ; i>=0; i--) {
            if (!re.isEmpty()) re += "\n";
            re += list.get(i);
        }
        return re;
    }

    public static String listToString(List<String> list) {
        String re = "";
        for (String st: list) {
            if (!re.isEmpty()) re += ", ";
            re += st;
        }
        return re;
    }

}
