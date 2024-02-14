package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.Coordinates;
import parzival.models.StudyGroup;

import java.util.Date;
import java.util.Scanner;

public class CreateCoordinates extends BaseCreator<Coordinates>{
    private boolean isScriptRun = CreateGroup.getIsScriptRun();
    private final Console console;
    private Scanner userScanner;

    public CreateCoordinates(Scanner userScanner, Console console) {
        this.console = console;
        this.userScanner = userScanner;
    }


    @Override
    public Coordinates make() throws IncorrectScriptException, IncorrectInputException {
        Coordinates coordinates = new Coordinates(
                requestX(),
                requestY()
        );
        if (!coordinates.validate()) throw new IncorrectInputException();
        return coordinates;
    }

    /**
     * Функция, спрашивающая у пользователя координату X
     *
     * @return X - переменная от пользователя
     * @throws IncorrectScriptException если при чтении скрипта возникла ошибка
     */
    private Integer requestX() throws IncorrectScriptException {
        Integer X;
        while (true) {
            console.printf("Введинте переменную Х (тип Integer)");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                X = Integer.parseInt(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return X;
    }

    private double requestY() throws IncorrectScriptException {
        double Y;
        while (true) {
            console.printf("Введинте переменную Y (тип double)");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                Y = Double.parseDouble(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return Y;
    }

}
