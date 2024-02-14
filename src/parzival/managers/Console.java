package parzival.managers;

/**
 * Класс для ввода команд и вывода результата
 *
 * @author petrovviacheslav
 */
public class Console implements Logger{

    /**
     * Вывод текста с окончанием строки
     *
     * @param text текст
     */
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Вывод текста без окончания строки
     *
     * @param text текст
     */
    public void printf(String text) {
        System.out.printf(text);
    }

    /**
     * Вывод частей текста через дефис
     *
     * @param text1 первая часть текста
     * @param text2 вторая часть текста
     */
    public void printlnTwoArgs(String text1, String text2) {
        System.out.println(text1 + " - " + text2);
    }
}
