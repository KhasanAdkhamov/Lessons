package Functional.HW.task8;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Реализуй собственный collector, который подсчитывает
// статистику по числам: min, max, avg, sum, count.
// Результат верни в виде объекта с этими полями.
public class MyCollector {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        MyCollectorStatistic myCollectorStatistic = getStatistic(list);
        System.out.println(myCollectorStatistic);


    }

    public static MyCollectorStatistic getStatistic(List<Integer> list) {
        IntSummaryStatistics intSummaryStatistics = list.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));


        MyCollectorStatistic myCollectorStatistic = new MyCollectorStatistic(
                intSummaryStatistics.getMin(),
                intSummaryStatistics.getMax(),
                (int) intSummaryStatistics.getAverage(),
                (int) intSummaryStatistics.getSum(),
                (int) intSummaryStatistics.getCount());
        return myCollectorStatistic;

    }

    public static class MyCollectorStatistic {
        int min;
        int max;
        int avg;
        int sum;
        int count;

        public MyCollectorStatistic(int min, int max, int avg, int sum, int count) {
            this.min = min;
            this.max = max;
            this.avg = avg;
            this.sum = sum;
            this.count = count;
        }

        @Override
        public String toString() {
            return "MyCollectorStatistic{" +
                    "min=" + min +
                    ", max=" + max +
                    ", avg=" + avg +
                    ", sum=" + sum +
                    ", count=" + count +
                    '}';
        }
    }


}
