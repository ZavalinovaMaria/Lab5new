package org.example.subjects;

public class Coordinates {
    /**
     * A class representing coordinates.
     */
    private Double x; //Значение поля должно быть больше -951, Поле не может быть null
    private int y;

    /**
     * Creates a new coordinates instance.
     *
     * @param x coordinate x
     * @param y coordinate y
     */

    public Coordinates(double x, int y) {
        this.x = x;
        this.y=y;
    }
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
