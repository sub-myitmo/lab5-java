package parzival.managers;

import parzival.managers.Console;
import parzival.models.StudyGroup;

import java.io.*;
import java.util.Stack;


/**
 * Класс FileManager для записи коллекции в файл и чтение из файла
 */
public class FileManager {
    public static String path;
    private Console console;

    public FileManager(Console console){
        this.console = console;
    }

    /**
     * Чтение текста из файла
     *
     * @param fileName имя файла
     * @return String текст из файла
     */
    public String readFromFile(String fileName) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
            String stringFile = "";
            int symbolNow = inputStreamReader.read();
            while (symbolNow != -1) {
                stringFile += String.valueOf((char) symbolNow);
                symbolNow = inputStreamReader.read();
            }
            inputStreamReader.close();
            return stringFile;
        }
        catch (IOException e) {
            console.println("Json-файл не найден.");
            return "ошибка при чтении файла!";
        }
    }

    /**
     * Запись текста в файл
     *
     * @param fileName имя файла
     * @param collection коллекция для файла
     */
    public void writeToFile(String fileName, String text) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
            outputStreamWriter.close();
        } catch (IOException e) {
            console.println("ошибка при записи файла!");
        }
    }

    /**
     * Метод для получения имени файла из переменной окружения
     *
     * @return имя файла
     */
    public String getName() {
        path = System.getenv("LAB5_DATA");
        return path;
    }
}