package Unused;

import java.lang.reflect.*;
import java.util.Arrays;

class GenericClass<T> { }

class TestClass<T extends Number>{
    Type type2 = new GenericClass<T>() {}.getClass().getGenericSuperclass();;
    
	public TestClass() {
        Type type = new GenericClass<T>() {}.getClass().getGenericSuperclass();
        
        if (type instanceof ParameterizedType) {
            Type[] typeArgs = ((ParameterizedType) type).getActualTypeArguments();
            System.out.println("isArray: " + ((ParameterizedType) type).getClass().isArray());
            System.out.println("Generic type: " + typeArgs[0] + ", " + Arrays.toString(typeArgs)); // Output: class java.lang.String
        }
        
        if (type2 instanceof ParameterizedType) {
            Type[] typeArgs = ((ParameterizedType) type2).getActualTypeArguments();
            System.out.println("isArray: " + ((ParameterizedType) type2).getClass().isArray());
            System.out.println("Generic type: " + typeArgs[0] + ", " + Arrays.toString(typeArgs)); // Output: class java.lang.String
        }        
	}
}

public class GenericTypeAnalyzer {
    public static void main(String[] args) {
    	TestClass<Integer> tc = new TestClass<Integer>();
    	System.out.println("Done");
    }
}