package parzival.managers;

/**
 * Класс для ввода команд и вывода результата
 *
 * @author petrovviacheslav
 */
public class Console implements Logger{


    public void println(String text) {
        System.out.println(text);
    }


    public void printf(String text) {
        System.out.printf(text);
    }


    public void printlnTwoArgs(String text1, String text2) {
        System.out.println(text1 + " - " + text2);
    }
}
