package parzival.managers;

/**
 * Класс для ввода команд и вывода результата
 *
 * @author petrovviacheslav
 */
public class Console implements Logger {

    @Override
    public void println(String text) {
        System.out.println(text);
    }
    @Override
    public void printerror(String text) {
        System.out.println("$ " + text);
    }

    @Override
    public void printf(String text) {
        System.out.printf(text);
    }
    @Override
    public void printlnTwoArgs(String text1, String text2) {
        System.out.println(text1 + " - " + text2);
    }
}
