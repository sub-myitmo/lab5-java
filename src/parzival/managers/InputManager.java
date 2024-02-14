package parzival.managers;


import parzival.commands.Command;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private final Console console;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    /**
     * Сканер для чтения пользовательского ввода.
     */
    private final Scanner userScanner;

    public static final String startInput = "-> ";

    public InputManager(Console console, CollectionManager collectionManager, CommandManager commandManager, Scanner userScanner) {
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.userScanner = userScanner;
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
            console.println("Работа программы прекращена.");
        } catch (IllegalStateException exception) {
            console.println("Непредвиденная ошибка.");
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
