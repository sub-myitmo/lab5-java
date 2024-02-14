package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
/**
 * Команда remove_first - удалить первый элемент из коллекции
 *
 * @author petrovviacheslav
 */
public class RemoveFirst extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса RemoveFirst
     * @param collectionManager менеджер коллекции
     * @param console консоль
     */
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
