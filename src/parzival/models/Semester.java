package parzival.models;

/**
 * Перечисление содержащее номера семестров
 *
 * @author petrovviacheslav
 */
public enum Semester {
    FIRST,
    THIRD,
    FOURTH,
    EIGHTH;

    /**
     * Получить все константы enum'а
     *
     * @return строка со всеми элементами enum'а через запятую
     */
    public static String getNames() {
        String listSemester = "";
        for (Semester semester : values()) {
            listSemester += semester + ", ";
        }
        return listSemester.substring(0, listSemester.length()-2);
    }
}