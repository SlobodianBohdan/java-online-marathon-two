// Create annotation Review with two string elements: reviewer and date.
// Element date has default string value today.
// This annotation can be applied to class when we execute static method review(String className) of class Util and the result of this method will be printed Class <ClassName> was reviewed <date in format yyyy-mm-dd> by <reviewer> to standard output (console).
// If the class <className> isn’t annotated we show message: Class <ClassName> isn't marked as Reviewed.
// If the class was not found we show message: Class <ClassName> was not found.

import java.lang.annotation.*;
import java.time.LocalDate;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface Review{
    String reviewer();
    String date() default "today";
}

class Util{
    static void review(String className){
        String result = "Class " + className;
        try {
            Class<?> clazz = Class.forName(className);
            Review review = clazz.getAnnotation(Review.class);
            if (clazz.isAnnotationPresent(Review.class)) {
                String date = "today".equals(review.date())? LocalDate.now().toString() : review.date();
                result += " was reviewed " + date + " by " + review.reviewer() + ".";
            }else {
                result += " isn't marked as Reviewed";
            }
        }catch (ClassNotFoundException e){
            result += " was not found";
        }
        System.out.println(result);
    }
}
