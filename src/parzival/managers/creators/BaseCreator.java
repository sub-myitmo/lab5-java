package parzival.managers.creators;

import parzival.exceptions.IncorrectInputException;
import parzival.exceptions.IncorrectScriptException;

public abstract class BaseCreator<T> {
    abstract T make() throws IncorrectScriptException, IncorrectInputException;
}
