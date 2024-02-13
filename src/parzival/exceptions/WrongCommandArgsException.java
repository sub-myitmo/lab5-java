package parzival.exceptions;

/**
 * Исключение для вызова команды с неправильными аргументами
 *
 * @author petrovviacheslav
 */
public class WrongCommandArgsException extends Exception {
    @Override
    public String toString() {
        return "Неправильные аргументы у команды!";
    }
}