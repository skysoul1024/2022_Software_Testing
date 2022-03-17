import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{-3, -1, -2, 5}, new int[]{-3, -2, -1, 5}),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
                Arguments.of(new int[]{3, 2, 1}, new int[]{1, 2 ,3})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int len = random_array.length;
        for(int i=0; i<len; i++)
            test.add(random_array[i]);

        int[] result = new int[len];
        for(int i=0; i<len; i++)
            result[i] = test.poll();

        assertArrayEquals(correct_array, result);
    }
    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }

    @Test
    public void whenExceptionThrown_nullForEach(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(10);
            test.forEach(null);
        });
    }
}
