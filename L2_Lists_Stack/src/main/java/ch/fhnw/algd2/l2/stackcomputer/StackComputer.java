package ch.fhnw.algd2.l2.stackcomputer;

import ch.fhnw.algd2.l2.stack.Stack;

import java.util.HashMap;
import java.util.function.BiFunction;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

/**
 * Postfix Calculator implementation for ALGD2
 *
 * @author J. Villing
 */
public class StackComputer {

    private static final HashMap<Character, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();

    static {
        operations.put('+', StackComputer::add);
        operations.put('-', StackComputer::subtract);
        operations.put('/', StackComputer::divide);
        operations.put('*', StackComputer::multiply);
    }

    private final Stack<Character> operators = new Stack<>();
    private final Stack<Integer> operands = new Stack<>();

    private static Integer add(Integer first, Integer second) {
        return first + second;
    }

    private static Integer subtract(Integer first, Integer second) {
        return first - second;
    }

    private static Integer divide(Integer first, Integer second) {
        return first / second;
    }

    private static Integer multiply(Integer first, Integer second) {
        return first * second;
    }

    public void addArgument(Character arg) {
        if (isDigit(arg)) {
            addArgument(getNumericValue(arg));
        } else {
            operators.push(arg);
        }
        if (!operators.isEmpty() && operands.size() > 1) {
            calculate();
        }
    }

    public void addArgument(Integer arg) {
        operands.push(arg);
    }

    public void calculate() {
        BiFunction<Integer, Integer, Integer> calculation = operations.get(operators.pop());
        Integer first = operands.pop();
        Integer second = operands.pop();
        Integer newOpperand = calculation.apply(first, second);
        operands.push(newOpperand);
    }

    @Override
    public String toString() {
        return operands.toString() + '\n' + operators.toString();
    }
}
