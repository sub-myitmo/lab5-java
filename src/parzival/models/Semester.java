package parzival.models;

/**
 * Перечисление содержащее номера семестров
 * @author petrovviacheslav
 */
public enum Semester {
    FIRST,
    THIRD,
    FOURTH,
    EIGHTH;

    /**
     * @return Строка со всеми элементами enum'а через строку
     */
    public static String getNames() {
        String listSemester = "";
        for (Semester semester : values()) {
            listSemester += semester + ", ";
        }
        return listSemester.substring(0, listSemester.length()-2);
    }
}