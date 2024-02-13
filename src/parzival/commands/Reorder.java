package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;

public class Reorder extends Command{
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Reorder
     * @param collectionManager менеджер коллекции.
     */
    public Reorder(CollectionManager collectionManager, Console console) {
        super(console, "reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
        this.collectionManager = collectionManager;
    }

    /**
     * Сортирует коллекцию в обратном порядке
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try{
            if(!args[1].isEmpty()) throw new WrongCommandArgsException();
            collectionManager.reorder();
            console.println("Коллекция отсортирована в обратном порядке");
            return true;

        } catch (WrongCommandArgsException e){
            console.println(e.toString());
            return false;
        }
    }
}
