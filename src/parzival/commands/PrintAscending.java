package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.StudyGroup;

import java.util.Collections;
import java.util.Stack;

/**
 * Команда print_ascending - вывести элементы коллекции в порядке возрастания
 *
 * @author petrovviacheslav
 */
public class PrintAscending extends Command{
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса PrintAscending
     *
     * @param collectionManager менеджер коллекции
     * @param console консоль
     */
    public PrintAscending(CollectionManager collectionManager, Console console) {
        super(console, "print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит элементы коллекции в порядке возрастания
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            Stack<StudyGroup> copyOfStackCollection = collectionManager.getStackCollection();
            Collections.sort(copyOfStackCollection);
            for(StudyGroup studyGroup : copyOfStackCollection){
                console.println(studyGroup.toString());
            }
            return true;
        } catch (WrongCommandArgsException e){
            console.println(e.toString());
            return false;
        }

    }
}
