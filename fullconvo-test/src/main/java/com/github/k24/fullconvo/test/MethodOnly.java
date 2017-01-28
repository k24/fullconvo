package com.github.k24.fullconvo.test;

/**
 * Created by k24 on 2016/12/25.
 */
public class MethodOnly {
    private boolean booleanValue;
    private double doubleValue;
    private float floatValue;
    private int intValue;
    private long longValue;
    private String stringValue;

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodOnly that = (MethodOnly) o;

        if (booleanValue != that.booleanValue) return false;
        if (Double.compare(that.doubleValue, doubleValue) != 0) return false;
        if (Float.compare(that.floatValue, floatValue) != 0) return false;
        if (intValue != that.intValue) return false;
        if (longValue != that.longValue) return false;
        return stringValue != null ? stringValue.equals(that.stringValue) : that.stringValue == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (booleanValue ? 1 : 0);
        temp = Double.doubleToLongBits(doubleValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (floatValue != +0.0f ? Float.floatToIntBits(floatValue) : 0);
        result = 31 * result + intValue;
        result = 31 * result + (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MethodOnly{" +
                "booleanValue=" + booleanValue +
                ", doubleValue=" + doubleValue +
                ", floatValue=" + floatValue +
                ", intValue=" + intValue +
                ", longValue=" + longValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
