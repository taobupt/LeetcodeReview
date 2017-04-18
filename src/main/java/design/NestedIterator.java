package design;

import java.util.*;

/**
 * Created by tao on 4/17/17.
 */
//主要涉及到一个lazy的问题，你说一次性把它全部装到一个array里可以完事，但是不太好，所需空间太大,愚蠢的办法我就不写了

public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stk=null;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk=new Stack<>();
        int n=nestedList.size();
        for(int i=n-1;i>=0;--i){
            if(!nestedList.get(i).isInteger()){
                if(nestedList.get(i).getList().isEmpty())
                    continue;
            }
            stk.push(nestedList.get(i));
        }

    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }

    //把职责放到hasnext里面来，不然hasnext判断有了，你就得必须有，否则就gg了，但是hasnext有不一定有啊，有可能是空的
    @Override
    public boolean hasNext() {
        while(!stk.isEmpty() && !stk.peek().isInteger()){
            NestedInteger top=stk.pop();
            if(top.isInteger()){
                stk.push(top);
                break;
            }else{
                List<NestedInteger>nestedList=top.getList();
                int n=nestedList.size();
                for(int i=n-1;i>=0;--i){
                    if(!nestedList.get(i).isInteger()){
                        if(nestedList.get(i).getList().isEmpty())
                            continue;
                    }
                    stk.push(nestedList.get(i));
                }
            }
        }
        return !stk.isEmpty();
    }
}
