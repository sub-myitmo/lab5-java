package parzival.exceptions;

/**
 * Выбрасывается, если обращаются к пустому файлу
 *
 * @author petrovviacheslav
 */
public class EmptyFileException extends Exception {
    @Override
    public String toString() {
        return "Файл пустой!!!";
    }
}
