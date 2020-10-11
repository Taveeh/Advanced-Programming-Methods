package Model;

import java.util.Objects;

public class Pig implements Animal {
    private double weight;
    public Pig(double w) {
        weight = w;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String toString() {
        return "Pig has weight " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pig)) return false;
        Pig pig = (Pig) o;
        return Double.compare(pig.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
