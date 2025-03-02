package Unused;

import java.lang.reflect.*;
import java.util.Arrays;

class GenericClass<T> { }


public class GenericTypeAnalyzer {
    public static void main(String[] args) {
        Type type = new GenericClass<String[]>() {}.getClass().getGenericSuperclass();
        
        if (type instanceof ParameterizedType) {
            Type[] typeArgs = ((ParameterizedType) type).getActualTypeArguments();
            System.out.println("isArray: " + ((ParameterizedType) type).getClass().isArray());
            System.out.println("Generic type: " + typeArgs[0] + ", " + Arrays.toString(typeArgs)); // Output: class java.lang.String
        }
    }
}