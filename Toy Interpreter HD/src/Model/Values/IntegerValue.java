package Model.Values;

import Model.Types.IntegerType;
import Model.Types.Type;

public class IntegerValue implements Value {
    final int value;
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

    @Override
    public Value createCopy() {
        return new IntegerValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerValue)) return false;
        IntegerValue that = (IntegerValue) o;
        return value == that.value;
    }
}
