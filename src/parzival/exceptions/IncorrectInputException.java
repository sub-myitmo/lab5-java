package parzival.exceptions;

/**
 * Выбрасывается, если допущена ошибка при создании объекта
 *
 * @author petrovviacheslav
 */
public class IncorrectInputException extends Exception{
    @Override
    public String toString() {
        return "Ошибка при создании объекта!";
    }
}
