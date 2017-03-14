package common;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/13/17.
 */
public class StringAlgoTest {

    public StringAlgo sa=null;

    @Before
    public void setup(){
        sa=new StringAlgo();
    }
    @Test
    public void indexof() throws Exception {
        List<Integer>res=sa.indexof("abababafgababa","ababa");
        for(int x:res)
            System.out.println(x);
    }

    @Test
    public void testMancher()throws Exception{
        System.out.println(sa.manacher("cbbd"));
    }

}