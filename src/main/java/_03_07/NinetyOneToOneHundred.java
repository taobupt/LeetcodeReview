package _03_07;

import common.ListNode;
import common.Tree;
import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 3/9/17.
 */

//补3月七号没做的作业
public class NinetyOneToOneHundred {


    TreeNode pre=null;
    TreeNode first=null;
    TreeNode second=null;



    //91 decode ways
    public int numDecodings(String s) {
        char []ss=s.toCharArray();
        int n=ss.length;
        int []dp=new int[n+1];
        dp[0]=1;
        if(s.isEmpty()||s.charAt(0)=='0')
            return 0;
        for(int i=1;i<n;++i){
            if(s.charAt(i-1)=='0')
                return 0;
        }
        return dp[n];
    }

    //92 reverse Linked list II

    public ListNode reverseList(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode newHead=null;
        while(head!=null){
            ListNode tmp=head.next;
            head.next=newHead;
            newHead=head;
            head=tmp;
        }
        return newHead;
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null||head.next==null||m==n)
            return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode p=dummy;
        int ind=1;
        while(ind<m) {
            p = p.next;
            ind++;
        }
        ListNode first=p;//第一段
        while(ind<=n){
            p=p.next;
            ind++;
        }
        ListNode second=p.next;
        p.next=null;
        ListNode mid=reverseList(first.next);
        first.next.next=second;
        first.next=mid;
        return dummy.next;


    }


    //93 restore the ip address
    //回溯法

    public boolean isValid(String s){
        if(s.length()>3)
            return false;
        if(s.length()>1 && s.charAt(0)=='0')
            return false;
        return Integer.valueOf(s)<=255;
    }

    public void backtrack(List<String>res,String s,List<String>path,int pos){
        if(pos==s.length() && path.size()==4){
            res.add(String.join(".",path));
            return ;
        }
        for(int i=pos+1;i<=s.length();++i){
            String sub=s.substring(pos,i);
            if(isValid(sub)){
                path.add(sub);
                backtrack(res,s,path,i);
                path.remove(path.size()-1);
            }
        }
    }
    public List<String> restoreIpAddresses(String s) {
        int n=s.length();
        List<String>res=new ArrayList<>();
        if(n<4||n>12)
            return res;
        backtrack(res,s,new ArrayList<String>(),0);
        return res;
    }
    //94 直接pass



    //95unique binary search tree II

    public List<TreeNode>generateTrees(int begin,int end){
        List<TreeNode>res=new ArrayList<>();
        if(begin>end){
            res.add(null);
        }
        else if(begin==end){
            res.add(new TreeNode(begin));
        }else if(begin<end){
            for(int i=begin;i<=end;++i){
                List<TreeNode>left=generateTrees(begin,i-1);
                List<TreeNode>right=generateTrees(i+1,end);
                for(int m=0;m<left.size();++m){
                    for(int n=0;n<right.size();++n){
                        TreeNode root=new TreeNode(i);
                        root.left=left.get(m);
                        root.right=right.get(n);
                        res.add(root);
                    }
                }
            }
        }

        return  res;
    }
    public List<TreeNode> generateTrees(int n) {
        if(n==0)
            return new ArrayList<>();
        return generateTrees(1,n);
    }
    //96
    //unique binary search tree
    public int numTrees(int n) {
        //catalan number
        int []dp=new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<=n;++i){
            for(int j=0;j<=i-1;++j){
                dp[i]+=dp[j]*dp[i-1-j];
            }
        }
        return dp[n];
    }

    //another way just cal the factorial
    //long overflow

    //97 dp

    public boolean isInterleave(String s1, String s2, String s3) {
        int m=s1.length(),n=s2.length();
        boolean [][]dp=new boolean[m+1][n+1];
        if(s3.length()!=m+n)//pay attention to this
            return false;
        dp[0][0]=true;
        for(int i=1;i<=m;++i)
            dp[i][0]=dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1);//条件不是那么轻松的
        for(int i=1;i<=n;++i)
            dp[0][i]=dp[0][i-1]&&s2.charAt(i-1)==s3.charAt(i-1);
        for(int i=1;i<=m;++i){
            for(int j=1;j<=n;++j){
                dp[i][j]=(dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1))||(dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    //98 valid binary search tree
    //naive way, wast of space
    public boolean isValidBST(TreeNode root) {
        Tree t=new Tree();
        List<Integer> res=new ArrayList<>();
        t.inorder(root,res);
        int n=res.size();
        for(int i=1;i<n;++i){
            if(res.get(i)<=res.get(i-1))
                return false;
        }
        return true;
    }

    //save space;

    public boolean isValidBSTSaveSpace(TreeNode root){
        if(root==null)
            return true;
        if(!isValidBSTSaveSpace(root.left))
            return false;
        if(pre!=null && pre.val>=root.val)
            return false;
        pre=root;
        return isValidBSTSaveSpace(root.right);
    }

    //两边有值的recursive way

    //iterative way
    public boolean isValidBSTIterative(TreeNode root){
        //其实就是一个中序遍历，你要知道可是有4种中序遍历，递归只是其中的一种罢了
        //一种递归，两种stack，一种Morris
        return false;

    }






    //99 recover binary search tree

    public void dfs(TreeNode root){
        if(root==null)
            return;
        dfs(root.left);
        if(pre!=null && pre.val>root.val){
            if(first==null)
                first=pre;
            second=root;
        }
        pre=root;
        dfs(root.right);
    }
    public void recoverTree(TreeNode root) {
        if(root==null||root.left==null && root.right==null)
            return;
        dfs(root);
        int tmp=first.val;
        first.val=second.val;
        second.val=tmp;

    }



    //100 same tree
    //树有两种解决办法，一种是recursive，一种是iterative way
    //recursive way
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null||q==null)
            return p==q;
        return (p.val==q.val) &&isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    //iterative way;

    //use queue
    //打得太快，容易出现typo
    //用一个queue也是可以的，每次pop出俩，每次进left之后都判断是不是%2
    public boolean isSameTreeIterative(TreeNode p,TreeNode q){
        if(p==null||q==null)
            return p==q;
        Queue<TreeNode>queue1=new LinkedList<>();
        Queue<TreeNode>queue2=new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty()){
            TreeNode pnode=queue1.poll();
            TreeNode qnode=queue2.poll();
            if(pnode.val!=qnode.val)
                return false;
            if(pnode.left!=null)
                queue1.offer(pnode.left);
            if(qnode.left!=null)
                queue2.offer(qnode.left);
            if(queue1.size()!= queue2.size())
                return false;
            if(pnode.right!=null)
                queue1.offer(pnode.right);
            if(qnode.right!=null)
                queue2.offer(qnode.right);
            if(queue1.size()!= queue2.size())
                return false;
        }
        return true;
    }

    //use stack ,其实也就是把尾递归改成你想要的

}
