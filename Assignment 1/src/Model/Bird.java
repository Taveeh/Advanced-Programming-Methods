package Model;

import java.util.Objects;

public class Bird implements Animal {
    private double weight;
    public Bird(double w) {
        weight = w;
    }
    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Bird has weight " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bird)) return false;
        Bird bird = (Bird) o;
        return Double.compare(bird.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
