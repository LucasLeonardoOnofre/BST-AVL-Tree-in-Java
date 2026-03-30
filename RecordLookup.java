import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RecordLookup {

    private RecordBST tree; // can be RecordBST or AVLRecords

    // Constructors
    public RecordLookup() {
        tree = new RecordBST();
    }

    public RecordLookup(RecordBST tree) {
        this.tree = tree;
    }

    // Load records from file
    public void loadRecords(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String genre = parts[0];
                    int year = Integer.parseInt(parts[1]);
                    String artist = parts[2];
                    String title = parts[3];
                    tree.insert(new Record(genre, year, artist, title));
                }
            }
            sc.close();
            System.out.println("Records loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Simple CLI
    public void startInterface() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- RECORD LOOKUP MENU ---");
            System.out.println("1. Search for a record");
            System.out.println("2. Add a new record");
            System.out.println("3. Remove a record");
            System.out.println("4. Print all records of a genre");
            System.out.println("5. Print records of a genre within year range");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine();

            try {
                if (choice.equals("1")) { // Search
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Year: "); int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Artist: "); String artist = sc.nextLine();
                    System.out.print("Title: "); String title = sc.nextLine();

                    boolean found = tree.search(new Record(genre, year, artist, title));
                    if (found) {
                        System.out.println("Record FOUND.");
                    } else {
                        System.out.println("Record NOT FOUND.");
                    }

                } else if (choice.equals("2")) { // Add
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Year: "); int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Artist: "); String artist = sc.nextLine();
                    System.out.print("Title: "); String title = sc.nextLine();

                    tree.insert(new Record(genre, year, artist, title));
                    System.out.println("Record added.");

                } else if (choice.equals("3")) { // Remove
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Year: "); int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Artist: "); String artist = sc.nextLine();
                    System.out.print("Title: "); String title = sc.nextLine();

                    tree.remove(new Record(genre, year, artist, title));
                    System.out.println("Record removed (if it existed).");

                } else if (choice.equals("4")) { // Print genre
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.println("Records in genre: " + genre);
                    tree.printGenre(genre);

                } else if (choice.equals("5")) { // Print genre within year range
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Earliest year: "); int start = Integer.parseInt(sc.nextLine());
                    System.out.print("Latest year: "); int end = Integer.parseInt(sc.nextLine());

                    System.out.println("Records in genre " + genre + " between years " + start + " and " + end);
                    tree.printGenreWithYearRange(genre, start, end);

                } else if (choice.equals("6")) { // Exit
                    running = false;
                    System.out.println("Exiting...");

                } else { // Invalid
                    System.out.println("Please choose a number between 1 and 6.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, try again.");
            }
        }

        sc.close();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose tree type:");
        System.out.println("1 = RecordBST (unbalanced)");
        System.out.println("2 = AVLRecords (balanced)");
        System.out.print("Enter choice: ");
        int treeChoice = Integer.parseInt(sc.nextLine());

        RecordBST tree;
        if (treeChoice == 1) {
            tree = new RecordBST();
        } else {
            tree = new AVLRecords();
        }

        RecordLookup app = new RecordLookup(tree);

        System.out.print("Enter file name: ");
        String filename = sc.nextLine();
        app.loadRecords(filename);

        app.startInterface();
        sc.close();
    }
}