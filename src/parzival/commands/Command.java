package parzival.commands;


import parzival.managers.Console;

/**
 * Абстрактный класс команд
 * @author petrovviacheslav
 */
public abstract class Command {
    protected final Console console;
    private String name;
    private String description;

    protected Command(Console console, String name, String description) {
        this.console = console;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Вызов команды
     */
    public abstract boolean execute(String[] args);

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }
}