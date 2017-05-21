package _04_15;

import java.util.*;

/**
 * Created by tao on 5/14/17.
 */
public class ThreeHundredEightysixToNinetyFive {


    //386  Lexicographical Numbers
    //普通的回溯

    public void dfs(List<Integer>res,int n,int sum){
        if(sum>n)
            return;
        res.add(sum);
        for(int i=0;i<10;++i){
            if(10*sum+i<=n)//这里为啥不把sum=10*sum+i，如果这样写了，那么就要after call就要调回来，不然就废了。所以一般不对sum做任何变化。
                dfs(res,n,10*sum+i);
            else
                break;
        }
    }
    public List<Integer> lexicalOrder(int n) {
        List<Integer>res=new ArrayList<>();
        for(int i=1;i<=9;++i){
            if(i<=n){
                dfs(res,n,i);
            }
        }
        return res;
    }


    //387 First Unique Character in a String
    //没啥好说的，直接是最优
    public int firstUniqChar(String s) {
        char []ss=s.toCharArray();
        int []cnt=new int[26];
        for(char c:ss){
            cnt[c-'a']++;
        }
        for(int i=0;i<26;++i) {
            if (cnt[i] == 1)
                return i;
        }
        return -1;
    }


    //388 google oa
   //Longest Absolute File Path


    //为什么不能用dfs呢？常用的dfs是可以第一个，第三个叠在一起的，但是这个不是，必须紧紧挨着，
    //操蛋的，不一定是从\n\t开始的。卧槽！！！！！
    public int lengthLongestPath(String input) {
//        String subs[]=input.split("\n");
//        int []res=new int[1];
//        dfs(subs,0,res,0,0);
//        return res[0];
        String []subs=input.split("\n");
        Stack<Integer>stk=new Stack<>();
        stk.push(0);
        int n=subs.length,maxLen=0;
        for(int i=0;i<n;++i){
            int lev = subs[i].lastIndexOf("\t")+1;
            while(!stk.isEmpty() && lev+1<stk.size()) //find parent
                stk.pop();
            int len = subs[i].length()+1+stk.peek()-lev;//remove \t add \
            stk.push(len);
            if(subs[i].contains("."))
                maxLen=Math.max(maxLen,len-1);
        }
        return maxLen;
    }



    //389 find the difference
    public char findTheDifference(String s, String t) {
        int []cnt=new int[26];
        char []ss=s.toCharArray();
        char []tt=t.toCharArray();
        int n=t.length();
        for(int i=0;i<n;++i){
            cnt[tt[i]-'a']++;
            if(i<n-1)
                cnt[ss[i]-'a']--;
        }
        for(int i=0;i<26;++i){
            if(cnt[i]==1){
                return (char)(i+'a');
            }
        }
        return ' ';
    }

    //bitwise way, 我咋就没想到呢，和数组那道很像的
    public char findTheDifferenceBitwise(String s, String t) {
        int n = t.length();
        char c = t.charAt(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }

    //390 Elimination Game
    //感觉好像约瑟夫问题
    //应该可以用递归来解
    int reverseOrder(int n){
        if(n==1||n==2)
            return 1;
        if((n&0x1)==0)
            return 2*(order(n/2)-1);
        else
            return 2*(order(n/2));

    }

    int order(int n){
        //返回的是最终的值
        if(n==1||n==2)
            return n;
        if((n&0x1)==0)
            return 2*reverseOrder(n/2);
        else
            return 2*(reverseOrder((n+1)/2)-1);

    }
    //双链表估计也可以，但是开销会不会太大啊。
    public int lastRemaining(int n) {
        return order(n);
    }


    //392 is subsequence
    public boolean isSubsequence(String s, String t) {
        int m=t.length(),n=s.length();
        char []ss=s.toCharArray();
        char []tt=t.toCharArray();
        int j=0;
        if(n==0)
            return true;
        for(int i=0;i<m;++i){
            if(tt[i]==ss[j]){
                j++;
                if(j==n)
                    return true;
            }
        }
        return false;
    }

    //394 decode string, 扫描过去
    // 要么是单个字符，要么是我们要解码的
    //一招致命，看看其他的答案，发现可以融会贯通的， decodeString(s, i);
    //应该还是可以用stack来做的，就像那个parser一样
    public String decodeString(String s) {
        if(!s.contains("["))
            return s;
        int n=s.length();
        char []ss=s.toCharArray();
        StringBuilder sb =new StringBuilder();
        int i=0;
        while(i<n){
            while(i<n && Character.isLetter(ss[i])){
                sb.append(ss[i++]);
            }
            int sum=0;
            if(i<n && Character.isDigit(ss[i])){
                while(i<n && Character.isDigit(ss[i])){
                    sum=10*sum+(ss[i++]-'0');
                }
            }
            if(i<n && sum!=0 && ss[i]=='['){
                int start =++i;
                int end=0;
                int cnt=1;
                while(i<n){
                    if(ss[i]=='['){
                        cnt++;
                    }
                    else if(ss[i]==']'){
                        cnt--;
                        end=i;
                    }
                    i++;
                    if(cnt==0){
                        String res = decodeString(s.substring(start,end));
                        for(int jj=0;jj<sum;++jj){
                            sb.append(res);
                        }
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }


    //395 Longest Substring with At Least K Repeating Characters
    //two window
    //居然不会，所想到的也就是枚举所有的字串，然后进行统计，最多是加加速啥的。
    //二维dp居然作出来了但是，memory limit,还好用了优化。但是只打败了27%的人，应该还有on的解法
    public int longestSubstring(String s, int k) {
        char []ss=s.toCharArray();
        int n=s.length();
        int maxRes=0;
        //int [][]dp=new int[n][n];
        for(int i=0;i<=n-k;++i){
            int []cnt=new int[26];
            int pre=0;
            for(int j=i;j<n;++j){
                int cur=j-i+1;
                cnt[ss[j]-'a']++;
                if(cnt[ss[j]-'a']==k){
                    cur=(j==0?0:pre-1);
                    cur=Math.max(0,cur);
                }else{
                    if(cnt[ss[j]-'a']==1)
                        cur=(j==0?1:pre+1);
                    else
                        cur=(j==0?1:pre);
                }
                if(cur==0){
                    maxRes=Math.max(maxRes,j-i+1);
                }
                pre=cur;
            }
        }
        return maxRes;
    }
}
