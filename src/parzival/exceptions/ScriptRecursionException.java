package parzival.exceptions;


/**
 * Выбрасывается при рекурсивном вызове файлов
 *
 * @author petrovviacheslav
 */
public class ScriptRecursionException extends Exception {
    @Override
    public String toString() {
        return "Нельзя вызывать файлы рекурсивно, при выполнении скрипта вы снова обращаетесь к уже выполняемому файлу!";
    }
}
