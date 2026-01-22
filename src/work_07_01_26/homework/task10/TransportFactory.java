package work_07_01_26.homework.task10;

import java.util.ArrayList;
import java.util.List;

public class TransportFactory {

    public static Transport createTransport(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "train" -> new Train();
            case "plane" -> new Plane();
            case "bicycle" -> new Bicycle();
            default -> throw new NullPointerException("");
        };
    }

    public static Transport createOptimalTransport(double distance, int passengers) {
        List<Transport> Transports = List.of(
                new Bicycle(),
                new Car(),
                new Train(),
                new Plane());
        Transport optimalTransport = null;
        double minTime = Double.MAX_VALUE;
        for (Transport transport : Transports) {
            if (transport.getCapacity() >= passengers) {
                double time = transport.travel(distance);
                if (time < minTime) {
                    minTime = time;
                    optimalTransport = transport;
                    break;
                }
            }
        }
        return optimalTransport;
    }

    }

