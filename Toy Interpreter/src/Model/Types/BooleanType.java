package Model.Types;

import Model.Values.BooleanValue;
import Model.Values.Value;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }
}
