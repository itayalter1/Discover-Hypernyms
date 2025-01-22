// Itay Alter 206132284

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * DiscoverHypernym is a class that identifies hypernyms (words representing a broader category)
 * for a given lemma within a corpus of text files.
 * It reads files from a specified directory, processes each line of the files,
 * and applies different patterns to find hypernyms related to the lemma.
 * The hypernyms are then counted and sorted in descending order by count.
 * Finally, the sorted hypernyms and their counts are printed to the console.
 */
public class DiscoverHypernym {
    public static final String NP = "<np>[^<]*</np>";


    /**
     * The main entry point of the application.
     * It expects two arguments: the directory path and the lemma.
     * It processes the files in the directory, finds hypernyms related to the lemma,
     * counts them, sorts them, and prints the results.
     *
     * @param args the command-line arguments, where args[0] is the directory path
     *             and args[1] is the lemma.
     */
    public static void main(String[] args) {
        // Argument validation
        if (args.length < 2) {
            System.out.println("Please provide the directory path and the lemma as arguments.");
            return;
        }
        String directoryPath = args[0];
        String lemma = args[1];
        // Directory validation
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path.");
            return;
        }
        HashMap<String, Integer> hypernymCount = new HashMap<>();
        // Read files in the directory
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("Empty directory.");
            return;
        }
        // Process each line of the files
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line, lemma, hypernymCount);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName());
            }
        }
        // Sort hypernyms by count in descending order
        List<Map.Entry<String, Integer>> sortedHypernyms = new ArrayList<>(hypernymCount.entrySet());
        sortedHypernyms.sort(Comparator.comparing(Map.Entry<String, Integer>::getValue)
                .reversed()
                .thenComparing(Map.Entry.comparingByKey()));
        // Print the sorted hypernyms and their counts
        if (sortedHypernyms.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
        } else {
            // Print hypernyms and their counts
            for (Map.Entry<String, Integer> entry : sortedHypernyms) {
                System.out.println(entry.getKey() + ": (" + entry.getValue() + ")");
            }
        }
    }

    /**
     * Process a single line of text to find hypernyms related to the lemma.
     * It applies different patterns to the line and updates the hypernymCount
     * map accordingly.
     *
     * @param line          the line of text to process
     * @param lemma         the lemma to find hypernyms for
     * @param hypernymCount the map to store hypernym counts
     */
    public static void processLine(String line, String lemma, HashMap<String, Integer> hypernymCount) {
        Patterns p1 = new Pattern1(line, lemma);
        Patterns p2 = new Pattern2(line, lemma);
        Patterns p3 = new Pattern3(line, lemma);
        Patterns p4 = new Pattern4(line, lemma);
        Patterns p5 = new Pattern5(line, lemma);
        String npLemma = "<np>" + lemma.toLowerCase() + "</np>";
        String npLemmas = "<np>" + lemma.toLowerCase() + "s" + "</np>";
        if (line.toLowerCase().contains(npLemma) || line.toLowerCase().contains(npLemmas)) {
            p1.process(hypernymCount);
            p2.process(hypernymCount);
            p3.process(hypernymCount);
            p4.process(hypernymCount);
            p5.process(hypernymCount);
        }
    }
}
