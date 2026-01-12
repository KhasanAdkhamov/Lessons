package work_27_12_25.tasks;

public class Temperature {

    private  double celsius;
    private static final double MINUS_TEMP = -273.15;
    private static final double PlUS_TEMP = 273.15;

    public Temperature(double celsius) {
        setCelsius(celsius);
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return celsius * 9/5 + 32;
    }

    public double getKelvin() {
        return celsius + PlUS_TEMP;
    }

    public void setCelsius(double celsius) {
        if(celsius < MINUS_TEMP) {
            throw new RuntimeException("Не может быть меньше нуля");
        }
        this.celsius = celsius;
    }

    public void setFahrenheit(double fahrenheit) {
        this.celsius = (fahrenheit - 32) / 1.8;
    }
}
