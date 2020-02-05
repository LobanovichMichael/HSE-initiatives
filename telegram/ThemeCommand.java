package ru.misha.telegram;

public class ThemeCommand implements Command {

    String chatID;
    String messageText;

    public ThemeCommand(String chatID, String messageText) {
        this.chatID = chatID;
        this.messageText = messageText;
    }

    @Override
    public String Run() {
        Data.themeMemory.put(chatID, messageText);
        String botMessage = "";
        botMessage = botMessage+"1.Теория"+"%0A"+"2.Тест"+"%0A"+"3.Задача";
        return botMessage;
    }

}
