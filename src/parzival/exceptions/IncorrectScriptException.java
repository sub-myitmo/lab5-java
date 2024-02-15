package parzival.exceptions;

/**
 * Выбрасывается, если допущена ошибка в скрипте
 *
 * @author petrovviacheslav
 */
public class IncorrectScriptException extends Exception {
    @Override
    public String toString() {
        return "Ошибка в скрипте!";
    }
}
