// Let the code given.
// Please create class Shape with abstract method to calculate area of figure and field name. Replace code in method getArea() according to principles of polymorphism i.e. Circle and Rectangle classes extends Shape class and override double getArea() method. Develop maxAreas() method of the MyUtils class to return a List with instances of maximum area.
// The original list must be unchanged.
// For example, for a given list
// [Circle [radius=2.00], Rectangle [height=2.00, width=3.00], Circle [radius=1.00], Rectangle [height=3.00, width=2.00],  Circle [radius=0.50], Rectangle [height=1.00, width=2.00]]
// you should get
// [Circle [radius=2.00], Rectangle [height=2.00, width=3.00], Rectangle [height=3.00, width=2.00]]


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }
}
class Student extends Person {
    private String studyPlace;
    private int studyYears;

    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }
}
class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}
class MyUtils {
    public List<Person> maxDuration(List<Person> persons) {
        List<Person> list = new ArrayList<>();
        
        return null;
    }
}
