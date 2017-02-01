import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BiggerIsGreaterTest {

    @Test
    public void ba_is_gt_ab(){
        String input = "ab";

        Assert.assertThat(nextWord("ab"), is("ba"));
    }

    private String nextWord(String word) {
        return null;
    }

}
