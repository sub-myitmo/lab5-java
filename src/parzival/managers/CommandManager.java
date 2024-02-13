package parzival.managers;

import parzival.commands.Command;

import java.util.*;

/**
 * Класс для запуска команд и хранения истории
 * @author petrovviachesalv
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    //private final TreeMap<String, Command> commands = new TreeMap<>();

    // private Stack<String> history = new Stack<>();
    // если надо будет, то можно будет допилить команду history тоже
//    public Stack<String> getHistory() {
//        return history;
//    }
//
//    public void addHistory(String command) {
//        history.add(command);
//    }

    public CommandManager (){};

    /**
     * Добавляет команду.
     * @param commandName Название команды.
     * @param command Команда.
     */
    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * @return Словарь команд.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }



}