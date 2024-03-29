package parzival;

import parzival.commands.*;
import parzival.managers.*;
import parzival.models.StudyGroup;


import java.util.Scanner;
import java.util.Stack;

/**
 * Главный класс приложения, он запускается в первую очередь
 *
 * @author petrovviacheslav
 */
public class Main {


    /**
     * Запуск приложения
     *
     * @param args аргументы запуска
     */
    public static void main(String[] args) {

        System.out.println("Приветствую!\nЧтение данных и запуск консольного приложения ...");

        String fileName = FileManager.getName();

        Console console = new Console();
        FileManager fileManager = new FileManager(console);

        if (fileName == null) {
            System.out.println("У вас отсутствует переменная окружения 'LAB5_DATA'!");
            System.exit(0);
        }
        ParseManager parseManager = new ParseManager(console);
        Stack<StudyGroup> startStack = parseManager.getStackFromJson(fileManager.readFromFile(fileName));

        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setCollection(startStack);
        StudyGroup.updateNextId(collectionManager);


        Scanner userScanner = new Scanner(System.in);


        CommandManager commandManager = new CommandManager() {{
            addCommand("help", new Help(this, console));
            addCommand("info", new Info(collectionManager, console));
            addCommand("show", new Show(collectionManager, console));
            addCommand("clear", new Clear(collectionManager, console));
            addCommand("remove_by_id", new RemoveById(collectionManager, console));
            addCommand("remove_first", new RemoveFirst(collectionManager, console));
            addCommand("shuffle", new Shuffle(collectionManager, console));
            addCommand("reorder", new Reorder(collectionManager, console));
            addCommand("print_ascending", new PrintAscending(collectionManager, console));
            addCommand("print_field_ascending_students_count", new PrintFieldAscendingStudentsCount(collectionManager, console));
            addCommand("save", new Save(fileManager, parseManager, collectionManager, console));
        }};

        InputManager inputManager = new InputManager(console, commandManager);
        InputManager.setUsedScanner(userScanner);

        StatusScript statusScript = inputManager.getStatusScript();

        commandManager.addCommand("execute_script", new ExecuteScript(inputManager, console));
        commandManager.addCommand("add", new Add(collectionManager, console, statusScript));
        commandManager.addCommand("update", new UpdateId(collectionManager, console, statusScript));

        commandManager.addCommand("exit", new Exit(console, statusScript));

        inputManager.interactiveRun();

    }
}