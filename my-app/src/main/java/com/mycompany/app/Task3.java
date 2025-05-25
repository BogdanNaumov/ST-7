package com.mycompany.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task3 {
    public static void main(String[] args) {
        try {
            String urlString = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder jsonResponse = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                jsonResponse.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse.toString());

            JSONObject hourly = (JSONObject) jsonObject.get("hourly");
            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");

            System.out.printf("| %-3s | %-16s | %-11s | %-12s |\n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("|-----|------------------|-------------|--------------|");

            for (int i = 0; i < timeArray.size(); i++) {
                String time = (String) timeArray.get(i);
                double temperature = (double) tempArray.get(i);
                double rain = (double) rainArray.get(i);
                System.out.printf("| %-3d | %-16s | %-11.1f | %-12.2f |\n", i + 1, time, temperature, rain);
            }

        } catch (Exception e) {
            System.out.println("Произошла ошибка:");
            e.printStackTrace();
        }
    }
}
