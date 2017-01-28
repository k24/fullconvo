package com.github.k24.fullconvo.test;

/**
 * Created by k24 on 2016/12/25.
 */
public class FieldOnly {
    public boolean booleanValue;
    public double doubleValue;
    public float floatValue;
    public int intValue;
    public long longValue;
    public String stringValue;

    private String privateValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldOnly fieldOnly = (FieldOnly) o;

        if (intValue != fieldOnly.intValue) return false;
        if (booleanValue != fieldOnly.booleanValue) return false;
        if (Float.compare(fieldOnly.floatValue, floatValue) != 0) return false;
        if (Double.compare(fieldOnly.doubleValue, doubleValue) != 0) return false;
        if (longValue != fieldOnly.longValue) return false;
        if (stringValue != null ? !stringValue.equals(fieldOnly.stringValue) : fieldOnly.stringValue != null)
            return false;
        return privateValue != null ? privateValue.equals(fieldOnly.privateValue) : fieldOnly.privateValue == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = intValue;
        result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
        result = 31 * result + (booleanValue ? 1 : 0);
        result = 31 * result + (floatValue != +0.0f ? Float.floatToIntBits(floatValue) : 0);
        temp = Double.doubleToLongBits(doubleValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + (privateValue != null ? privateValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FieldOnly{" +
                "booleanValue=" + booleanValue +
                ", doubleValue=" + doubleValue +
                ", floatValue=" + floatValue +
                ", intValue=" + intValue +
                ", longValue=" + longValue +
                ", stringValue='" + stringValue + '\'' +
                ", privateValue='" + privateValue + '\'' +
                '}';
    }
}
