package ru.misha.telegram;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

public class CommandParser {

    public Command Parse(String text, String chatID) {
        String command = "";
        String textMessage = "";
        // парсим текст команды и текст сообщения после команды
        int numOfSpace = text.indexOf(" "); // return -1 if no space symbol in text
        if (text.charAt(0) == '/') {
            if (numOfSpace == -1) {
                command = text.substring(1, text.length());
            } else {
                command = text.substring(1, numOfSpace);
                textMessage = text.substring(numOfSpace + 1, text.length());
            }
        } else {
            textMessage = text;
        }
        System.out.println("команда = " + command);
        System.out.println("текстмессадж = " + textMessage);
        Command cmd;
        String lastCommand = Data.lastCommandMemory.get(chatID);


        if (command.equals("start")) {
            cmd = new StartCommand();
        } else if (command.equals("theme") || lastCommand.equals("start")) {
            Data.lastCommandMemory.put(chatID, "theme");
            cmd = new ThemeCommand(chatID, textMessage);
        } else if (command.equals("theory") || (command.isEmpty() && textMessage.equals("1") && lastCommand.equals("theme"))) {
            Data.lastCommandMemory.put(chatID, "theory");
            cmd = new TheoryCommand(chatID);
        } else if (command.equals("solve")) {
            cmd = new SolveCommand(chatID, textMessage);
        } else if (command.equals("hint")) {
            cmd = new HintCommand(chatID, textMessage);
        } else if (command.equals("test") || lastCommand.equals("test")) {
            cmd = new TestCommand(chatID, textMessage);
        }else if (command.isEmpty() && textMessage.equals("2") && lastCommand.equals("theme")) { //Добавление возможности выброра теста не только при помощи команды, но и при помощи цифры, если перед этим выбрали тему
            Data.lastCommandMemory.put(chatID, "test");
            cmd = new TestCommand(chatID, ""); //добавляем пустой текстмессадж, так как на этом завязана логика выбора первого вопроса в тесте
        }else if (command.equals("task") || lastCommand.equals("task")) {
            cmd = new TaskCommand(chatID, textMessage);
        } else if (command.isEmpty() && textMessage.equals("3") && lastCommand.equals("theme")) {
            Data.lastCommandMemory.put(chatID, "task");
            cmd = new TaskCommand(chatID, ""); //добавляем пустой текстмессадж, так как на этом завязана логика выбора задачи
        } else {
            cmd = new UnknownCommand();
        }
        if (!command.isEmpty() && !command.equals("hint") && !command.equals("solve")) {
            Data.lastCommandMemory.put(chatID, command);
        }
        return cmd;
    }
}
