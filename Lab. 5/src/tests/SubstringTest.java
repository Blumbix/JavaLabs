package tests;

import org.junit.Test;

import static lab3.cw3.Substring.substring;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubstringTest {
    String a = "abcd";
    String b = "bcda";
    //S b = "bcdabcda";

    @Test
    public void shouldRepeatStringBTwoTimes() {
        assertEquals(1,substring(a,b));
    }
}