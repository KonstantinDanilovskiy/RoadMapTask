package entity;

import java.util.Objects;

public class Road extends ObjectWithName {
    private double length;

    public Road(String name, double length) {
        super(name);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

//    @Override
//    public int compareTo(Road o) {
//        int nameCompare = this.getName().compareTo(o.getName());
//        if (nameCompare == 0) {
//            double lengthCompare = length - o.getLength();
//            if (lengthCompare == 0d) {
//                return 0;
//            }
//            return lengthCompare < 0 ? -1 : 1;
//        }
//        return nameCompare;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;
        if (!super.equals(o)) return false;
        Road road = (Road) o;
        return Double.compare(road.getLength(), getLength()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLength());
    }
}
