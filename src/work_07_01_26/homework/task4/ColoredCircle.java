package work_07_01_26.homework.task4;

public class ColoredCircle implements Drawable{
    private double radius;
    private String color;

    public ColoredCircle(double radius, String color) {
        this.radius = radius;
        setColor(color);
    }

    @Override
    public void draw() {
        System.out.println("нарисован круг");
    }

    @Override
    public void setColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new NullPointerException("");
        }
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public double getArea() {
        return Math.PI *Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void display() {
        draw();
        Drawable.super.display();
    }
}
