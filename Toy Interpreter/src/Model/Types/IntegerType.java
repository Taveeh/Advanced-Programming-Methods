package Model.Types;

import Model.Values.Value;

public class IntegerType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "int";
    }

}
