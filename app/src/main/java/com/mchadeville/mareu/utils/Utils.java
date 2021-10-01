package com.mchadeville.mareu.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class Utils {

    public static String textFromTextInputLayout(TextInputLayout til) {
        return Objects.requireNonNull(til.getEditText()).getText().toString();
    }

    public static String listToStringRevert(List<String> li) {
        String re = "";
        for (int i= li.size() -1 ; i>=0; i--) {
            if (!re.isEmpty()) re += "\n";
            re += li.get(i);
        }
        return re;
    }

}
