package ru.misha.telegram;

public class TheoryCommand implements Command {

    String chatID;


    public TheoryCommand(String chatID) {
        this.chatID = chatID;
    }

    @Override
    public String Run() {
        String botMessage = "";
        String numberString = Data.themeMemory.get(chatID);
        int number = Integer.valueOf(numberString);
        botMessage = botMessage + Data.themes.get(number - 1).themeTheory.theoryText;
        return botMessage;
    }
}
