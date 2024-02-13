package parzival.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import parzival.managers.adapters.LocalDateTimeAdapter;
import parzival.models.StudyGroup;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Stack;

public class ParseManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setDateFormat("dd/MM/yyyy")
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    private final Console console;

    public ParseManager(Console console) {
        this.console = console;
    }

    /**
     * Получает из стек групп из json-строки
     */
    public Stack<StudyGroup> getStackFromJson(String json) {
        try {
            Stack<StudyGroup> studyGroups = new Stack<>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<Stack<StudyGroup>>() {
                }.getType();
                studyGroups = gson.fromJson(json, collectionType);
            }
            return studyGroups;
        } catch (Exception e) {
            console.println("Json-файл повреждён, данные из него не были взяты.");
            return new Stack<>();
        }
    }

    /**
     * Получает json-строку из связанного списка работников
     */
    public String getJsonFromStack(Stack<StudyGroup> studyGroups) {
        try {
            String json = gson.toJson(studyGroups);
            return json;
        } catch (Exception e) {
            console.println(e.toString());
            return "ошибка парсинга";
        }
    }
}
