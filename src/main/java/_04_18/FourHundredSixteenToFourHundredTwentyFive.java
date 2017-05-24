package _04_18;

import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 5/22/17.
 */

class TrieNode {
    // Initialize your data structure here.
    boolean isEnd;
    TrieNode[]nodes=null;
    public TrieNode() {
        isEnd=false;
        nodes=new TrieNode[26];
    }
    public TrieNode getNodes(char c){
        return nodes!=null?nodes[c-'a']:null;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void addWord(String word) {
        int n=word.length();
        TrieNode cur=root;
        for(int i=0;i<n;++i){
            if(cur.getNodes(word.charAt(i))==null){
                cur.nodes[word.charAt(i)-'a']=new TrieNode();
            }
            cur=cur.nodes[word.charAt(i)-'a'];
        }
        cur.isEnd=true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        int n=word.length();
        TrieNode cur=root;
        for(int i=0;i<n;++i){
            if(cur.getNodes(word.charAt(i))==null)
                return false;
            else
                cur=cur.nodes[word.charAt(i)-'a'];
        }
        return cur.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.

    public boolean startsWith(String word) {
        int n=word.length();
        TrieNode cur=root;
        for(int i=0;i<n;++i){
            if(cur.getNodes(word.charAt(i))==null)
                return false;
            else
                cur=cur.nodes[word.charAt(i)-'a'];
        }
        return true;
    }
    public boolean startsWith(char c) {
        return startsWith(c+"");
    }
    public void dfs(List<String>res,TrieNode node,StringBuilder sb){
        if(node==null)
            return;
        if(node.isEnd){
            res.add(sb.toString());
            return;
        }
        for(char c='a';c<='z';++c){
            if(node.nodes[c-'a']!=null){
                sb.append(c);
                dfs(res,node.nodes[c-'a'],sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    public List<String>startWith(String prefix){
        int n=prefix.length();
        TrieNode cur=root;
        List<String>res=new ArrayList<>();
        for(int i=0;i<n;++i){
            cur=cur.nodes[prefix.charAt(i)-'a'];
        }
        StringBuilder sb=new StringBuilder();
        sb.append(prefix);
        dfs(res,cur,sb);
        return res;
    }

    public List<String>startWith(char c){
        return startWith(""+c);
    }
}
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
    public boolean canPartitionBetter(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int x:nums)
            sum+=x;
        if(sum%2!=0)
            return false;
        boolean []dp=new boolean[sum/2+1];
        dp[0]=true;
        for(int i=0;i<n;++i){
            for(int j=sum/2;j>=nums[i];--j){
                dp[j]|=dp[j-nums[i]];
            }
        }
        return dp[sum/2];
    }

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

    //422 Valid Word Square
    public boolean validWordSquare(List<String> words) {
        int m=words.size();
        for(int i=0;i<m;++i){
            if(words.get(i).length()>m)
                return false;
            for(int j=0;j<words.get(i).length();++j){
                //注意访问时的index问题，每次都犯傻，还是稳妥一点比较好。
                if(i>=words.get(j).length()||words.get(i).charAt(j)!=words.get(j).charAt(i))
                    return false;
            }
        }
        return true;
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

    //425 word squares;
    //观察出一些规律了，估计就是整成26组，就用trie吧。
    public void dosomething(Trie t,List<List<String>>res,String word){
        int m=word.length();
        if(m==2){
            List<String>tmp=t.startWith(word.charAt(1));
            for(String wo:tmp){
                List<String>squares=new ArrayList<>();
                squares.add(word);
                squares.add(wo);
                res.add(squares);
            }
            return;
        }else if(m==3){
            List<String>sec=t.startWith(word.charAt(1));
            for(String word1:sec){
                List<String>third=t.startWith(""+word.charAt(2)+word1.charAt(2));
                for(String word2:third){
                        List<String>tmp=new ArrayList<>();
                        tmp.add(word);
                        tmp.add(word1);
                        tmp.add(word2);
                        res.add(tmp);
                }
            }
        }else if(m==4){
            List<String>sec=t.startWith(word.charAt(1));


            for(String word1:sec){
                List<String>third=t.startWith(""+word.charAt(2)+word1.charAt(2));
                for(String word2:third){
                    List<String>four=t.startWith(""+word.charAt(3)+word1.charAt(3)+word2.charAt(3));
                        for(String word3:four){

                                List<String>tmp=new ArrayList<>();
                                tmp.add(word);
                                tmp.add(word1);
                                tmp.add(word2);
                                tmp.add(word3);
                                res.add(new ArrayList<>(tmp));
                        }
                    }
            }
        }else if(m==5){
            List<String>sec=t.startWith(word.charAt(1));

            for(String word1:sec){
                List<String>third=t.startWith(""+word.charAt(2)+word1.charAt(2));
                for(String word2:third){
                    List<String>four=t.startWith(""+word.charAt(3)+word1.charAt(3)+word2.charAt(3));
                        for(String word3:four){
                            List<String>five=t.startWith(""+word.charAt(4)+word1.charAt(4)+word2.charAt(4)+word3.charAt(4));
                                for(String word4:five){

                                        List<String>tmp=new ArrayList<>();
                                        tmp.add(word);
                                        tmp.add(word1);
                                        tmp.add(word2);
                                        tmp.add(word3);
                                        tmp.add(word4);
                                        res.add(tmp);

                                }

                        }

                }
            }
        }


    }

    //居然过了，卧槽，看来暴力也是分种类的，有的暴力就能过。卧槽～，一道题200行。真是
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>>res=new ArrayList<>();
        int n=words.length;
        int m=words[0].length();
        if(m==1){
            for(String word:words){
                List<String>tmp=new ArrayList<>();
                tmp.add(word);
                res.add(tmp);
            }
            return res;
        }
        Trie t=new Trie();
        for(String word:words)
            t.addWord(word);

        for(String word:words){
            char []arr=word.toCharArray();
            int i=0;
            for(i=1;i<m;++i){
                if(!t.startsWith(arr[i])){
                    break;
                }
            }
            if(i==m){
                dosomething(t, res,word);
            }
        }
        return res;
    }
}
