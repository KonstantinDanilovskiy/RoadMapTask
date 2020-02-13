package entity;

import java.util.Objects;

public abstract class ObjectWithName implements Comparable<ObjectWithName> {
    private String name;

    public ObjectWithName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ObjectWithName o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectWithName)) return false;
        ObjectWithName that = (ObjectWithName) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "name=" + name;
    }
}
