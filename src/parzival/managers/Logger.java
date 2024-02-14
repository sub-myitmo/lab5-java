package parzival.managers;

/**
 * Интерфейс для консоли
 *
 * @author petrovviacheslav
 */
public interface Logger {
    void println(String text);

    void printf(String text);

    void printlnTwoArgs(String text1, String text2);
}
