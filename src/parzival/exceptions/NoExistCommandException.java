package parzival.exceptions;

/**
 * Исключение для вызова несуществующей команды
 *
 * @author petrovviacheslav
 */
public class NoExistCommandException extends Exception {
    @Override
    public String toString() {
        return "Несуществующая команда!";
    }
}
