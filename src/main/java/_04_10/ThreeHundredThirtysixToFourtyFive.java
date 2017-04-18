package _04_10;

import common.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao on 4/16/17.
 */
public class ThreeHundredThirtysixToFourtyFive {



    //337 house robber III

    public int[]dfs(TreeNode root){
        int []res=new int[2];//res[0] contains the node, res[1] does not co
        if(root==null)
            return res;
        int []l=dfs(root.left);
        int []r=dfs(root.right);
        res[0]=root.val+l[1]+r[1];
        res[1]=Math.max(l[0],l[1])+Math.max(r[0],r[1]);
        return res;

    }
    public int rob(TreeNode root) {
        int []res=dfs(root);
        return Math.max(res[0],res[1]);
    }
    //338. Counting Bits
    public int[] countBits(int num) {
        int[]res=new int[num+1];
        for(int i=0;i<num;++i){
            int cnt=0;
            int number=i;
            while(number!=0){
                cnt++;
                number&=(number-1);
            }
            res[i]=cnt;
        }
        return res;
    }

    //concise way
    //感觉太厉害了
    public int[]countBitsConcise(int num){
        int []res=new int[num+1];
        for(int i=1;i<=num;++i)
            res[i]=res[i<<1]+(i&0x1);
        return res;
    }

    //341 flatten nested list iterator
    //in design

    //342 power of four
    //循环，递归，
    public boolean isPowerOfFour(int num) {
        if(num<=0)
            return false;
        //return 1073741824%num==0;//不能这样啊，4能被2整除
        return (num&num-1)==0 && (num&0x55555555)!=0;
    }

    //343 integer break
    public int integerBreak(int n) {
        if(n<=3)//这里wrong 了两次
            return n-1;
        if(n%3==0)
            return (int)Math.pow(3,n/3);
        else if(n%3==1)
            return (int)Math.pow(3,n/3-1)*4;
        else
            return (int)Math.pow(3,n/3)*2;
    }

    //试试dp的解法
    public int integerBreakDP(int n){
        int []dp=new int[n+1];
        if(n<=3)
            return n-1;
        dp[2]=1;
        dp[3]=2;
        for(int i=4;i<=n;++i){
            for(int j=2;j<i-1;++j){
                dp[i]=Math.max(dp[i],Math.max(dp[i-j],i-j)*Math.max(dp[j],j));//很有意思，出了dp[2],dp[3]比i小，其他的都比i大
            }
        }
        return dp[n];
    }

    //344 revese string
    public String reverseString(String s) {
        int n=s.length();
        char []ss=s.toCharArray();
        int begin=0,end=n-1;
        while(begin<end){
            char c=ss[begin];
            ss[begin++]=ss[end];
            ss[end--]=c;
        }
        return String.valueOf(ss);
    }

    //345 remove vowels of a string
    public String reverseVowels(String s) {
        int n=s.length();
        char []ss=s.toCharArray();
        int begin=0,end=n-1;
        Set<Character>vowels=new HashSet<>();
        Character []vowelss={'a','e','i','o','u'};
        vowels.addAll(Arrays.asList(vowelss));
        while(begin<end){
            while(begin<end && !vowels.contains(Character.toLowerCase(ss[begin])))
                begin++;
            while(begin<end &&!vowels.contains(Character.toLowerCase(ss[end])))
                end--;
            if(begin<end){
                char c=ss[begin];
                ss[begin++]=ss[end];
                ss[end--]=c;
            }
        }
        return String.valueOf(ss);
    }
}
