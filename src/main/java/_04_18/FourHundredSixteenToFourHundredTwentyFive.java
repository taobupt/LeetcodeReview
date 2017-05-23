package _04_18;

import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 5/22/17.
 */
public class FourHundredSixteenToFourHundredTwentyFive {


    //416 partition sum,勉强搞定，唉，太惨了。
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int x:nums)
            sum+=x;
        if(sum%2!=0)
            return false;
        boolean []dp=new boolean[sum/2+1];
        dp[0]=true;
        Map<Integer,Set<Integer>>map=new HashMap<>();
        Arrays.sort(nums);
        for(int i=0;i<=sum/2;++i){
            map.put(i,new HashSet<>());
            for(int j=0;j<n;++j){
                if(i>=nums[j] && !map.get(i-nums[j]).contains(j)){
                    dp[i]=dp[i-nums[j]];
                    map.get(i).addAll(map.get(i-nums[j]));
                    map.get(i).add(j);
                    if(dp[i])
                        break;
                }else if(i<nums[j]){
                    break;
                }
            }
        }
        return dp[sum/2];
    }
    //好好研究下背包问题。通常的优化也是需要掌握的。和那个硬币的问题，相差的只是一个能无限制使用，一个是只能使用一次。卧槽


    //417 Pacific Atlantic Water Flow
    //两条bfs取交集。其实都不需要hashset，直接生命两个vis 数组，然后取交集
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return new ArrayList<>();
        int m=matrix.length,n=matrix[0].length;
        boolean[][]vis=new boolean[m][n];
        Queue<int[]>pacific=new LinkedList<>();
        Queue<int[]>atlantic=new LinkedList<>();
        Set<ArrayList<Integer>> paci=new HashSet<>();
        Set<ArrayList<Integer>>atlan=new HashSet<>();
        for(int i=0;i<m;++i){
            pacific.add(new int[]{i,0});
            vis[i][0]=true;
        }
        for(int i=0;i<n;++i){
            pacific.add(new int[]{0,i});
            vis[0][i]=true;
        }
        while(!pacific.isEmpty()){
            int []top=pacific.poll();
            ArrayList<Integer>tmp=new ArrayList<>();
            tmp.add(top[0]);
            tmp.add(top[1]);
            paci.add(tmp);
            vis[top[0]][top[1]]=true;
            if(top[0]>=1 && !vis[top[0]-1][top[1]] && matrix[top[0]-1][top[1]]>=matrix[top[0]][top[1]])
                pacific.add(new int[]{top[0]-1,top[1]});
            if(top[0]<m-1 && !vis[top[0]+1][top[1]] && matrix[top[0]+1][top[1]]>=matrix[top[0]][top[1]])
                pacific.add(new int[]{top[0]+1,top[1]});
            if(top[1]>=1 && !vis[top[0]][top[1]-1] && matrix[top[0]][top[1]-1]>=matrix[top[0]][top[1]])
                pacific.add(new int[]{top[0],top[1]-1});
            if(top[1]<n-1 && !vis[top[0]][top[1]+1] && matrix[top[0]][top[1]+1]>=matrix[top[0]][top[1]])
                pacific.add(new int[]{top[0],top[1]+1});
        }
        vis=new boolean[m][n];
        for(int i=0;i<m;++i){
            atlantic.add(new int[]{i,n-1});
            vis[i][n-1]=true;
        }
        for(int i=0;i<n;++i){
            atlantic.add(new int[]{m-1,i});
            vis[m-1][i]=true;
        }
        while(!atlantic.isEmpty()){
            int []top=atlantic.poll();
            ArrayList<Integer>tmp=new ArrayList<>();
            tmp.add(top[0]);
            tmp.add(top[1]);
            atlan.add(tmp);
            vis[top[0]][top[1]]=true;
            if(top[0]>=1 && !vis[top[0]-1][top[1]] && matrix[top[0]-1][top[1]]>=matrix[top[0]][top[1]])
                atlantic.add(new int[]{top[0]-1,top[1]});
            if(top[0]<m-1 && !vis[top[0]+1][top[1]] && matrix[top[0]+1][top[1]]>=matrix[top[0]][top[1]])
                atlantic.add(new int[]{top[0]+1,top[1]});
            if(top[1]>=1 && !vis[top[0]][top[1]-1] && matrix[top[0]][top[1]-1]>=matrix[top[0]][top[1]])
                atlantic.add(new int[]{top[0],top[1]-1});
            if(top[1]<n-1 && !vis[top[0]][top[1]+1] && matrix[top[0]][top[1]+1]>=matrix[top[0]][top[1]])
                atlantic.add(new int[]{top[0],top[1]+1});
        }

        List<int[]>res=new ArrayList<>();
        if(atlan.size()>paci.size()){
            for(ArrayList<Integer> p:paci){
                if(atlan.contains(p))
                    res.add(new int[]{p.get(0),p.get(1)});
            }
        }else{
            for(ArrayList<Integer> p:atlan){
                if(paci.contains(p))
                    res.add(new int[]{p.get(0),p.get(1)});
            }

        }
        return res;
    }

    //419 battleships in a board;
    public int countBattleships(char[][] board) {
        if(board.length==0||board[0].length==0)
            return 0;
        int m=board.length,n=board[0].length;
        UnionFind uf =new UnionFind(m*n);
        //boolean [][]vis=new boolean[m][n];
        int cnt=0;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(board[i][j]=='X'){
                    int index=i*n+j;
                    cnt++;
                    // vis[i][j]=true;
                    if(i>0 && board[i-1][j]=='X' ){
                        if(uf.mix(index,index-n))
                            cnt--;
                    }
                    if(j>0 && board[i][j-1]=='X' ){
                        if(uf.mix(index,index-1))
                            cnt--;
                    }
                    if(i<m-1 && board[i+1][j]=='X'){
                        if(uf.mix(index,index+n))
                            cnt--;
                    }
                    if(j<n-1 && board[i][j+1]=='X' ){
                        if(uf.mix(index,index+1))
                            cnt--;
                    }
                }
            }
        }
        return cnt;
    }

    //the easy way was to count only the first cell(top left). You can do this by
    /*
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
     */


    //421 421. Maximum XOR of Two Numbers in an Array
    //brute force 都不好意思写出来了
    //tag,非常惊险。
    public int findMaximumXOR(int[] nums) {
        int n=nums.length;
        int maxVal=0,mask=0;
        //一般要拆成32bit来解决。
        for(int i=31;i>=0;--i){
            mask=mask|(1<<i);
            Set<Integer>set=new HashSet<>();
            for(int x:nums){
                set.add(x&mask);
            }

            int tmp=maxVal|(1<<i);//每步都去尝试可不可能，如果不可能那也没有啥好办法。
            for(int prefix:set){
                if(set.contains(tmp^prefix)){
                    maxVal=tmp;
                    break;
                }
            }
        }
        return maxVal;
    }

    //423 Reconstruct Original Digits from English
    public String originalDigits(String s) {
        int []cnt=new int[26];
        char []ss=s.toCharArray();
        int n=ss.length;
        for(int i=0;i<n;++i)
            cnt[ss[i]-'a']++;
        StringBuilder sb=new StringBuilder();
        int []res =new int[10];
        res[0]=cnt['z'-'a'];
        for(char c:"zero".toCharArray())
            cnt[c-'a']-=res[0];
        res[2]=cnt['w'-'a'];
        for(char c:"two".toCharArray())
            cnt[c-'a']-=res[2];
        res[4]=cnt['u'-'a'];
        for(char c:"four".toCharArray())
            cnt[c-'a']-=res[4];
        res[6]=cnt['x'-'a'];
        for(char c:"six".toCharArray())
            cnt[c-'a']-=res[6];
        res[8]=cnt['g'-'a'];
        for(char c:"eight".toCharArray())
            cnt[c-'a']-=res[8];

        res[1]=cnt['o'-'a'];
        for(char c:"one".toCharArray())
            cnt[c-'a']-=res[1];
        res[3]=cnt['r'-'a'];
        for(char c:"three".toCharArray())
            cnt[c-'a']-=res[3];
        res[5] =cnt['f'-'a'];
        for(char c:"five".toCharArray())
            cnt[c-'a']-=res[5];
        res[7]=cnt['s'-'a'];
        for(char c:"seven".toCharArray())
            cnt[c-'a']-=res[7];
        res[9]=cnt['i'-'a'];
        for(char c:"nine".toCharArray())
            cnt[c-'a']-=res[9];
        for(int i=0;i<10;++i){
            for(int j=0;j<res[i];++j)
                sb.append(i);
        }
        return sb.toString();
    }

    //424 Longest Repeating Character Replacement
    //失算啊，都不知到用window
    public int characterReplacement(String s, int k) {
        int []cnt=new int[26];
        int start=0,end=0,n=s.length(),maxCharCount=0,res=0;
        char []ss=s.toCharArray();
        while(end<n){
            maxCharCount=Math.max(maxCharCount,++cnt[ss[end++]-'A']);
            while(end-start-maxCharCount>k){//because end has already ++, so end is no longer what we thought
                --cnt[ss[start++]-'A'];
            }
            res=Math.max(res,end-start);
        }
        return res;
    }
}
