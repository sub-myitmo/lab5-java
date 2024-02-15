package parzival.models;

/**
 * Перечисление содержащее номера семестров
 *
 * @author petrovviacheslav
 */
public enum Semester {
    /**
     * первый
     */
    FIRST,
    /**
     * третий
     */
    THIRD,
    /**
     * четвертый
     */
    FOURTH,
    /**
     * восьмой
     */
    EIGHTH;

    /**
     * Получить все константы enum'а
     *
     * @return строка со всеми элементами enum'а через запятую
     */
    public static String getNames() {
        StringBuilder listSemester = new StringBuilder();
        for (Semester semester : values()) {
            listSemester.append(semester).append(", ");
        }
        return listSemester.substring(0, listSemester.length() - 2);
    }
}