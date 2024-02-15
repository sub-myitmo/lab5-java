package parzival.exceptions;

/**
 * Выбрасывается, если оставляют пустое поле при вводе данных
 *
 * @author petrovviacheslav
 */
public class MustBeNotEmptyException extends Exception {
    @Override
    public String toString() {
        return "Поле не может быть пустым!";
    }
}
