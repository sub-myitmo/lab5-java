package parzival.managers;

/**
 * Класс для получения быстрого доступа к состоянию (запущен/не запущен) скрипта
 *
 * @author petrovviacheslav
 */
public class StatusScript {
    private static boolean isScriptRun = false;
    /**
     * Получает флаг, сигнализирующий о том, что запущен/не запущен скрипт
     *
     * @return состояние запуска скрипта из файла
     */
    public static boolean getIsScriptRun() {
        return isScriptRun;
    }

    /**
     * Поднимает флаг, сигнализирующий о том, что запущен скрипт
     */
    public static void setIsScriptRun() {
        isScriptRun = true;
    }

    /**
     * Опускает флаг, сигнализирующий о том, что запущен скрипт
     */
    public static void deleteIsScriptRun() {
        isScriptRun = false;
    }
}
