package parzival.models.validate;

/**
 * Интерфейс для классов, поля которых могут быть валидными или нет
 * @author petrovviacheslav
 */
public interface Validatable {
    /**
     * Метод для проверки полей моделей
     *
     * @return true - если все поля верны, иначе нет
     */
    boolean validate();
}