// Generic Calculator that works with any subclass of Number
public class GenericCalculator<T extends Number> {

    // Addition
    public double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // Subtraction
    public double subtract(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    // Multiplication
    public double multiply(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    // Division (with simple zero check)
    public double divide(T a, T b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a.doubleValue() / b.doubleValue();
    }

    // Example: Display type info
    public void printType(T value) {
        System.out.println("Type: " + value.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        GenericCalculator<Integer> intCalc = new GenericCalculator<>();
        System.out.println("Integer Add: " + intCalc.add(10, 5));
        System.out.println("Integer Multiply: " + intCalc.multiply(3, 4));

        GenericCalculator<Double> doubleCalc = new GenericCalculator<>();
        System.out.println("Double Divide: " + doubleCalc.divide(10.5, 2.5));
        System.out.println("Double Subtract: " + doubleCalc.subtract(10.0, 3.5));
    }
}
