package _03_22;

import common.ListNode;
import common.TreeNode;
import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 3/22/17.
 */
public class OneHundredNinetyOneToZero {

    //191 number of 1bits
    public int hammingWeight(int n) {
        //return Integer.bitCount(n); 作弊
        int bit=0;
        while(n!=0){
            bit++;
            n&=(n-1);
        }
        return bit;
    }

    //198 house robber
    //dp
    public int rob(int[] nums) {
        int n=nums.length;
        int []dp=new int[n+1];
        for(int i=1;i<=n;++i){
            dp[i]=Math.max((i<2?0:dp[i-2])+nums[i-1],dp[i-1]);
        }
        return dp[n];
    }

    //199 typical questions
    //recursive way
    public void rightSideView(List<Integer>res,TreeNode node,int level){
        if(node==null)
            return;
        if(res.size()<=level)
            res.add(node.val);
        rightSideView(res,node.right,level+1);//just change direction to get left view
        rightSideView(res,node.left,level+1);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>res=new ArrayList<>();
        rightSideView(res,root,0);
        return res;
    }

    //iteartiave way
    public List<Integer>rightSideViewIterative(TreeNode node){
        List<Integer>res=new ArrayList<>();
        if(node==null)
            return res;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            int size=q.size();
            while(size -- >0){
                TreeNode top=q.poll();
                if(size==0)
                    res.add(top.val);
                if(top.left!=null)
                    q.offer(top.left);
                if(top.right!=null)
                    q.offer(top.right);
            }
        }
        return res;
    }

    //200 number of islands
    //bfs dfs uf
    public void dfs(char[][]grid,int x,int y){
        if(x<0||x>=grid.length||y<0||y>=grid[0].length||grid[x][y]!='1')
            return;
        grid[x][y]='@';
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y-1);
        dfs(grid,x,y+1);
    }
    public int numIslands(char[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    ans++;
                }
            }
        }
        return ans;
    }

    //bfs
    public int numIsLands(char[][]grid){
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    ans++;

                    q.offer(new int[]{i,j});
                    while(!q.isEmpty()){
                        int []top=q.poll();
                        for(int k=0;k<4;++k){
                            int xx=top[0]+dx[k];
                            int yy=top[1]+dy[k];
                            if(xx<0||xx>=m||yy<0||yy>=n||grid[xx][yy]!='1')
                                continue;
                            grid[xx][yy]='@';
                            q.offer(new int[]{xx,yy});
                        }
                    }
                }
            }
        }
        return ans;
    }

    //union find
    public int numIslandsByUnionFind(char[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        UnionFind uf=new UnionFind(m*n);
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    ans++;
                    int index=i*n+j;
                    if(i>0 && grid[i-1][j]=='1'){
                        if(uf.mix(index,index-n))
                            ans--;
                    }
                    if(i<m-1 && grid[i+1][j]=='1'){
                        if(uf.mix(index,index+n))
                            ans--;
                    }
                    if(j>0 && grid[i][j-1]=='1'){
                        if(uf.mix(index,index-1))
                            ans--;
                    }
                    if(j<n-1 && grid[i][j+1]=='1'){
                        if(uf.mix(index,index+1))
                            ans--;
                    }
                }
            }
        }
        return ans;
    }

    //201 bitwise and number range
    //其实 n>=2*m的时候，我们就知道已经是0了
    //last bit of (odd number & even number) is 0.
    //when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    //Move m and n rigth a position
    //说白了就是求prefix
    public int rangeBitwiseAnd(int m, int n) {
        int bit=0;
        while(m!=n){
            m>>=1;
            n>>=1;
            bit++;
        }
        return m<<bit;
    }

    //还有一种方法，我们很熟悉，俩数不一样是吧，那必定是低位的1制造了这个差异。我们一步一步去掉这个1就可以了
    //但是是反悔了m，事实上证明我是错的。
    public int rangeBitwiseAndAnother(int m,int n){
        int bit=0;
        while(n>m){
            n&=(n-1);
        }
        return n;
    }

    //202 happy number
    //其实就是快慢指针的事情
    public int getSum(int n){
        char []ss=String.valueOf(n).toCharArray();
        int nn=ss.length;
        int res=0;
        for(int i=0;i<nn;++i){
            res+=(ss[i]-'0')*(ss[i]-'0');
        }
        return res;
    }
    //其实可以不用string的,事实上这种效率更高
    public int getSum1(int n){
        int res=0;
        while(n>0){
            int x=n%10;
            res+=x*x;
            n/=10;
        }
        return res;
    }
    public boolean isHappy(int n) {
        int slow=getSum(n);
        int fast=getSum(slow);
        while(slow!=fast){
            slow=getSum(slow);
            fast=getSum(getSum(fast));
        }
        return slow==1;
    }

    //203 removed linked list element
    //事实上这就废题
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode p=dummy;
        while(head!=null){
            if(head.val==val){
                p.next=head.next;
            }else
                p=p.next;
            head=head.next;
        }
        return dummy.next;
    }
    //recursive way
    public ListNode removeElementsRecursive(ListNode head,int val){
        if(head==null)
            return null;
        if(head.val==val)
            return removeElementsRecursive(head.next,val);
        head.next=removeElementsRecursive(head.next,val);
        return head;

        // head.next=removeElementsRecursive(head.next,val);
        //return head.val==val?head.next:head;
    }

    //204 count primes
    //O(N^1.5),居然过不了 1500000
    //
    public boolean isPrime(int n){
        int nn=(int)Math.sqrt(n);
        for(int i=2;i<=nn;++i){
            if(n%i==0)
                return false;
        }
        return true;
    }
    public int countPrimes(int n) {
        int count=0;
        for(int i=2;i<n;++i){
            if(isPrime(i))
                count++;
        }
        return count;
    }

    //sieve
    public int countPrimesSieve(int n){
        boolean []notprime=new boolean[n];
        int count=0;
        for(int i=2;i<n;++i){//小于2，不用检查，acm有更屌的方式
            if(!notprime[i]){
                count++;
                for(int j=2;i*j<n;j++){
                    notprime[j*i]=true;
                }
            }
        }
        return count;
    }

    //205  isomorphic string,
    //比较蠢的方法是两个hashmap走
    public boolean isIsomorphic(String s, String t) {
        //hashmap
        int m=s.length(),n=t.length();
        if(m!=n)
            return false;
        Map<Character,Character> map=new HashMap<>();
        Map<Character,Character>map1=new HashMap<>();
        for(int i=0;i<m;++i){
            if(!map.containsKey(s.charAt(i)))
                map.put(s.charAt(i),t.charAt(i));
            if(!map1.containsKey(t.charAt(i)))
                map1.put(t.charAt(i),s.charAt(i));
            if(map.get(s.charAt(i))!=t.charAt(i)||map1.get(t.charAt(i))!=s.charAt(i))
                return false;
        }
        return true;
    }

    //是有简便方法的
    //test case
    //"ab"
    //"aa"
    //基本细想就是让俩个字符都指向一个一个唯一的数
    //和290 word pattern很像很像
    public boolean isIsomorphicConcise(String s,String t){
        if(s.length()!=t.length())
            return false;
        int n=s.length();
        int []m1=new int[128];
        int []m2=new int[128];
        int count=1;//不能从0开始，知道不，不然就gg了，从零开始的话，if里面也会得到零，岂不是和没赋值是一样的？卧槽
        for(int i=0;i<n;++i){
            if(m1[s.charAt(i)]!=m2[t.charAt(i)])
                return false;
            if(m1[s.charAt(i)]==0){
                m1[s.charAt(i)]=count;
                m2[t.charAt(i)]=count++;
            }
        }
        return true;
    }





}
