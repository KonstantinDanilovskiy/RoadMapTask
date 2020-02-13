package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class City extends ObjectWithName {
    private double x;
    private double y;
    private List<Road> cityRoads;

    public City(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
        cityRoads = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Road> getCityRoads() {
        return cityRoads;
    }

    public void setCityRoads(List<Road> cityRoads) {
        this.cityRoads = cityRoads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return Double.compare(city.getX(), getX()) == 0 &&
                Double.compare(city.getY(), getY()) == 0 &&
                Objects.equals(getCityRoads(), city.getCityRoads());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getX(), getY(), getCityRoads());
    }

    @Override
    public String toString() {
        return "City{" + super.toString() +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
