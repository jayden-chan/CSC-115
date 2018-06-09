/**
 * Name: Jayden Chan
 * Date: April 6, 2018
 * Filename: EmergencyTriage.java
 * Details: Uses the ADT Heap to implement a
 * priority queue for sick patients at a hospital.
*/

public class EmergencyTriage {
    private Heap<ERPatient> waiting;

    /**
     * The empty EmergencyTriage is initialized.
     */
    public EmergencyTriage() {
        waiting = new Heap<>();
    }

    /**
     * Main method used for testing only.
     */
    public static void main(String args[]) {
        EmergencyTriage e = new EmergencyTriage();

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
    }

    public ERPatient admitToER() {
        return waiting.getRootItem();
    }

    public int numberOfPatientsWaiting() {
        return waiting.size();
    }

    public void register(String lastName, String firstName, String triageCategory) {
        ERPatient toAdd = new ERPatient(lastName, firstName, triageCategory);
        waiting.insert(toAdd);
    }

    public ERPatient whoIsNext() {
        return waiting.peek();
    }
}
