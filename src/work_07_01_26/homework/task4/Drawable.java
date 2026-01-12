package work_07_01_26.homework.task4;

public interface Drawable extends Shape2D, Colorable{
    void draw();
    default void display() {
        System.out.println("Фигура: площадь = " + getArea() + ", цвет = " + getColor());
    }
}
