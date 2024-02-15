package parzival.managers;

import parzival.commands.Command;

import java.util.*;

/**
 * Класс для запуска команд и хранения истории
 *
 * @author petrovviachesalv
 */
public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();
    // private final TreeMap<String, Command> commands = new TreeMap<>();

    /**
     * Добавляет команду
     *
     * @param commandName название команды.
     * @param command     команда
     */
    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Возвращает словарь команд
     *
     * @return словарь команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /*
    private Stack<String> history = new Stack<>();
     если надо будет, то можно будет допилить команду history тоже
    public Stack<String> getHistory() {
        return history;
    }

    public void addHistory(String command) {
        history.add(command);
    }
     */


}