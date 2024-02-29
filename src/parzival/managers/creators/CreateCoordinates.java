package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.Console;
import parzival.managers.InputManager;
import parzival.models.Coordinates;

import java.util.Scanner;

/**
 * Класс для создания координат
 *
 * @author petrovviacheslav
 */
public class CreateCoordinates extends BaseCreator<Coordinates> {
    private final boolean isScriptRun;
    private final Console console;
    private final Scanner usedScanner = InputManager.getUsedScanner();

    /**
     * Конструктор класса CreateCoordinates
     *
     * @param console     консоль
     * @param isScriptRun состояние скрипта
     */
    public CreateCoordinates(Console console, boolean isScriptRun) {
        this.console = console;
        this.isScriptRun = isScriptRun;
    }


    @Override
    public Coordinates create() throws IncorrectScriptException {
        try {
            Coordinates coordinates = new Coordinates(
                    requestX(),
                    requestY()
            );
            if (!coordinates.validate()) throw new IncorrectInputException();
            return coordinates;
        } catch (IncorrectInputException e) {
            console.printerror(e.toString());
            return create();
        }
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
            console.printf("Введите переменную Х (для Coordinates, тип Integer): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                X = Integer.parseInt(variable);
                if (X > 265) throw new NumberFormatException();
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число в формате Integer и не больше 265!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return X;
    }

    private double requestY() throws IncorrectScriptException {
        double Y;
        while (true) {
            console.printf("Введите переменную Y (для Coordinates, тип double): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                Y = Double.parseDouble(variable);
                break;

            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число в формате double!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return Y;
    }

}
