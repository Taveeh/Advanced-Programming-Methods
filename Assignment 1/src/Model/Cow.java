package Model;

import java.util.Objects;

public class Cow implements Animal {
    private double weight;
    public Cow(double w) {
        weight = w;
    }
    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Cow has weight " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cow)) return false;
        Cow cow = (Cow) o;
        return Double.compare(cow.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
