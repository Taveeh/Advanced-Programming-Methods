package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;
import com.sun.nio.sctp.AbstractNotificationHandler;

public class StringValue implements Value {
    String value;
    @Override
    public Type getType() {
        return new StringType();
    }
    public StringValue(String s) {
        value = s;
    }
    public StringValue() {
        value = "";
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringValue)) return false;
        StringValue that = (StringValue) o;
        return value.equals(that.value);
    }
}
