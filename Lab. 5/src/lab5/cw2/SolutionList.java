package lab3.cw2;

import java.util.*;

public abstract class SolutionList {

    public static int solution(List<Integer> a) throws TooBigListException{
        if(a.size()>10000) {
            throw new TooBigListException("The List is too big! (over 10E4 elements)");
        }
        Collections.sort(a);
        int lowest = 1;
        for(int next : a){
            if(lowest>next){
                continue;
            }
            else if(lowest==next){
                lowest++;
            }
            else {
                break;
            }
        }
        return lowest;
    }

    public static void solutionMain() {
        Random generator = new Random();
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(generator.nextInt(5), generator.nextInt(5), -generator.nextInt(5), generator.nextInt(5), generator.nextInt(5), -generator.nextInt(5)));
        System.out.println("List:");
        for(int x : list){
            System.out.print(x + "\t");
        }
        try{
            int x=solution(list);
            System.out.println("\nLowest positive argument not on list: "+x);
        }
        catch(TooBigListException ex){
            System.out.println("Caught the exception: "+ex.getMessage());;
        }
    }
}
