/**
 * @author Jayden Chan
 * @version 1
 * Date created: Feb 2 2018
 *
 * Tests {@code StringStack, PostfixTokenizer,} and {@code Evaluator} classes.
*/

public class Tester {

    private static final double MULT_TOLLERANCE = 1e-5;
    private static final double ADD_TOLLERANCE  = 1e-12;

    private static final String C_RESET  = "\u001B[0m";
    private static final String C_BLACK  = "\u001B[30m";
    private static final String C_RED    = "\u001B[31m";
    private static final String C_GREEN  = "\u001B[32m";
    private static final String C_YELLOW = "\u001B[33m";
    private static final String C_BLUE   = "\u001B[34m";
    private static final String C_PURPLE = "\u001B[35m";
    private static final String C_CYAN   = "\u001B[36m";
    private static final String C_WHITE  = "\u001B[37m";

    public static void main(String args[]) {
        printGreeting();
        waitForUser("StringStack");
        testStringStack();
        waitForUser("PostfixTokenizer");
        testPostfixTokenizer();
        waitForUser("Evaluator");
        testEvaluator();
    }

    private static void testStringStack() {
        System.out.printf(C_CYAN + "----- BEGIN STRING STACK TEST -----\n\n" + C_RESET);

        StringStack stack = new StringStack();

        for(int i = 0; i < 6; i++) {
            try {
                stack.push(""+i*2);
            }
            catch(StackException e) {
                System.out.printf(C_RED + "TEST %d FAILED: " + C_RESET + "Stack threw exception when it should not have. (push method) \n", i+1);
                System.out.print("Message: ");
                System.out.println(e.getMessage());
                break;
            }
            System.out.printf(C_BLUE + "Test: " + C_RESET + "%-2d ", i+1);
            System.out.printf(C_BLUE + "Operation: " + C_RESET + "PUSH     ");
            System.out.printf(C_BLUE + "Expected: " + C_RESET + "%-3d ", i*2);
            try {
                System.out.printf(C_BLUE + "Actual: " + C_RESET + "%-3s ", stack.peek());
            }
            catch(StackException e) {
                System.out.printf(C_RED + "TEST %d FAILED: " + C_RESET + "Stack threw exception when it should not have. (peek method)", i+1);
            }
            try {
                if(assertEquals(i*2.0, Double.parseDouble(stack.peek()), ADD_TOLLERANCE)) {
                    System.out.printf(C_BLUE + "Result: " + C_GREEN + "PASS\n" + C_RESET);
                }
                else {
                    System.out.printf(C_BLUE + "Result: " + C_RED + "FAIL\n" + C_RESET);
                }
            }
            catch(NumberFormatException e) {
                System.out.printf(C_RED + "TEST %d FAILED: " + C_RESET + "Stack contained invalid string.\n", i+1);
            }
        }

        for(int i = 6; i < 12; i++) {
            String item;
            try {
                item = stack.pop();
            }
            catch(StackException e) {
                System.out.printf(C_RED + "TEST %d FAILED: " + C_RESET + "Stack threw exception when it should not have.\n", i+1);
                break;
            }

            System.out.printf(C_BLUE + "Test: " + C_RESET + "%-2d ", i+1);
            System.out.printf(C_BLUE + "Operation: " + C_RESET + "POP      ");
            System.out.printf(C_BLUE + "Expected: " + C_RESET + "%-3d ", 10-(i-6)*2);
            System.out.printf(C_BLUE + "Actual: " + C_RESET + "%-3s ", item);
            try {
                if(assertEquals(10-(i-6)*2, Double.parseDouble(item), ADD_TOLLERANCE)) {
                    System.out.printf(C_BLUE + "Result: " + C_GREEN + "PASS\n" + C_RESET);
                }
                else {
                    System.out.printf(C_BLUE + "Result: " + C_RED + "FAIL\n" + C_RESET);
                }
            }
            catch(NumberFormatException e) {
                System.out.printf(C_RED + "TEST %d FAILED: " + C_RESET + "Stack contained invalid string.\n", i+1);
            }

        }
        System.out.println();

        try {
            stack.pop();
            printTest(13, "POP EXCEPTION", "Exception thrown", "Not thrown", false);
            System.out.println(C_RED + "ERROR: " + C_RESET + "StackException was not thrown when popping from empty stack.\n");
        }
        catch(StackException e1) {
            printTest(13, "POP EXCEPTION", "Exception thrown", "Thrown", true);
        }
        catch(Exception e2) {
            printFail(13, "An unknown error occurred while popping from stack.", e2.getMessage());
        }

        try {
            stack.peek();
            printTest(14, "PEEK EXCEPTION", "Exception thrown", "Not thrown", false);
            System.out.println(C_RED + "ERROR: " + C_RESET + "StackException was not thrown when peeking empty stack.\n");
        }
        catch(StackException e1) {
            printTest(14, "PEEK EXCEPTION", "Exception thrown", "Thrown", true);
        }
        catch (Exception e2) {
            printFail(14, "An unknown error occurred while peeking from stack.", e2.getMessage());
        }

        try {
            stack.popAll();
            if(stack.isEmpty()) {
                printTest(15, "POP ALL", "null", "null", true);
            }
            else {
                printTest(15, "POP ALL", "null", stack.peek(), false);
            }
            printTest(16, "POP ALL", "No exception ", "Not thrown", true);
        }
        catch(StackException e1) {
            printTest(16, "POP ALL", "No exception", "Thrown", false);
            System.out.println(C_RED + "\nERROR: " + C_RESET + "StackException was thrown when running popAll(). This method should not throw an exception.\n");
        }
        catch(Exception e2) {
            printFail(16, "An unknown error occurred while running popAll().", e2.getMessage());
        }

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        try {
            stack.popAll();
            if(stack.isEmpty()) {
                printTest(17, "POP ALL", "null", "null", true);
            }
            else {
                printTest(17, "POP ALL", "null", stack.peek(), false);
            }
        }
        catch(StackException e) {
            System.out.println();
            printFail(17, "Stack threw exception when it should not have. (popAll method)", e.getMessage());
        }

        printTest(18, "IS EMPTY", "true", String.valueOf(stack.isEmpty()), stack.isEmpty());

        stack.push("7");

        printTest(19, "IS EMPTY", "false", String.valueOf(stack.isEmpty()), !stack.isEmpty());

        System.out.printf(C_CYAN + "\n----- END STRING STACK TEST -----\n\n" + C_RESET);
    }

    private static void testPostfixTokenizer() {
        System.out.printf(C_CYAN + "----- BEGIN POSTFIX TOKENIZER TEST -----\n\n" + C_RESET);

        OperatorTokenizer ops[] = new OperatorTokenizer[10];
        PostfixTokenizer posts[] = new PostfixTokenizer[10];

        String expressions[] = new String[10];
        String real[] = new String[10];
        String eval[] = new String[10];
        int numNextsReal[] = new int[]{9, 9, 5, 13, 11, 3, 11, 13, 5, 7};

        expressions[0] = "(9-1)-(3+1)*3";
        expressions[1] = "(4+4)-(10--8)*4";
        expressions[2] = "(5+1)^2";
        expressions[3] = "5+1-5*6-1+3^2";
        expressions[4] = "7*1-8+9-4^2";
        expressions[5] = "7-1";
        expressions[6] = "3+1*6-2+4*7";
        expressions[7] = "(2*((8-34)+(9+13)*1.5))/3";
        expressions[8] = "(7-7)*2";
        expressions[9] = "(7+3)*6^2";

        real[0] = "9 1 - 3 1 + 3 * -";
        real[1] = "4 4 + 10 -8 - 4 * -";
        real[2] = "5 1 + 2 ^";
        real[3] = "5 1 + 5 6 * - 1 - 3 2 ^ +";
        real[4] = "7 1 * 8 - 9 + 4 2 ^ -";
        real[5] = "7 1 -";
        real[6] = "3 1 6 * + 2 - 4 7 * +";
        real[7] = "2 8 34 - 9 13 + 1.5 * + * 3 /";
        real[8] = "7 7 - 2 *";
        real[9] = "7 3 + 6 2 ^ *";

        for(int i = 0; i < 10; i++) {
            try {
                ops[i] = new OperatorTokenizer(expressions[i]);
                posts[i] = new PostfixTokenizer(ops[i]);
                eval[i] = posts[i].toString();
            }
            catch(IllegalExpressionException e) {
                System.out.printf(C_RED + "ERROR: " + C_RESET + "PostfixTokenizer threw exception when it should not have. (IllegalExpressionException)");
            }
        }

        for(int i = 0; i < 10; i++) {
            System.out.printf(C_BLUE + "Test: " + C_RESET + "%-3d", i+1);
            System.out.printf(C_BLUE + "Expression: " + C_RESET + "%-26s", expressions[i]);
            System.out.printf(C_BLUE + "Expected: " + C_RESET + "%-30s", real[i]);
            System.out.printf(C_BLUE + "Actual: " + C_RESET + "%-30s", eval[i]);
            if(real[i].equals(eval[i])) {
                System.out.printf(C_BLUE + "Result: " + C_GREEN + "PASS\n" + C_RESET);
            }
            else {
                System.out.printf(C_BLUE + "Result: " + C_RED + "FAIL\n" + C_RESET);
            }
        }

        System.out.println();
        int numTests = 11;

        for(int i = 0; i < 10; i++) {
            int numNexts = 0;
            StringBuilder sb = new StringBuilder();

            while(posts[i].hasNext()) {
                numNexts++;
                sb.append(posts[i].next());
                if(posts[i].hasNext()) {
                    sb.append(" ");
                }
            }

            printTest(numTests++, "HAS NEXT", ""+numNextsReal[i], ""+numNexts, numNexts == numNextsReal[i]);

            printTest(numTests++, "NEXT", ""+real[i], ""+sb.toString(), real[i].equals(sb.toString()));
        }

        System.out.println();

        for(int i = 0; i < 10; i++) {
            printTest(numTests++, "NUM TOKENS", ""+numNextsReal[i], ""+posts[i].numTokens(), numNextsReal[i] == posts[i].numTokens());
        }

        System.out.println();

        for(int i = 0; i < 10; i++) {
            while(posts[i].hasNext()) {
                posts[i].next();
            }

            posts[i].reset();

            printTest(numTests++, "RESET", "true", ""+posts[i].hasNext(), posts[i].hasNext());
        }

        System.out.printf(C_CYAN + "\n----- END POSTFIX TOKENIZER TEST -----\n\n" + C_RESET);
    }

    private static void testEvaluator() {
        System.out.printf(C_CYAN + "----- BEGIN EVALUATOR TEST -----\n\n" + C_RESET);

        String badParens = "(4))";
        String toomanyOperands = "(4+6)12";
        String notEnough = "3--6+2*";

        String expressions[] = new String[10];
        double real[];
        double eval[] = new double[10];

        expressions[0] = "(9-1)-(3+1)*3";
        expressions[1] = "(4+4)-(10--8)*4";
        expressions[2] = "(5+1)^2";
        expressions[3] = "5+1-5*6-1+3^2";
        expressions[4] = "7*1-8+9-4^2";
        expressions[5] = "7-1";
        expressions[6] = "3+1*6-2+4*7";
        expressions[7] = "(2*((8-34)+(9+13)*1.5))/3";
        expressions[8] = "(7-7)*2";
        expressions[9] = "(7+3)*6^2";

        for(int i = 0; i < 10; i++) {
            try {
                eval[i] = Evaluator.evaluate(expressions[i]);
            }
            catch(IllegalExpressionException e) {
                System.out.println();
                System.out.printf(C_RED + "ERROR: " + C_RESET + "Evaluator threw exception when it should not have. (evaluate method)\n");
                System.out.print("Message: ");
                System.out.println(e.getMessage());
                System.out.println();
            }
        }

        real = new double[]{-4.0, -64.0, 36.0, -16.0, -8.0, 6.0, 35.0, 4.666667, 0.0, 360.0};

        for(int i = 0; i < 10; i++) {
            System.out.printf(C_BLUE + "Test: " + C_RESET + "%-3d", i+1);
            System.out.printf(C_BLUE + "Expression: " + C_RESET + "%-30s", expressions[i]);
            System.out.printf(C_BLUE + "Expected: " + C_RESET + "%-8.3f", real[i]);
            System.out.printf(C_BLUE + "Actual: " + C_RESET + "%-8.3f", eval[i]);
            if(assertEquals(real[i], eval[i], MULT_TOLLERANCE)) {
                System.out.printf(C_BLUE + "Result: " + C_GREEN + "PASS\n" + C_RESET);
            }
            else {
                System.out.printf(C_BLUE + "Result: " + C_RED + "FAIL\n" + C_RESET);
            }
            // System.out.printf("Test: %-2d Expression: %-25s Expected: %-7.3f Actual: %-7.3f Pass: %b\n",
            //                   i+1,
            //                   expressions[i],
            //                   real[i],
            //                   eval[i],
            //                   assertEquals(real[i], eval[i], 1e-4));
        }

        System.out.println();

        try {
            Evaluator.evaluate(badParens);
            printTest(11, "EVALUATE", "Exception thrown", "Not thrown", false);
        }
        catch(IllegalExpressionException e) {
            printTest(11, "EVALUATE", "Exception thrown", "Thrown", true);
        }

        try {
            Evaluator.evaluate(toomanyOperands);
            printTest(12, "EVALUATE", "Exception thrown", "Not thrown", false);
        }
        catch(IllegalExpressionException e) {
            printTest(12, "EVALUATE", "Exception thrown", "Thrown", true);
        }

        try {
            Evaluator.evaluate(notEnough);
            printTest(13, "EVALUATE", "Exception thrown", "Not thrown", false);
        }
        catch(IllegalExpressionException e) {
            printTest(13, "EVALUATE", "Exception thrown", "Thrown", true);
        }

        System.out.printf(C_CYAN + "\n----- END EVALUATOR TEST -----\n"+ C_RESET);

    }

/****************************************************************/
/*                        Helper methods                        */
/****************************************************************/

    private static void printTest(int num, String operation, String expected, String actual, boolean result) {
        System.out.printf(C_BLUE + "Test: " + C_RESET + "%-3d", num);
        System.out.printf(C_BLUE + "Operation: " + C_RESET + "%-30s ", operation);
        System.out.printf(C_BLUE + "Expected: " + C_RESET + "%-30s ", expected);
        System.out.printf(C_BLUE + "Actual: " + C_RESET + "%-30s ", actual);
        if(result) {
            System.out.printf(C_BLUE + "Result: " + C_GREEN + "PASS\n" + C_RESET);
        }
        else {
            System.out.printf(C_BLUE + "Result: " + C_RED + "FAIL\n" + C_RESET);
        }

    }

    private static void printFail(int num, String error, String message) {
        System.out.printf(C_RED + "TEST %2d FAILED: " + C_RESET + error + "\n", num);
        System.out.print("Message: ");
        System.out.println(message + "\n");

    }

    private static boolean assertEquals(double expected, double actual, double tollerance) {
        return Math.abs(expected - actual) < tollerance;
    }

    private static boolean assertEquals(String expected, String actual) {
        return expected.equals(actual);
    }

    private static boolean assertEquals(boolean expected, boolean actual) {
        return expected == actual;
    }

    private static void waitForUser(String test) {
        System.out.println("Press Enter to begin "+ test + " tests.");
        try {
            System.in.read();
        }
        catch(Exception e) {

        }
    }

    private static void printGreeting() {
        System.out.println("CSC 115 ASSIGNMENT 2 TESTING CLASS");
        System.out.println();
        System.out.println("Author: Jayden Chan <jaydencn7@gmail.com>");
        System.out.println();
    }
}
