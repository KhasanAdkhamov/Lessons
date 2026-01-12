package work_07_01_26.homework.task4;

import java.util.ArrayList;
import java.util.List;

public class DrawTest {
    public static void main(String[] args) {
        List<Drawable> differentFigure = new ArrayList<>();
        differentFigure.add(new ColoredCircle(5, "blue"));
        differentFigure.add(new ColoredRectangle(4, 3, "red"));
        displayAll(differentFigure);
    }

    public static void displayAll(List<Drawable> d) {
        for (Drawable drawable: d) {
            drawable.display();
        }
    }
}
