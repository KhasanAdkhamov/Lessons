package Enum.hw.task4;

public enum Operation {
    ADD() {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT() {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY() {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE() {
        @Override
        public double apply(double a, double b) {
            return a / b;
        }
    };

    public abstract double apply(double a,  double b);
}
