package ru.misha.telegram;

public class UnknownCommand implements Command {
    @Override
    public String Run() {
        String unknown = "К сожалению, команда не найдена. Попробуйте еще раз.";
        return unknown;
    }
}
