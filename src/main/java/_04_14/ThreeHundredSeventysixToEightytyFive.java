package _04_14;

import java.util.*;

/**
 * Created by tao on 4/21/17.
 */


  class NestedInteger {
      private List<NestedInteger>res=null;
      private Integer val =null;
      // Constructor initializes an empty nested list.
              public NestedInteger(){
                  res=new ArrayList<>();
              }

              // Constructor initializes a single integer.
              public NestedInteger(int value){
                  val = value;
              }

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger(){
                  return val!=null;
              }

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger(){
                  return val;
              }

              // Set this NestedInteger to hold a single integer.
              public void setInteger(int value){
                  val =value;
              }

              // Set this NestedInteger to hold a nested list and adds a nested integer to it.
              public void add(NestedInteger ni){
                  res.add(ni);
              }

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return null if this NestedInteger holds a single integer
              public List<NestedInteger> getList(){
                  return res;
              }
  }
public class ThreeHundredSeventysixToEightytyFive {

    //376

    //377 combination sum iV
    //
    /*
    good solution, but some cases such as
    [100000000,200000000,300000000]
    1400000000
    will get Memory Limit Exceeded due to you assign the length of "res" array as target+1,

    有负数的话其实是不行的额，我们知道有负，有正，你就可以无限制的搞了，所以要限定次数的条件下才有计算的必要，否则没有这个必要
     */
    public int combinationSum4(int[] nums, int target) {
        int []dp=new int[target+1];
        dp[0]=1;
        int n=nums.length;
        Arrays.sort(nums);
        for(int i=1;i<=target;++i){
            for(int j=0;j<n;++j){
                if(i>=nums[j]){
                    dp[i]+=dp[i-nums[j]];
                }else
                    break;
            }
        }
        return dp[target];
    }

    //其实可以用hashmap搞，你晚上再看看hashmap的version


    //378 kth smallest element in a sorted matrix
    //和373居然是一样的，我想不通啊
    public int kthSmallest(int[][] matrix, int k) {
        return 0;
    }

    ///379 phone directory, one pass, 没啥好说的
    //核心考察的是用linkedlist来链接，用set来判断是否合法，我太sb了。当然还得用一个数来记录最大值，这样判断是否是valid的时候，首先先判断是不是在[0,maxi]里，再去判断是否在set里。还是不稳啊


    //380 Insert Delete GetRandom O(1)
    //非常经典的一道题，就是交换到尾部，然后进行删除，真是瞎了狗眼了，居然4个月前的东西忘了怎么破

    //382 in design solution
    //398 in design solution ,same question

    //383 ransome note
    public boolean canConstruct(String ransomNote, String magazine) {
        char []ransom=ransomNote.toCharArray();
        char []mag=magazine.toCharArray();
        int []cnt=new int[128];
        for(char c:mag)
            cnt[c]++;
        for(char c:ransom){
            cnt[c]--;
            if(cnt[c]<0)
                return false;
        }
        return true;
    }

    //384 shuffle an array
    //in design solution

    //385 mini parser
    //stack and string
    //stack iterative way & recursive way
    //好吧，浪费了巨大的时间才把它写出来，太sble
    //corner case, [],-126; 空括号，负数
    public NestedInteger deserialize(String s) {
        if(s.charAt(0)!='['){
            return new NestedInteger(Integer.parseInt(s));
        }else if(s.equals("[]")){
            return new NestedInteger();}
        else{
            //先检测有没有单个的数。
            int i=1;
            int cnt=0;
            int start=i;
            int end=i;
            NestedInteger res=new NestedInteger();
            while(i<s.length()){
                if(s.charAt(i)==','){
                    i++;
                    continue;
                }else if(s.charAt(i)=='-' ||Character.isDigit(s.charAt(i))){
                    start=i;
                    while(i<s.length() && s.charAt(i)!='[')
                        i++;
                    String sub = "";
                    if(i==s.length())
                        sub=s.substring(start,i-1);
                    else
                        sub=s.substring(start,i);
                    String[]subs=sub.split(",");
                    for(String substr:subs){
                        if(!substr.isEmpty())
                            res.add(new NestedInteger(Integer.parseInt(substr)));
                    }
                }else if(i<s.length() && s.charAt(i)=='['){
                    start=i;
                    while(i<s.length()){
                        if(s.charAt(i)=='['){
                            cnt++;
                        }
                        else if(s.charAt(i)==']'){
                            end=i;
                            cnt--;
                        }
                        i++;
                        if(cnt==0){
                            res.add(deserialize(s.substring(start,end+1)));
                            i++;
                            break;
                        }
                    }
                }
            }
            return res;
        }
    }

    //iterative way
    public NestedInteger deserializeIterative(String s){
        if(s.charAt(0)!='['){
            return new NestedInteger(Integer.parseInt(s));
        }
        else{
            NestedInteger cur =new NestedInteger();
            Stack<NestedInteger>stk=new Stack<>();
            int i=1,n=s.length(),start=0,end=0;
            while(i<n){
                if(s.charAt(i)==','){
                    i++;
                    continue;
                }else if(s.charAt(i)=='-'||Character.isDigit(s.charAt(i))){
                    start = i;
                    while(i<n && (s.charAt(i)!=']' && s.charAt(i)!='['))
                        i++;
                    String sub = "";
                    if(s.charAt(i)==']'){
                        sub = s.substring(start,i);
                    }else
                        sub=s.substring(start,i-1);//,[]
                    String []subs=sub.split(",");
                    for(String substr:subs){
                        if(!substr.isEmpty())
                            cur.add(new NestedInteger(Integer.parseInt(substr)));
                    }
                }else if(i<n && s.charAt(i)=='['){
                    stk.push(cur);
                    cur=new NestedInteger();
                    i++;
                }else if(i<n && s.charAt(i)==']'){
                    if(!stk.isEmpty()){
                        stk.peek().add(cur);
                        cur=stk.pop();
                    }
                    i++;
                }
            }
            return cur;
        }
    }

}
