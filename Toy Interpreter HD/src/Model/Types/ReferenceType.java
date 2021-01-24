package Model.Types;

import Model.Values.ReferenceValue;
import Model.Values.Value;

import java.util.Objects;

public class ReferenceType implements Type {
    Type inner;

    public ReferenceType() {
        inner = new IntegerType();
    }

    public ReferenceType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    public String toString() {
        return "Ref (" + inner.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ReferenceType) {
            return inner.equals(((ReferenceType) o).getInner());
        } else {
            return false;
        }
    }

    @Override
    public Value defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public Type createCopy() {
        return new ReferenceType(inner);
    }
}
