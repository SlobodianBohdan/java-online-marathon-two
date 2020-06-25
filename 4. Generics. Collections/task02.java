import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

class Person{
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Employee extends Person {

    private double Salary;

    public Employee(String name, int age, double salary) {
        super(name, age);
        Salary = salary;
    }

    public double getSalary() {
        return Salary;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "Salary=" + Salary +
                '}';
    }
}

class Developer extends Employee{

    private Level level;

    public Developer(String name, int age, double salary, Level level) {
        super(name, age, salary);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Developer{" + super.toString() +
                "level=" + level +
                '}';
    }
}

enum Level{
    JUNIOR, MIDDLE, SENIOR;
}


class PersonComparator implements Comparator<Object> {
    Comparator<Person> comparator =  Comparator.comparing(Person::getName)
            .thenComparing(Person::getAge);

    @Override
    public int compare(Object o1, Object o2) {
        return comparator.compare((Person) o1,(Person) o2);
    }

}

class EmployeeComparator implements Comparator<Object>{
    Comparator<Employee> comparator =
            Comparator.comparing(Employee::getName)
                    .thenComparing(Employee::getAge)
                    .thenComparing(Employee::getSalary);

    @Override
    public int compare(Object o1, Object o2) {
        return comparator.compare((Employee) o1,(Employee) o2);
    }
}

class DeveloperComparator implements Comparator<Object>{
    Comparator<Developer> comparator =
            Comparator.comparing(Developer::getName)
                    .thenComparing(Developer::getAge)
                    .thenComparing(Developer::getSalary)
                    .thenComparing(Developer::getLevel);

    @Override
    public int compare(Object o1, Object o2) {
        return comparator.compare((Developer) o1,(Developer) o2);
    }
}

class Utility {

    public static <T extends Person> void sortPeople(T[] array, Comparator<Object> comparator){
        Arrays.sort(array, comparator);
    }

}
