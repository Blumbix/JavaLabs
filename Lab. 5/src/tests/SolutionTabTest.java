package tests;

import org.junit.Test;

import static lab3.cw4.SolutionTab.solution;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTabTest {
    float[] array = {8, 1, 3, 14, 1, 2, 9, 5, 6, 4};
    float target = 11;
    int[] res = solution(array, target);

    @Test
    public void twoNumbersFromArrayGiveTarget() {
        assertTrue(array[res[0]]+array[res[1]]==target);
    }
}