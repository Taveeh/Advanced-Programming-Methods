package Model.Values;

import Model.Types.ReferenceType;
import Model.Types.Type;

public class ReferenceValue implements Value {
    Integer address;
    Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}
