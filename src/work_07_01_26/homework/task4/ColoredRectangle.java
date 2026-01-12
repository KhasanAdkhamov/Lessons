package work_07_01_26.homework.task4;

public class ColoredRectangle implements Drawable{
    private double width;
    private double height;
    private String color;

    public ColoredRectangle(double width, double height, String color) {
        this.width = width;
        this.height = height;
        setColor(color);
    }

    @Override
    public void draw() {
        System.out.println("нарисован квадрат");
    }

    @Override
    public void display() {
        draw();
        Drawable.super.display();
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
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return (height + width) * 2;
    }
}
