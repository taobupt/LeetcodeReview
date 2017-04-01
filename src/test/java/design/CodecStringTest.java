package design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tao on 4/1/2017.
 */
public class CodecStringTest {

    @Test
    public void testCodec()throws Exception{
        CodecString cs=new CodecString();
        List<String>res=new ArrayList<>();
        res.add("acv");
        res.add("cdefg");
        res.add("hgyihjio");
        List<String>ans=cs.decode(cs.encode(res));
        for(String str:ans)
            System.out.println(str);
    }

}