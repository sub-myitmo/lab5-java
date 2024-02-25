package parzival.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс человека
 *
 * @author petrovviacheslav
 */

public class Person implements Validatable {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final LocalDateTime birthday; //Поле может быть null
    private double weight; //Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    /**
     * Конструктор класса Person
     *
     * @param name имя
     * @param birthday день рождения
     * @param weight вес
     * @param location местоположение
     */
    public Person(String name, LocalDateTime birthday, double weight, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.location = location;
    }

    /**
     * Получить имя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получить дату рождения
     *
     * @return дата рождения
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Получить вес
     *
     * @return вес
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Получить местоположение
     *
     * @return местоположение
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean validate() {
        if (name == null || name.isEmpty()) return false;
        if (birthday == null) return false;
        if (weight <= 0) return false;
        return location != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, weight, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return weight == person.weight && Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && Objects.equals(location, person.location);
    }

    @Override
    public String toString() {
        return "Person{" + "\n\tname=" + name +
                ", \n\tbirthday=" + birthday +
                ", \n\tweight=" + weight +
                ", \n\tlocation='" + location + '\'' +
                "\n}";
    }
}