// Create a new version of the Circle class where the draw method will be overloaded four times:
// The version without parameters.
// Using the one parameter color of String type.
// Using the one parameter scale of float type.
// Using two parameters color and scale of String and float type.
// * For correct passing of all tests don't use print and println methods in your code.

class Circle{
    private String color;
    private float scale;

    public void draw() {
    }

    public void draw(String color) {
        this.color = color;
    }

    public void draw(float scale) {
        this.scale = scale;
    }

    public void draw(String color, float scale) {
        this.color = color;
        this.scale = scale;
    }
}
