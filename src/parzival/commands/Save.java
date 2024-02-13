package parzival.commands;

import parzival.exceptions.WrongCommandArgsException;
import parzival.managers.*;

/**
 * Записывает коллекцию в файл
 *
 * @author petrovviacheslav
 */
public class Save extends Command{
    /**
     Менеджер файлов.
     */
    private final FileManager fileManager;
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;
    private final ParseManager parseManager;

    /**
     * Конструктор SaveCommand создает новый объект команды "save".
     *
     * @param fileManager объект для работы с файлами.
     * @param collectionManager объект для работы с коллекцией.
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
     * @param args вводимая в консоль строка
     * @return true, если выполнение команды прошло успешно, и false, если произошла ошибка.
     */
    @Override
    public boolean execute(String[] args) {
        try {
            if(!args[1].isEmpty()) throw new WrongCommandArgsException();
            fileManager.writeToFile("data2.json", parseManager.getJsonFromStack(collectionManager.getStackCollection()));
            return true;
        } catch (WrongCommandArgsException e){
            console.println(e.toString());
            return false;
        }

    }
}
