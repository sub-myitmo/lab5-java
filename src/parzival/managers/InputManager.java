package parzival.managers;


import parzival.commands.Command;
import parzival.managers.creators.CreateGroup;

import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * Сканер для чтения пользовательского ввода/ввода из файла
     */
    private static Scanner usedScanner;
//    private final String startInput = "-> ";
//    private final String startInputForScript = "(from script)-> ";


    public InputManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Установить новый сканер
     *
     * @param scanner  новый сканер для использования
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

        try {
            String[] scriptCommand;
            int status = 1;
            Scanner scriptScanner = new Scanner(new File(fileName));
            Scanner oldScanner = usedScanner;
            usedScanner = scriptScanner;

            StatusScript.setIsScriptRun();
            while (usedScanner.hasNext() && status != 2) {

                console.printf("(from script)-> ");

                String gettingString = usedScanner.nextLine();
                scriptCommand = (gettingString.trim() + " ").split(" ", 2);
                console.println(gettingString);

                status = executeCommand(scriptCommand);
            }
            StatusScript.deleteIsScriptRun();
            usedScanner = oldScanner;

        } catch (
                FileNotFoundException exception) {
            console.println("Файл со скриптом не найден!");
        } catch (
                NoSuchElementException exception) {
            console.println("Весь файл прочитан!");
        } catch (
                IllegalStateException exception) {
            console.println("Непредвиденная ошибка.");
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
                console.printf("-> ");

                userCommand = (usedScanner.nextLine().trim() + " ").split(" ", 2);

                status = executeCommand(userCommand);
            } while (status != 2);
        } catch (NoSuchElementException exception) {
            console.println("Работа программы прекращена!");
        } catch (IllegalStateException exception) {
            console.println("Непредвиденная ошибка!");
        }

    }

    // 0 - успешно
    // 1 - ошибка
    // 2 - exit

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
            console.println("Команда '" + argsCommand[0] + "' не найдена, используйте команду 'help', чтобы вывести справку");
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
