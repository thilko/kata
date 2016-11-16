import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AnagramsTest {

    @Test
    public void check_anagrams() {
        assertThat(areAnagrams("A", "A"), is(true));
        assertThat(areAnagrams("A", "B"), is(false));
        assertThat(areAnagrams("AB", "BA"), is(true));
        assertThat(areAnagrams("AB", "AC"), is(false));

        assertThat(areAnagrams("ABC", "BAC"), is(true));
        assertThat(areAnagrams("ABC", "ACB"), is(true));
        assertThat(areAnagrams("ABC", "CAB"), is(true));

        assertThat(areAnagrams("AABC", "CAAB"), is(true));
        assertThat(areAnagrams("AABC", "BAAC"), is(true));

        assertThat(areAnagrams("anagram", "margana"), is(true));

        assertThat(areAnagrams("ABC", "BAD"), is(false));
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
