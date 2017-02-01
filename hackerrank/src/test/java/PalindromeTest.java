import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PalindromeTest {

    @Test
    public void returnsTrue_for_palindrome() {
        assertThat(isPalindrome("A"), is(true));
        assertThat(isPalindrome("madam"), is(true));
    }

    @Test
    public void returnsFalse_for_palindrome() {
        assertThat(isPalindrome("AB"), is(false));
    }

    private boolean isPalindrome(String word) {
        return new StringBuffer(word).reverse().toString().equals(word);
    }
}
