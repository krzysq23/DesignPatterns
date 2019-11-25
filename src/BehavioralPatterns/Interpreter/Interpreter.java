package BehavioralPatterns.Interpreter;

/**
 * Wzorzec interpretera służy do zdefiniowania gramatycznej reprezentacji języka
 * i zapewnia tłumacza do obsługi tej gramatyki.
 */
public class Interpreter {

    public InterpreterContext ic;
    public Interpreter(InterpreterContext i) { this.ic = i; }

    public String interpret(String str){
        Expression exp = null;
        if (str.contains("Hexadecimal")) {
            exp = new IntToHexExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
        } else if (str.contains("Binary")) {
            exp = new IntToBinaryExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
        } else return str;

        return exp.interpret(ic);
    }

    public static void main(String args[]){
        String str1 = "28 in Binary";
        String str2 = "28 in Hexadecimal";

        Interpreter ec = new Interpreter(new InterpreterContext());
        System.out.println(str1 + " = " + ec.interpret(str1));
        System.out.println(str2 + " = " + ec.interpret(str2));
    }
}

interface Expression {
    String interpret(InterpreterContext ic);
}

class InterpreterContext {
    public String getBinaryFormat(int i) { return Integer.toBinaryString(i); }
    public String getHexadecimalFormat(int i) { return Integer.toHexString(i); }
}

class IntToBinaryExpression implements Expression {
    private int i;
    public IntToBinaryExpression(int c) { this.i = c; }
    @Override
    public String interpret(InterpreterContext ic) { return ic.getBinaryFormat(this.i); }
}

class IntToHexExpression implements Expression {
    private int i;
    public IntToHexExpression(int c) { this.i = c; }
    @Override
    public String interpret(InterpreterContext ic) {
        return ic.getHexadecimalFormat(i);
    }
}
