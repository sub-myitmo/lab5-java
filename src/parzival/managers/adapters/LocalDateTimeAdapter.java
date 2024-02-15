package parzival.managers.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Сериализатор-десериализатор для правильного парсинга формата LocalDateTime
 *
 * @author petrovviacheslav
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    @Override
    public JsonElement serialize(LocalDateTime dateTime, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateTime.toString());
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString());
    }
}