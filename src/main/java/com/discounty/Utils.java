package com.discounty;

import android.util.Log;

import java.util.Random;

public final class Utils {

    public static String getColorOfTheListItem(char a) {
        return getColorsArray()[LetterInAlphabet(a)];
    }

    public static String TestColorArray() {
        String[] colors = getColorsArray();
        StringBuilder str = new StringBuilder();
        for (String x : colors)
            str.append(x).append(" ");
        return str.toString();
    }

    private static int LetterInAlphabet(char a) {
        try {
            if (Character.toString(a).matches("[a-zA-Z]")) {
                if (Character.isLowerCase(a))
                    return "abcdefghijklmnopqrstuvwxyz".indexOf(Character.toString(a)) + 1;
                else return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(Character.toString(a)) + 1;
            } else if (Character.toString(a).matches("[а-яА-Я]")) {
                if (Character.isLowerCase(a))
                    return "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".indexOf(Character.toString(a)) + 1;
                else return "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".indexOf(Character.toString(a)) + 1;
            }
            return new Random().nextInt(26);
        } catch (Exception e) {
            Log.d("UtilsLetterInAlphabet", e.getMessage());
            e.printStackTrace();
            return new Random().nextInt(26);
        }
    }

    private static String[] getColorsArray() {
        return new String[]{"#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4",
                "#00BCD4", "#009688", "#4CAF50", "#74D108", "#CDDC39", "#FFEB3B", "#FFC107", "#FF9800",
                "#FF5722", "#795548", "#9E9E9E", "#607D8B", "#FF2D55", "#5856D6", "#007AFF", "#34AADC",
                "#5AC8FA", "#4CD964", "#FF3B30", "#FF9500", "#FFCC00", "#1ABC9C", "#8E44AD", "#27AE60",
                "#3498DB", "#16A085", "#FF9500", "#5AC8FA", "#E74C3C"};
    }
}
