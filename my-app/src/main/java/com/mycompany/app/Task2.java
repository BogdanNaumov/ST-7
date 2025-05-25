package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void main(String[] args) {
        // Укажите путь к вашему chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bogda\\OneDrive\\Рабочий стол\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try {
            // Обращаемся к сервису получения IP
            webDriver.get("https://api.ipify.org/?format=json");

            // Получаем весь текст JSON из элемента <pre> или body
            WebElement elem = webDriver.findElement(By.tagName("body"));
            String json_str = elem.getText();

            // Парсим JSON-строку
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);

            // Извлекаем IP и выводим
            String ip = (String) obj.get("ip");
            System.out.println("Ваш IP-адрес: " + ip);
        } catch (Exception e) {
            System.out.println("Ошибка:");
            System.out.println(e.toString());
        } finally {
            // Закрываем браузер
            webDriver.quit();
        }
    }
}
