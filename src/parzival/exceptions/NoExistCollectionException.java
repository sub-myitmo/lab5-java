package parzival.exceptions;

/**
 * Выбрасывается, если обращаются к несуществующей коллекции
 *
 * @author petrovviacheslav
 */
public class NoExistCollectionException extends Exception {

    @Override
    public String toString() {
        return "Коллекции с таким id не существует!";
    }
}
