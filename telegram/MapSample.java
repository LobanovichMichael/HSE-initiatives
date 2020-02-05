package ru.misha.telegram;

import java.util.ArrayList;
import java.util.HashMap;

public class MapSample {
    public static void main(String[] args) {
        HashMap<String, Theme> map = new HashMap<String, Theme>();

        Theme theme = new Theme("name", new ArrayList<Task>(), new ArrayList<Test>(), null);
        map.put("1723459823", theme);

        System.out.println(map.get("sdfgsdg"));
        System.out.println(map.get("1723459823"));
    }
}
