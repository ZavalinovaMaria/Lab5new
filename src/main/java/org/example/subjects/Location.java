package org.example.subjects;

public class Location {
    private double x;
    private long y;
    private float z;

    public Location(double x, long y, float z) {
        this.x = x;
        this.y=y;
        this.z=z;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x= x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z= z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
