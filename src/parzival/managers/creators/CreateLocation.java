package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.Console;
import parzival.managers.InputManager;
import parzival.models.Location;

import java.util.Scanner;

/**
 * Класс для создания местоположения человека
 *
 * @author petrovviacheslav
 */
public class CreateLocation extends BaseCreator<Location> {
    private final boolean isScriptRun;
    private final Console console;
    private final Scanner usedScanner = InputManager.getUsedScanner();

    /**
     * Конструктор класса CreateLocation
     *
     * @param console консоль
     * @param isScriptRun состояние скрипта
     */
    public CreateLocation(Console console, boolean isScriptRun) {
        this.console = console;
        this.isScriptRun = isScriptRun;
    }


    @Override
    public Location create() throws IncorrectScriptException, IncorrectInputException {
        Location location = new Location(
                requestX(),
                requestY(),
                requestZ()
        );
        if (!location.validate()) throw new IncorrectInputException();
        return location;
    }

    /**
     * Функция, спрашивающая у пользователя координату X в локации
     *
     * @return X - переменная от пользователя
     * @throws IncorrectScriptException если при чтении скрипта возникла ошибка
     */
    private Float requestX() throws IncorrectScriptException {
        Float X;
        while (true) {
            console.printf("Введите переменную Х (для Location, тип Float): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                X = Float.parseFloat(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return X;
    }

    /**
     * Функция, спрашивающая у пользователя координату X в локации
     *
     * @return X - переменная от пользователя
     * @throws IncorrectScriptException если при чтении скрипта возникла ошибка
     */
    private Integer requestY() throws IncorrectScriptException {
        Integer Y;
        while (true) {
            console.printf("Введите переменную Y (для Location, тип Integer): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                Y = Integer.parseInt(variable);
                break;

            } catch (NumberFormatException e) {
                console.printerror("Надо ввести число!");
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

    private double requestZ() throws IncorrectScriptException {
        double Z;
        while (true) {
            console.printf("Введите переменную Z (для Location, тип double): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                Z = Double.parseDouble(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.printerror(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return Z;
    }
}
