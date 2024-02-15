package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

/**
 * Команда shuffle - перемешать элементы коллекции в случайном порядке
 *
 * @author petrovviacheslav
 */
public class Shuffle extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Shuffle
     *
     * @param collectionManager менеджер коллекции
     * @param console           консоль
     */
    public Shuffle(CollectionManager collectionManager, Console console) {
        super(console, "shuffle", "перемешать элементы коллекции в случайном порядке");
        this.collectionManager = collectionManager;
    }

    /**
     * Перемешивает коллекцию
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.shuffle();
            console.println("Коллекция была перемешана");
            return true;

        } catch (WrongCommandArgsException e) {
            console.printerror(e.toString());
            return false;
        }
    }
}
