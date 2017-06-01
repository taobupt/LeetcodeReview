package design;

import org.junit.Test;

/**
 * Created by tao on 6/1/17.
 */
public class AllOneTest {

    @Test
    public void testAllOne(){

        /*
        ["AllOne","inc","inc","inc","inc","getMaxKey","inc","inc","inc","dec","inc","inc","inc","getMaxKey"]
        [[],["hello"],["goodbye"],["hello"],["hello"],[],["leet"],["code"],["leet"],["hello"],["leet"],["code"],["code"],[]]
         */

        /*
        ["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
         */
        AllOne all = new AllOne();
        all.inc("a");
        all.inc("b");
        all.inc("b");
        all.inc("b");
        all.inc("b");
        all.dec("b");
        all.dec("b");
        System.out.println(all.getMaxKey());
        System.out.println(all.getMinKey());


//        all.inc("hello");
//        all.inc("goodbye");
//        all.inc("hello");
//        all.inc("hello");
//        System.out.println(all.getMaxKey());
//        all.inc("leet");
//        all.inc("code");
//        all.inc("leet");
//        all.dec("hello");
//        all.inc("leet");
//        all.inc("code");
//        all.inc("code");
//        System.out.println(all.getMaxKey());


//        System.out.println(all.getMaxKey());
//        System.out.println(all.getMinKey());
//        all.inc("hha");
//        all.inc("hha");
//        System.out.println(all.getMaxKey());
//        System.out.println(all.getMinKey());
//        all.inc("hha");
//        all.dec("hha");
//        System.out.println(all.getMaxKey());
//        System.out.println(all.getMinKey());
    }
}
