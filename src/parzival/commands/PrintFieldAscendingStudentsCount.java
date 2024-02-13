package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.StudyGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PrintFieldAscendingStudentsCount extends Command {
    /**
     * Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Reorder
     *
     * @param collectionManager менеджер коллекции.
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
                ArrayList<Long> arrayStudentsCount = new ArrayList<>();

                for (StudyGroup studyGroup : collectionManager.getStackCollection()) {
                    if (!arrayStudentsCount.contains(studyGroup.getStudentsCount())) {
                        arrayStudentsCount.add(studyGroup.getStudentsCount());
                    }
                }

                Collections.sort(arrayStudentsCount);

                console.println("Поля studentsCount: ");
                String studentsCountInSting = "";
                for (Long studentsCount : arrayStudentsCount) {
                    studentsCountInSting += " - " + studentsCount + "\n";
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
