package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.*;
import parzival.models.*;

import java.util.Date;
import java.util.Scanner;

public class CreateGroup extends BaseCreator<StudyGroup> {
    private static boolean isScriptRun;
    private final Console console;
    private Scanner userScanner;
    private final CollectionManager collectionManager;

    public CreateGroup(CollectionManager collectionManager, Scanner userScanner, Console console) {
        this.console = console;
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
        isScriptRun = false;
    }

    public static boolean getIsScriptRun() {
        return isScriptRun;
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
     * Функция, спрашивающая у пользователя , которрое после приведится к данным типа Long
     *
     * @param request запрос того, что требуется ввести
     * @return variable - переменная от пользователя
     * @throws IncorrectScriptException если при чтении скрипта возникла ошибка
     */
    private Long requestNumberField(String request) throws IncorrectScriptException {
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
            console.printf("Введите имя учебной группы:");
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

    private Coordinates requestCoordinates() throws IncorrectScriptException {
        return new CreateCoordinates(collectionManager, userScanner, console).make();
    }

    private Long requestStudentsCount() throws IncorrectScriptException {
        return requestNumberField("Введите количество студентов в учебной группе: ");
    }

    private Long requestExpelledStudents() throws IncorrectScriptException {
        return requestNumberField("Введите количество отчисленных студентов в учебной группе: ");
    }

    private Integer requestTransferredStudents() throws IncorrectScriptException {
        return requestNumberField("Введите количество переведённых студентов в учебной группе: ").intValue();
    }

    private Semester requestSemester() throws IncorrectScriptException {
        return new CreateSemester(collectionManager, userScanner, console).make();
    }

    private Person requestGroupAdmin() throws IncorrectScriptException {
        return new CreateGroupAdmin(collectionManager, userScanner, console).make();
    }


}
