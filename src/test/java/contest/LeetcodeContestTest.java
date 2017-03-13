package contest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/12/17.
 */
public class LeetcodeContestTest {

    LeetcodeContest lc=null;

    @Before
    public void setup(){
        lc=new LeetcodeContest();
    }
    @Test
    public void wordsAbbreviation() throws Exception {
        String []strs={"bbbbab","bbbaab","baaaab"};
        List<String> dict=new ArrayList<>(Arrays.asList(strs));
        List<String>res=lc.wordsAbbreviation(dict);
        for(String str:res)
            System.out.println(str);
    }

}