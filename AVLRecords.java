// AVL Tree implementation of RecordBST
public class AVLRecords extends RecordBST {
    // Override insert method to maintain AVL balance
    @Override
    public void insert(Record r) {
        root = insertRec(root, r);
    }
    // Recursive method to insert a record into the AVL tree
    private Node insertRec(Node node, Record r) {
        if (node == null) {
            return new Node(r);
        }
        // Standard BST insertion
        int cmp = r.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRec(node.left, r);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, r);
        } else {
            // duplicate, do nothing
            return node;
        }

        // Update balance factor
        node.updateNode();

        // Balance node if needed
        return balance(node);
    }
    // Method to remove a record from the AVL tree
    @Override
    public void remove(Record r) {
        root = removeRec(root, r);
    }
    // Recursive method to remove a record from the AVL tree
    private Node removeRec(Node node, Record r) {
        if (node == null) return null;

        int cmp = r.compareTo(node.value);  
        if (cmp < 0) {
            node.left = removeRec(node.left, r);
        } else if (cmp > 0) {
            node.right = removeRec(node.right, r);
        } else {
            // Node to delete found
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                // Node with two children: get inorder successor
                Node successor = getMinNode(node.right);
                node.value = successor.value;
                node.right = removeRec(node.right, successor.value);
            }
        }

        // Update balance factor
        node.updateNode();

        // Balance node
        return balance(node);
    }
    // Helper method to get minimum node in a subtree
    private Node getMinNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }
    // AVL Rotations
    private Node balance(Node node) {
        if (node.balanceFactor > 1) {  // left heavy
            if (node.left.balanceFactor < 0) {  // Left-Right case
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);  // Left-Left case
        } else if (node.balanceFactor < -1) {  // right heavy
            if (node.right.balanceFactor > 0) {  // Right-Left case
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);  // Right-Right case
        }
        return node; // already balanced
    }
    // Left rotation
    private Node rotateLeft(Node z) {
        Node y = z.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = z;
        z.right = T2;

        // Update balance factors
        z.updateNode();
        y.updateNode();

        return y;
    }
    // Right rotation
    private Node rotateRight(Node z) {
        Node y = z.left;
        Node T3 = y.right;

        // Perform rotation
        y.right = z;
        z.left = T3;

        // Update balance factors
        z.updateNode();
        y.updateNode();

        return y;
    }
}