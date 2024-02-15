package parzival.models.validate;

/**
 * Интерфейс для классов, поля которых могут быть валидными, а могут и не быть
 *
 * @author petrovviacheslav
 */
public interface Validatable {
    /**
     * Метод для проверки полей моделей
     *
     * @return true - если все поля верны, иначе false
     */
    boolean validate();
}