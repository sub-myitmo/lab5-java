package parzival.managers.creators;


import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.Console;
import parzival.managers.InputManager;
import parzival.models.Location;
import parzival.models.Person;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Класс для создания человека
 *
 * @author petrovviacheslav
 */
public class CreatePerson extends BaseCreator<Person> {
    private final boolean isScriptRun;
    private final Console console;
    private final Scanner usedScanner = InputManager.getUsedScanner();

    /**
     * Конструктор класса CreatePerson
     *
     * @param console консоль
     * @param isScriptRun состояние скрипта
     */
    public CreatePerson(Console console, boolean isScriptRun) {
        this.console = console;
        this.isScriptRun = isScriptRun;
    }


    @Override
    public Person create() throws IncorrectScriptException, IncorrectInputException {
        Person groupAdmin = new Person(
                requestNamePerson(),
                requestBirthday(),
                requestWeight(),
                requestLocation()
        );
        if (!groupAdmin.validate()) throw new IncorrectInputException();
        return groupAdmin;
    }

    private String requestNamePerson() throws IncorrectScriptException {
        String name;
        while (true) {
            console.printf("Введите имя админа группы:");
            try {
                name = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(name);
                if (name.isEmpty()) throw new MustBeNotEmptyException();
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

    private LocalDateTime requestBirthday() throws IncorrectScriptException {
        LocalDateTime birthday;
        while (true) {
            console.printf("Введите день рождения (тип LocalDateTime yyyy-MM-ddTHH:mm:ss): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                birthday = LocalDateTime.parse(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (Exception e) {
                console.println("Неверно ввели дату");
                if (isScriptRun) throw new IncorrectScriptException();
            }
        }
        return birthday;
    }

    private double requestWeight() throws IncorrectScriptException {
        double weight;
        while (true) {
            console.printf("Введите вес (тип double): ");
            try {
                String variable = usedScanner.nextLine().trim();

                if (isScriptRun) console.println(variable);
                if (variable.isEmpty()) throw new MustBeNotEmptyException();

                weight = Double.parseDouble(variable);
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
        return weight;
    }

    private Location requestLocation() throws IncorrectScriptException, IncorrectInputException {
        return (new CreateLocation(console, isScriptRun)).create();
    }

}
