// GenericCalculator.java
class GenericCalculator<T extends Number> {
    
    // Method for Addition
    public double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // Method for Subtraction
    public double subtract(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    // Method for Multiplication
    public double multiply(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    // Method for Division
    public double divide(T a, T b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a.doubleValue() / b.doubleValue();
    }
}

// Test class
public class Main {
    public static void main(String[] args) {
        GenericCalculator<Integer> intCalc = new GenericCalculator<>();
        GenericCalculator<Double> doubleCalc = new GenericCalculator<>();

        System.out.println("Integer Calculator:");
        System.out.println("Addition: " + intCalc.add(10, 5));
        System.out.println("Subtraction: " + intCalc.subtract(10, 5));
        System.out.println("Multiplication: " + intCalc.multiply(10, 5));
        System.out.println("Division: " + intCalc.divide(10, 5));

        System.out.println("\nDouble Calculator:");
        System.out.println("Addition: " + doubleCalc.add(12.5, 3.7));
        System.out.println("Subtraction: " + doubleCalc.subtract(12.5, 3.7));
        System.out.println("Multiplication: " + doubleCalc.multiply(12.5, 3.7));
        System.out.println("Division: " + doubleCalc.divide(12.5, 3.7));
    }
}
