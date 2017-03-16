package contest;

import design.LRUCache;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/16/17.
 */
public class LRUCacheTest {

    LRUCache lruCache=null;

    @Test
    public void setup(){
        lruCache=new LRUCache(1);
        lruCache.put(2,1);
        System.out.println(lruCache.get(2));
        lruCache.put(3,2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }

}