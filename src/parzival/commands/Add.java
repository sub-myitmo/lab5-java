package parzival.commands;


import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.StatusScript;
import parzival.managers.creators.CreateGroup;


/**
 * Команда add - добавить новый элемент в коллекцию
 *
 * @author petrovviacheslav
 */
public class Add extends Command {
    /**
     * Менеджер коллекции
     */
    private CollectionManager collectionManager;
    private StatusScript statusScript;

    /**
     * Конструктор класса Add
     *
     * @param collectionManager менеджер коллекции
     * @param console           консоль
     * @param statusScript состояние скрипта
     */
    public Add(CollectionManager collectionManager, Console console, StatusScript statusScript) {
        super(console, "add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.statusScript = statusScript;
    }

    /**
     * Добавить новый элемент в коллекцию
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();

            collectionManager.addElementToCollection((new CreateGroup(console, statusScript.getIsScriptRun())).create());

            console.println("Группа была создана успешно");
            return true;

        } catch (WrongCommandArgsException | IncorrectInputException | IncorrectScriptException e) {
            console.printerror(e.toString());
            return false;
        }
    }
}
