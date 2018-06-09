/**
 * Name: Jayden Chan
 * ID: V00898517
 * Date: April 6, 2018
 * Filename: Heap.java
 * Details: Generic ADT Heap implementation.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

// This is the beginning of the source code to help you get started.
// Do not change the following:

public class Heap<E extends Comparable<E>> {

    // I couldn't stand having the list called
    // 'heap' so I renamed it.
    private ArrayList<E> heapList;
    private int size;
    private static final int CAPACITY = 100;

    /**
     * Creates an empty heap.
     */
    public Heap() {
        heapList = new ArrayList<E>(CAPACITY);
        for (int i=0; i<CAPACITY; i++) {
            heapList.add(null);
        }
        size = 0;
    }

    /**
     * Main method used for testing only.
     */
    public static void main(String args[]) {
        Heap<ERPatient> testingHeap = new Heap<>();
        ERPatient a = new ERPatient("Hollier" , "Cobey" , "Acute");
        ERPatient b = new ERPatient("Sunny"   , "Fred"  , "Acute");
        ERPatient c = new ERPatient("Lennon"  , "John"  , "Major Fracture");
        ERPatient d = new ERPatient("Zastre"  , "Mike"  , "Life-threatening");
        ERPatient e = new ERPatient("Harrison", "George", "Chronic");
        ERPatient f = new ERPatient("Bourne"  , "Jason" , "Ambulatory");
        ERPatient g = new ERPatient("null"    , "Tibor" , "Major Fracture");
        ERPatient h = new ERPatient("Zimbin"  , "Ike"   , "Chronic");

        testingHeap.insert(a);
        testingHeap.insert(b);
        testingHeap.insert(c);
        testingHeap.insert(d);

        for(int i = 0; i < 4; i++) {
            testingHeap.LET();
            System.out.println("Size: " + testingHeap.size());
            ERPatient r = testingHeap.getRootItem();
            System.out.println();
        }

        testingHeap.insert(e);
        testingHeap.insert(f);
        testingHeap.insert(g);
        testingHeap.insert(h);

        for(int i = 0; i < 4; i++) {
            testingHeap.LET();
            System.out.println("Size: " + testingHeap.size());
            ERPatient r = testingHeap.getRootItem();
            System.out.println();
        }

        Heap<Integer> testingHeap2 = new Heap<>();
        testingHeap2.insert(4);
        System.out.println();
        System.out.println(testingHeap2.isEmpty());
        testingHeap2.insert(2);
        testingHeap2.insert(8);
        testingHeap2.insert(9);
        System.out.println(testingHeap2.size());
        System.out.println(testingHeap2.getRootItem());

    }

    //==================================================
    // PUBLIC METHODS
    //==================================================

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Returns the root element of the heap without
     * removing it.
     *
     * @return The root element of the heap.
     */
    public E peek() {
        return heapList.get(0);
    }

    public void insert(E element) {
        int currentIndex = size;
        heapList.add(size, element);
        size++;

        if(size == 1) return;
        E currentParent = heapList.get(getParentIndex(currentIndex));
        while(element.compareTo(currentParent) < 0) {
            swap(currentIndex, getParentIndex(currentIndex));
            currentIndex = getParentIndex(currentIndex);
            if(currentIndex == 0) break;
            currentParent = heapList.get(getParentIndex(currentIndex));
        }
    }

    public E getRootItem() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException("Heap empty, cannot return root item!");
        }

        // Insert the last element into the root position
        E toReturn = heapList.get(0);
        swap(size-1, 0);
        heapList.set(size-1, null);
        size--;

        // Restructure the heap in case it was broken when we
        // swapped the last element into the root position
        int currentIndex = 0;
        while(true) {
            ArrayList<E> children = getChildren(currentIndex);

            // If both children are null, we're done.
            if(children.get(0) == null && children.get(1) == null) {
                break;
            }

            // Check to see which child has higher precedence
            boolean usingRight = compareWrap(children.get(0), children.get(1)) > 0;

            // If both children are smaller than the current node, we are done.
            // Otherwise, swap the correct node with the parent.
            if(compareWrap(heapList.get(currentIndex), children.get(0)) < 0
            && compareWrap(heapList.get(currentIndex), children.get(1)) < 0)
            {
                break;
            }
            else if(usingRight) {
                swap(currentIndex, 2 * currentIndex + 2);
                currentIndex = 2 * currentIndex + 2;
            }
            else {
                swap(currentIndex, 2 * currentIndex + 1);
                currentIndex = 2 * currentIndex + 1;
            }
        }

        return toReturn;
    }

    /**
     * Performs a level-order traversal of the
     * heap and prints the result. Used for
     * debugging only.
     */
    public void LET() {
        for(E i : heapList) {
            if(i == null) break;
            System.out.println(i);
        }
    }

    //==================================================
    // PRIVATE HELPER METHODS
    //==================================================

    /**
     * Returns a list containing the two children
     * of the provided node. If a child is not present,
     * null will be returned in place of the node.
     *
     * @param index The index of the parent node.
     * @return The children of the parent node.
     */
    private ArrayList<E> getChildren(int index) {
        ArrayList<E> toReturn = new ArrayList<>();

        try {
            toReturn.add(heapList.get(index * 2 + 1));
        }
        catch(IndexOutOfBoundsException e) {
            toReturn.add(null);
        }

        try {
            toReturn.add(heapList.get(index * 2 + 2));
        }
        catch(IndexOutOfBoundsException e) {
            toReturn.add(null);
        }

        return toReturn;
    }

    /**
     * Returns the index of the parent node
     * whose child is at the index passed.
     *
     * @param index The index of the child node.
     * @return The index of the parent node.
     */
    private int getParentIndex(int index) {
        if(index == 0) return -1;
        return (int)Math.floor((index - 1) / 2);
    }

    /**
     * Swaps the two nodes provided.
     *
     * @param index1 The first node to swap.
     * @param index2 The second node to swap.
     */
    private void swap(int index1, int index2) {
        E temp = heapList.get(index1);
        heapList.set(index1, heapList.get(index2));
        heapList.set(index2, temp);
    }

    /**
     * Wrapper function around the compareTo method.
     * If a non-null item is compared to a null item,
     * it should always have higher precedence. This
     * simplifies the getRootItem code. This function
     * still throws a NullPointerException if both
     * items are null.
     *
     * @param one The first item to compare.
     * @param two The second item to compare.
     * @return The precedence.
     */
    private int compareWrap(E one, E two) {
        if(one != null && two == null) {
            return -1;
        }
        else if(one == null && two != null) {
            return 1;
        }
        else {
            return one.compareTo(two);
        }
    }
}
