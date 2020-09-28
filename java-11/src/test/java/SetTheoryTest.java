import io.nimbus.math.SetTheory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetTheoryTest {

    private final SetTheory<Integer> objectUnderTest = new SetTheory<>();

    @Test
    public void union() {
        Set<Integer> a = Set.of(1, 2, 3);
        Set<Integer> b = Set.of(3, 4, 5);

        Set<Integer> union = objectUnderTest.union(a, b);

        assertEquals(Set.of(1, 2, 3, 4, 5), union);
    }

    @Test
    public void intersection() {
        Set<Integer> a = Set.of(1, 2, 3, 4);
        Set<Integer> b = Set.of(3, 4, 5, 6);

        Set<Integer> intersection = objectUnderTest.intersection(a, b);

        assertEquals(Set.of(3, 4), intersection);
    }

    @Test
    public void difference() {
        Set<Integer> a = Set.of(1, 2, 3);
        Set<Integer> b = Set.of(2, 3, 4, 5);

        Set<Integer> intersection = objectUnderTest.difference(a, b);

        assertEquals(Set.of(1), intersection);
    }


}