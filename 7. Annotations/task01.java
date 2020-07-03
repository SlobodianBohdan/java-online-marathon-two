// Create marker-annotation CamelCase which will check whether method named according to code conventions. 
// Create class CheckCamelCase for checking if all the annotated methods of some class satisfy naming pattern. 
// This class contains constant 'CAMELCASE_PATTERN' that introduces regex for checking method name. 
// Also this class contains method checkAndPrint(Class clazz) which returns true if 
// all annotated methods of class satisfy 'CAMELCASE_PATTERN' and prints to standard output information about
// all incorrect method names by template: method <className>.<methodName> doesn't satisfy camelCase naming convention. 

// For example
// For class 
// public class Class1{
// @CamelCase
// public void correct(){} 
// @CamelCase
// public void InCorrect(){} 
// public void JustMethod(){}

// call CheckCamelCase.checkAndPrint(Class1.class) 
// prints to console 
// method Class1.InCorrect doesn't satisfy camelCase naming convention


import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Field;


public class Task01 {
    public static void main(String[] args) throws NoSuchFieldException {
        CheckCamelCase.checkAndPrint(ClassForAnnot.class);
    }
}

class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}

class Class1 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void InCorrect() {
    }

    @CamelCase
    public void JustMethod() {
    }
}

class Class2 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void oneMoreCorrect() {
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@interface CamelCase {
}

class CheckCamelCase {
    public static final String CAMELCASE_PATTERN = "^[a-z]+[a-zA-Z0-9]*$";

    static boolean checkAndPrint(Class clazz) {
        Method arrayMethods[] = clazz.getDeclaredMethods();
        for (Method method : arrayMethods) {
            if (method.isAnnotationPresent(CamelCase.class) && !method.getName().matches(CAMELCASE_PATTERN)) {
                System.out.println("method " + clazz.getName() + "." + method.getName() + " doesn't satisfy camelCase naming convention");
            }
        }
        return true;
    }
}
