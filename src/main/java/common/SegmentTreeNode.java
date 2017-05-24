package common;

/**
 * Created by tao on 5/22/17.
 */
public class SegmentTreeNode {
    public int start,end;
    public SegmentTreeNode left,right;
    public SegmentTreeNode(int start,int end){
        this.start=start;
        this.end=end;
        this.left=null;
        this.right=null;
    }
}

class SegmentTreeNodeMax{
    public int start,end,max;
    public SegmentTreeNodeMax left,right;
    public SegmentTreeNodeMax(int start,int end,int max){
        this.start=start;
        this.end=end;
        this.max=max;
        this.left=null;
        this.right=null;
    }
}

class SegmentTreeNodeCount{
    public int start,end,count;
    public SegmentTreeNodeCount left,right;
    public SegmentTreeNodeCount(int start,int end,int count){
        this.start=start;
        this.end=end;
        this.count=count;
        this.left=null;
        this.right=null;
    }
}


