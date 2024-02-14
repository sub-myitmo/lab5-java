package parzival.commands;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.NoExistCollectionException;
import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.creators.CreateGroup;
import parzival.models.StudyGroup;

import java.util.Scanner;

public class UpdateId extends Command {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;
    private Console console;

    /**
     * Конструктор класса Add
     * @param collectionManager менеджер коллекции.
     */
    public UpdateId(CollectionManager collectionManager, Console console) {
        super(console, "update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Заменить элемент по id
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try{
            if(args[1].isEmpty()) throw new WrongCommandArgsException();

            long id = Long.parseLong(args[1].substring(0, args[1].length()-1));
            StudyGroup studyGroup = collectionManager.getById(id);
            if (studyGroup == null) throw new NoExistCollectionException();
            collectionManager.updateElement(studyGroup, (new CreateGroup(collectionManager, console)).make());
            console.println("Группа была заменена успешно");
            return true;

        } catch (WrongCommandArgsException | IncorrectInputException | IncorrectScriptException | NoExistCollectionException e){
            console.println(e.toString());
            return false;
        }
    }
}