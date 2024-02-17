package parzival.managers;

/**
 * Класс для получения быстрого доступа к состоянию (запущен/не запущен) скрипта
 *
 * @author petrovviacheslav
 */
public class StatusScript {
    private boolean isScriptRun;

    /**
     * Конструктор класса StatusScript
     */
    public StatusScript() {
        this.isScriptRun = false;
    }

    /**
     * Получает флаг, сигнализирующий о том, что запущен/не запущен скрипт
     *
     * @return состояние запуска скрипта из файла
     */
    public boolean getIsScriptRun() {
        return isScriptRun;
    }

    /**
     * Поднимает флаг, сигнализирующий о том, что запущен скрипт
     */
    public void setIsScriptRun() {
        isScriptRun = true;
    }

    /**
     * Опускает флаг, сигнализирующий о том, что запущен скрипт
     */
    public void deleteIsScriptRun() {
        isScriptRun = false;
    }
}
