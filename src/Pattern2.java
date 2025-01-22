// Itay Alter 206132284
import java.util.HashMap;

/**
 * The Pattern2 class extends the BasePattern class and implements the Patterns
 * interface. It represents a specific pattern for discovering hypernyms in text.
 */
public class Pattern2 extends BasePattern implements Patterns {
    /**
     * Constructs a new Pattern2 object with the provided line and lemma.
     *
     * @param line  the line of text to process
     * @param lemma the lemma to search for as a hypernym
     */
    public Pattern2(String line, String lemma) {
        super(line, lemma, "(?=(such NP as NP((,| ,| , |, )NP)*(( |,| ,| , |, )?(and |or )NP)?))");
    }

    @Override
    public void process(HashMap<String, Integer> hypernymCount) {
        super.findPattern(hypernymCount);
    }
}
