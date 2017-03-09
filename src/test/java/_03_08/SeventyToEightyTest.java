package _03_08;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.annotation.ExceptionProxy;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/8/17.
 */
public class SeventyToEightyTest {

    SeventyToEighty se=null;

    @Before
    public void setup(){
        se=new SeventyToEighty();
    }

    @Test
    public void removeDuplicatesII() throws Exception {
        int[]nums={1,1,1,2,2,3,3,3,3,3,3,4,4,4,4,5};
        System.out.println(se.removeDuplicatesIICount(nums));
    }

    @Test
    public void testWordDistance()throws Exception{
        System.out.println(se.minDistance("abcddexx","abdccecc"));
    }

    @Test
    public void testSimplifyPath()throws  Exception{
        System.out.println(se.simplifyPath("/a/./b/../../c/"));
    }

    @Test
    public void setZeroes()throws Exception{
        int[][]matrix={{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        se.setZeroes(matrix);
    }

    @Test
    public void window()throws Exception{
        System.out.println(se.minWindow("ADOBECODEBANC","ABC"));
    }

    @Test
    public void testSubset()throws Exception{
        int []nums={1,2,3};
        se.subsetsIterative(nums);
    }
    @Test
    public void testSearchMatrix()throws Exception{
        int [][]matrix={{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        System.out.println(se.searchMatrixByRow(matrix,23));
    }

}