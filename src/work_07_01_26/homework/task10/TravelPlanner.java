package work_07_01_26.homework.task10;

import java.util.List;

public class TravelPlanner {
    public void planTrip(double distance, int passengers) {
        System.out.println("Планирование поездки");
        System.out.printf("расстояние %.2f, кол-во пассажиров %d ", distance, passengers);

        List<Transport> transportList = List.of(
                TransportFactory.createTransport("car"),
                TransportFactory.createTransport("bicycle"),
                TransportFactory.createTransport("train"),
                TransportFactory.createTransport("plane")
        );
        System.out.println("транспорт на котором можно поехать");

        Transport bestTransport = null;
        double bestTime = Double.MAX_VALUE;
        for (Transport transport : transportList) {
            transport.displayInfo();
            if (transport.getCapacity() >= passengers) {
                double time = transport.travel(distance);
                if (time < bestTime) {
                    bestTime = time;
                    bestTransport = transport;
                }
                break;
            }
        }

        System.out.println("оптимальный транспорт " + bestTransport);
    }
}
