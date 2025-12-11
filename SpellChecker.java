import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * SpellChecker (Phase 1)
 * -----------------------
 * - Loads all words from WordList.txt into a HashTable
 * - Prompts user for input
 * - For each word, prints:
 *        "ok"        if the word is in the dictionary
 *        "not found" if the word is NOT in the dictionary
 *
 * Near-miss logic will be added later.
 */
public class SpellChecker {

    public static void main(String[] args) {
        try {
            // 1. Create hash table with appropriate size
            HashTable dict = new HashTable(65000);

            // 2. Load dictionary words
            BufferedReader br = new BufferedReader(new FileReader("WordList.txt"));
            String word;
            int count = 0;

            System.out.println("Loading dictionary...");

            while ((word = br.readLine()) != null) {
                word = word.trim().toLowerCase();
                if (!word.isEmpty()) {
                    dict.add(word);
                    count++;
                }
            }

            br.close();
            System.out.println("Dictionary loaded with " + count + " words.");
            System.out.println();

            // 3. Scanner for user input
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter words to check (type 'exit' to quit):");

            // 4. Main loop
            while (true) {
                System.out.print("> ");
                String input = scan.next().toLowerCase();

                if (input.equals("exit")) {
                    System.out.println("Exiting spell checker.");
                    break;
                }

                // 5. Check the dictionary
                if (dict.contains(input)) {
                    System.out.println("ok");
                } else {
                    System.out.println("not found");
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR: Could not read WordList.txt");
        }
    }
}
