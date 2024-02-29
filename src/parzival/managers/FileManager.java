package parzival.managers;

import parzival.exceptions.NotEnoughRightsReadException;
import parzival.exceptions.NotEnoughRightsWriteException;

import java.io.*;


/**
 * Класс FileManager для записи коллекции в файл и чтение из файла
 */
public class FileManager {
    /**
     * Переменная хранящая путь до начального файла .json
     */
    public static String path;
    private final Console console;

    /**
     * Конструктор класса CreateSemester
     *
     * @param console консоль
     */
    public FileManager(Console console) {
        this.console = console;
    }

    /**
     * Чтение текста из файла
     *
     * @param fileName имя файла
     * @return String текст из файла
     */
    public String readFromFile(String fileName) {
        var filePath = new File(fileName);
        InputStreamReader inputStreamReader;
        try {

            if (!filePath.canRead()) throw new NotEnoughRightsReadException();
            if (!filePath.canWrite()) console.println("Внимание! Вы не сможете использовать команду save!");

            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
            StringBuilder stringFile = new StringBuilder();
            int symbolNow = inputStreamReader.read();
            while (symbolNow != -1) {
                stringFile.append(((char) symbolNow));
                symbolNow = inputStreamReader.read();
            }
            inputStreamReader.close();
            return stringFile.toString();
        } catch (NotEnoughRightsReadException e) {
            console.printerror(e + " Коллекция пуста!");
            return "";
        } catch (IOException e) {
            console.printerror("Json-файл не найден. Коллекция пуста!");
            return "";
        }
    }

    /**
     * Запись текста в файл
     *
     * @param fileName имя файла
     * @param text     текст для файла
     */
    public void writeToFile(String fileName, String text) {
        try {
            var filePath = new File(fileName);
            if (!filePath.canWrite()) throw new NotEnoughRightsWriteException();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
            console.println("Коллекция была успешно сохранена");
            outputStreamWriter.close();
        } catch (NotEnoughRightsWriteException e) {
            console.println(e.toString());
        } catch (IOException e) {
            console.println("ошибка при записи файла!");
        }
    }

    /**
     * Метод для получения имени файла из переменной окружения
     *
     * @return имя файла
     */
    public static String getName() {
        path = System.getenv("LAB5_DATA");
        return path;
    }
}