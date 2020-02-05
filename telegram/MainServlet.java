package ru.misha.telegram;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        out.print("qwertyuoip[[");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("*******####################**********************");
        System.out.println("out test POST");
        PrintWriter out = resp.getWriter();

        String body = extractPostRequestBody(req);

        System.out.println(body);

        new servlet().parse(body);

        out.print("{jbPOSTPOST }");

    }

    static String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }


    public static String convert(InputStream inputStream, String charset) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } finally {
            reader.close();
        }

        return stringBuilder.toString();
    }

}