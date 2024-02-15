package parzival.exceptions;

/**
 * Выбрасывается, если вызывают команду с неправильными аргументами
 *
 * @author petrovviacheslav
 */
public class WrongCommandArgsException extends Exception {
    @Override
    public String toString() {
        return "Неправильные аргументы у команды!";
    }
}