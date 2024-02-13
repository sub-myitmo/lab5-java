

package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

/**
 * Команда show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор
     * @param collectionManager менеджер коллекции.
     */
    public Show(CollectionManager collectionManager, Console console) {
        super(console, "show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит на экран пользователя все группы
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.printAllElements(console);
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }
}