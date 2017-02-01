import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngryProfessorTest {

    @Test
    public void oneStudentInTime_classIsNotCanceled() {
        List<Integer> studentsArrival = Collections.singletonList(-1);
        String isCanceled = new StudentsClass(1).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("NO"));
    }

    @Test
    public void oneStudentNotInTime_classIsCanceled() {
        List<Integer> studentsArrival = Collections.singletonList(1);
        String isCanceled = new StudentsClass(1).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("YES"));
    }

    @Test
    public void twoStudentsInTime_classIsNotCanceled() {
        List<Integer> studentsArrival = Arrays.asList(-2, -1);
        String isCanceled = new StudentsClass(1).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("NO"));
    }

    @Test
    public void twoStudentsInTime_moreThanCancelThreshold_classIsNotCanceled() {
        List<Integer> studentsArrival = Arrays.asList(-2, -1);
        String isCanceled = new StudentsClass(1).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("NO"));
    }

    @Test
    public void oneStudentInTime_lessThanCancelThreshold_classIsCanceled() {
        List<Integer> studentsArrival = Arrays.asList(1, -2);
        String isCanceled = new StudentsClass(2).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("YES"));
    }

    @Test
    public void studentsExactlyInTime_countedAsInTime_classIsNotCanceled() {
        List<Integer> studentsArrival = Arrays.asList(0, 0);
        String isCanceled = new StudentsClass(2).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("NO"));
    }

    @Test
    public void acceptance_1() {
        List<Integer> studentsArrival = Arrays.asList(-1, -3, 4, 2);
        String isCanceled = new StudentsClass(3).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("YES"));
    }

    @Test
    public void acceptance_2() {
        List<Integer> studentsArrival = Arrays.asList(0, -1, 2, 1);
        String isCanceled = new StudentsClass(2).isClassCancelled(studentsArrival);

        assertThat(isCanceled, is("NO"));
    }

    private class StudentsClass {
        private int cancellationThreshold;

        StudentsClass(int cancellationThreshold) {
            this.cancellationThreshold = cancellationThreshold;
        }

        private String isClassCancelled(List<Integer> studentsArrivalTime) {
            int studentsInTime = studentsArrivalTime.stream()
                    .filter((arrivalTime) -> arrivalTime <= 0)
                    .collect(toList()).size();

            return studentsInTime >= cancellationThreshold ? "NO" : "YES";
        }
    }


}
