package design;

/**
 * Created by Tao on 3/29/2017.
 */

import java.util.Iterator;
import java.util.List;

//brute force?
//正确的做法应该是存iterator
//
public class Vector2D implements Iterator<Integer> {

    private List<List<Integer>>res=null;
    private int x;
    private int y;
    public Vector2D(List<List<Integer>> vec2d) {
        res=vec2d;
        x=0;
        y=0;
    }

    @Override
    public Integer next() {
        while(res.get(x).isEmpty())
            x++;
        int top=res.get(x).get(y++);
        if(y==res.get(x).size()){
            y=0;
            x++;
        }
        return top;
    }

    @Override
    public boolean hasNext() {
        //防止多个空出现
        while(x<res.size() && y==0 && res.get(x).isEmpty())
            x++;
        return (x<res.size()-1||(x==res.size()-1 && y<res.get(x).size()));
    }
}
