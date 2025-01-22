// Itay Alter 206132284
import java.util.HashMap;

/**
 * The Patterns interface represents a contract for pattern classes that process
 * text and extract hypernyms. Classes implementing this interface must provide
 * a method to process the text and update the hypernym count.
 */
public interface Patterns {
    /**
     * Processes the text and updates the hypernym count based on the implemented
     * pattern logic.
     *
     * @param hypernymCount a HashMap representing the count of hypernyms where
     *                     the key is the hypernym and the value is the count
     */
    void process(HashMap<String, Integer> hypernymCount);
}
