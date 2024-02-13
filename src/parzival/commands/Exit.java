package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

/**
 * Команда exit - завершить программу (без сохранения в файл)
 * @author petrovviacheslav
 */
public class Exit extends Command{
    private CollectionManager collectionManager;

    public Exit(Console console) {
        super(console, "exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Очищает коллекцию
     */
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            console.println("Звершение выполнения");
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }
}
