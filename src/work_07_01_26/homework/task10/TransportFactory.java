package work_07_01_26.homework.task10;

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
    }

//    public static Transport createOptimalTransport(double distance, int passengers) {
//        if (distance < 15 && passengers < 1) {
//            return new Bicycle();
//        } else if (distance < 80 && passengers < 4) {
//            return new Car();
//        } else if (distance < 150 && passengers < 30) {
//            return new Train();
//        } else {
//            return new Plane();
//        }
//    }
//}
