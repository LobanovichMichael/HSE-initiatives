package ru.misha.telegram;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class servlet {

    CommandParser parser = new CommandParser();

    public static void main(String[] args) throws IOException {

        servlet s = new servlet();

    }

    public void handleRequest(String text, String chatID) {
        Command command = parser.Parse(text.toLowerCase(), chatID);
        String botMssage = command.Run();
        showResult(chatID, botMssage);
    }

    public void parse(String telegramString) {


        String command = "";
        String textMessage = "";

        JsonElement root = new JsonParser().parse(telegramString);

        System.out.println(root.getAsJsonObject().get("message").getAsJsonObject().get("text").getAsString());

        String text = (root.getAsJsonObject().get("message").getAsJsonObject().get("text").getAsString());
        int chatID = (root.getAsJsonObject().get("message").getAsJsonObject().get("chat").getAsJsonObject().get("id").getAsInt());
        if (text.isEmpty()) {
            System.out.println("Text is empty");
            return;
        }
            handleRequest(text, chatID+"");
    }


    public static void sendRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        long length = urlConnection.getContentLengthLong();
        if (length == -1)
            System.out.println("Длина содержимого недоступна");
        else
            System.out.println("Длина содержимого: " + length);

        if (length != 0) {
            System.out.println("=== Содержимое ===");
            InputStream input;
            try {
                input = urlConnection.getInputStream();
            } catch (Exception e) {
                input = urlConnection.getErrorStream();
            }
            System.out.println("Response encoding: " + urlConnection.getContentEncoding());
            System.out.println("Response code: " + urlConnection.getResponseCode());

            input.close();
        }
    }

    public static void showResult(String chatID, String botMessage){
        try {
            sendRequest("https://api.telegram.org/bot734766672:AAFP5wNwBOiiqpvMPp5MQS9Wu8KbG5q3LUQ/sendMessage?chat_id=" + chatID + "&text=" + botMessage);
        } catch (IOException e) {
          e.printStackTrace();
        }

    }

}
