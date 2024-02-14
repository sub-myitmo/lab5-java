package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

/**
 * Команда clear - очищает коллекцию
 *
 * @author petrovviacheslav
 */
public class Clear extends Command {

    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Clear
     * @param collectionManager менеджер коллекции
     * @param console консоль
     */
    public Clear(CollectionManager collectionManager, Console console) {
        super(console, "clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.clearCollection();
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }

}