package parzival.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import parzival.exceptions.SameIdException;
import parzival.managers.adapters.LocalDateTimeAdapter;
import parzival.models.StudyGroup;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 * Класс ParseManager для парсинг данных из строки и в строку json
 *
 * @author petrovviacheslav
 */
public class ParseManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setDateFormat("dd/MM/yyyy")
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    private final Console console;

    /**
     * Конструктор класса ParseManager
     *
     * @param console консоль
     */
    public ParseManager(Console console) {
        this.console = console;
    }

    /**
     * Получает стек групп из json-строки
     *
     * @param json начальная json-строка
     * @return стек (коллекция) групп
     */
    public Stack<StudyGroup> getStackFromJson(String json) {
        try {
            Stack<StudyGroup> studyGroups = new Stack<>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<Stack<StudyGroup>>() {
                }.getType();
                studyGroups = gson.fromJson(json, collectionType);

                ArrayList<Long> idList = new ArrayList<>();
                for (StudyGroup elem : studyGroups) {
                    if (idList.contains(elem.getId()) || !elem.validate()) throw new SameIdException();
                    idList.add(elem.getId());
                }

                //Comparator<StudyGroup> comparator = (o1, o2) -> o1.getStudentsCount().compareTo(o2.getStudentsCount());
                studyGroups.sort((o1, o2) -> o1.getStudentsCount().compareTo(o2.getStudentsCount()));

            }
            return studyGroups;
        } catch (Exception e) {
            console.println("Json-файл повреждён, данные из него не были взяты. Коллекция, с которой вы работаете пуста");
            return new Stack<>();
        }
    }

    /**
     * Получает json-строку из связанного списка работников
     *
     * @param studyGroups стек (коллекция) групп
     * @return json-строка
     */
    public String getJsonFromStack(Stack<StudyGroup> studyGroups) {
        try {
            return gson.toJson(studyGroups);
        } catch (Exception e) {
            console.println(e.toString());
            return "ошибка парсинга";
        }
    }
}
