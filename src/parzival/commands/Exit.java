package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.Console;
import parzival.managers.StatusScript;

/**
 * Команда exit - завершить программу (без сохранения в файл)
 *
 * @author petrovviacheslav
 */
public class Exit extends Command {

    private StatusScript statusScript;
    /**
     * Конструктор класса Exit
     *
     * @param console консоль
     * @param statusScript состояние скрипта
     */
    public Exit(Console console, StatusScript statusScript) {
        super(console, "exit", "завершить программу (без сохранения в файл)");
        this.statusScript = statusScript;
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

            console.println("Завершение выполнения");
            if (statusScript.getIsScriptRun()) System.exit(0);

            return true;
        } catch (WrongCommandArgsException e) {
            console.printerror(e.toString());
            return false;
        }

    }
}
