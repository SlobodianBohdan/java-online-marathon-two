// Suppose we have the next class:
// 1. Create an instances of Employee class named 'emp1' and 'emp2'.
// 2. Set not null values for 'fullName' and 'salary' properties.
// 3. Create array of Employee type with name 'employees' and add two objects created before.
// 4. Create variable with name 'employeesInfo' of String type.
// 5. Using a loop, iterrate across array and write to variable named 'employeesInfo' info about each employee in next fommat:
// json
// * You don't need to create any classes and describe the main(...) method. Just write the code!
// * For correct passing of all tests don't use print and println methods in your code.
  
  
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        emp1.fullName = "Tania";
        emp1.salary = 1000;
        emp2.fullName = "Taras";
        emp2.salary =1100;

        Employee[] employees = {emp1,emp2};

        String employeesInfo = "[";
        for (int i = 0; i < employees.length; i++) {
            employeesInfo += "{fullName: \"" +employees[i].fullName + "\", salary: " + employees[i].salary + "}";
            if (i != employees.length - 1){
                employeesInfo += ", ";
            }else {
                employeesInfo += "]";
            }
        }
