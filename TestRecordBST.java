public class TestRecordBST {

    public static void main(String[] args) {

        RecordBST tree = new RecordBST();

        // =========================
        // INSERT TEST
        // =========================
        System.out.println("=== INSERTING RECORDS ===");

        tree.insert(new Record("Pop", 2000, "Artist1", "C"));
        tree.insert(new Record("Rock", 1995, "Artist2", "A"));
        tree.insert(new Record("Jazz", 2010, "Artist3", "E"));
        tree.insert(new Record("Pop", 2005, "Artist4", "B"));
        tree.insert(new Record("Rock", 2001, "Artist5", "D"));

        // =========================
        // PRINT TEST (in-order)
        // =========================
        System.out.println("\n=== TREE (IN-ORDER) ===");
        tree.print();

        // =========================
        // SEARCH TEST
        // =========================
        System.out.println("\n=== SEARCH TESTS ===");

        Record search1 = new Record("Pop", 2000, "Artist1", "C");
        Record search2 = new Record("Classical", 1800, "Mozart", "Z");

        System.out.println("Search existing record: " + tree.search(search1)); // true
        System.out.println("Search non-existing record: " + tree.search(search2)); // false

        // =========================
        // MIN / MAX TEST
        // =========================
        System.out.println("\n=== MIN / MAX ===");

        System.out.println("Minimum: " + tree.getMinimum());
        System.out.println("Maximum: " + tree.getMaximum());

        // =========================
        // HEIGHT TEST
        // =========================
        System.out.println("\n=== HEIGHT ===");
        System.out.println("Tree Height: " + tree.getHeight());

        // =========================
        // REMOVE TESTS
        // =========================
        System.out.println("\n=== REMOVE TESTS ===");

        // Remove leaf node
        System.out.println("\nRemoving leaf node:");
        tree.remove(new Record("Pop", 2005, "Artist4", "B"));
        tree.print();

        // Remove node with one child
        System.out.println("\nRemoving node with one child:");
        tree.remove(new Record("Jazz", 2010, "Artist3", "E"));
        tree.print();

        // Remove node with two children
        System.out.println("\nRemoving node with two children:");
        tree.remove(new Record("Pop", 2000, "Artist1", "C"));
        tree.print();

        // =========================
        // FINAL STATE
        // =========================
        System.out.println("\n=== FINAL TREE ===");
        tree.print();

        System.out.println("\nFinal Height: " + tree.getHeight());
    }
}