package design;

import org.junit.Test;

/**
 * Created by Tao on 4/15/2017.
 */
public class ValidWordAbbrTest {
    @Test
    public void testValid(){
        String[]strs={"deer","door","cake","card"};
        ValidWordAbbr va=new ValidWordAbbr(strs);
        String []args={"dear","cart","cane","make"};
        for(String str:args){
            System.out.println(va.isUnique(str));
        }
    }
}
