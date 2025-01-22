// Itay Alter 206132284
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FatherPattern is a class representing a pattern for finding hypernyms related
 * to a lemma within a line of text. It provides methods for finding patterns and
 * counting hypernyms based on the provided regex. Subclasses can inherit from
 * this class to customize the behavior of finding patterns and processing hypernyms.
 */
public class BasePattern {
    // define fields
    private String line;
    private String lemma;
    private String regex;


    /**
     * Constructs a BasePattern object with the specified line, lemma, and regex.
     *
     * @param line  the line of text to search for patterns
     * @param lemma the lemma to find hypernyms for
     * @param regex the regular expression representing the pattern
     */
    public BasePattern(String line, String lemma, String regex) {
        this.line = line;
        this.lemma = lemma;
        this.regex = regex;
    }

    /**
     * Finds patterns in the line and counts hypernyms related to the lemma.
     *
     * @param hypernymCount the map to store hypernym counts
     */
    public void findPattern(HashMap<String, Integer> hypernymCount) {
        Pattern pattern = Pattern.compile(this.regex.replace("NP", DiscoverHypernym.NP));
        Matcher matcher = pattern.matcher(this.line);
        // Process the line
        while (matcher.find()) {
            List<String> nouns = new ArrayList<>();
            String sentence = matcher.group(1);
            Pattern p1 = Pattern.compile(DiscoverHypernym.NP);
            Matcher m1 = p1.matcher(sentence);
            // find NP
            while (m1.find()) {
                String np = m1.group();
                // Remove <np> and </np> tags
                np = np.substring(4, np.length() - 5);
                nouns.add(np);
            }
            if (nouns.size() > 1) {
                findLemma(nouns, hypernymCount);
            }
        }
    }

    /**
     * Finds the lemma among the given nouns and updates the hypernymCount map
     * accordingly.
     *
     * @param nouns          the list of nouns to search for the lemma
     * @param hypernymCount  the map to store hypernym counts
     */
    public void findLemma(List<String> nouns, HashMap<String, Integer> hypernymCount) {
        String hypernym = nouns.get(0);
        nouns.remove(0);
        for (String noun : nouns) {
            if (noun.equalsIgnoreCase(this.lemma)) {
                if (hypernymCount.containsKey(hypernym.toLowerCase())) {
                    int count = hypernymCount.get(hypernym.toLowerCase());
                    hypernymCount.put(hypernym.toLowerCase(), count + 1);
                } else {

                    hypernymCount.put(hypernym.toLowerCase(), 1);
                }
            }
        }
    }

    /**
     * Retrieves the lemma associated with this pattern.
     *
     * @return the lemma
     */
    public String getLemma() {
        return lemma;
    }
}
