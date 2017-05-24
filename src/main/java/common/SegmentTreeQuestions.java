package common;

import java.util.ArrayList;

/**
 * Created by tao on 5/22/17.
 */
public class SegmentTreeQuestions {


    //lintcode 206 interval sum
    class Node{
        public long val;
        public int start,end;
        public Node left,right;
        public Node(long val,int start,int end){
            this.start=start;
            this.end=end;
            this.val=val;
        }
    }

    public Node build(int []A,int start,int end){
        if(start>end)
            return null;
        if(start==end)
            return new Node((long)A[start],start,end);
        int mid=(end-start)/2+start;
        Node root=new Node(0,start,end);
        root.left=build(A,start,mid);
        root.right=build(A,mid+1,end);
        root.val=root.left.val+root.right.val;
        return root;
    }

    public long query(Node root,int start,int end){
        if(start>end||root==null||end<root.start||start>root.end)
            return 0;
        if(start<=root.start && root.end<=end)//这个很关键
            return root.val;
        if(root.left.end>=end)
            return query(root.left,start,end);
        else if(root.right.start<=start)
            return query(root.right,start,end);
        else
            return query(root.right,root.right.start,end)+query(root.left,start,root.left.end);
    }

    public void modify(Node root,int index,int val){
        if(root==null||index<root.start||index>root.end)
            return;
        if(root.left==null && root.right==null){
            root.val=val;
            return;
        }
        if(root.left.end>=index)
            modify(root.left,index,val);
        else if(root.right.start<=index)
            modify(root.right,index,val);
        root.val=root.left.val+root.right.val;
    }

    //the first version, second version will add modify;
    //也是可以用Fenwick Tree
    public ArrayList<Long> intervalSum(int[] A, ArrayList<Interval> queries) {
        // write your code here
        Node root=build(A,0,A.length-1);
        ArrayList<Long>res=new ArrayList<>();
        for(Interval interval:queries){
            res.add(query(root,interval.start,interval.end));
        }
        return res;

    }
}
