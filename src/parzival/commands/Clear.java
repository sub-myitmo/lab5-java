package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

public class Clear extends Command {

    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager, Console console) {
        super(console, "clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию
     */
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