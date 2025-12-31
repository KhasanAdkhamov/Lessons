package lessonTest.samples.rectangle;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        setWidth(width);
        this.setHeight(getHeight());
    }

    public Rectangle(double width) {
        setWidth(width);
        this.setHeight(getHeight());
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (height + width);
    }

    public boolean isSquare() {
        if(width != height) {
            return false;
        }
        return true;
    }

    public void resize(double factor) {
        System.out.println(getArea() * factor);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width < 0) {
            throw new RuntimeException("it can not be 0");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height < 0) {
            throw new RuntimeException("it can not be 0");
        }
        this.height = height;
    }
}
