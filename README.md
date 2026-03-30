# Record BST & AVL Tree (COMPX201 Assignment)

## Overview

This project implements a **Binary Search Tree (BST)** and an **AVL Tree** in Java to manage a record store catalogue. It supports efficient insertion, deletion, searching, and range queries based on record attributes such as genre, year, artist, and title.

The AVL extension ensures the tree remains balanced, improving performance when inserting sorted data.

---

## Features

### Core Functionality

* Insert and remove records
* Search for a specific record
* Print all records (in-order traversal)
* Get minimum and maximum records
* Compute tree height

### Range Queries

* Print all records of a given genre
* Print records within a genre and year range
* (Additional methods included for extended filtering)

### AVL Tree Enhancements

* Self-balancing tree using rotations
* Maintains efficient performance for sorted inputs

---

## Project Structure

* `Record.java` – Defines the Record object and comparison logic
* `RecordBST.java` – Unbalanced Binary Search Tree implementation
* `AVLRecords.java` – AVL Tree (balanced BST using inheritance)
* `RecordLookup.java` – Command-line interface for interacting with the tree
* `TestRecordBST.java` – Test class for debugging and validation

---

## How to Run

1. Compile all files:

   ```
   javac *.java
   ```

2. Run the program:

   ```
   java RecordLookup
   ```

3. Follow the prompts:

   * Choose BST or AVL tree
   * Enter the input file name (e.g., `records.txt`)
   * Use the menu to interact with the system

---

## Input File Format

Each line in the input file must follow:

```
genre|year|artist|title
```

Example:

```
Pop|1977|Fleetwood Mac|Rumours
Rock|1991|Nirvana|Nevermind
```

---

## Notes

* Duplicate records are not inserted.
* The AVL tree improves performance when data is inserted in sorted order.
* The program handles invalid input gracefully via basic error handling.

