package parzival.commands;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.NoExistCollectionException;
import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.creators.CreateGroup;
import parzival.models.StudyGroup;

/**
 * Команда update id - обновить значение элемента коллекции, id которого равен заданному
 *
 * @author petrovviacheslav
 */
public class UpdateId extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Show
     *
     * @param collectionManager менеджер коллекции
     * @param console           консоль
     */
    public UpdateId(CollectionManager collectionManager, Console console) {
        super(console, "update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Заменить элемент по id
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongCommandArgsException();

            long id = Long.parseLong(args[1].substring(0, args[1].length() - 1));
            StudyGroup studyGroup = collectionManager.getById(id);
            if (studyGroup == null) throw new NoExistCollectionException();
            collectionManager.updateElement(studyGroup, (new CreateGroup(console)).make());
            console.println("Группа была заменена успешно");
            return true;

        } catch (WrongCommandArgsException | IncorrectInputException | IncorrectScriptException |
                 NoExistCollectionException e) {
            console.printerror(e.toString());
            return false;
        }
    }
}
