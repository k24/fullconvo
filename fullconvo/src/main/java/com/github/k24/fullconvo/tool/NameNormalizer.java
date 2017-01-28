package com.github.k24.fullconvo.tool;

/**
 * Created by k24 on 2017/01/27.
 */
public abstract class NameNormalizer {
    public abstract String normalize(String name);

    public static NameNormalizer forGetterName() {
        return Holder.GETTER;
    }

    public static NameNormalizer forSetterName() {
        return Holder.SETTER;
    }

    private static class Holder {
        private static final NameNormalizer GETTER = new NameNormalizer() {
            @Override
            public String normalize(String name) {
                return normalizeGetterName(name);
            }
        };
        private static final NameNormalizer SETTER = new NameNormalizer() {
            @Override
            public String normalize(String name) {
                return normalizeSetterName(name);
            }
        };
    }

    protected static String normalizeGetterName(String name) {
        if (shouldTrip("get", name)) {
            return decapitalize(name.substring(3));
        } else if (shouldTrip("is", name)) {
            return decapitalize(name.substring(2));
        } else {
            return name;
        }
    }

    protected static String normalizeSetterName(String name) {
        return shouldTrip("set", name) ? decapitalize(name.substring(3)) : name;
    }

    protected static String decapitalize(String name) {
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    protected static boolean shouldTrip(String prefix, String name) {
        int length = prefix.length();
        return name.startsWith(prefix) && name.length() > length && name.charAt(length) == Character.toUpperCase(name.charAt(length));
    }
}
