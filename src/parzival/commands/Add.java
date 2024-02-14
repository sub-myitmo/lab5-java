package parzival.commands;


import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.creators.CreateGroup;

import java.util.Scanner;

public class Add extends Command{
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;
    private Console console;
    private Scanner userScanner;

    /**
     * Конструктор класса Add
     * @param collectionManager менеджер коллекции.
     */
    public Add(CollectionManager collectionManager, Scanner userScanner, Console console) {
        super(console, "add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Добавить новый элемент в коллекцию
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try{
            if(!args[1].isEmpty()) throw new WrongCommandArgsException();

            collectionManager.addElementToCollection((new CreateGroup(collectionManager, userScanner, console)).make());

            console.println("Группа была создана успешно");
            return true;

        } catch (WrongCommandArgsException | IncorrectInputException | IncorrectScriptException e){
            console.println(e.toString());
            return false;
        }
    }
}
