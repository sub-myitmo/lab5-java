package parzival.commands;


import parzival.managers.Console;

/**
 * Абстрактный класс команд
 * @author petrovviacheslav
 */
public abstract class Command {
    /**
     * Консоль для ввода/вывода данных
     */
    protected final Console console;
    /**
     * Название команды
     */
    private String name;
    /**
     * Описание команды
     */
    private String description;

    /**
     * Конструктор класса Command
     *
     * @param console консоль
     * @param name название команды
     * @param description описание команды
     */
    protected Command(Console console, String name, String description) {
        this.console = console;
        this.name = name;
        this.description = description;
    }

    /**
     * Получить название команды
     *
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /**
     * Получить описание команды
     *
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Вызов команды
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
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