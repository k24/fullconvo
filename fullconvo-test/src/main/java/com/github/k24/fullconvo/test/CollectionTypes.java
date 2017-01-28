package com.github.k24.fullconvo.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by k24 on 2016/12/25.
 */
public class CollectionTypes {
    public int[] intArrayValue;
    public String[] stringArrayValue;
    public Set<String> setValue;
    public List<String> listValue;
    public Map<Integer, String> mapValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionTypes that = (CollectionTypes) o;

        if (!Arrays.equals(intArrayValue, that.intArrayValue)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(stringArrayValue, that.stringArrayValue)) return false;
        if (setValue != null ? !setValue.equals(that.setValue) : that.setValue != null) return false;
        if (listValue != null ? !listValue.equals(that.listValue) : that.listValue != null) return false;
        return mapValue != null ? mapValue.equals(that.mapValue) : that.mapValue == null;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(intArrayValue);
        result = 31 * result + Arrays.hashCode(stringArrayValue);
        result = 31 * result + (setValue != null ? setValue.hashCode() : 0);
        result = 31 * result + (listValue != null ? listValue.hashCode() : 0);
        result = 31 * result + (mapValue != null ? mapValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CollectionTypes{" +
                "intArrayValue=" + Arrays.toString(intArrayValue) +
                ", stringArrayValue=" + Arrays.toString(stringArrayValue) +
                ", setValue=" + setValue +
                ", listValue=" + listValue +
                ", mapValue=" + mapValue +
                '}';
    }
}
