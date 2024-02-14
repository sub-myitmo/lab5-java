package parzival.managers.creators;

import parzival.exceptions.IncorrectScriptException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.InputManager;
import parzival.managers.StatusScript;
import parzival.models.Semester;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для создания номера семестра
 *
 * @author petrovviacheslav
 */
public class CreateSemester extends BaseCreator<Semester> {
    private boolean isScriptRun = StatusScript.getIsScriptRun();
    private final Console console;
    private Scanner userScanner = InputManager.getUserScanner();

    public CreateSemester(Console console) {
        this.console = console;
    }

    @Override
    public Semester make() throws IncorrectScriptException {
        Semester semester;
        while (true) {
            console.println("Список номеров семестров - " + Semester.getNames());
            console.printf("Введите нужный семестр: ");
            try {
                String strSemester = userScanner.nextLine().trim();

                if (isScriptRun) console.println(strSemester);

                semester = Semester.valueOf(strSemester.toUpperCase());
                break;

            } catch (NoSuchElementException exception) {
                console.println("Номер семестра не распознан!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalArgumentException exception) {
                console.println("Номера семестра нет в списке!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return semester;
    }

}
