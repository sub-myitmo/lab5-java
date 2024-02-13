package parzival.models;

import parzival.models.validate.Validatable;

/**
 * Класс координат
 * @author petrovviacheslav
 */

public class Coordinates implements Validatable {
    private final Integer x; //Максимальное значение поля: 265, Поле не может быть null
    private final double y;

    public Coordinates(Integer x, double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean validate() {
        if (x > 265 || x == null) return false;
        return true;
    }

// дописать hashCode equals

    @Override
    public String toString() {
        return "Coordinates{" + "\n\tx=" + x + ", \n\ty=" + y + "\n}";
    }
}