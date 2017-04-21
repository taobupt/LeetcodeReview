package _04_13;

import common.ListNode;
import common.TreeNode;
import common.Tuple;

import java.util.*;

/**
 * Created by tao on 4/20/17.
 */
public class ThreeHundredSixtysixToSeventytyFive {


    //366 find leaves of binary tree
    //俩次dfs就可以了

    public TreeNode dfs1(TreeNode root, List<Integer>path){
        if(root==null)
            return null;
        if(root.left==null && root.right==null){
            path.add(root.val);
            return null;
        }
        root.left=dfs1(root.left,path);//这里写成了root=，妈蛋，差点就完了。sb了，java不会引用修改的
        root.right=dfs1(root.right,path);
        return root;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        while(root!=null){
            root=dfs1(root,path);
            res.add(new ArrayList<>(path));
            path.clear();
        }
        return res;
    }

    //其实是按高度来的，你可以尝试用高度来解决。挺好的，晚上想想
    //叶子节点都是0，然后一层一层递归上去就可以了。

    //bottom to up
    //居然也是过了，真是奇怪了，难道下意思也能写对？看来还是有一点作用的
    public int height(TreeNode root,List<List<Integer>>res){
        if(root==null)
            return -1;
        int l=height(root.left,res);
        int r=height(root.right,res);
        int h=Math.max(l,r)+1;
        if(res.size()<=h)
            res.add(new ArrayList<>());
        res.get(h).add(root.val);
        return h;
    }
    public List<List<Integer>> findLeavesByHeight(TreeNode root) {
        List<List<Integer>>res=new ArrayList<>();
        height(root,res);
        return res;
    }

    //367 valid prefect square
    //牛顿法求，或者binary search求
    public boolean isPerfectSquare(int num) {
        if(num<0)
            return false;
        int begin=0,end=num;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(mid>=46341){
                end=mid;
                continue;
            }
            int product1=mid*mid;
            if(product1==num)
                return true;
            else if(product1<num)
                begin=mid+1;
            else
                end=mid;
        }
        return begin*begin==num;
    }

    //可能还会有一些精度的问题
    public boolean isPerfectSquareNetwon(int num){
        if(num<0)
            return false;
        double first=num*1.0;
        while(Math.abs(first*first-num)>1e-10){
            first=first-(first-num/first)/2;
        }
        int res=(int)first;
        return res*res==num;
    }

    //368 largest divisible subset
    //得想一想了,能做出来，我已经很欣慰了
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //先算出个数出来？
        Arrays.sort(nums);
        int n=nums.length;
        int lastIndex=0;
        int []dp=new int[n+1];
        int ans=0;
        int[]parent=new int[n+1];
        Arrays.fill(parent,-2);
        for(int i=1;i<=n;++i){
            dp[i]=1;
            for(int j=1;j<i;++j){
                if(nums[i-1]%nums[j-1]==0){
                    if(dp[j]+1>dp[i]){
                        dp[i]=dp[j]+1;
                        parent[i]=j;
                    }
                }
            }
            if(ans<dp[i]){
                lastIndex=i;
                ans=dp[i];
            }
        }
        List<Integer>res=new ArrayList<>();
        if(ans!=0){
            while(lastIndex!=-2){
                res.add(nums[lastIndex-1]);
                lastIndex=parent[lastIndex];
            }
            Collections.reverse(res);
        }
        return res;
    }



    //369. Plus One Linked List
    //sb 的做法
    //先reverse 然后再解决
    //最佳的做法是俩指针，然后解决
    public ListNode plusOne(ListNode head) {
        Stack<Integer>stk=new Stack<>();
        Stack<Integer>stk1=new Stack<>();
        ListNode p=head;
        int cnt=0;
        while(p!=null){
            stk.push(p.val);
            p=p.next;
            cnt++;
        }
        boolean hasCarry=true;
        while(!stk.isEmpty()){
            int val=stk.pop();
            if(hasCarry &&val==9){
                stk1.push(0);
            }else if(hasCarry){
                stk1.push(val+1);
                hasCarry=false;
            }else
                stk1.push(val);
        }

        if(cnt!=stk1.size()){
            hasCarry=true;
            stk1.pop();
        }

        ListNode dummy=new ListNode(1);
        dummy.next=head;
        p=head;
        while(p!=null){
            p.val=stk1.pop();
            p=p.next;
        }
        return hasCarry?dummy:dummy.next;
    }

    public ListNode plusOneConcise(ListNode head){
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode i=dummy;
        ListNode j=dummy;
        while(j.next!=null){
            if(j.val!=9){
                i=j;
            }
            j=j.next;
        }

        if(j.val!=9){
            j.val++;
            return dummy.next;
        }else{
            i.val++;
            i=i.next;
            while(i!=null){
                i.val=0;
                i=i.next;
            }
        }
        return dummy.val==1?dummy:dummy.next;
    }


    //370 range addition
    //很有技巧的,暴力会浪费太多时间
    public int[] getModifiedArray(int length, int[][] updates) {
        int []res=new int[length];
        Arrays.fill(res,0);
        for(int[]update:updates){
            res[update[0]]+=update[2];
            if(update[1]<length-1)
                res[update[1]+1]-=update[2];
        }
        for(int i=1;i<length;++i)
            res[i]+=res[i-1];
        return res;
    }

    //371 add two number
    //挺好玩的
    public int getSum(int a, int b) {
        if(a==0||b==0)
            return a==0?b:a;
        return getSum((a&b)<<1,a^b);
    }

    //372 super pow (a*b)%c=(a%c)*(b%c)%c;


    public int fastpow(int a,int b){
        int res=1;
        while(b!=0){
            if((b&0x1)!=0)
                res=((res%1337)*(a%1337))%1337;
            a=((a%1337)*(a%1337))%1337;
            b>>=1;
        }
        return res;
    }

    public int superPow(int a,int[]b,int end){
        if(end==0){
            return fastpow(a,b[0]);
        }
        return (fastpow(superPow(a,b,end-1),10)*fastpow(a,b[end]))%1337;
    }
    public int superPow(int a, int[] b) {
        int n=b.length;
        if(n==0)
            return 1;
        return superPow(a,b,n-1);
    }

    //373 find k pairs with smallest sums
    //暴力，就没用到sort属性了
    //耻辱
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]>pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0]+o2[1],o1[0]+o1[1]);
            }
        });
        int m= nums1.length,n=nums2.length;
        if(k>m*n)
            k=m*n;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(pq.size()<k)
                    pq.offer(new int[]{nums1[i],nums2[j]});
                else{
                    if(nums1[i]+nums2[j]<pq.peek()[0]+pq.peek()[1]){
                        pq.poll();
                        pq.offer(new int[]{nums1[i],nums2[j]});
                    }
                }
            }
        }
        List<int[]>res=new ArrayList<>();
        while(res.size()<k){
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }

    //concise
    public List<int[]> kSmallestPairsSaveTime(int[] nums1, int[] nums2, int k){
        PriorityQueue<Tuple<Integer,Integer>>pq=new PriorityQueue<>(new Comparator<Tuple<Integer, Integer>>() {
            @Override
            public int compare(Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) {
                return Integer.compare(nums1[o1.x]+nums2[o1.y],nums1[o2.x]+nums2[o2.y]);
            }
        });
        int m=nums1.length,n=nums2.length;
        List<int[]>res=new ArrayList<>();
        if(m==0||n==0)
            return res;
        for(int i=0;i<n;++i)
            pq.add(new Tuple<>(0,i));
        while(res.size()<m*n && res.size()<k){
            Tuple tuple=pq.poll();
            int xx=(Integer) tuple.x;
            int yy=(Integer)tuple.y;
            res.add(new int[]{nums1[xx],nums2[yy]});
            if(xx<m-1)
                pq.add(new Tuple<>(xx+1,yy));
        }
        return res;
    }

    //374 太简单了

    //375 guess number higher or lower II 博弈论
    public int getMoneyAmount(int n){
        return getMoneyAmount(1,n,new int[n+1][n+1]);
    }
    public int getMoneyAmount(int start,int end,int[][]dp){
        if(start>=end)
            return 0;
        if(dp[start][end]!=0)//start==end, 必猜中啊
            return dp[start][end];
        int value=Integer.MAX_VALUE;
        for(int i=start;i<=end;++i){
            value=Math.min(value,i+Math.max(getMoneyAmount(start,i-1,dp),getMoneyAmount(i+1,end,dp)));
        }
        dp[start][end]=value;
        return value;
    }
}
