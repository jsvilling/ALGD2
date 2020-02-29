package ch.fhnw.algd2.l2.stackcomputer;

import java.io.IOException;

/**
 * Minimal Console UI Class for interacting with {@link StackComputer}
 */
public class StackComputerUI {
    private static final StackComputer computer = new StackComputer();

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Stack Computer.");
        System.out.println("Enter the operands for your equation.");
        char c;
        do {
            c = readNext();
            if (c != '\n' && c != ' ') {
                computer.addArgument(c);
                System.out.println(computer);
            }
        } while (c != 'q');
    }

    private static char readNext() throws IOException {
        return (char) System.in.read();
    }
}
