package design;

import java.util.Iterator;

/**
 * Created by tao on 4/2/17.
 */
class PeekingIterator implements Iterator<Integer> {

    private  Iterator<Integer>it=null;
    private Integer next=null;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it=iterator;
        if(it.hasNext())
            next=it.next();

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer tmp=next;
        next=it.hasNext()?it.next():null;
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return next!=null;
    }
}
