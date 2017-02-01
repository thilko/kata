import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TokensTest {

    @Test
    public void can_return_one_token(){
        List<String> tokens = toTokens("he");

        assertThat(tokens.get(0), is("he"));
    }

    @Test
    public void acceptance_test_2(){
        List<String> tokens = toTokens("     YES      leading spaces        are valid,    problemsetters are         evillllll");

        assertThat(tokens.get(0), is("YES"));
    }

    @Test
    public void can_return_two_token(){
        List<String> tokens = toTokens("he is");

        assertThat(tokens.get(0), is("he"));
        assertThat(tokens.get(1), is("is"));
    }

    @Test
    public void can_return_three_token_with_different_delimiters(){
        List<String> tokens = toTokens("he is!good");

        assertThat(tokens.get(0), is("he"));
        assertThat(tokens.get(1), is("is"));
        assertThat(tokens.get(2), is("good"));
    }

    @Test
    public void acceptance_test(){
        List<String> tokens = toTokens("He is a very very good boy, isn't he?");

        assertThat(tokens.get(0), is("He"));
        assertThat(tokens.get(1), is("is"));
        assertThat(tokens.get(2), is("a"));
        assertThat(tokens.get(3), is("very"));
        assertThat(tokens.get(4), is("very"));
        assertThat(tokens.get(5), is("good"));
        assertThat(tokens.get(6), is("boy"));
        assertThat(tokens.get(7), is("isn"));
        assertThat(tokens.get(8), is("t"));
        assertThat(tokens.get(9), is("he"));

        tokens.forEach(System.out::println);
    }

    private List<String> toTokens(String sentence) {
        StringTokenizer st = new StringTokenizer(sentence, " !,?._'@");
        List<String> b = new ArrayList<>();
        while(st.hasMoreTokens()){
            b.add(st.nextToken());
        }
        return b;
    }
}
