package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.Console;
import parzival.managers.StatusScript;

/**
 * Команда exit - завершить программу (без сохранения в файл)
 *
 * @author petrovviacheslav
 */
public class Exit extends Command{

    /**
     * Конструктор класса Exit
     *
     * @param console консоль
     */
    public Exit(Console console) {
        super(console, "exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Завершает выполнение программы
     *
     * @param args вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();

            if (StatusScript.getIsScriptRun()) console.println("Завершение выполнения скрипта из файла");
            else console.println("Завершение выполнения");

            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }
}
