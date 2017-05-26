package common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/25/17.
 */
public class LFUCacheTest {

    LFUCache cache=null;


    @Test
    public void test(){
        cache =new LFUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5,5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));


//        cache.put(3,1);
//        cache.put(2,1);
//        cache.put(2,2);
//        cache.put(4,4);
//        System.out.println(cache.get(2));

//        System.out.println(cache.get(1));
//        cache.put(3, 3);    // evicts key 2
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3.
//        cache.put(4, 4);    // evicts key 1.
//        System.out.println(cache.get(1));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3
//        System.out.println(cache.get(4));       // returns 4
    }

}