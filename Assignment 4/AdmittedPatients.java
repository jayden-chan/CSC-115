/**
 * @author Jayden Chan
 * @version 1.0
 * Date Created: March 17, 2018
 *
 * Maintains a current list of the admitted patients.
 */

public class AdmittedPatients {

    protected TreeNode root;

    public static void main(String args[]) {

        // The following is testing code.

        // AdmittedPatients admitted = new AdmittedPatients();
        // HospitalPatient p1 = new HospitalPatient(new SimpleDate(2018,2,27),"Duck","Donald",'C',123);
        // HospitalPatient p2 = new HospitalPatient(new SimpleDate(2018,1,15),"Mouse","Minnie",'B',307);
        // HospitalPatient p3 = new HospitalPatient(new SimpleDate(2018,3,1),"Dog","Goofie",'A',422);
        // HospitalPatient p4 = new HospitalPatient(new SimpleDate(2017,12,25),"Newman","Alfred",'X',111);
        // admitted.admit(p1);
        // admitted.admit(p4);
        // admitted.admit(p3);
        // admitted.admit(p2);
        // System.out.println(admitted.getPatientInfo(p1.getId()));
        // System.out.println(admitted.getPatientInfo(p2.getId()));
        // System.out.println(admitted.getPatientInfo(p3.getId()));
        // System.out.println(admitted.getPatientInfo(p4.getId()));
        // admitted.printLocations();
        // admitted.discharge(p4);
        // admitted.printLocations();
        // these two lines cause the tree to be viewed in a little
        // resizable window.
        // ViewableTree dbt = new ViewableTree(admitted);
        // dbt.showFrame();
    }

    /**
     * Initialize the tree with a single empty node.
     */
    public AdmittedPatients() {
        root = new TreeNode();
    }

    /**
     * Admit a patient to the hospital.
     * (Adds the passed patient object to
     * the tree of patients.)
     *
     * @param patient The patient to admit.
     */
    void admit(HospitalPatient patient) {
        if(root.item == null) {
            root.item = patient;
            return;
        }
        TreeNode curr = root;

        while(true) {
            if(patient.compareTo(curr.item) > 0) {
                if(curr.right == null) {
                    TreeNode n = new TreeNode(patient);
                    curr.right = n;
                    break;
                }
                curr = curr.right;
            }
            else if(patient.compareTo(curr.item) < 0) {
                if(curr.left == null) {
                    TreeNode n = new TreeNode(patient, null, null, curr);
                    curr.left = n;
                    break;
                }
                curr = curr.left;
            }
        }
    }

    /**
     * Removes a patient from the hospital
     * database.
     *
     * @param patient The patient to remove
     * from the hospital.
     */
    void discharge(HospitalPatient patient) {
        root = delRec(root, patient);
    }

    /**
     * Recursively deletes a node from the tree
     * and returns the new root of the tree (in case
     * the root is the one to delete.)
     *
     * @param n The root node of the tree
     * @param toDel The data to delete from the tree.
     * @return The new root of the tree.
     */
    private TreeNode delRec(TreeNode n, HospitalPatient toDel) {
        if(n == null) return n;

        if(toDel.compareTo(n.item) < 0) {
            // System.out.println(">0");
            n.left = delRec(n.left, toDel);
        }
        else if(toDel.compareTo(n.item) > 0) {
            // System.out.println("<0");
            n.right = delRec(n.right, toDel);
        }
        else {
            if(n.left == null) {
                return n.right;
            }
            else if(n.right == null) {
                return n.left;
            }

            n.item = inorderSuccessor(n.right);
            n.right = delRec(n.right, n.item);
        }

        return n;
    }

    /**
     * Returns the inorder successor of the
     * root node passed.
     *
     * @param n The root of the tree for which
     * to find the inorder successor.
     * @return The inorder successor.
     */
    HospitalPatient inorderSuccessor(TreeNode n) {
        HospitalPatient temp = n.item;
        while(n.left != null) {
            temp = n.left.item;
            n = n.left;
        }

        return temp;
    }

    /**
     * Returns a string containing the information
     * for the patient passed.
     *
     * @param id The ID of the patient for which to
     * return the information.
     * @return The HospitalPatient object containing
     * the patient's info.
     */
    HospitalPatient getPatientInfo(String id) {
        TreeNode curr = root;
        while(true) {
            if(id.equals(curr.item.getId())) {
                return curr.item;
            }
            else if(id.compareTo(curr.item.getId()) > 0) {
                if(curr.right == null) {
                    return null;
                }
                curr = curr.right;
            }
            else if(id.compareTo(curr.item.getId()) < 0) {
                if(curr.left == null) {
                    return null;
                }
                curr = curr.left;
            }
        }
    }

    /**
     * Prints the locations of the patients
     * in alphabetical order.
     */
    void printLocations() {
        printInOrder(root);
    }

    /**
     * Prints all of the nodes of the tree
     * using an inorder traversal algorithm.
     *
     * @param n The root node of the tree.
     */
    private void printInOrder(TreeNode n) {
        if(n == null || n.item == null) {
            return;
        }

        printInOrder(n.left);
        System.out.println(n.item.getLocation());
        printInOrder(n.right);
    }
}
