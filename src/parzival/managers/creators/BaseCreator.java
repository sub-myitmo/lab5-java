package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;

/**
 * Абстрактный класс создания базового объекта
 *
 * @author petrovviacheslav
 */
public abstract class BaseCreator<T> {
    abstract T create() throws IncorrectScriptException, IncorrectInputException;
}
