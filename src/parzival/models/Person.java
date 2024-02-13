package parzival.models;

import parzival.models.validate.Validatable;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс человека
 * @author petrovviacheslav
 */

public class Person implements Validatable {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final LocalDateTime birthday; //Поле может быть null
    private double weight; //Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    public Person(String name, LocalDateTime birthday, double weight, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public double getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean validate() {
        if (name == null || name.isEmpty()) return false;
        if (birthday == null) return false;
        if (weight <= 0) return false;
        if (location == null) return false;
        return true;
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