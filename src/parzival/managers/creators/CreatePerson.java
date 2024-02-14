package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.models.Coordinates;
import parzival.models.Location;
import parzival.models.Person;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreatePerson extends BaseCreator<Person> {
    private boolean isScriptRun = CreateGroup.getIsScriptRun();
    private final Console console;
    private Scanner userScanner;
    private final CollectionManager collectionManager;

    public CreatePerson(CollectionManager collectionManager, Scanner userScanner, Console console) {
        this.console = console;
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
    }


    @Override
    public Person make() throws IncorrectScriptException, IncorrectInputException {
        Person groupAdmin = new Person(
                requestNamePerson(),
                LocalDateTime.now(),
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

    private double requestWeight() throws IncorrectScriptException {
        double weight;
        while (true) {
            console.printf("Введинте вес (тип double)");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                weight = Double.parseDouble(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            } catch (IllegalStateException e) {
                console.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weight;
    }

    private Location requestLocation() throws IncorrectScriptException, IncorrectInputException {
        return (new CreateLocation(collectionManager, userScanner, console)).make();
    }




}
