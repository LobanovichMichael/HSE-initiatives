package ru.misha.telegram;

import java.util.ArrayList;
import java.util.HashMap;

public class TestCommand implements Command {
    String chatID;
    String messageText;

    public TestCommand(String chatID, String messageText) {
        this.chatID = chatID;
        this.messageText = messageText;
    }

    @Override
    public String Run() {
        String botMessage = "";
        String numberString = Data.themeMemory.get(chatID);
        int numberOfTheme = Integer.valueOf(numberString);
        if (messageText.isEmpty()) { //в этом if-е происходит выбор рандомного теста и выдача первого вопроса
            int testNumber = getNextTestNumber(numberOfTheme);
            if (testNumber == -1) {
                Data.lastCommandMemory.put(chatID, "");
                return "Поздравляем! Вы решили все тесты, ждите обновления.";
            }
            Data.testMemory.put(chatID,testNumber);
            int i = 0;
            Data.testAnswersMemory.put(chatID, new ArrayList<String>());
            Data.questionNumberMemory.put(chatID, i);
            botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTests.get(testNumber).questions.get(i).questionText;
        } else { //все остальные вопросы
            int testNumber = Data.testMemory.get(chatID);
            int questionNumber = Data.questionNumberMemory.get(chatID); //todo сохранить ответ куда нибудь
            questionNumber++;
            ArrayList<String> answers = Data.testAnswersMemory.get(chatID);
            answers.add(messageText);
            ArrayList <Question> questions = Data.themes.get(numberOfTheme - 1).themeTests.get(testNumber).questions;
            if (questionNumber >= questions.size()){  // номер вопроса больше, чем размер списка вопросов - конец теста
                Data.lastCommandMemory.put(chatID, ""); // чистим память, чтобы при вводе следующей команды бот не думал, что мы все еще в тесте
                String answersForEnd = "Ваши ответы   Правильные ответы %0A";//создаем строку для формирования таблицы правильных ответов и ответов пользователя
                for (int i = 0; i <questions.size(); i++) {
                    String thisQuestionTrueAnswer = questions.get(i).questionAnswer;
                    String thisQuestionUsersAnswer = answers.get(i);
                    answersForEnd +="     " + thisQuestionUsersAnswer +"                    "+ thisQuestionTrueAnswer + "%0A";
                }

                ArrayList<Integer> solvedList = Data.solvedTests.get(chatID).get(numberOfTheme - 1);
                solvedList.add(testNumber);

                return answersForEnd;
            }
            Data.questionNumberMemory.put(chatID, questionNumber);
            botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTests.get(testNumber).questions.get(questionNumber).questionText;
        }
        return botMessage;
    }
    int getNextTestNumber(int numberOfTheme) {

        if (Data.solvedTests.get(chatID) == null) {
            Data.solvedTests.put(chatID, new HashMap<Integer, ArrayList<Integer>>());
        }
        ArrayList <Integer> solvedTestsList = Data.solvedTests.get(chatID).get(numberOfTheme - 1);

        if (solvedTestsList == null) {
            solvedTestsList = new ArrayList<Integer>();
            Data.solvedTests.get(chatID).put(numberOfTheme - 1, solvedTestsList);
        }

        ArrayList <Test> thisThemeTests = Data.themes.get(numberOfTheme - 1).themeTests;
        if (solvedTestsList.size() >= thisThemeTests.size()) {
            return -1;
        }
        int testNumber = Randomizer.getRandomInt(0, Data.themes.get(numberOfTheme - 1).themeTests.size());
        if (solvedTestsList.contains(testNumber)){
            while (solvedTestsList.contains(testNumber)) {
                testNumber ++;
                if (thisThemeTests.size() == testNumber) {
                    testNumber = 0;
                }
            }
        }
        return testNumber;
    }
}
