import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CutTheSticks {

    @Test
    public void sticksCanBeCutByAGivenNumber() {
        List<Integer> sticks = Arrays.asList(4, 5, 6);

        List<Integer> result = cutTheSticks(sticks);

        assertThat(result, is(Arrays.asList(1, 2)));
    }

    @Test
    public void sticksWithZeroOrLessSizeAreFiltered() {
        List<Integer> sticks = Arrays.asList(1, 3, 2, 6);

        List<Integer> result = cutTheSticks(sticks);

        assertThat(result, is(Arrays.asList(2, 1, 5)));
    }

    @Test
    public void theSmallestStickCanBeCalculated() {
        List<Integer> sticks = Arrays.asList(45, 78, 22);

        Integer smallestStick = smallestStick(sticks);

        assertThat(smallestStick, is(22));
    }


    @Test
    public void theSmallestStickWithMinimumLengthCanBeCalculated() {
        List<Integer> sticks = Arrays.asList(5, 7, 9, 11);

        assertThat(cutTheSticks(sticks), is(Arrays.asList(2, 4, 6)));
    }

    @Test
    public void numberOfSticksCanBeCalculated() {
        List<Integer> sticks = Arrays.asList(5, 4, 4, 2, 2, 8);

        List<Integer> result = cutAllSticks(sticks);

        assertThat(result, is(Arrays.asList(6, 4, 2, 1)));
    }

    private List<Integer> cutAllSticks(List<Integer> sticks) {
        List<Integer> count = new ArrayList<>();
        while (!sticks.isEmpty()) {
            count.add(sticks.size());
            sticks = cutTheSticks(sticks);
        }
        return count;
    }

    private List<Integer> cutTheSticks(List<Integer> sticks) {
        Integer smallestStick = smallestStick(sticks);
        return sticks.stream().map((stickToCut) -> stickToCut - smallestStick)
                .filter((stickToFilter) -> stickToFilter > 0)
                .collect(Collectors.toList());
    }

    private Integer smallestStick(List<Integer> sticks) {
        return sticks.stream().min((i, j) -> i > j ? 1 : 0).get();
    }

}
