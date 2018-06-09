/*
 * Name: Jayden Chan
 * ID: V00898517
 * Date: Jan 30 2018
 * Filename: PostfixTokenizer.java
 * Details: Converts a set of operator and operand tokens into postfix notation.
*/

import java.util.NoSuchElementException;

public class PostfixTokenizer implements Tokenizer {

    private String[] tokens;
    private int currTokenIndex = 0;

    /**
     * Create the PostfixTokenizer object. Initializes the token array and populates it with the tokens in the correct order.
     * @param infixTokens The tokens to be arranged into postfix notation.
     */
    public PostfixTokenizer(OperatorTokenizer infixTokens) throws IllegalExpressionException {
        tokens = new String[numTokens(infixTokens)];
        assembleTokenArray(infixTokens);
    }

    /**
     * Converts the postfix tokens into a formatted string.
     * @return The formatted string of tokens separated by spaces.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < tokens.length-1; i++) {
            sb.append(tokens[i] + " ");
        }
        if(tokens.length > 0) {
            sb.append(tokens[tokens.length-1]);
        }

        return sb.toString();
    }

/****************************************************************/
/*                  Iterator required methods                   */
/****************************************************************/

    /**
     * Checks whether there is another element remaining in the token list.
     * @return Whether or not there is another item in the token list.
     */
    public boolean hasNext() {
        return currTokenIndex < tokens.length;
    }

    /**
     * Gets the next element in the token list.
     * @return The next element in the token list as a string.
     * @throws NoSuchElementException if there are no more elements in the list.
     */
    public String next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException("Out of tokens!");
        }

        return tokens[currTokenIndex++];
    }

    /**
     * This operation is not supported on an immutable list and should not be used.
     */
    public void remove() {
        throw new UnsupportedOperationException("This method is not supported on an immutable list.");
    }

/****************************************************************/
/*                  Tokenizer required methods                  */
/****************************************************************/

    /**
     * Gets the number of tokens in the list.
     * @return The number of tokens.
     */
    public int numTokens() {
        return tokens.length;
    }

    /**
     * Resets the iterator index.
     */
    public void reset() {
        currTokenIndex = 0;
    }

/****************************************************************/
/*                        Helper methods                        */
/****************************************************************/

    /**
     * Computes the number of tokens that the postfix expression will contain.
     * Used for sizing the array that contains the tokens.
     * @param infixTokens The expression in infix form.
     * @return The number of tokens.
     */
    private int numTokens(OperatorTokenizer infixTokens) {
        int count = 0;

        while(infixTokens.hasNext()) {
            switch(infixTokens.next()) {
                case ")":
                case "(":
                    continue;
            }
            count++;
        }

        infixTokens.reset();
        return count;
    }

    /**
     * Converts the infix expression into postfix notation.
     * @param infixTokens The expression in infix form.
     */
    private void assembleTokenArray(OperatorTokenizer infixTokens) {
        StringStack operatorStack = new StringStack();

        int index = 0;
        String currToken;

        while(infixTokens.hasNext()) {
            currToken = infixTokens.next();

            if(currToken.equals("(")) {
                operatorStack.push(currToken);
            }
            else if(currToken.equals(")")) {

                while(Operator.isOperator(operatorStack.peek())) {
                    tokens[index++] = operatorStack.pop();
                }

                operatorStack.pop();
            }
            else if(Operator.isOperator(currToken)) {

                while(true) {
                    if(operatorStack.isEmpty() || operatorStack.peek().equals("(") || Operator.comparePrecedence(currToken, operatorStack.peek()) < 0) {
                        operatorStack.push(currToken);
                        break;
                    }

                    tokens[index++] = operatorStack.pop();
                }
            }
            else {
                tokens[index++] = currToken;
            }
        }

        // Add remaining operators to the end of the token list.
        while(!operatorStack.isEmpty()) {
            tokens[index++] = operatorStack.pop();
        }
    }

/****************************************************************/
/*                       Testing methods                        */
/****************************************************************/

    public static void main(String[] args) {
        OperatorTokenizer op = new OperatorTokenizer("3+3");
        PostfixTokenizer po = new PostfixTokenizer(op);

        po.conductTest();
    }

    public void conductTest() {
        OperatorTokenizer ops[] = new OperatorTokenizer[10];
        PostfixTokenizer posts[] = new PostfixTokenizer[10];

        String expressions[] = new String[10];
        String real[] = new String[10];
        String eval[] = new String[10];

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
            ops[i] = new OperatorTokenizer(expressions[i]);
            posts[i] = new PostfixTokenizer(ops[i]);
            eval[i] = posts[i].toString();
        }

        System.out.printf("----- BEGIN POSTFIX TOKENIZER TEST -----\n\n");

        for(int i = 0; i < 10; i++) {
            System.out.printf("Test: %-2d Expression: %-25s Expected: %-29s Actual: %-29s Pass: %b\n",
                              i+1,
                              expressions[i],
                              real[i],
                              eval[i],
                              real[i].equals(eval[i]));
        }

        System.out.printf("\n----- END POSTFIX TOKENIZER TEST -----\n");
    }
}
