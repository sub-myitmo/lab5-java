package parzival.managers;

/**
 * Интерфейс для консоли
 *
 * @author petrovviacheslv
 */
public interface Logger {
    /**
     * Вывод текста с окончанием строки
     *
     * @param text текст
     */
    void println(String text);

    /**
     * Вывод текста без окончания строки
     *
     * @param text текст
     */
    void printf(String text);

    /**
     * Вывод частей текста через дефис
     *
     * @param text1 первая часть текста
     * @param text2 вторая часть текста
     */
    void printlnTwoArgs(String text1, String text2);
}

