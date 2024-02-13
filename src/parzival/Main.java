package parzival;

import parzival.commands.*;
import parzival.managers.*;
import parzival.models.StudyGroup;



import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        System.out.println("Приветствую!\nЧтение данных и запуск консольного приложения ...");

        Console console = new Console();

        FileManager fileManager = new FileManager(console);

        String fileName = fileManager.getName();

        //System.out.println(System.getenv());
        //String fileName = "data.json";

        if (fileName == null) {
            System.out.println("Запуск с переменной окружения 'LAB5_DATA'!");
            System.exit(0);
        }
        ParseManager parseManager = new ParseManager(console);
        Stack<StudyGroup> startStack = parseManager.getStackFromJson(fileManager.readFromFile(fileName));

        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setCollection(startStack);

        Scanner userScanner = new Scanner(System.in);

        CommandManager commandManager = new CommandManager() {{
            addCommand("help", new Help(this, console));
            addCommand("info", new Info(collectionManager, console));
            addCommand("show", new Show(collectionManager, console));
            addCommand("clear", new Clear(collectionManager, console));
            addCommand("exit", new Exit(console));
            addCommand("remove_by_id", new RemoveById(collectionManager, console));
            addCommand("remove_first", new RemoveFirst(collectionManager, console));
            addCommand("shuffle", new Shuffle(collectionManager, console));
            addCommand("reorder", new Reorder(collectionManager, console));
            addCommand("print_ascending", new PrintAscending(collectionManager, console));
            addCommand("print_field_ascending_students_count", new PrintFieldAscendingStudentsCount(collectionManager, console));
            addCommand("save", new Save(fileManager, parseManager, collectionManager, console));
            addCommand("add {element}", new Add(collectionManager, userScanner, console));
        }};

        InputManager inputManager = new InputManager(console, collectionManager, commandManager, userScanner);
        inputManager.interactiveRun();

    }
}