/**
 * @author Jayden Chan
 * @version 1.0
 * Date Created: April 8, 2018
 *
 * Testing code for CSC 115 assn 5
 */

public class Tester {

    public static void main(String args[]) {
        testInts();
        testERPatients();
        testEmTriage();
    }

    private static void testEmTriage() {
        EmergencyTriage e = new EmergencyTriage();

        System.out.println();
        e.register("Hollier" , "Cobey" , "Acute");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Sunny"   , "Fred"  , "Acute");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Lennon"  , "John"  , "Major Fracture");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Zastre"  , "Mike"  , "Life-threatening");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Harrison", "George", "Chronic");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Bourne"  , "Jason" , "Ambulatory");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("null"    , "Tibor" , "Major Fracture");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.register("Zimbin"  , "Ike"   , "Chronic");
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());

        e.admitToER();
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.admitToER();
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.admitToER();
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.admitToER();
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());
        e.admitToER();
        System.out.println(e.numberOfPatientsWaiting());
        System.out.println(e.whoIsNext());

        e.register("Hollier" , "Cobey" , "Acute");
        e.register("Sunny"   , "Fred"  , "Acute");
        e.register("Lennon"  , "John"  , "Major Fracture");
        e.register("Zastre"  , "Mike"  , "Life-threatening");
        e.register("Harrison", "George", "Chronic");
        e.register("Bourne"  , "Jason" , "Ambulatory");
        e.register("null"    , "Tibor" , "Major Fracture");
        e.register("Zimbin"  , "Ike"   , "Chronic");

        System.out.println();
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.admitToER());
        System.out.println(e.numberOfPatientsWaiting());
    }

    private static void testERPatients() {
        Heap<ERPatient> ERPatientHeap = new Heap<>();

        ERPatientHeap.insert(new ERPatient("a", "h", "Chronic"));
        ERPatientHeap.insert(new ERPatient("b", "i", "Acute"));
        ERPatientHeap.insert(new ERPatient("c", "j", "Chronic"));
        ERPatientHeap.insert(new ERPatient("d", "k", "Life-threatening"));
        ERPatientHeap.insert(new ERPatient("e", "l", "Ambulatory"));
        ERPatientHeap.insert(new ERPatient("f", "m", "Major fracture"));
        ERPatientHeap.insert(new ERPatient("g", "n", "Acute"));
        ERPatientHeap.insert(new ERPatient("o", "p", "Acute"));

        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
        System.out.println(ERPatientHeap.getRootItem());
    }

    private static void testInts() {
        Heap<Integer> intHeap = new Heap<>();
        intHeap.insert(8);
        intHeap.insert(25);
        intHeap.insert(13);
        intHeap.insert(-349);
        intHeap.insert(999);
        intHeap.insert(8);
        intHeap.insert(5);
        intHeap.insert(0);

        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());

        intHeap.insert(8);
        intHeap.insert(25);
        intHeap.insert(13);
        intHeap.insert(-349);

        System.out.println();
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());

        intHeap.insert(999);
        intHeap.insert(8);
        intHeap.insert(5);
        intHeap.insert(0);

        System.out.println();
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());

        intHeap.insert(999);
        intHeap.insert(8);
        intHeap.insert(5);
        intHeap.insert(0);

        System.out.println();
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());

        intHeap.insert(8);
        intHeap.insert(25);
        intHeap.insert(13);
        intHeap.insert(-349);

        System.out.println();
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println(intHeap.getRootItem());
        System.out.println();
    }
}
