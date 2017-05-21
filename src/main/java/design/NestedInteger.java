package design;

import java.util.List;

/**
 * Created by tao on 4/17/17.
 */
public interface NestedInteger {


    //public NestedInteger();
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
      public Integer getInteger();

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
    public void setInteger(int value);
    public void add(NestedInteger ni);
}
