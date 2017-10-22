package main;

import java.util.Scanner;

public class Calculator {

    /**
     * Operation, that will be executed, after any subsequent change in a and b
     */
    private static Operation currentOperation = Operation.NONE;
    /**
     * "a" collector
     */
    private static double a;
    /**
     * "b" collector
     */
    private static double b;
    /**
     * If "a" initialized
     */
    private static boolean isInitA = false;
    /**
     * If "b" initialized
     */
    private static boolean isInitB = false;

    /**
     * Getter for a
     * @return a
     */
    public static double getA(){
        return a;
    }

    /**
     * Getter for b
     * @return b
     */
    public static double getB(){
        return b;
    }

    /**
     * Getter for currentOperation
     * @return currentOperation
     */
    public static Operation getCurrentOperation(){
        return currentOperation;
    }

    /**
     * Take number from line, like "a:[number].
     * @throws IllegalArgumentException if line can't be parsed
     * @param line input line, that must be parsed
     * @return Number from line
     */
    public static double getNumber(String line) throws IllegalArgumentException{
        double t;
        try{
            t = Double.parseDouble(line.substring(2,line.length()));
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Attempt to initialize variable with illegal value");
        }
        return t;
    }

    /**
     * @param sign character of operation
     * @return Operation appropriate sign
     * @throws IllegalArgumentException if no operation appropriate sign
     */
    public static Operation getOperation(char sign) throws IllegalArgumentException{
        switch (sign){
            case '+':
                return Operation.PLUS;
            case '*':
                return Operation.MULT;
            case ':':
                return Operation.DIV;
            case '-':
                return Operation.MINUS;
            default:
                throw new IllegalArgumentException("No such operation!");
        }
    }

    /**
     * Prints expression for current operation
     * @throws IllegalArgumentException if "a" of "b" is not initialized
     */
    public static void print() throws IllegalArgumentException{
        if (!isInitA){
            throw new IllegalArgumentException("\"a\" is not initialized");
        }
        if (!isInitB){
            throw new IllegalArgumentException("\"b\" is not initialized");
        }
        else if (!currentOperation.equals(Operation.NONE))
            System.out.println(currentOperation.result(a,b));
    }

    /**
     * Parses command and calls corresponding method
     * @param command Input command
     * @throws IllegalArgumentException if command can't be parsed correctly
     */
    public static void parseCommand(String command)  throws IllegalArgumentException{
        if (command.length() == 1) {
            currentOperation = getOperation(command.charAt(0));
            print();
        }
        else if (command.substring(0,2).equals("a:") && command.length() > 2){
            a = getNumber(command);
            isInitA = true;
            if (isInitB){
                print();
            }
        }
        else if (command.substring(0,2).equals("b:") && command.length() > 2) {
            b = getNumber(command);
            isInitB = true;
            if (isInitA){
                print();
            }
        }
        else {
            throw new IllegalArgumentException("Illegal command!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        while (!"exit".equals(line = input.nextLine())){
            try {
                parseCommand(line);
            }
            catch (IllegalArgumentException | ArithmeticException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
