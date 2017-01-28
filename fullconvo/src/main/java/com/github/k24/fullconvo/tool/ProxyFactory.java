package com.github.k24.fullconvo.tool;

import com.github.k24.nullproxy.NullProxy;
import com.github.k24.nullproxy.ObjectUtil;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by k24 on 2017/01/26.
 */
public class ProxyFactory {
    public static final ProxyFactory DEFAULT = new ProxyFactory();
    private final NameNormalizer normalizerForGetter;
    private final NameNormalizer normalizerForSetter;

    public ProxyFactory() {
        this(NameNormalizer.forGetterName(), NameNormalizer.forSetterName());
    }

    public ProxyFactory(NameNormalizer normalizerForGetter, NameNormalizer normalizerForSetter) {
        this.normalizerForGetter = normalizerForGetter;
        this.normalizerForSetter = normalizerForSetter;
    }

    @SuppressWarnings("unchecked")
    public <T> T createProxyWithMap(@Nonnull Class<T> interfaceClass, Map<String, Object> map) {
        if (!interfaceClass.isInterface())
            throw new IllegalArgumentException(interfaceClass.getName() + " is not interface");
        if (map == null || map.isEmpty())
            return NullProxy.newProxyInstance(interfaceClass);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new ProxyWithMap(map));
    }

    private class ProxyWithMap implements InvocationHandler {
        private final Map<String, Object> map;

        ProxyWithMap(@Nonnull Map<String, Object> map) {
            this.map = map;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> returnType = method.getReturnType();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (returnType.equals(Void.class) && parameterTypes.length == 1) {
                // Setter
                map.put(normalizerForSetter.normalize(method.getName()), args[0]);
                return null;
            } else if (parameterTypes.length == 0) {
                // Getter
                Object value = map.get(normalizerForGetter.normalize(method.getName()));
                if (returnType.isPrimitive() && value == null) {
                    return ObjectUtil.getNull(returnType);
                }
                if ((returnType.isPrimitive() || Number.class.isAssignableFrom(returnType)) && value instanceof Number) {
                    Number number = (Number) value;
                    GetterFromNumber getterFromNumber = Holder.PRIMITIVE_FROM_NUMBER.get(returnType);
                    if (getterFromNumber == null) {
                        return value;
                    } else {
                        return getterFromNumber.get(number);
                    }
                }
                return value;
            } else {
                throw new UnsupportedOperationException(method + " cannot be resolved");
            }
        }
    }

    private static class Holder {
        public static final Map<Class<?>, GetterFromNumber> PRIMITIVE_FROM_NUMBER;

        static {
            HashMap<Class<?>, GetterFromNumber> map = new HashMap<Class<?>, GetterFromNumber>(12);

            map.put(byte.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.byteValue();
                }
            });
            map.put(Byte.class, map.get(byte.class));
            map.put(float.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.floatValue();
                }
            });
            map.put(Float.class, map.get(float.class));
            map.put(double.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.doubleValue();
                }
            });
            map.put(Double.class, map.get(double.class));
            map.put(int.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.intValue();
                }
            });
            map.put(Integer.class, map.get(int.class));
            map.put(long.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.longValue();
                }
            });
            map.put(Long.class, map.get(long.class));
            map.put(short.class, new GetterFromNumber() {
                @Override
                public Object get(Number number) {
                    return number.shortValue();
                }
            });
            map.put(Short.class, map.get(short.class));

            PRIMITIVE_FROM_NUMBER = Collections.unmodifiableMap(map);
        }

    }

    interface GetterFromNumber {
        Object get(Number number);
    }
}
