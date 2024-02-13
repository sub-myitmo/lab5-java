package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

/**
 * Команда info - вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор
     * @param collectionManager менеджер коллекции
     */
    public Info(CollectionManager collectionManager, Console console) {
        super(console, "info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит информацию о коллекции
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.printInfoAboutCollection(console);
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }
    }
}