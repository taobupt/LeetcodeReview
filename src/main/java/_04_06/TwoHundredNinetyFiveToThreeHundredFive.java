package _04_06;

import common.TreeNode;
import common.Tuple;
import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 4/6/17.
 */
public class TwoHundredNinetyFiveToThreeHundredFive {


    //296 in mathQuestions
    //297 serialize and deserialize tree in design

    //298. Binary Tree Longest Consecutive Sequence

    //iterative way and recurisve way
    //自顶向下

    //勉强及格，算是写出来了
    public void dfs(TreeNode root,int val,int[]res,int cnt){
        if(root==null)
            return ;

        if(root.val==val)
            cnt++;
        else
            cnt=1;
        res[0]=Math.max(res[0],cnt);
        dfs(root.left,root.val+1,res,cnt);
        dfs(root.right,root.val+1,res,cnt);

    }
    public int longestConsecutive(TreeNode root) {

        //the first way
//        int []res=new int[1];//global maximum;
//        if(root==null)
//            return 0;
//        dfs(root,root.val,res,0);
//        return res[0];


        if(root==null)
            return 0;
        return dfs(root,root.val,0);
    }
    public int dfs(TreeNode root,int val,int cnt){
        if(root==null)
            return cnt;
        if(root.val==val)
            cnt++;
        else
            cnt=1;
        int m=Math.max(dfs(root.left,root.val+1,cnt),dfs(root.right,root.val+1,cnt));
        return Math.max(m,cnt);
    }

    //更深层次的体验 queue
    //iterative
    public int longestConsecutiveIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res=1;
        Queue<Tuple<TreeNode,Integer>> q=new LinkedList<Tuple<TreeNode, Integer>>();
        q.offer(new Tuple(root,1));
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=((Integer)t.y).intValue();
            res=Math.max(val,res);
            if(node.left!=null){
                int count=node.left.val==node.val+1?val+1:1;
                q.offer(new Tuple(node.left,count));
            }
            if(node.right!=null){
                int count=node.right.val==node.val+1?val+1:1;
                q.offer(new Tuple(node.right,count));
            }
        }
        return res;
    }


    //299 bulls and cows
    public String getHint(String secret, String guess) {
        int[]cnt=new int[10];
        char[]secrets=secret.toCharArray();
        char[]guesses=guess.toCharArray();
        Set<Integer> set=new HashSet<>();//store the bulls index;
        int n=secret.length();
        for(int i=0;i<n;++i){
            if(secrets[i]==guesses[i]){
                set.add(i);
            }else
                cnt[secrets[i]-'0']++;
        }

        int bulls=set.size();
        int cows=0;
        for(int i=0;i<n;++i){
            if(set.contains(i))
                continue;
            if(cnt[guesses[i]-'0']>0){
                cows++;
                cnt[guesses[i]-'0']--;
            }
        }
        return bulls+"A"+cows+"B";
    }

    //remove the set

    public String getHintRemoveSet(String secret, String guess) {
        int[]cnt1=new int[10];
        int[]cnt2=new int[10];
        char[]secrets=secret.toCharArray();
        char[]guesses=guess.toCharArray();
        int n=secret.length();
        int bulls=0;
        int cows=0;
        for(int i=0;i<n;++i){
            if(secrets[i]==guesses[i]){
                bulls++;
            }else{
                cnt1[secrets[i]-'0']++;
                cnt2[guesses[i]-'0']++;
            }
        }

        for(int i=0;i<10;++i){
            cows+=Math.min(cnt1[i],cnt2[i]);
        }
        return bulls+"A"+cows+"B";
    }




    //300 longest increasing sequence
    //dp n^2
    //最大值不一定在最后啊，跪在这里了，不 一定在最后
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int []dp=new int[n+1];
        int res=0;
        for(int i=1;i<=n;++i){
            dp[i]=1;
            for(int j=1;j<i;++j){
                if(nums[i-1]>nums[j-1])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    //onlog(n) 的解法,非常经典


    //301 remove invalid parenthesis
    //bfs 拓展，valid
    //如果不用set来标记的化，很容易tle，这告诉我们，进队列的话不能重复进，不然很容易暴增，所以要用vis来标记哪些访问过了，哪些没有访问过
    //tag
    public boolean validParenthese(String s){
        char []ss=s.toCharArray();
        int n=ss.length;
        int cnt=0;
        for(int i=0;i<n;++i){
            if(ss[i]=='(')
                cnt++;
            else if(ss[i]==')')
                cnt--;
            if(cnt<0)
                return false;
        }
        return cnt==0;
    }
    public List<String> removeInvalidParentheses(String s) {
        List<String>res=new ArrayList<>();
        Set<String>vis=new HashSet<>();
        if(validParenthese(s)){
            res.add(s);
            return res;
        }
        Queue<String>q=new LinkedList<>();
        q.offer(s);
        boolean hasNext=true;
        while(!q.isEmpty() &&hasNext){
            int size=q.size();
            for(int i=0;i<size;++i){
                StringBuilder sb=new StringBuilder(q.poll());
                for(int j=0;j<sb.length();++j){
                    if(sb.charAt(j)!='(' && sb.charAt(j)!=')')
                        continue;
                    String newstr=sb.substring(0,j)+sb.substring(j+1);
                    if(!vis.contains(newstr)&&validParenthese(newstr)){
                        hasNext=false;
                        vis.add(newstr);
                        res.add(newstr);
                        continue;
                    }
                    if(!vis.contains(newstr)){
                        q.offer(newstr);
                        vis.add(newstr);
                    }
                }
            }
        }
        return res;
    }

    //dfs way 需要好好研究一下



    //302. Smallest Rectangle Enclosing Black Pixels
    //binary search
    //写得紧凑倒是不容易啊，本来应该分开写的，今天效率不高，人有点迷糊 le

    public boolean containsOne(char[][]image,int index,boolean isCol){
        int m=image.length,n=image[0].length;
        if(isCol){
            for(int i=0;i<m;++i){
                if(image[i][index]=='1')
                    return true;
            }
        }else{
            for(int i=0;i<n;++i){
                if(image[index][i]=='1')
                    return true;
            }
        }
        return false;
    }

    public int binarySearch(char[][]image,int begin,int end,boolean isLower,boolean isCol){
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(isLower){
                if(containsOne(image,mid,isCol))
                    end=mid;
                else
                    begin=mid+1;
            }else{
                if(containsOne(image,mid,isCol))
                    begin=mid+1;
                else
                    end=mid;
            }
        }
        return containsOne(image,begin,isCol)?begin:begin-1;
    }

    public int minArea(char[][] image, int x, int y) {
        if(image.length==0||image[0].length==0)
            return 0;
        int m=image.length,n=image[0].length;
        int left=binarySearch(image,0,y,true,true);
        int right=binarySearch(image,y,n-1,false,true);
        int upper=binarySearch(image,0,x,true,false);
        int lower=binarySearch(image,x,m-1,false,false);
        return (right-left+1)*(lower-upper+1);
    }

    //303 range sum query
    //in design


    //304 range sum query2d-immutable
    //in design
    //-[row2][col1-1] [row1-1][col2]+[row1-1][col1-1]

    //305 number of islandsII
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer>res=new ArrayList<>();
        int k=positions.length;
        int[][]grid=new int[m][n];
        UnionFind uf=new UnionFind(m*n);
        int cnt=0;
        for(int i=0;i<k;++i){
            cnt++;
            int x=positions[i][0];
            int y=positions[i][1];
            grid[x][y]=1;
            int index=x*n+y;
            if(x>=1 && grid[x-1][y]==1){
                if(uf.mix(index,index-n))
                    cnt--;
            }
            if(y>=1 && grid[x][y-1]==1)
                if(uf.mix(index,index-1))
                    cnt--;
            if(x<m-1 && grid[x+1][y]==1)
                if(uf.mix(index,index+n))
                    cnt--;
            if(y<n-1 && grid[x][y+1]==1)
                if(uf.mix(index,index+1))
                    cnt--;
            res.add(cnt);
        }
        return res;
    }
}
