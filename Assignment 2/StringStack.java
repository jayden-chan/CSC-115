/*
 * Name: Jayden Chan
 * ID: V00898517
 * Date: Jan 30 2018
 * Filename: StringStack.java
 * Details: A reference-based stack implementation that stores Strings only.
*/

public class StringStack {

    private Node top;

    /*
     * Used for the testing method.
     */
    private static final String PUSH    = "Push";
    private static final String POP     = "Pop";
    private static final String PEEK    = "Peek";
    private static final String POPALL  = "Pop all";
    private static final String ISEMPTY = "Check empty";

    /**
     * Creates a new empty StringStack.
     */
    public StringStack() {
        top = new Node();
    }

    /**
     * Checks to see whether the stack contains any items.
     * @return Whether or not the stack is empty.
     */
    public boolean isEmpty() {
        return top.getItem() == null;
    }

    /**
     * Returns the topmost item in the stack.
     * @return The string in the topmost item of the stack.
     */
    public String peek() {
        if(isEmpty()) {
            throw new StackException("Stack is already empty!");
        }

        return top.getItem();
    }

    /**
     * Removes the topmost item in the stack and returns its value.
     * @return The string in the topmost item of the stack.
     */
    public String pop() throws StackException {
        String temp = top.getItem();

        if(top.getNext() != null) {
            top.setItem(top.getNext().getItem());
            top.setNext(top.getNext().getNext());
            return temp;
        }
        else if(!isEmpty()) {
            top = new Node();
            return temp;
        }

        throw new StackException("Stack is already empty!");
    }

    /**
     * Removes all items from the stack.
     */
    public void popAll() {
        top = new Node();
    }

    /**
     * Adds an element to the top of the stack.
     * @param s The string to be added to the stack.
     */
    public void push(String s) {
        if(top.getItem() == null) {
            top.setItem(s);
        }
        else {
            top.setNext(new Node(top.getItem(), top.getNext()));
            top.setItem(s);
        }
    }

/****************************************************************/
/*                       Testing methods                        */
/****************************************************************/

    public static void main(String args[]) {
        StringStack st = new StringStack();
        st.conductTest();
    }

    public void conductTest() {
        System.out.printf("----- BEGIN STRING STACK TEST -----\n\n");

        this.push("a");
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PUSH + " (a)", "a", this.peek(), this.peek().equals("a"));
        push("b");
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PUSH + " (b)", "b", this.peek(), this.peek().equals("b"));
        this.push("c");
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PUSH + " (c)", "c", this.peek(), this.peek().equals("c"));
        this.pop();
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", POP, "b", this.peek(), this.peek().equals("b"));
        this.peek();
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PEEK, "b", this.peek(), this.peek().equals("b"));
        this.pop();
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", POP, "a", this.peek(), this.peek().equals("a"));
        this.pop();
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", POP, "null", "null", true);
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", ISEMPTY, "null", "null", this.isEmpty());
        this.push("f");
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PUSH + " (f)", "f", this.peek(), this.peek().equals("f"));
        this.push("o");
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", PUSH + " (o)", "o", this.peek(), this.peek().equals("o"));
        this.popAll();
        System.out.printf("Operation: %-12s Expected Top Item: %-4s Actual: %-6s Pass: %b\n", POPALL, "null", "null", this.isEmpty());

        System.out.printf("\n----- END STRING STACK TEST -----\n");
    }
}
