/*
 * Name: Jayden Chan
 * ID: V00898517
 * Date: Jan 30 2018
 * Filename: Evaluator.java
 * Details: Evaluates a math expression given as a string.
*/

public class Evaluator {

    /**
     * Evaluates the given expression.
     * @return The result of the evaluation as a double.
     * @throws IllegalExpressionException if the expression cannot be solved.
     */
    public static double evaluate(String expression) throws IllegalExpressionException {
        OperatorTokenizer opTokenizer = new OperatorTokenizer(expression);

        if(!goodParens(opTokenizer)) throw new IllegalExpressionException("Mismatched parens");

        PostfixTokenizer postTokenizer = new PostfixTokenizer(opTokenizer);

        return stackEvaluate(postTokenizer);
    }

/****************************************************************/
/*                        Helper methods                        */
/****************************************************************/

    private static boolean goodParens(OperatorTokenizer expTokens) {
        StringStack braceStack = new StringStack();
        String currToken;

        while(expTokens.hasNext()) {
            currToken = expTokens.next();

            switch(currToken) {
                case "(":
                    braceStack.push(currToken);
                    break;
                case ")":
                    try {
                        braceStack.pop();
                    }
                    catch (StackException e) {
                        return false;
                    }
                    break;
            }
        }

        expTokens.reset();
        return braceStack.isEmpty();
    }

    /*
     * Takes in a postfix notation expression and evaluates it, returning its value
     */
    private static double stackEvaluate(PostfixTokenizer postfixExpression) {
        StringStack operandStack = new StringStack();

        String currToken;

        while(postfixExpression.hasNext()) {
            currToken = postfixExpression.next();

            if(Operator.isOperator(currToken)) {
                try {
                    double operand2 = Double.parseDouble(operandStack.pop());
                    double operand1 = Double.parseDouble(operandStack.pop());
                    double tempEval = Operator.evaluate(operand1, operand2, currToken);

                    operandStack.push(Double.toString(tempEval));
                }
                catch(StackException e) {
                    throw new IllegalExpressionException("Too few operands");
                }
                catch(NumberFormatException e) {
                    throw new IllegalExpressionException("Too few operands");
                }
            }
            else {
                operandStack.push(currToken);
            }
        }

        double toReturn;

        try {
            toReturn = Double.parseDouble(operandStack.pop());
        }
        catch(NumberFormatException e) {
            throw new IllegalExpressionException("Not enough operands");
        }

        if(!operandStack.isEmpty()) {
            throw new IllegalExpressionException("Too many operands");
        }

        return toReturn;
    }

/****************************************************************/
/*                       Testing methods                        */
/****************************************************************/

    public static void main(String[] args) {
        OperatorTokenizer op = new OperatorTokenizer("(3+3)");
        PostfixTokenizer po = new PostfixTokenizer(op);
        StringStack st = new StringStack();
        st.conductTest();
        System.out.println();
        po.conductTest();
        System.out.println();
        conductTest();

        String good = "3*(4+2)-4*(-6--3)";
        String badParens = "(4))";
        String toomanyOperands = "(4+6)12";
        String notEnough = "3--6+2*";
        System.out.println(badParens);
        try {
        evaluate(badParens);
        } catch (IllegalExpressionException iee) {
        System.out.println(iee.getMessage());
        }
        System.out.println(toomanyOperands);
        try {
        evaluate(toomanyOperands);
        } catch (IllegalExpressionException iee) {
        System.out.println(iee.getMessage());
        }
        System.out.println(notEnough);
        try {
        evaluate(notEnough);
        } catch (IllegalExpressionException iee) {
        System.out.println(iee.getMessage());
        }
    }

    private static boolean assertEquals(double val1, double val2, double threshold) {
        return Math.abs(val1 - val2) < threshold;
    }

    private static void conductTest() {
        System.out.printf("----- BEGIN EVALUATOR TEST -----\n\n");

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
            eval[i] = Evaluator.evaluate(expressions[i]);
        }

        real = new double[]{-4.0, -64.0, 36.0, -16.0, -8.0, 6.0, 35.0, 4.6667, 0.0, 360.0};

        for(int i = 0; i < 10; i++) {
            System.out.printf("Test: %-2d Expression: %-25s Expected: %-7.3f Actual: %-7.3f Pass: %b\n",
                              i+1,
                              expressions[i],
                              real[i],
                              eval[i],
                              assertEquals(real[i], eval[i], 1e-4));
        }

        System.out.printf("\n----- END EVALUATOR TEST -----\n");
    }
}
