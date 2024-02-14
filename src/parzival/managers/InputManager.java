package parzival.managers;


import parzival.commands.Command;
import parzival.managers.creators.CreateGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    /**
     * Сканер для чтения пользовательского ввода.
     */
    private static Scanner userScanner;
    public static final String startInput = "-> ";

    public InputManager(Console console, CollectionManager collectionManager, CommandManager commandManager) {
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public static void setUserScanner(Scanner scanner){
        userScanner = scanner;
    }
    public static Scanner getUserScanner(){
        return userScanner;
    }


    /**
     * Запуск скрипта из файла
     */
    public void scriptRun(String fileName){

        try {
            String[] scriptCommand;
            int status;
            Scanner scriptScanner = new Scanner(new File(fileName));
            Scanner oldScanner = userScanner;
            userScanner = scriptScanner;

            CreateGroup.setIsScriptRun();
            while (userScanner.hasNext()) {
                console.printf(startInput);

                String gettingString = userScanner.nextLine();
                scriptCommand = (gettingString.trim() + " ").split(" ", 2);
                console.println(gettingString);

                status = executeCommand(scricdptCommand);
            } //while (status != 2);
            CreateGroup.deleteIsScriptRun();
            userScanner = oldScanner;
        } catch (FileNotFoundException exception) {
            console.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            console.println("Весь файл прочитан!");
        } catch (IllegalStateException exception) {
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
                console.printf(startInput);

                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);

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
    private int executeCommand(String[] userCommand) {
        if (userCommand[0].isEmpty()) return 0;

        Command command = commandManager.getCommands().get(userCommand[0]);
        if (command == null) {
            console.println("Команда '" + userCommand[0] + "' не найдена, используйте команду 'help', чтобы вывести справку");
            return 1;
        }

        if (userCommand[0].equals("exit")) {
            if (command.execute(userCommand)) return 2;
            else return 1;
        }
        else if (!command.execute(userCommand))
            return 1;
        return 0;

    }
}
