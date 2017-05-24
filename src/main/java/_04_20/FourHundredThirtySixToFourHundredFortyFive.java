package _04_20;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tao on 5/23/17.
 */
public class FourHundredThirtySixToFourHundredFortyFive {



    //437 path sum III
    //两次dfs


    public int dfs1(TreeNode root,int sum){
        if(root==null)
            return 0;
        int ans=0;
        if(root.val==sum)
            ans++;
        ans+=dfs1(root.left,sum-root.val)+dfs1(root.right,sum-root.val);
        return ans;
    }

    //枚举所有的起点，然后做dfs
    public int dfs(TreeNode root,int sum){
        if(root==null)
            return 0;
        return dfs1(root,sum)+dfs(root.left,sum)+dfs(root.right,sum);
    }
    public int pathSum(TreeNode root, int sum) {
        return dfs(root,sum);
    }


    //438 find all anagrams in string
    //效率很低，超级低
    public boolean check(String ss, String p){
        int []cnt=new int[26];
        char []sss=ss.toCharArray();
        char []pp=p.toCharArray();
        int n=p.length();
        for(int i=0;i<n;++i){
            cnt[sss[i]-'a']++;
            cnt[pp[i]-'a']--;
        }
        for(int i=0;i<26;++i)
            if(cnt[i]!=0)
                return false;
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        //一遍一遍扫吧
        int m=s.length(),n=p.length();
        if(m<n)
            return new ArrayList<>();
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<=m-n;++i){
            if(check(s.substring(i,i+n),p))
                res.add(i);
        }
        return res;
    }

    //two window;
    //tag two window
    //非常精妙
    public List<Integer> findAnagramsSaveTime(String s, String p){
        List<Integer>res=new ArrayList<>();
        if(s.isEmpty())
            return res;
        int []cnt=new int[26];
        char []pp=p.toCharArray();
        char []ss=s.toCharArray();
        for(char c:pp)
            cnt[c-'a']++;
        int start=0,end=0,n=s.length(),m=pp.length;
        int count=p.length();
        while(end<n){
            if(cnt[ss[end++]-'a']-- >0)
                count--;
            if(count==0)
                res.add(start);
            if(end-start==m && ++cnt[ss[start++]-'a']>0)
                count++;
        }
        return res;
    }



    //439 ternary expression parser
    //不用递归了
    //关键是查找到问号
    //递归是栈溢出了。还是用stack吧,知道数据范围你还这么搞，真是作死
    public String parseTernary(String expression) {
        if(expression.length()==1)
            return expression;
        if(expression.length()==5){
            return ""+(expression.charAt(0)=='T'?expression.charAt(2):expression.charAt(4));
        }
        int index = expression.lastIndexOf('?');
        if(expression.charAt(index-1)=='T'){
            return parseTernary(expression.substring(0,index-1)+expression.charAt(index+1)+expression.substring(index+4));
        }else
            return parseTernary(expression.substring(0,index-1)+expression.charAt(index+3)+expression.substring(index+4));
    }

    //
    public String parseTernaryByStack(String expression) {
        Stack<Character>stk=new Stack<>();
        char []ss=expression.toCharArray();
        int n=ss.length,i=n-1;
        while(i>=0){
            if(ss[i]==':'){
                i--;
                continue;
            }
            if(ss[i]!='?'){
                stk.push(ss[i--]);
            }else{
                char a=stk.pop();
                char b=stk.pop();
                stk.push(ss[i-1]=='T'?a:b);
                i-=2;
            }
        }
        return ""+stk.peek();
    }
}
