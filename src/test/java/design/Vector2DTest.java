package design;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao on 3/29/2017.
 */
public class Vector2DTest {
    Vector2D v2d=null;


    @Test
    public void test(){
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>l1=new ArrayList<>();
        l1.add(1);
//        l1.add(2);
//        res.add(new ArrayList<>(l1));
//        l1.clear();
//        l1.add(3);
//        res.add(new ArrayList<>(l1));
//        l1.clear();;
//        l1.add(4);
//        l1.add(5);
//        l1.add(6);
        res.add(new ArrayList<>(l1));
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        v2d=new Vector2D(res);
        while(v2d.hasNext()){
            System.out.println(v2d.next());
        }
    }

}
