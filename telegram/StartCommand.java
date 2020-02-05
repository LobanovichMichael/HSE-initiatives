package ru.misha.telegram;

public class StartCommand implements Command{

    @Override
    public String Run() {
        String botMessage = "";
        int i = 0;
        while (i < Data.themes.size()) {
            botMessage = botMessage + (i + 1) + ". " + Data.themes.get(i).themeName + "%0A";
            i++;
        }
        return botMessage;
    }
}
