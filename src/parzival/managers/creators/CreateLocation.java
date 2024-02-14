package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.Coordinates;
import parzival.models.Location;

import java.util.Locale;
import java.util.Scanner;

public class CreateLocation extends BaseCreator<Location>{
    private boolean isScriptRun = CreateGroup.getIsScriptRun();
    private final Console console;
    private Scanner userScanner;
    private final CollectionManager collectionManager;

    public CreateLocation(CollectionManager collectionManager, Scanner userScanner, Console console) {
        this.console = console;
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
    }


    @Override
    public Location make() throws IncorrectScriptException, IncorrectInputException {
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
            console.printf("Введинте переменную Х (тип Float)");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                X = Float.parseFloat(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (NumberFormatException e){
                console.println("Надо ввести число!");
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
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
            console.printf("Введинте переменную Y (тип Integer): ");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                Y = Integer.parseInt(variable);
                break;

            } catch (NumberFormatException e){
                console.println("Надо ввести число!");
                if (isScriptRun) throw new IncorrectScriptException();
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

    private double requestZ() throws IncorrectScriptException {
        double Z;
        while (true) {
            console.printf("Введинте переменную Z (тип double)");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                Z = Double.parseDouble(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return Z;
    }
}
