package ru.misha.telegram;

public class HintCommand implements Command{
    String chatID;
    String messageText;

    public HintCommand(String chatID, String messageText) {
        this.chatID = chatID;
        this.messageText = messageText;
    }

    @Override
    public String Run() {
        String botMessage = "";
        int taskNumber = Data.taskMemory.get(chatID);
        String numberString = Data.themeMemory.get(chatID);
        int numberOfTheme = Integer.valueOf(numberString);
        botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTasks.get(taskNumber).taskHint;
        return botMessage;
    }
}
