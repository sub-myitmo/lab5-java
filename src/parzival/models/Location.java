package parzival.models;

import parzival.models.validate.Validatable;


public class Location implements Validatable {
    private final Float x; //Поле не может быть null
    private final Integer y; //Поле не может быть null
    private final double z;

    public Location(Float x, Integer y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public double getZ() {
        return y;
    }

    public boolean validate() {
        if (y == null || x == null) return false;
        return true;
    }

    // дописать hashCode equals

    @Override
    public String toString() {
        return "\n\tLocation{" + "\n\t\tx=" + x + ", \n\t\ty=" + y + ", \n\t\tz=" + z + "\n\t}";
    }
}