package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.CommandManager;
import parzival.managers.Console;

/**
 * Команда help - вывести справку по доступным командам
 *
 * @author petrovviacheslav
 */
public class Help extends Command {
    /**
     * Менеджер команд
     */
    private final CommandManager commandManager;

    /**
     * Конструктор класса Help
     *
     * @param commandManager менеджер команд
     * @param console консоль
     */
    public Help(CommandManager commandManager, Console console) {
        super(console, "help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    /**
     * Выводит описание команд
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
            commandManager.getCommands().values().forEach(command -> {
                console.printlnTwoArgs(command.getName(), command.getDescription());
            });
            return true;
        } catch (WrongCommandArgsException e) {
            console.println(e.toString());
            return false;
        }

    }
}