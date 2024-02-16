package parzival.managers;


import parzival.commands.Command;
import parzival.exceptions.EmptyFileException;
import parzival.exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс InputManager для обработки ввода и запуска режимов работы
 *
 * @author petrovviacheslav
 */
public class InputManager {
    private final Console console;
    private final CommandManager commandManager;

    private ArrayList<String> filesNamesList = new ArrayList<>();

    /**
     * Сканер для чтения пользовательского ввода/ввода из файла
     */
    private static Scanner usedScanner;

    /**
     * Конструктор класса InputManager
     *
     * @param commandManager менеджер команд
     * @param console        консоль
     */
    public InputManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Установить новый сканер
     *
     * @param scanner новый сканер для использования
     */
    public static void setUsedScanner(Scanner scanner) {
        usedScanner = scanner;
    }

    /**
     * Получить используемый сейчас сканер
     *
     * @return используемый сейчас сканер
     */
    public static Scanner getUsedScanner() {
        return usedScanner;
    }


    /**
     * Запуск скрипта из файла
     *
     * @param fileName путь до файла со скриптом
     */
    public void scriptRun(String fileName) {

        Scanner oldScanner = usedScanner;

        try {

            String[] scriptCommand;
            int status = 1;

            usedScanner = new Scanner(new File(fileName));

            if (filesNamesList.isEmpty()) StatusScript.setIsScriptRun();

            filesNamesList.add(fileName);

            if (!usedScanner.hasNext()) throw new EmptyFileException();

            while (usedScanner.hasNext() && status != 2) {

                console.printf(String.format("script %s-> ~ ", fileName));

                String gettingString = usedScanner.nextLine();
                scriptCommand = (gettingString.trim() + " ").split(" ", 2);
                console.println(gettingString);

                if (scriptCommand[0].equals("execute_script")) {
                    if (filesNamesList.contains(scriptCommand[1])) throw new ScriptRecursionException();
                }

                status = executeCommand(scriptCommand);
            }

            filesNamesList.remove(fileName);
            if (filesNamesList.isEmpty()) StatusScript.deleteIsScriptRun();

        } catch (
                ScriptRecursionException | EmptyFileException exception) {
            console.printerror(exception.toString());
        } catch (
                FileNotFoundException exception) {
            console.printerror("Файл со скриптом не найден!");
        } catch (
                NoSuchElementException exception) {
            console.println("");
            console.printerror("Весь файл " + fileName + "прочитан!");
        } catch (
                IllegalStateException exception) {
            console.println("");
            console.printerror("Непредвиденная ошибка.");
        } finally {
            StatusScript.deleteIsScriptRun();
            usedScanner = oldScanner;
        }


    }

    /**
     * Запуск интерактивного режима
     */
    public void interactiveRun() {
        try {
            String[] userCommand;
            int status;
            do {
                console.printf("~ ");

                userCommand = (usedScanner.nextLine().trim() + " ").split(" ", 2);

                status = executeCommand(userCommand);
            } while (status != 2);
        } catch (NoSuchElementException exception) {
            console.println("");
            console.printerror("Работа программы прекращена!");
        } catch (IllegalStateException exception) {
            console.println("");
            console.printerror("Непредвиденная ошибка!");
        }

    }

    /**
     * Метод для обработки команды и запуска её выполнения
     *
     * @param argsCommand полученная команда
     * @return 0 - успешно, 1 - ошибка, 2 - exit
     */
    private int executeCommand(String[] argsCommand) {
        if (argsCommand[0].isEmpty()) return 0;

        Command command = commandManager.getCommands().get(argsCommand[0]);
        if (command == null) {
            console.printerror("Команда '" + argsCommand[0] + "' не найдена, используйте команду 'help', чтобы вывести справку");
            return 1;
        }

        if (argsCommand[0].equals("exit")) {
            if (command.execute(argsCommand)) return 2;
            else return 1;
        } else if (!command.execute(argsCommand))
            return 1;
        return 0;

    }
}
