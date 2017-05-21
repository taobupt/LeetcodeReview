package design;

import org.junit.Test;

/**
 * Created by tao on 5/13/17.
 */
public class PhoneDirectoryTest {

    @Test
    public void test(){
        PhoneDirectory pd=new PhoneDirectory(3);
        System.out.println(pd.get());
        System.out.println(pd.get());
        System.out.println(pd.check(2));


    }
}
