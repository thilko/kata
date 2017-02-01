import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AnagramsTest {

    @Test
    public void check_anagrams() {
        assertTrue(areAnagrams("A", "A"));
        assertTrue(areAnagrams("AB", "BA"));
        assertTrue(areAnagrams("AABC", "BAAC"));
    }

    private boolean areAnagrams(String firstString, String secondString) {
        return sortString(firstString).equals(sortString(secondString));
    }

    private String sortString(String string) {
        char[] charsToSort = string.toLowerCase().toCharArray();

        for (int i = 1; i < charsToSort.length; i++) {
            char selectedChar = charsToSort[i];
            while (i > 0 && charsToSort[i-1] > charsToSort[i]){
                char temp = charsToSort[i-1];
                charsToSort[i-1] = selectedChar;
                charsToSort[i] = temp;
                i--;
            }
        }

        return new String(charsToSort);
    }
}
