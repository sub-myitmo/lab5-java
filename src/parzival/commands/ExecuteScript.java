package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CollectionManager;
import parzival.managers.*;
import parzival.managers.creators.CreateGroup;

public class ExecuteScript extends Command {
    private final InputManager inputManager;
    private final FileManager fileManager;

    /**
     * Конструктор ExecuteScript
     * @param inputManager менеджер коллекции
     */
    public ExecuteScript(InputManager inputManager, FileManager fileManager, Console console) {
        super(console, "execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.inputManager = inputManager;
        this.fileManager = fileManager;
    }

    /**
     * Выполняет скрипт из файла
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongCommandArgsException();
            CreateGroup.setIsScriptRun();
            inputManager.scriptRun(args[1]);
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }
    }
}
