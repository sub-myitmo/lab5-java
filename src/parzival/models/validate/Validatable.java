package parzival.models.validate;

/**
 * Интерфейс для классов, поля которых могут быть валидными или нет
 * @author petrovviacheslav
 */
public interface Validatable {
    boolean validate();
}