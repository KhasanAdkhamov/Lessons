package lessonTest.samples.temperature;

public class Temperature {
    private double celsius;
    private static final double MAX_TEMP = 273.15;
    private static final double MIN_TEMP = -273.15;

    public Temperature(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    public double getKelvin() {
        return celsius + MAX_TEMP;
    }

    public void setCelsius(double celsius) {
        if(celsius < MIN_TEMP) {
            throw new RuntimeException("you can not enter < -273.15");
        }
        this.celsius = celsius;
    }

    public void setFahrenheit(double fahrenheit) {
        this.celsius = (fahrenheit - 32) / 1.8;
    }
}
