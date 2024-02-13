package parzival.commands;

import parzival.exceptions.NoExistCollectionException;
import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

public class RemoveById extends Command {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager, Console console) {
        super(console, "remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент из коллекции по его id
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args[1].isEmpty() || args.length > 2) throw new WrongCommandArgsException();

            long id = Long.parseLong(args[1].substring(0, args[1].length()-1));
            if (collectionManager.getById(id) == null) throw new NoExistCollectionException();
            collectionManager.removeGroup(collectionManager.getById(id));

            console.println("Организация была успешно удалена");
            return true;

        } catch (WrongCommandArgsException | NoExistCollectionException e) {
            console.println(e.toString());
            return false;
        }

    }
}
