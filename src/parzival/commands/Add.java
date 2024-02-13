package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.creators.CreateGroup;

public class Add extends Command{
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;
    private Console console;
    private ProcessingOfInputData processingOfInputData;

    /**
     * Конструктор класса Add
     * @param collectionManager менеджер коллекции.
     */
    public Add(CollectionManager collectionManager, ProcessingOfInputData processingOfInputData, Console console) {
        super(console, "add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.processingOfInputData = processingOfInputData;
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

            collectionManager.addElementToCollection((new CreateGroup(collectionManager, console)).make());

            console.println("Группа была создана успешно");
            return true;

        } catch (WrongCommandArgsException e){
            console.println(e.toString());
            return false;
        }
    }
}
