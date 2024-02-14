package parzival.managers;

/**
 * Класс для получения быстрого доступа к состоянию (запущен/не запущен) скрипта
 *
 * @author petrovviacheslav
 */
public class StatusScript {
    private static boolean isScriptRun = false;
    public static boolean getIsScriptRun() {
        return isScriptRun;
    }

    public static void setIsScriptRun() {
        isScriptRun = true;
    }
    public static void deleteIsScriptRun() {
        isScriptRun = false;
    }
}
