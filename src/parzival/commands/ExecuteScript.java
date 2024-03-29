package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.*;


/**
 * Команда clear - выполняет скрипт из файла
 *
 * @author petrovviacheslav
 */

public class ExecuteScript extends Command {
    /**
     * Менеджер ввода
     */
    private final InputManager inputManager;

    /**
     * Конструктор ExecuteScript
     *
     * @param inputManager менеджер ввода
     * @param console      консоль
     */
    public ExecuteScript(InputManager inputManager, Console console) {
        super(console, "execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.inputManager = inputManager;
    }

    /**
     * Выполняет скрипт из файла
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args[1].isEmpty() || args.length > 2) throw new WrongCommandArgsException();
            console.println(String.format("Выполнение скрипта из файла %s", args[1]));
            inputManager.scriptRun(args[1].substring(0, args[1].length()-1));
            return true;
        } catch (WrongCommandArgsException e) {
            console.printerror(e.toString());
            return false;
        }
    }
}
