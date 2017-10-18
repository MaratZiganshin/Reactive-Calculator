package main;

import java.util.Scanner;

public class Calculator {

    private static Operation currentOperation = Operation.NONE;
    private static double a;
    private static double b;
    private static boolean isInitA = false;
    private static boolean isInitB = false;

    public static double getNumber(String line){
        double t;
        try{
            t = Double.parseDouble(line.substring(2,line.length()));
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Attempt to initialize variable with illegal value");
        }
        return t;
    }

    public static Operation getOperation(char sign){
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

    public static void print() {
        if (!isInitA){
            throw new IllegalArgumentException("\"a\" is not initialized");
        }
        if (!isInitB){
            throw new IllegalArgumentException("\"b\" is not initialized");
        }
        else if (!currentOperation.equals(Operation.NONE))
            System.out.println(currentOperation.result(a,b));
    }

    public static void parseCommand(String command){
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
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
