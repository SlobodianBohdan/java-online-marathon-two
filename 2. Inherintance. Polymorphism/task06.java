// Let the code given
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

public class Task06 {
}

abstract class Shape {
    private String name;

    public Shape() {
    }

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return getName().equals(shape.getName());
    }

    @Override
    public int hashCode() {
        return name.length() * 31;
    }

    abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    public Circle() {

    }


    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }




    @Override
    double getArea() {
        return Math.PI * Math.sqrt(radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "name=" + "'" + getName() + '\'' +
                "radius=" + radius +
                '}';
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return  getName().compareTo(circle.getName()) == 0 &&
                Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    @Override
    public int hashCode() {
        return (getName().length() + (int) radius)*31;
    }
}

class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle() {

    }

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    @Override
    double getArea() {
        return height * width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name=" + "'" + getName() + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return  getName().compareTo(rectangle.getName()) == 0 &&
                Double.compare(rectangle.getHeight(), getHeight()) == 0 &&
                Double.compare(rectangle.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        return (getName().length() + (int) getHeight() + (int) getWidth()) * 31;
    }
}

class MyUtils6 {
    public List<Shape> maxAreas(List<Shape> shapes) {
        List<Shape> list = new ArrayList<>();
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        if (shapes.isEmpty() || shapes.get(0) == null){
            return list;
        }
        for (Shape s : shapes) {
            if (s instanceof Circle) {
                if (s.getArea() > circle.getArea()) {
                    circle = s;
                }
            }
            if (s instanceof Rectangle) {
                if (s.getArea() > rectangle.getArea()) {
                    rectangle = s;
                }
            }

        }

        if (circle.getName() != null ) {
            list.add(circle);
        }
        if (rectangle.getName() != null) {
            list.add(rectangle);
        }

        for (Shape s : shapes) {
            if (s instanceof Circle) {
                if (s.getArea() == circle.getArea() && !s.equals(circle)) {
                    list.add(s);
                }
            }
            if (s instanceof Rectangle) {
                if (s.getArea() == rectangle.getArea()&& !s.equals(rectangle)) {
                    list.add(s);
                }
            }

        }
        return list;
    }
}
