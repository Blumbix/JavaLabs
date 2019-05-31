package tests;

import lab3.cw2.TooBigListException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static lab3.cw2.SolutionList.solution;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SolutionListTest {
    Random generator = new Random();
    List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4));

    @Test
    public void shouldFindLowestIntNumberNotOnList() throws TooBigListException {
        Integer lowest = solution(list);
        //Integer lowest=1;
        assertFalse(list.contains(lowest));
    }

   @Test(expected = TooBigListException.class)
    public void shouldThrowExceptionTooBigListException() throws TooBigListException{
        int n=100000;
       List<Integer> list = new ArrayList<Integer>(n);
       for(int i=0;i<n;i++)
           list.add(i);
       Integer lowest=solution(list);
       System.out.println(lowest);
          // assertThrows(TooBigListException.class,()->{solution(new ArrayList<Integer>(111000));});
  }

}