package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/13/17.
 */
public class RandomizedSetTest {


    @Test
    public void test(){
        RandomizedSet rs =new RandomizedSet();
        rs.insert(1);
        rs.remove(2);
        rs.insert(2);
        rs.getRandom();
        rs.remove(1);
        rs.insert(2);
        rs.getRandom();
    }

}