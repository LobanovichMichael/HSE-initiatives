package ru.misha.telegram;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskCommand implements Command {

    String chatID;
    String messageText;

    public TaskCommand(String chatID, String messageText) {
        this.chatID = chatID;
        this.messageText = messageText;
    }

    @Override
    public String Run() {
        int taskNumber;
        String botMessage = "";

        if (messageText.isEmpty()) {
            String numberString = Data.themeMemory.get(chatID);
            int numberOfTheme = Integer.valueOf(numberString);
            taskNumber = getNextTaskNumber(numberOfTheme);
            if (taskNumber == -1) {
                Data.lastCommandMemory.put(chatID, "");
                return "Поздравляем! Вы решили все задачи, ждите обновления.";
            }
            Data.taskMemory.put(chatID,taskNumber);
            botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTasks.get(taskNumber).taskName + "%0A";
            botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTasks.get(taskNumber).taskText;
        } else {
            taskNumber = Data.taskMemory.get(chatID);
            String numberString = Data.themeMemory.get(chatID);
            int numberOfTheme = Integer.valueOf(numberString);
            if (messageText.equalsIgnoreCase(Data.themes.get(numberOfTheme - 1).themeTasks.get(taskNumber).taskAnswer)){
                botMessage = botMessage + "правильный ответ";
                ArrayList<Integer> solvedList = Data.solvedTasks.get(chatID).get(numberOfTheme - 1);
                solvedList.add(taskNumber);
                Data.lastCommandMemory.put(chatID,"");
            } else {
                botMessage = botMessage + "попробуй еще раз";
            }
        }
        return botMessage;
    }
    int getNextTaskNumber(int numberOfTheme) {
        if (Data.solvedTasks.get(chatID) == null) {
            Data.solvedTasks.put(chatID, new HashMap<Integer, ArrayList<Integer>>());
        }
        ArrayList <Integer> solvedTasksList = Data.solvedTasks.get(chatID).get(numberOfTheme - 1);

        if (solvedTasksList == null) {
            solvedTasksList = new ArrayList<Integer>();
            Data.solvedTasks.get(chatID).put(numberOfTheme - 1, solvedTasksList);
        }

        ArrayList <Task> thisThemeTests = Data.themes.get(numberOfTheme - 1).themeTasks;
        if (solvedTasksList.size() >= thisThemeTests.size()) {
            return -1;
        }
        int taskNumber = Randomizer.getRandomInt(0, Data.themes.get(numberOfTheme - 1).themeTasks.size());
        if (solvedTasksList.contains(taskNumber)){
            while (solvedTasksList.contains(taskNumber)) {
                taskNumber ++;
                if (thisThemeTests.size() == taskNumber) {
                    taskNumber = 0;
                }
            }
        }
        return taskNumber;
    }
}
