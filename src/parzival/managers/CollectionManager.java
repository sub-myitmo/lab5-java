package parzival.managers;

import parzival.models.StudyGroup;
//import exceptions.NotUniqueIdException;

import java.util.Collections;
import java.util.Objects;
import java.util.Stack;
//import java.util.TreeMap;
import java.util.Date;

/**
 * Класс для работы с коллекцией
 * Методы: добавление, удаление, сортировка, очистка, поиск по id, ...
 */
public class CollectionManager {

    private Stack<StudyGroup> stackCollection;
    private final Date creationDate;

    public CollectionManager() {
        stackCollection = new Stack<>();
        creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Получает коллекцию групп
     *
     * @return stackCollection коллекция групп
     */
    public Stack<StudyGroup> getStackCollection() {
        return stackCollection;
    }

    /**
     * Устанавливает коллекцию групп
     *
     * @param stackCollection коллекция групп
     */
    public void setCollection(Stack<StudyGroup> stackCollection) {
        this.stackCollection = stackCollection;

    }


    /**
     * Выводит информацию о коллекции
     */
    public void printInfoAboutCollection(Console console) {
        console.println("Тип данных: " + stackCollection.getClass().getName());
        console.println("Дата инициализации: " + creationDate);
        console.println("Количество элементов: " + stackCollection.size());
    }

    /**
     * Возвращает группу по id
     */
    public StudyGroup getById(long id){
        for (StudyGroup studyGroup: stackCollection) {
            if (Objects.equals(studyGroup.getId(), id)) return studyGroup;
        }
        return null;
    }

    /**
     * Удаляет первый элемент коллекции
     */
    public void removeFirstElementCollection(){
        stackCollection.remove(stackCollection.firstElement());
    }

    /**
     * Удаляет группу из коллекции
     */
    public void removeGroup(StudyGroup elem){
        stackCollection.remove(elem);
    }

    /**
     * Сортирует коллекцию в обратном порядке
     */
    public void reorder(){
        Collections.reverse(stackCollection);
    }

    /**
     * Возвращает размер коллекции
     */
    public int getSizeCollection(){
        return stackCollection.size();
    }

    /**
     * Очистка коллекции
     */
    public void clearCollection(){
        stackCollection.clear();
    }

    /**
     * Вывод элементов коллекции
     */
    public void printAllElements(Console console) {
        if (stackCollection.isEmpty()) {
            console.println("Коллекция пустая");
            return;
        }
        console.println("Элементы коллекции: " + stackCollection.size());
        String studyGroupsInString = stackCollection.toString().replaceAll("\\[", "").replaceAll("]", "");
        console.println(studyGroupsInString);
    }

    /**
     * Метод перемешивает элементы коллекции групп в случайном порядке.
     */
    public void shuffle(){
        Collections.shuffle(stackCollection);
    }


    /**
     * Метод добавляет новую группу в коллекцию
     */
    public void addElementToCollection(StudyGroup studyGroup){
        stackCollection.add(studyGroup);
    }

    /**
     * Метод обновляет элемент коллекции
     */
    public void updateElement(StudyGroup oldStudyGroup, StudyGroup newStudyGroup){
        oldStudyGroup.update(newStudyGroup);
    }

}