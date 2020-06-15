// Please, make refactoring of the code:
// class Person {
//     String childIDNumber;    
// }

// class Child {
//     int age;
//     String healthInfo;
//     String name;
//     String getHealthStatus(){ return name +" " + healthInfo; }
// }

// class Adult {
//     int age;
//     String healthInfo;
//     String passportNumber;   
//     String name;
//     String getHealthStatus(){ return name +" " + healthInfo; }
// }
// We know that adult  doesn't have childIDNumber.
// Child doesn't have passportNumber.
// Create a public constructor in each class to initialize all their fields (make the first parameter of type int).

class Person{
    String name;
    int age;
    String healthInfo;

    public Person(int age, String name, String healthInfo) {
        this.name = name;
        this.age = age;
        this.healthInfo = healthInfo;
    }
    
    public String getHealthStatus(){
        return healthInfo;
    }
    
}

class Child extends Person{
    private String childIDNumber;

    public Child(int age, String name, String healthInfo, String childIDNumber) {
        super(age, name, healthInfo);
        this.childIDNumber = childIDNumber;
    }
}



class Adult extends Person{
    private String passportNumber;

    public Adult(int age, String name, String healthInfo, String passportNumber) {
        super(age, name, healthInfo);
        this.passportNumber = passportNumber;
    }
}
