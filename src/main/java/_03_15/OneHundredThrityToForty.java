package _03_15;

import common.RandomListNode;
import common.UndirectedGraphNode;

import java.util.*;

/**
 * Created by tao on 3/14/17.
 */
public class OneHundredThrityToForty {


    //131
    //普通的back，既可以自己判断是palindrome，也可以dp先判断好

    public boolean isPalindrome(String ss){
        int begin=0,end=ss.length()-1;
        while(begin<end){
            if(ss.charAt(begin++)!=ss.charAt(end--))
                return false;
        }
        return true;
    }
    public void backtrack(List<List<String>>res,List<String>path,String s,int pos){
        if(pos==s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=pos+1;i<=s.length();++i){
            String sub=s.substring(pos,i);
            if(isPalindrome(sub)){
                path.add(sub);
                backtrack(res,path,s,i);
                path.remove(path.size()-1);//这里千万要注意，是删除index，不是object，否则会出现大问题的
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>>res=new ArrayList<>();
        backtrack(res,new ArrayList<>(),s,0);
        return res;
    }


    public void backtrack(List<List<String>>res,List<String>path,String s,int pos,boolean[][]dp){
        if(pos==s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=pos;i<s.length();++i){
            if(dp[pos][i]){
                path.add(s.substring(pos,i+1));
                backtrack(res,path,s,i+1,dp);
                path.remove(path.size()-1);//这里千万要注意，是删除index，不是object，否则会出现大问题的
            }
        }
    }

    boolean [][]getDP(String s){
        int m=s.length();
        boolean [][]dp=new boolean[m][m];
        for(int i=0;i<m;++i){
            for(int j=0;j<=i;++j){
                if(s.charAt(i)==s.charAt(j)){
                    if(i-j<=2 || dp[j+1][i-1])
                        dp[j][i]=true;
                }
            }
        }
        return dp;
    }

    //用dp提前打表
    public List<List<String>> partitionDP(String s) {
        List<List<String>>res=new ArrayList<>();
        int m=s.length();
        boolean [][]dp=getDP(s);
        backtrack(res,new ArrayList<>(),s,0,dp);
        return res;
    }

    //iterative way
    //也就是dp打表，顺比把递归弄成iterative
    public List<List<String>>partitionDPIterative(String s){
        int n=s.length();
        List<List<String>>res[]=new List[n+1];
        res[0]=new ArrayList<>();
        res[0].add(new ArrayList<>());
        boolean [][]pair=new boolean[n][n];
        for(int i=0;i<n;++i){
            res[i+1]=new ArrayList<>();
            for(int j=0;j<=i;++j){
                if(s.charAt(i)==s.charAt(j)&&(i-j<=2||pair[j+1][i-1])){
                    pair[j][i]=true;
                    String sub=s.substring(j,i+1);
                    for(List<String>r:res[j]){
                        List<String>ri=new ArrayList<>(r);
                        ri.add(sub);
                        res[i+1].add(ri);
                    }
                }
            }
        }
        return res[n];
    }

    //还有一种挺有意思的
//    public:
//    vector<vector<string>> partition(string s) {
//        vector<vector<string>> res;
//        vector<string> tmp;
//        getPartition(s, 0, tmp, res);
//        return res;
//    }
//    private:
//    void getPartition(string& s, int idx, vector<string>& tmp, vector<vector<string>>& res) {
//        if (idx == s.length()) {
//            res.push_back(tmp);
//            return;
//        }
//        for (int i = idx, n = s.length(); i < n; i++) {
//            int l = idx, r = i;
//            while (l < r && s[l] == s[r]) l++, r--;// 为啥不是l<=r呢，如果是这样的话，那你就无法得到单个字符是回文串了，实在是精妙绝伦
//            if (l >= r) {
//                tmp.push_back(s.substr(idx, i - idx + 1));
//                getPartition(s, i + 1, tmp, res);
//                tmp.pop_back();
//            }
//        }
//    }

    //为避免重复计算，其实我们是可以用hashmap的

    public List<List<String>>dfs(String s,Map<String,List<List<String>>>map,int pos,boolean[][]dp){

        if(map.containsKey(s.substring(pos)))
            return map.get(s.substring(pos));
        List<List<String>>res=new ArrayList<>();
        if(pos==s.length()){
            List<String>tmp=new ArrayList<>();
            tmp.add("");
            res.add(tmp);
            return res;
        }
        for(int i=pos,n=s.length();i<n;++i){

            if(dp[pos][i]){
                String sub=s.substring(pos,i+1);
                List<List<String>>ans=dfs(s,map,i+1,dp);
                for(List<String>li:ans){
                    List<String>rr=new ArrayList<>(li);
                    rr.add(sub);
                    res.add(rr);
                }
            }
        }
        map.put(s,res);
        return res;
    }
    public List<List<String>>partitionHASHMAP(String s){
        Map<String,List<List<String>>>map=new HashMap<>();
        int m=s.length();
        boolean [][]dp=getDP(s);
        return dfs(s,map,0,dp);
    }


    //132 palindrome partition II
    public int minCut(String s) {
        int n=s.length();
        char []ss=s.toCharArray();
        int []cut=new int[n+1];
        boolean [][]dp=new boolean[n][n];
        for(int i=0;i<n;++i){
            cut[i+1]=i;
            for(int j=0;j<=i;++j){
                if(ss[i]==ss[j] &&((i-j<=2)||dp[j+1][i-1])){
                    dp[j][i]=true;
                    if(j==0)
                        cut[i+1]=0;
                    else
                        cut[i+1]=Math.min(cut[i+1],cut[j]+1);
                }
            }
        }
        return cut[n];
    }
    //还有一种是从后面开始整的
    //save space
    public int minCutSaveSpace(String s){
        int n=s.length();
        char []ss=s.toCharArray();
        int []cnt=new int[n+1];
        for(int i=0;i<n;++i)
            cnt[i+1]=i;
        for(int i=0;i<n;++i){
            //odd length
            for(int j=0;i-j>=0 && i+j<n && ss[i+j]==ss[i-j];++j){
                if(i==j)
                    cnt[i+j+1]=0;
                else
                    cnt[i+j+1]=Math.min(cnt[i+j+1],cnt[i-j]+1);
            }

            //even length
            for(int j=1;i-j+1>=0 && i+j<n && ss[i-j+1]==ss[i+j];++j){
                if(i-j+1==0)
                    cnt[i+j+1]=0;
                else
                    cnt[i+j+1]=Math.min(cnt[i+j+1],cnt[i-j+1]+1);
            }
        }
        return cnt[n];
    }


    //133 clone graph
    //recursive way and iterative way
    //思路非常清晰
    Map<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null)
            return null;
        if(!map.containsKey(node)){
            map.put(node,new UndirectedGraphNode(node.label));
            for(UndirectedGraphNode nd:node.neighbors){
                map.get(node).neighbors.add(cloneGraph(nd));
            }
        }
        return map.get(node);
    }

    //iterative way
    public UndirectedGraphNode cloneGraphIterative(UndirectedGraphNode node){
        //先遍历一次图，建立起所有的关联，然后再copy
        if(node==null)
            return null;
       //bfs visit all the node
        Queue<UndirectedGraphNode>q=new LinkedList<>();
        Map<UndirectedGraphNode,Boolean>vis=new HashMap<>();
        q.offer(node);
        while(!q.isEmpty()){
            UndirectedGraphNode top=q.poll();
            if(!vis.containsKey(top)){
                vis.put(top,true);
                map.put(top,new UndirectedGraphNode(top.label));
                for(UndirectedGraphNode no:top.neighbors){
                    q.offer(no);
                }
            }
        }
        //start to copy
        vis.clear();
        q.offer(node);
        while(!q.isEmpty()){
            UndirectedGraphNode node1=q.poll();
            if(!vis.containsKey(node1)){
                vis.put(node1,true);
                for(UndirectedGraphNode no2:node1.neighbors){
                    map.get(node1).neighbors.add(map.get(no2));
                    q.offer(no2);
                }
            }
        }
        return map.get(node);
    }

    //可以试试另外一种copy iterative
    //比我写的好多了，sigh
    public UndirectedGraphNode cloneGraphIterativeII(UndirectedGraphNode node){
        if(node==null)
            return null;
        map.put(node,new UndirectedGraphNode(node.label));
        Queue<UndirectedGraphNode>q=new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            UndirectedGraphNode top=q.poll();
            for(UndirectedGraphNode no:top.neighbors){
                if(!map.containsKey(no)){
                    map.put(no,new UndirectedGraphNode(no.label));
                    q.offer(no);
                }
                map.get(top).neighbors.add(map.get(no));
            }
        }
        return map.get(node);
    }

    //134 gas station
    //tag
    //和求maximum subarray sum  差不多
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        int ind=0,res=0,remaining=0;
        for(int i=0;i<n;++i){
            res+=gas[i]-cost[i];
            remaining+=gas[i]-cost[i];
            if(remaining<0){
                remaining=0;
                ind=i+1;
            }
        }
        return res>=0?ind:-1;
    }

    //another way

    //核心是想就是，如果sum大于0，一直向前拓展，小于0就start就像前拓展，
    public int canCompleteCircuitAno(int[] gas, int[] cost) {
        int n=gas.length;
        int start=n-1,end=0,sum=gas[start] - cost[start];
        while(start>end){
            if(sum>=0){
                sum+=gas[end]-cost[end];
                end++;
            }else{
                --start;
                sum+=gas[start]-cost[start];
            }
        }
        return sum>=0?start:-1;
    }

    //135 candy
    //tag
    //往返两次求最值
    //sb 都能想到，但是如何优化空间复杂度呢，这是个问题。感觉吧，优化到o(n)实在是有点变态。不说了，题目咋就这么难呢
    public int candy(int[] ratings) {
        int n=ratings.length;
        if(n==0)
            return 0;
        int []dp=new int[n];
        dp[0]=1;
        for(int i=1;i<n;++i){
            if(ratings[i]>ratings[i-1])
                dp[i]=dp[i-1]+1;
            else
                dp[i]=1;
        }
        int ans=dp[n-1];

        for(int i=n-2;i>=0;--i){
            if(ratings[i]>ratings[i+1] && dp[i]<dp[i+1]+1)
                dp[i]=dp[i+1]+1;
            ans+=dp[i];
        }
        return ans;

    }
    //136 single number
    //用bitmap， hashmap，set+ 求和
    public int singleNumber(int[] nums) {
        int ans=0;
        for(int x:nums)
            ans^=x;
        return ans;
    }

    //137 single number II
    //bit map
    //当然hashmap也是可以搞的，但是没任何意义
    public int singleNumberII(int[] nums) {
        int res=0,n=nums.length;
        int []bits=new int[32];
        for(int j=0;j<32;++j){
            for(int i=0;i<n;++i){
                bits[j]+=((nums[i]>>j)&0x1);
            }
            res+=(bits[j]%3)<<j;
        }
        return res;
    }

    //138 copy with random list pointer
    //recursive way
    Map<RandomListNode,RandomListNode>mp=new HashMap<>();
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null)
            return null;
        if(!mp.containsKey(head)){
            mp.put(head,new RandomListNode(head.label));
            mp.get(head).random=copyRandomList(head.random);
            mp.get(head).next=copyRandomList(head.next);
        }
        return mp.get(head);
    }

    //should be iterative way
    public RandomListNode copyRandomListIterative(RandomListNode head){
        if(head==null)
            return null;
        RandomListNode p=head;
        Map<RandomListNode,RandomListNode>map1=new HashMap<>();

        //这种是错误的思路
//        while(p!=null){
//            map1.put(p,new RandomListNode(p.label));
//            map1.get(p).next=p.next==null?null:new RandomListNode(p.next.label);//画画图就知道了，有很多值一样的节点，冗余了
//            map1.get(p).random=p.random==null?null:new RandomListNode(p.random.label);
//            p=p.next;
//        }
//        return map1.get(head);
        //这种是行不通的，p.random,也需要很久才会被map收进去，所以，最保险的是遍历完一边后，然后再去copy。

        while(p!=null){
            map1.put(p,new RandomListNode(p.label));
            p=p.next;
        }
        p=head;
        while(p!=null){
            map1.get(p).next=map1.get(p.next);//这里为啥是map.get(p.next)有点扯淡啊。事实上没有必要生成first，tail
            map1.get(p).random=map1.get(p.random);
            p=p.next;
        }
        return map1.get(head);

    }

    //save more space
    public RandomListNode copyRandomListIterativeII(RandomListNode head){
        if(head==null)
            return null;
        RandomListNode p=head;
        Map<RandomListNode,RandomListNode>map1=new HashMap<>();
        while(p!=null){
            if(!map1.containsKey(p))
                map1.put(p,new RandomListNode(p.label));
            if(p.next!=null){
                if(!map1.containsKey(p.next))
                    map1.put(p.next,new RandomListNode(p.next.label));
                map1.get(p).next=map1.get(p.next);
            }
            if(p.random!=null){
                if(!map1.containsKey(p.random))
                    map1.put(p.random,new RandomListNode(p.random.label));
                map1.get(p).random=map1.get(p.random);
            }
            p=p.next;
        }
        return map1.get(head);
    }

    //139 word break
    //dp
    //应该还有其他的方式
    //tag bfs
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n=s.length();
        boolean []dp=new boolean[n+1];
        Set<String> set=new HashSet<>(wordDict);
        dp[0]=true;
        for(int i=1;i<=n;++i){
            for(int j=0;j<i;++j){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    //140 word break II
    //记错了。。。
    //暴力的话肯定得跪。之前的做法是取了一个substring后，就用第一问的dp来判断可不可以，若是可以就继续，否则就闪退。
    //还有一种就是用map来做memorization
    //先用自己的办法吧
    public void dfs(List<String>res,String s,List<String> sb,Set<String>set,int pos){
        if(pos==s.length()){
            res.add(String.join(" ",sb));
            return;
        }
        for(int i=pos+1;i<=s.length();++i){
            String sub=s.substring(pos,i);
            if(set.contains(sub) && wordBreak(s.substring(i),set)){
                sb.add(sub);
                dfs(res,s,sb,set,i);
                sb.remove(sb.size()-1);
            }
        }
    }
    public List<String> wordBreakII(String s, List<String> wordDict) {
        List<String>res=new ArrayList<>();
        List<String> sb=new ArrayList<>();
        Set<String>set=new HashSet<>(wordDict);
        if(!wordBreak(s,set))
            return res;
        dfs(res,s,sb,set,0);
        return res;
    }


}
