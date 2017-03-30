package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Tao on 3/30/2017.
 */
public class Vector2DIterator implements Iterator<Integer> {
    private Iterator<Integer>it=null;
    private Queue<Iterator<Integer>>q=null;
    public Vector2DIterator(List<List<Integer>> vec2d) {
        q=new LinkedList<>();
        int n=vec2d.size();
        for(int i=0;i<n;++i){
            q.offer(vec2d.get(i).iterator());
        }
        it=q.poll();//return null if q is empty
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        if(it==null)
            return false;
        while(!it.hasNext()){
            if(!q.isEmpty())
                it=q.poll();
            else
                return false;
        }
        return true;
    }
}
