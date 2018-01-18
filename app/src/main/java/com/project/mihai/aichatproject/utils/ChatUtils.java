package com.project.mihai.aichatproject.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adasc on 1/7/2018.
 */

public class ChatUtils {
    public static List<String> tempWords = new ArrayList<>();

    public static boolean isNull(String string) {
        return string.length() == 0 || string.trim().equals("");
    }

    public static String getTempWords() {
        tempWords.add("Nu am inteles foarte bine.");
        tempWords.add("Poti repeta te rog?");
        tempWords.add("Te rog sa repeti.");
        tempWords.add("Cred ca nu te-am inteles.");
        tempWords.add("Nu stiu exact ce doresti.");
        tempWords.add("Mai incearca cu o alta intrebare.");
        tempWords.add("Cred ca am inteles gresit...");
        tempWords.add("Se pare ca nu stiu despre ce vorbesti.");
        tempWords.add("Ramane sa mai incercam inca o data cu alt cuvant.");
        tempWords.add("Iti ofer sansa sa repeti intrebarea.");

        Random random = new Random();
        return tempWords.get(random.nextInt(tempWords.size()));
    }

}
