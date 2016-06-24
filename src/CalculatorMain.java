import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by syjansse on 23/06/2016.
 */
public class CalculatorMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;
        Map<Character, Binary> operations = getOperations();
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<Character> inputOperators = new ArrayList<>();

        boolean done = false;

        while (done == false) {
            System.out.print("Please enter your operation: ");


            while (scanner.hasNext()) {
                doubles.add(scanner.nextDouble());
                inputOperators.add(scanner.next().charAt(0));
            }

            for (int i = 0; i < inputOperators.size(); i++) {
                Character c = inputOperators.get(i);
                if (c == '*' || c == '/') {
                    double temp = calculate(doubles.get(i), doubles.get(i + 1), c, operations);
                    for (int ii = i; ii < inputOperators.size(); ii++) {
                        Character cc = inputOperators.get(i);
                        if (cc == '*' || cc == '/') {
                            temp = +calculate(temp, doubles.get(ii), cc, operations);
                        }
                    }
                    result = +temp;
                } else if (c == '+' || c == '-') {
                    double temp = calculate(doubles.get(i), doubles.get(i + 1), c, operations);
                }
                done = true;
            }
        }
        System.out.println(result);
    }

    static Map<Character, Binary> getOperations() {
        Map<Character, Binary> binaryOperationMap = new HashMap<>();
        binaryOperationMap.put('*', new Multiply());
        binaryOperationMap.put('+', new Add());
        binaryOperationMap.put('/', new Divide());
        binaryOperationMap.put('-', new Substract());
        return binaryOperationMap;

    }

    static double calculate(double a, double b, Character operator, Map<Character, Binary> operations) {
        Binary op = operations.get(operator);
        return op.binaryFunction(a, a);
    }


}
