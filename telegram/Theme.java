package ru.misha.telegram;

import java.util.ArrayList;

public class Theme {
    String themeName;
    ArrayList<Task> themeTasks;
    ArrayList<Test> themeTests;
    Theory themeTheory;

    public Theme(String themeName, ArrayList<Task> themeTasks, ArrayList<Test> themeTests, Theory themeTheory) {
        this.themeName = themeName;
        this.themeTasks = themeTasks;
        this.themeTests = themeTests;
        this.themeTheory = themeTheory;
    }
}
