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
    private final Scanner usedScanner = InputManager.getUsedScanner();
    private final boolean isScriptRun;

    /**
     * Конструктор класса CreateGroup
     *
     * @param console консоль
     * @param isScriptRun состояние скрипта
     */
    public CreateGroup(Console console, boolean isScriptRun) {
        this.console = console;
        this.isScriptRun = isScriptRun;
    }


    @Override
    public StudyGroup create() throws IncorrectScriptException, IncorrectInputException {
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
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable + " ");
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                number = Long.parseLong(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число в формате Long!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
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
                name = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(name);
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    private Coordinates requestCoordinates() throws IncorrectScriptException, IncorrectInputException {
        return (new CreateCoordinates(console, isScriptRun)).create();
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
                String variable = usedScanner.nextLine().trim();


                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                transferredStudents = Integer.parseInt(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число в формате Integer!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return transferredStudents;
    }

    private Semester requestSemester() throws IncorrectScriptException {
        return new CreateSemester(console, isScriptRun).create();
    }

    private Person requestGroupAdmin() throws IncorrectScriptException, IncorrectInputException {
        return (new CreatePerson(console, isScriptRun)).create();
    }

}
