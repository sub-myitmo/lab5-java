package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.*;
import parzival.models.*;

import java.util.Date;
import java.util.Scanner;

/**
 * Класс для создания группы
 *
 * @author petrovviacheslav
 */
public class CreateGroup extends BaseCreator<StudyGroup> {

    private final Console console;
    private Scanner userScanner = InputManager.getUserScanner();
    private final CollectionManager collectionManager;
    private boolean isScriptRun = StatusScript.getIsScriptRun();

    /**
     * Конструктор класса CreateGroup
     * @param collectionManager менеджер коллекции
     * @param console консоль
     */
    public CreateGroup(CollectionManager collectionManager, Console console) {
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public StudyGroup make() throws IncorrectScriptException, IncorrectInputException {
        StudyGroup studyGroup = new StudyGroup(
                requestName(),
                requestCoordinates(),
                new Date(),
                requestStudentsCount(),
                requestExpelledStudents(),
                requestTransferredStudents(),
                requestSemester(),
                requestGroupAdmin()
        );
        if (!studyGroup.validate()) throw new IncorrectInputException();
        return studyGroup;
    }


    /**
     * Функция, спрашивающая у пользователя строку, которая после приводится к данным типа Long
     *
     * @param request запрос того, что требуется ввести
     * @return number - строка приведенная к типу Long
     * @throws IncorrectScriptException если при чтении скрипта возникла ошибка
     */
    private Long requestLongField(String request) throws IncorrectScriptException {
        Long number;
        while (true) {
            console.printf(request);
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                number = Long.parseLong(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.println("Надо ввести число в формате Long!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return number;
    }

    private String requestName() throws IncorrectScriptException {
        String name;
        while (true) {
            console.printf("Введите имя учебной группы: ");
            try {
                name = userScanner.nextLine().trim();

                if (name.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(name);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    private Coordinates requestCoordinates() throws IncorrectScriptException, IncorrectInputException {
        return (new CreateCoordinates(console)).make();
    }

    private Long requestStudentsCount() throws IncorrectScriptException {
        return requestLongField("Введите количество студентов в учебной группе: ");
    }

    private Long requestExpelledStudents() throws IncorrectScriptException {
        return requestLongField("Введите количество отчисленных студентов в учебной группе: ");
    }

    private Integer requestTransferredStudents() throws IncorrectScriptException {
        Integer transferredStudents;
        while (true) {
            console.printf("Введите количество переведённых студентов в учебной группе: ");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                transferredStudents = Integer.parseInt(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.println("Надо ввести число в формате Integer!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return transferredStudents;
    }

    private Semester requestSemester() throws IncorrectScriptException {
        return new CreateSemester(console).make();
    }

    private Person requestGroupAdmin() throws IncorrectScriptException, IncorrectInputException {
        return (new CreatePerson(console)).make();
    }

}
