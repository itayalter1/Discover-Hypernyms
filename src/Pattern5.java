// Itay Alter 206132284
import java.util.HashMap;
import java.util.List;

/**
 * The Pattern5 class extends the BasePattern class and implements the Patterns
 * interface. It represents a specific pattern for discovering hypernyms in text.
 */
public class Pattern5 extends BasePattern implements Patterns {
    /**
     * Constructs a new Pattern5 object with the provided line and lemma.
     *
     * @param line  the line of text to process
     * @param lemma the lemma to search for as a hypernym
     */
    public Pattern5(String line, String lemma) {
        super(line, lemma, "(?=(NP( |,| ,| , |, )which is (an example |a kind |a class )?(of )?NP))");
    }

    @Override
    public void process(HashMap<String, Integer> hypernymCount) {
        super.findPattern(hypernymCount);
    }

    /**
     * Finds the lemma in the given list of nouns and updates the hypernym count.
     * It different from the method in the parent class because it looks for a
     * specific noun phrase structure where the second noun phrase is the hyponym
     * and the first noun phrase is the hypernym
     *
     * @param nouns         a List of nouns
     * @param hypernymCount a HashMap representing the count of hypernyms
     *                      where the key is the hypernym and the value is the
     *                      count
     *
     */
    @Override
    public void findLemma(List<String> nouns, HashMap<String, Integer> hypernymCount) {
        if (nouns.size() >= 2) {
            String hyponym = nouns.get(0);
            String lemmas = super.getLemma() + "s";
            String hypernym = nouns.get(1);
            if ((hyponym.equalsIgnoreCase(super.getLemma())) || (hyponym.equalsIgnoreCase(lemmas))) {
                if (hypernymCount.containsKey(hypernym.toLowerCase())) {
                    int count = hypernymCount.get(hypernym.toLowerCase());
                    hypernymCount.put(hypernym.toLowerCase(), count + 1);
                } else {
                    hypernymCount.put(hypernym.toLowerCase(), 1);
                }
            }
        }
    }
}
