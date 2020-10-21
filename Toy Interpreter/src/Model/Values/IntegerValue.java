package Model.Values;

import Model.Types.IntegerType;
import Model.Types.Type;

public class IntegerValue implements Value {
    int value;
    public IntegerValue(int val) {
        value = val;
    }

    public IntegerValue() {
        value = 0;
    }

    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
