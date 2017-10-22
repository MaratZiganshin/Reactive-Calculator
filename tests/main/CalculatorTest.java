package main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getNumber() throws Exception {
        System.out.println("getNumber() invoked");
        String s1 = "a:543";
        double d = Calculator.getNumber(s1);
        assert(543 == d);
        s1 = "b:5.1";
        d = Calculator.getNumber(s1);
        assert(5.1 == d);
        s1 = "a:t";
        expectedException.expect(IllegalArgumentException.class);
        d = Calculator.getNumber(s1);
    }

    @Test
    public void getOperation() throws Exception {
        System.out.println("getOperation() invoked");
        char c = '+';
        Operation o = Calculator.getOperation(c);
        assertEquals(Operation.PLUS, o);
        c = '-';
        o = Calculator.getOperation(c);
        assertEquals(Operation.MINUS, o);
        c = '*';
        o = Calculator.getOperation(c);
        assertEquals(Operation.MULT, o);
        c = ':';
        o = Calculator.getOperation(c);
        assertEquals(Operation.DIV, o);
        c = '&';
        expectedException.expect(IllegalArgumentException.class);
        o = Calculator.getOperation(c);
    }

    @Test
    public void parseCommand() throws Exception {
        System.out.println("parseCommand() invoked");
        Calculator.parseCommand("a:5");
        assert(Calculator.getA() == 5);
        Calculator.parseCommand("b:34.1");
        assert(Calculator.getB() == 34.1);
        Calculator.parseCommand("*");
        assert(Calculator.getCurrentOperation().equals(Operation.MULT));
    }

}