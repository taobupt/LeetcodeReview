package _04_01;

import common.TreeNode;

import java.util.*;

/**
 * Created by Tao on 4/1/2017.
 */
public class TwoHundredSeventySixToEightyFive {


    //276 paint fence
    //想清楚就没事了，唉，要耐住寂寞啊，word哥
    public int numWays(int n, int k) {
        if(n==0)
            return 0;
        if(n==1)
            return k;
        int same=k,diff=k*(k-1);
        for(int i=3;i<=n;++i){
            int tmp=diff;
            diff=(k-1)*(same+diff);
            same=tmp;
        }
        return diff+same;
    }

    //277
    boolean knows(int a, int b){
        return true;
    }


    public int findCelebrity(int n) {
        int ans=0;
        for(int i=0;i<n;++i){
            if(knows(ans,i))
                ans=i;
        }

        //check if it is ok
        for(int i=0;i<n;++i){
            if(i==ans)
                continue;
            if(!knows(i,ans)||knows(ans,i))
                return -1;
        }
        return ans;


        //reduce the calls of knows
        /*
        if(i<candidate && knows(candidate, i) || !knows(i, candidate)) return -1;
        if(i>candidate && !knows(i, candidate)) return -1;
         */
    }

    //using stack

    public int findCelebrityBystack(int n) {
        if(n<=0)
            return -1;
        if(n==0)
            return 1;
        Stack<Integer> stk=new Stack<>();
        for(int i=0;i<n;++i)
            stk.push(i);
        while(stk.size()>1){
            int a=stk.pop(),b=stk.pop();
            if(knows(a,b))
                stk.push(b);
            else
                stk.push(a);
        }

        int c = stk.pop();

        for (int i = 0; i < n; i++)
            // c should not know anyone else
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;

        return c;
        //check whether it is a valid guy

    }



    //278 first bad version
    boolean isBadVersion(int version){
        return true;
    }
    public int firstBadVersion(int n) {
        int begin=1,end=n;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(isBadVersion(mid))//查找low_bound
                end=mid;
            else
                begin=mid+1;
        }
        return begin;
    }


    //perfect squares
    // 就一普通的dp
    //不同脑筋的家伙就是这个下场
    public boolean isSquare(int n){
        int mid=(int)Math.sqrt(n);
        return mid*mid==n;
    }
    public int numSquares(int n) {

        int []dp=new int[n+1];
        if(isSquare(n))
            return 1;
        for(int i=1;i<=n;++i){
            dp[i]=i;
            if(isSquare(i)){
                dp[i]=1;
                continue;
            }
            for(int j=1;j<i;++j){
                if(isSquare(i-j))
                    dp[i]=Math.min(dp[i],dp[j]+1);
            }
        }
        return dp[n];
    }

    public int numSquaresConcise(int n){
        int []dp=new int[n+1];
        for(int i=1;i<=n;++i){
            dp[i]=i;
            for(int j=0;j<=46340 && j*j<=i;++j)
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
        }
        return dp[n];
    }

    //serach by bfs
    public int numSquaresBFS(int n){
        int cnt=1;
        List<Integer>square=new ArrayList<>();
        for(int i=1;i<=46340;++i){
            if(i*i<=n)
                square.add(i*i);
        }
        int m=square.size();
        Queue<Integer>q=new LinkedList<>();
        q.offer(n);
        while(!q.isEmpty()){
            int size=q.size();
            //cnt++;
            for(int i=0;i<size;++i){
                int tmp=q.poll();
                for(int j=m-1;j>=0;--j){
                    if(tmp==square.get(j))
                        return cnt;
                    if(tmp<square.get(j))
                        continue;
                    q.offer(tmp-square.get(j));
                }
            }
            cnt++;
        }
        return cnt;
    }



    //283
    public void swap(int[]nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    //keep order
    public void moveZeroes(int[] nums) {
        int i=0,j=0,n=nums.length;
        while(i<n){
            if(nums[i]!=0)
                nums[j++]=nums[i];
            i++;
        }
        while(j<n)
            nums[j++]=0;
    }
    //285 麻蛋，昨天在HEB想了很久，我擦，居然忘了，看来还是理解不够深入啊，得经常复习啊，少年
    //norder Successor in BST
    //recursive
    //without parent
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null)
            return null;
        if(root.val<=p.val)
            return inorderSuccessor(root.right,p);
        else{
            TreeNode left=inorderSuccessor(root.left,p);
            return left!=null?left:root;
        }
    }

    //iterative way
    public TreeNode inorderSuccessorIterative(TreeNode root,TreeNode p){
        TreeNode cur=root;
        TreeNode res=null;
        while(cur!=null){
            if(cur.val<=p.val)
                cur=cur.right;
            else{
                res=cur;
                cur=cur.left;
            }
        }
        return res;
    }

    //predecessor 和这个是一样的，稍微改变一下就可以了,J就是把判断条件改一下

    //with parent
    //有右节点，就往右，再往左，不然就是利用parent节点向上爬，爬到parent.left=cur;
    public TreeNode inorderSuccessorWithParent(TreeNode p){
        //
        TreeNode res=null;
        TreeNode curr=p;
        if(curr.right!=null){
            curr=curr.right;
            while(curr.left!=null)
                curr=curr.left;
            res=curr;
        }else{
            TreeNode parent=curr.parent;
            while(parent!=null && parent.right==curr){
                curr=parent;
                parent=curr.parent;
            }
            res=parent;
        }
        return res;
    }
}
