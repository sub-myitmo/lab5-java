package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.Semester;
import parzival.models.StudyGroup;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateSemester extends BaseCreator<Semester>{
    private boolean isScriptRun = CreateGroup.getIsScriptRun();
    private final Console console;
    private Scanner userScanner;
    private final CollectionManager collectionManager;

    public CreateSemester(CollectionManager collectionManager, Scanner userScanner, Console console) {
        this.console = console;
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
    }

    @Override
    public Semester make() throws IncorrectScriptException {
        Semester semester;
        while (true) {
            console.println("Список номеров семестров - " + Semester.getNames());
            console.println("Введите нужный семестр:");
            try {
                String strSemester = userScanner.nextLine().trim();

                if(isScriptRun) console.println(strSemester);

                semester = Semester.valueOf(strSemester.toUpperCase());
                break;

            } catch (NoSuchElementException exception) {
                console.println("Тип организации не распознан!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalArgumentException exception) {
                console.println("Типа организации нет в списке!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return semester;
    }

}
