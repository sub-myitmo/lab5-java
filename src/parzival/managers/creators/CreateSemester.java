package parzival.managers.creators;

import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
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
    private final boolean isScriptRun;
    private final Console console;
    private final Scanner usedScanner = InputManager.getUsedScanner();

    /**
     * Конструктор класса CreateSemester
     *
     * @param console консоль
     * @param isScriptRun состояние скрипта
     */
    public CreateSemester(Console console, boolean isScriptRun) {
        this.console = console;
        this.isScriptRun = isScriptRun;
    }

    @Override
    public Semester create() throws IncorrectScriptException {
        Semester semester;
        while (true) {
            console.println("Список номеров семестров - " + Semester.getNames());
            console.printf("Введите нужный семестр: ");
            try {
                String strSemester = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(strSemester);
                if (strSemester.isEmpty()) throw new MustBeNotEmptyException();

                semester = Semester.valueOf(strSemester.toUpperCase());
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                console.printerror("Номер семестра не распознан!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalArgumentException exception) {
                console.printerror("Номера семестра нет в списке!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return semester;
    }

}
