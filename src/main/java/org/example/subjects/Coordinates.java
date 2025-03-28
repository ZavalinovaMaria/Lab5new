package org.example.subjects;

/**
 * Класс, описывающий координаты
 */
public class Coordinates {

    private Double x; //Значение поля должно быть больше -951, Поле не может быть null
    private int y;

    /**
     * Создает координаты
     *
     * @param x координата x
     * @param y координата y
     */

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
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
