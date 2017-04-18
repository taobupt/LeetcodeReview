package _04_09;

import common.FenwickTree;
import common.ListNode;
import common.TreeNode;

import java.util.*;

/**
 * Created by Tao on 4/14/2017.
 */
public class ThreeHundredTwentysixToThirtyFive {

    //POWER OF THREE
    //循环，递归
    boolean isPowerOfThree(int n) {
        if(n<=0)
            return  false;
        while(n>=3){
            if(n%3!=0)
                return  false;
            n/=3;
        }
        return n==1;
    }

    boolean isPowerOfThreeRecrusive(int n){
        if(n<=0)
            return false;
        if(n==1)
            return true;
        return n%3==0 && isPowerOfThreeRecrusive(n/3);
    }

    //log 最nb的解法
    boolean isPowerOfThreeMath(int n){
//        if(n<=0)
//            return false;
//        double res=Math.log(n)/Math.log(3);
//        System.out.println(Math.log(129140162)/Math.log(3));
//        int ans=(int)res;
//        return ans==res ||(Math.abs(1+ans-res)<1e-14);
        return ( n>0 &&  1162261467%n==0);
    }


    // Count of Range Sum
    //naive is brute force
    //区间，其实都是可以用fenwick tree
    //lower + sum[i – 1] ≤ sum[j] ≤ upper + sum[i – 1]
    //overflow
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n=nums.length;
        Set<Long>set=new HashSet<>();
        long total=0;
        set.add((long)lower-1);
        set.add((long)upper);
        for(long x:nums){
            total+=(long)x;
            set.add(total);
            set.add(total+(long)lower-1);
            set.add(total+(long)upper);
        }
        List<Long> res=new ArrayList<>(set);
        Collections.sort(res);
        Map<Long,Integer>map=new HashMap<>();
        int m=res.size();
        //离散化
        for(int i=0;i<m;++i)
            map.put(res.get(i),i+1);
        int ans=0;
        FenwickTree tree=new FenwickTree(m);
        for(int i=n-1; i>=0;--i){
            tree.add(map.get(total),1);
            total-=(long)nums[i];
            int a=tree.sum(map.get((long)upper+total));
            int b=tree.sum(map.get((long)total+lower-1));
            ans+=(a-b);
            //为啥不放在循环里的第一句，是有原因的，求的一直都是sums[i-1]
        }
        return ans;
    }

    //328 odd even linked list
    //tag
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode odd=new ListNode(0);
        ListNode even=new ListNode(0);
        ListNode podd=odd;
        ListNode peven=even;
        int cnt=0;
        while(head!=null){
            if((cnt&0x1)!=0){
                peven.next=head;
                peven=peven.next;
            }
            else{
                podd.next=head;
                podd=podd.next;
            }
            head=head.next;
            cnt++;
        }

        //还是要注意清空的问题
        peven.next=null;
        podd.next=even.next;
        return odd.next;
    }

    //o(1) 其实得把其他的linkedlist的题一块复习了，好像是有技巧的
    //原来这个就叫做o1 space
    public ListNode oddEvenListSaveSpace(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode odd=head;
        ListNode even=head.next;
        ListNode saveNode=even;
        while(even!=null && even.next!=null){
            odd.next=even.next;
            even.next=even.next.next;
            odd=odd.next;
            even=even.next;
        }
        odd.next=saveNode;
        return dummy.next;
    }

    //329 longest increasing path in a matrix
    //tag ,主要是利用另外一个矩阵来memory，这样就省得包里搜索了。我暴力搜的话，会tle，想想也是，
    //养成一个好习惯，能memory based就memory based，这样就省得tle
    int dfs(int[][]matrix,int[]res,int x,int y,int []dx,int []dy,int[][]dist){
        if(dist[x][y]!=0)//典型的memory based
            return dist[x][y];
        dist[x][y]=1;//最小值都是1，不然会过不了
        for(int k=0;k<4;++k){
            int xx=x+dx[k];
            int yy=y+dy[k];
            if(xx<0||xx>=matrix.length||yy<0||yy>=matrix[0].length)
                continue;
            if(matrix[xx][yy]>matrix[x][y]){
                dist[x][y]=Math.max(dist[x][y],1+dfs(matrix,res,xx,yy,dx,dy,dist));
            }
        }
        res[0]=Math.max(res[0],dist[x][y]);
        return dist[x][y];

    }
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return 0;
        int m=matrix.length,n=matrix[0].length;
        int []res=new int[1];
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        res[0]=1;//min value;
        int [][]dist=new int[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                dfs(matrix,res,i,j,dx,dy,dist);
            }
        }
        return res[0];
    }

    //333 largest bst subtree
    //错误的代码，因为你并没有把该有的值的大小关系传上来，应该用结构体来传的。
//    int ans=0;
//    public int isBSTTree(TreeNode root){
//        if(root==null)
//            return 0;
//        if(root.left==null && root.right==null)
//            return 1;
//        int l=isBSTTree(root.left);
//        int r=isBSTTree(root.right);
//        if(l!=-1 && r!=-1){
//            if(root.left!=null && root.right!=null){
//                if(root.val>root.left.val && root.val<root.right.val){
//                    ans=Math.max(ans,l+r+1);
//                    return 1+l+r;
//                }else
//                    return -1;
//            }else if(root.left!=null && root.right==null && root.val>root.left.val){
//                ans=Math.max(ans,l+1);
//                return 1+l;
//            }else if(root.right!=null && root.left==null && root.val<root.right.val){
//                ans=Math.max(ans,r+1);
//                return 1+r;
//            }else
//                return -1;
//        }
//        return -1;
//    }
    class info{
        int minvalue;
        int maxvalue;
        int size;
        boolean isBST;
        public info(){
            minvalue=Integer.MAX_VALUE;
            maxvalue=Integer.MIN_VALUE;
            isBST=true;
            size=0;
        }

    }

    public info dfs(TreeNode root){
        info res=new info();
        if(root==null){
            return res;
        }
        if(root.left==null && root.right==null){
            res.isBST=true;
            res.size=1;
            res.maxvalue=root.val;
            res.minvalue=root.val;
            return res;
        }
        info l=dfs(root.left);
        info r=dfs(root.right);
        if(!l.isBST ||!r.isBST||l.maxvalue>=root.val||r.minvalue<=root.val){
            res.isBST=false;
            res.size=Math.max(l.size,r.size);// 要想root是最大，必须要哦更新
            return res;
        }
        res.isBST=true;
        res.size=l.size+r.size+1;
        res.minvalue=root.left!=null?l.minvalue:root.val;
        res.maxvalue=root.right!=null?r.maxvalue:root.val;//手误，忘了写
        return res;

    }
    public int largestBSTSubtree(TreeNode root) {
        return dfs(root).size;
    }

    //334 increasing triplet subsequence
    public boolean increasingTriplet(int[] nums) {
        int c1=Integer.MAX_VALUE,c2=Integer.MAX_VALUE;
        for(int x:nums){
            if(x<=c1)//如果少了等于号，那么22222，这样的例子也会过
                c1=x;
            else if(x<=c2)
                c2=x;
            else
                return true;
        }
        return false;
    }






}
