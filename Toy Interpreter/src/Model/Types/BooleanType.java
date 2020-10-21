package Model.Types;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }
}
