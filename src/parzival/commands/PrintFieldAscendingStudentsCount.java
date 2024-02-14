package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.StudyGroup;

import java.util.*;

/**
 * Команда print_field_ascending_students_count - вывести значения поля studentsCount всех элементов в порядке возрастания
 *
 * @author petrovviacheslav
 */
public class PrintFieldAscendingStudentsCount extends Command {
    /**
     * Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Reorder
     *
     * @param collectionManager менеджер коллекции.
     * @param console консоль
     */
    public PrintFieldAscendingStudentsCount(CollectionManager collectionManager, Console console) {
        super(console, "print_field_ascending_students_count", "вывести значения поля studentsCount всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит значения поля studentsCount всех элементов в порядке возрастания
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();

            if (collectionManager.getStackCollection().isEmpty()) {
                console.println("Коллекция пуста!");
            } else {
                Set<Long> setStudentsCount = new HashSet<>();

                for (StudyGroup studyGroup : collectionManager.getStackCollection()) {
                    setStudentsCount.add(studyGroup.getStudentsCount());
                }

                ArrayList<Long> arrayList = new ArrayList<>(setStudentsCount);
                Collections.sort(arrayList);

                console.println("Поля studentsCount: ");
                StringBuilder studentsCountInSting = new StringBuilder();
                for (Long studentsCount : arrayList) {
                    studentsCountInSting.append(" - ").append(studentsCount).append("\n");
                }
                console.println(studentsCountInSting.substring(0, studentsCountInSting.length() - 1));
            }
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }
}
