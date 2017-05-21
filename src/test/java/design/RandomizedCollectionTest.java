package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/13/17.
 */
public class RandomizedCollectionTest {

    @Test
    public void teste(){
        RandomizedCollection rc =new RandomizedCollection();
        rc.insert(4);
        rc.insert(3);
        rc.insert(4);
        rc.insert(2);
        rc.insert(4);
        rc.remove(4);
        rc.remove(3);
        rc.remove(4);
        rc.remove(4);
    }

}