package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.*;

/**
 * Команда save - сохранить коллекцию в файл
 *
 * @author petrovviacheslav
 */
public class Save extends Command{
    /**
     * Менеджер файлов
     */
    private final FileManager fileManager;
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;
    /**
     * Менеджер парсинга
     */
    private final ParseManager parseManager;

    /**
     * Конструктор класса Save
     *
     * @param fileManager менеджер файлов
     * @param parseManager менеджер парсинга
     * @param collectionManager Менеджер коллекции
     * @param console консоль
     */
    public Save(FileManager fileManager, ParseManager parseManager, CollectionManager collectionManager, Console console) {
        super(console, "save", "сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.parseManager = parseManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Записывает коллекцию в файл
     *
     * @param args аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if(!args[1].isEmpty()) throw new WrongCommandArgsException();
            fileManager.writeToFile("data2.json", parseManager.getJsonFromStack(collectionManager.getStackCollection()));

            console.println("Коллекция была успешно сохранена");
            return true;
        } catch (WrongCommandArgsException e){
            console.println(e.toString());
            return false;
        }

    }
}
