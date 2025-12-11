import java.util.LinkedList;

/**
 * HashTable
 * ----------
 * A simple hash table of Strings using **separate chaining**.
 *
 * - The table is an array of LinkedLists.
 * - Each index of the array represents a "bucket."
 * - Strings that hash to the same index go into the same LinkedList.
 *
 * Functions:
 *   add(String)
 *   remove(String)
 *   contains(String)
 *   size()
 *   constructor(int capacity)
 */
public class HashTable {

    // The array that holds buckets.
    private LinkedList<String>[] table;

    // Number of actual strings stored (NOT the number of buckets!)
    private int size;

    /**
     * Constructor
     * Creates a hash table with the given number of buckets.
     *
     * @param capacity number of buckets in the hash table
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        // Java cannot directly create a generic array, so we cast manually.
        table = (LinkedList<String>[]) new LinkedList[capacity];

        // Initialize each bucket with an empty LinkedList
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }

        size = 0; // initially no strings are stored
    }

    /**
     * Computes a hash index for a given string.
     * We use String.hashCode(), then take the absolute value,
     * then reduce it modulo the number of buckets.
     *
     */
    private int hash(String s) {
        return Math.abs(s.hashCode()) % table.length;
    }

    /**
     * Adds a string to the hash table if it is NOT already present.
     *
     * @param s the string to add
     */
    public void add(String s) {
        int index = hash(s);                 // find the correct bucket
        LinkedList<String> bucket = table[index];

        // only add if the string is not already in this bucket
        if (!bucket.contains(s)) {
            bucket.add(s);                   // append to the bucket's list
            size++;                          // increase count of stored strings
        }
    }

    /**
     * Checks if a string exists in the hash table.
     *
     * @param s the string to search for
     * @return true if found, false otherwise
     */
    public boolean contains(String s) {
        int index = hash(s);
        return table[index].contains(s);     // search inside the bucket's list
    }

    /**
     * Removes a string from the hash table only if it exists.
     *
     * @param s the string to remove
     */
    public void remove(String s) {
        int index = hash(s);
        if (table[index].remove(s)) {        // remove() returns true if successful
            size--;
        }
    }

    /**
     * @return number of strings currently stored in the table
     */
    public int size() {
        return size;
    }

    /**
     * Counts collisions:
     * A "collision" occurs when a bucket contains more than 1 string.
     * For example:
     *   bucket size = 0 → no_collision
     *   bucket size = 1 → no_collision
     *   bucket size = 4 → 3 collisions (because 3 had to collide with the first)
     *
     * @return total number of collisions in the table
     */
    public int collisionCount() {
        int collisions = 0;

        for (LinkedList<String> bucket : table) {
            if (bucket.size() > 1) {
                collisions += (bucket.size() - 1);
            }
        }

        return collisions;
    }
}
