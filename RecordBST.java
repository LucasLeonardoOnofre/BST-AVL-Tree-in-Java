public class RecordBST {
    protected Node root;  // modified for part 3 to allow access in AVLRecords (code below)
// Node class Self-referential class.
    public class Node{
        public Record value;
        public Node left;
        public Node right;
        protected int balanceFactor; //Part 3
    // Constructor
        public Node(Record value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.balanceFactor = 0;
        }
        // Get height for a node
        public int getHeight() {
            int leftHeight = (left == null) ? 0 : left.getHeight();
            int rightHeight = (right == null) ? 0 : right.getHeight();
            return 1 + Math.max(leftHeight, rightHeight);
        }
        // Update balance factor for a node
        public void updateNode() {
            int leftHeight = (left == null) ? 0 : left.getHeight();
            int rightHeight = (right == null) ? 0 : right.getHeight();
            this.balanceFactor = leftHeight - rightHeight;
        }

    }
    
    // Constructor
    public RecordBST() {
        root = null;
    }
    // Method to insert a record into the BST
    public void insert(Record r) {
        root = insertRec(root, r);
    }
    // Recursive method to insert a record into the BST
    private Node insertRec(Node curr, Record r){
        if (curr == null) {
            return new Node(r);
        }
        int cmp = r.compareTo(curr.value);
        if (cmp < 0) {
            curr.left = insertRec(curr.left, r);
        } else if (cmp > 0) {
            curr.right = insertRec(curr.right, r);
        }
        return curr;
    }
    
    // Method to search for a record in the BST
    public void remove(Record r) {
        root = removeRec(root, r);
    }
    // Recursive method to remove a record from the BST
    private Node removeRec(Node curr, Record r) {
        if (curr == null) {
            return null;
        }
        int cmp = r.compareTo(curr.value);
        if (cmp < 0) {
            curr.left = removeRec(curr.left, r);
        } else if (cmp > 0) {
            curr.right = removeRec(curr.right, r);
        } else {
            // Node not found
            if (curr.left == null && curr.right == null) {
                return null;
            }
            // Node with only one child or no child
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            }
            // Node with two children
            Record minRecord = getMin(curr.right);
            curr.value = minRecord;
            curr.right = removeRec(curr.right, minRecord);
        }
        return curr;
    }
    // Method to search for a record in the BST
    public boolean search(Record r) {
        return searchRec(root, r);
    }
    // Recursive method to search for a record in the BST
    private boolean searchRec(Node curr, Record r) {
        if (curr == null) {
            return false;
        }
        int cmp = r.compareTo(curr.value);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRec(curr.left, r);
        } else {
            return searchRec(curr.right, r);
        }
    }
    // Get the height of the BST
    public int getHeight() {
        if (root == null) {
            return -1; // Height of an empty tree is -1
        }
        return root.getHeight();
    }

    // Method to find the minimum record in the BST
    public Record getMinimum (){
        if(root == null) return null;
        return getMin(root);
    }
    // Helper method to find the minimum record in a subtree
    private Record getMin(Node curr) {
        if(curr.left == null) {
            return curr.value;
        }
        return getMin(curr.left);
    }
    // Get the maximum record in the BST
    public Record getMaximum() {
        if (root == null) return null;
        return getMax(root);
    }
    // Helper method to find the maximum record in a subtree
    private Record getMax(Node curr) {
        if (curr.right == null) {
            return curr.value;
        }
        return getMax(curr.right);
    }
    
    // Print the records in the BST in sorted order
    public void print() {
        printRec(root);
    }
    // Recursive method to print the records in sorted order
    private void printRec(Node curr) {
        if (curr != null) {
            printRec(curr.left);
            System.out.println(curr.value);
            printRec(curr.right);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    /// PART 2 
   
    // Print all records in a genre
    public void printGenre(String g) {
        printGenreRec(root, g);
    }
    // Recursive method to print all records in a genre
    private void printGenreRec(Node current, String genre) {
        if (current == null) return;

        // Only explore branches where genre could exist
        if (genre.compareTo(current.value.getGenre()) <= 0) {
            printGenreRec(current.left, genre);
        }
        // If current node matches genre, print it
        if (current.value.getGenre().equalsIgnoreCase(genre)) {
            System.out.println(current.value);
        }
        // Continue searching right subtree if genre is greater or equal
        if (genre.compareTo(current.value.getGenre()) >= 0) {
            printGenreRec(current.right, genre);
        }
    }

    // Print records in genre within a year range: earliest < year < latest
    public void printGenreWithYearRange(String g, int earliest, int latest) {
        printGenreWithYearRangeRec(root, g, earliest, latest);
    }
    // Recursive method to print records in genre within a year range
    private void printGenreWithYearRangeRec(Node current, String genre, int earliest, int latest) {
        // Base case if current node is null, return
        if (current == null) return;
        // Only explore branches where genre could exist
        if (genre.compareTo(current.value.getGenre()) <= 0) {
            printGenreWithYearRangeRec(current.left, genre, earliest, latest);
        }
        // If current node matches genre and year is within range, print it
        if (current.value.getGenre().equalsIgnoreCase(genre)
            && current.value.getYear() > earliest && current.value.getYear() < latest) {
            System.out.println(current.value);
        }
        // Continue searching right subtree if genre is greater or equal
        if (genre.compareTo(current.value.getGenre()) >= 0) {
            printGenreWithYearRangeRec(current.right, genre, earliest, latest);
        }
    }

    // Print genre after earliest year: year > earliest
    public void printGenreAfterYear(String g, int earliest) {
        printGenreAfterYearRec(root, g, earliest);
    }
    // Recursive method to print genre after earliest year
    private void printGenreAfterYearRec(Node current, String genre, int earliest) {
        // Base case if current node is null, return
        if (current == null) return;
        // Only explore branches where genre could exist
        if (genre.compareTo(current.value.getGenre()) <= 0) {
            printGenreAfterYearRec(current.left, genre, earliest);
        }
        // If current node matches genre and year is after earliest, print it
        if (current.value.getGenre().equalsIgnoreCase(genre)
            && current.value.getYear() > earliest) {
            System.out.println(current.value);
        }
        // Continue searching right subtree if genre is greater or equal
        if (genre.compareTo(current.value.getGenre()) >= 0) {
            printGenreAfterYearRec(current.right, genre, earliest);
        }
    }

    // Print genre below latest year: year < latest
    public void printGenreBelowYear(String g, int latest) {
        printGenreBelowYearRec(root, g, latest);
    }
    // Recursive method to print genre below latest year
    private void printGenreBelowYearRec(Node current, String genre, int latest) {
        // Base case if current node is null, return
        if (current == null) return;

        // Only explore branches where genre could exist            
        if (genre.compareTo(current.value.getGenre()) <= 0) {
            printGenreBelowYearRec(current.left, genre, latest);
        }
        // If current node matches genre and year is below latest, print it
        if (current.value.getGenre().equalsIgnoreCase(genre)
            && current.value.getYear() < latest) {
            System.out.println(current.value);
        }
        // Continue searching right subtree if genre is greater or equal
        if (genre.compareTo(current.value.getGenre()) >= 0) {
            printGenreBelowYearRec(current.right, genre, latest);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////
/// PART 3 - AVL Tree Implementation
    protected Node getRoot() {
        return root;
    }
    protected void setRoot(Node node) {
        root = node;
    }




}
