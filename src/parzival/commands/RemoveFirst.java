package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

public class RemoveFirst extends Command {
    private CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager, Console console) {
        super(console, "remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет первый элемент из коллекции
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.removeFirstElementCollection();
            console.println("Первая группа была успешно удалена");
            return true;
        } catch (ArrayIndexOutOfBoundsException | WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }
    }
}
