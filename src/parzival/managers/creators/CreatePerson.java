package parzival.managers.creators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;
import parzival.exceptions.MustBeNotEmptyException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.adapters.LocalDateTimeAdapter;
import parzival.models.Coordinates;
import parzival.models.Location;
import parzival.models.Person;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private LocalDateTime requestBirthday() throws IncorrectScriptException {
        LocalDateTime birthday;
        while (true) {
            console.printf("Введите день рождения (тип LocalDateTime yyyy-MM-ddTHH:mm:ss): ");
            try {
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                //DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                birthday = LocalDateTime.parse(variable);
                break;

            } catch (MustBeNotEmptyException e) {
                console.println(e.toString());
                if (isScriptRun) throw new IncorrectScriptException();
            }  catch (IllegalStateException e) {
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
                String variable = userScanner.nextLine().trim();

                if (variable.isEmpty()) throw new MustBeNotEmptyException();
                if (isScriptRun) console.println(variable);

                weight = Double.parseDouble(variable);
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
        return weight;
    }

    private Location requestLocation() throws IncorrectScriptException, IncorrectInputException {
        return (new CreateLocation(collectionManager, userScanner, console)).make();
    }

}
