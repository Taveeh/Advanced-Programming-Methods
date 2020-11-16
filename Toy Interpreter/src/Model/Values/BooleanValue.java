package Model.Values;

import Model.Types.BooleanType;
import Model.Types.Type;

public class BooleanValue implements Value {
    final Boolean value;
    public BooleanValue(Boolean val) {
        value = val;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public BooleanValue() {
        value = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanValue)) return false;
        BooleanValue that = (BooleanValue) o;
        return value.equals(that.value);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }


}
