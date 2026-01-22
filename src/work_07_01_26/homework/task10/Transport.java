package work_07_01_26.homework.task10;

public class Transport {
    protected String type;
    protected double speed;
    protected int capacity;

    public Transport(String type, double speed, int capacity) {
        this.type = type;
        this.speed = speed;
        this.capacity = capacity;
    }

    public Transport() {

    }

    public double travel(double distance) {
        return distance / speed;
    }

    public void displayInfo() {
        System.out.println("тип транспорта " + type + " скорость " + speed + " вместимость пассажиров " + capacity);
    }

    public String getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "type='" + type + '\'' +
                ", speed=" + speed +
                ", capacity=" + capacity +
                '}';
    }
}
