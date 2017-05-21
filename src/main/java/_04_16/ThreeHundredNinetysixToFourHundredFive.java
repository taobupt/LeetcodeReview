package _04_16;

import common.TreeNode;

import java.util.*;

/**
 * Created by tao on 5/20/17.
 */

//大礼包，以后转成任意进制的都可以按照405题来拓展,
// 很有意思的一道题，居然要减1，400题
public class ThreeHundredNinetysixToFourHundredFive {



    //397 Integer Replacement
    Map<Integer,Integer>map=new HashMap<>();
    public int integerReplacement(int n) {
        if(map.containsKey(n))
            return map.get(n);
        if(n<=0)
            return Integer.MAX_VALUE;
        if(n==1)
            return 0;
        int x=0;
        if(n%2==0){
            x=integerReplacement(n/2);
            map.put(n,x+1);
            return x+1;
        }else{
            if(n!=Integer.MAX_VALUE)
                x=Math.min(integerReplacement(n+1),integerReplacement(n-1));
            else
                x=Math.min(integerReplacement(n-1),integerReplacement(1+(n-1)/2)+1);
            map.put(n,x+1);
            return x+1;
        }
    }
    //400 n-digit
    //binary search
    //calculate how much digits less than or equal mid;
    public long getNum(int mid,long []dp){

        String str = String.valueOf(mid);
        int n=str.length();
        return dp[n-1]+n*(long)(mid-(int)Math.pow(10,n-1)+1);
    }
    public int findNthDigit(int n) {

        long []dp=new long[10];
        dp[1]=9;
        for(int i=2;i<10;++i){
            dp[i]=dp[i-1]+i*(9*(long)Math.pow(10,i-1));
        }
        int begin=1,end=n;
        while(begin<end){
            int mid =(end-begin)/2+begin;
            long num=getNum(mid,dp);
            String str = String.valueOf(mid);
            int len=str.length();
            if(num>=n && n>num-len){
                System.out.println(str);
                getNum(mid,dp);
                for(int i=1;i<=len;++i){
                    if(num-len+i==n)
                        return (str.charAt(i-1)-'0');
                }
            }else if(num-len>=n){
                end=mid;
            }else
                begin=mid+1;
        }
        if(n==10)
            return 1;
        return begin%10;
    }

    //another way
    public int findNthDigitConcise(int n) {
        int len =1;
        int cnt=9;
        int start=1;
        while(n>len*cnt){
            n-=cnt*len;
            len++;
            cnt*=10;
            start*=10;
        }
        start +=(n-1)/len;//减1是因为start 自己算一个数，要把start 从计算中抠掉
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));//非常高效
    }

    //401 binary watch
    public List<String> readBinaryWatch(int num) {
        List<String>res= new ArrayList<>();
        for(int i=0;i<12;++i){
            for(int j=0;j<60;++j){
                if(Integer.bitCount(i)+Integer.bitCount(j)==num){
                    String minutes = (j<10)?"0"+j:""+j;
                    res.add(String.valueOf(i)+":"+minutes);
                }
            }
        }
        return res;
    }
    //笨一点的办法就是双方各有多少个1，然后在根据多少个1来推断每部分的数。就是combination 取k个位置。
    // combination k
    public void dfs(List<List<Integer>>res,List<Integer>path,int k,int n,int ind){
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        if(path.size()>k)
            return;
        for(int i=ind;i<=n;++i){
            path.add(i);
            dfs(res,path,k,n,i+1);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>>combinations(int n,int k){
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        dfs(res,path,k,n,1);
        return res;
    }


    //402 remove k digits
    //is the smallest possible
    //取出n-k个数，然后求最小
    public String removeKdigits(String num, int k){
        int n=num.length();
        if(k>=n)
            return "0";
        StringBuilder sb = new StringBuilder();
        int i=0;
        k=n-k;
        Stack<Character>stk=new Stack<>();
        char []ss=num.toCharArray();
        while(i<n){
            while(!stk.isEmpty() &&(n-i>k-stk.size()) && stk.peek()>ss[i]){
                stk.pop();
            }
            if(stk.size()<k)
                stk.push(ss[i]);
            i++;
        }
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        sb=sb.reverse();
        //remove leading zeros;
        int index=0;
        while(index<sb.length()-1){
            if(sb.charAt(index)=='0')
                index++;
            else
                break;
        }
        String res = sb.toString().substring(index);
        return res;
        /*
        ans=ans.replaceFirst ("^0*", "");
        return ans.isEmpty()?"0":ans;
         */

    }

    //404 sum of left leaves
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null ||(root.left==null && root.right==null)){
            return 0;
        }
        int sum =0;
        if(root.left!=null){
            if(root.left.right==null && root.left.left==null)
                sum+=root.left.val;
            sum+=sumOfLeftLeaves(root.left);
        }
        sum+=sumOfLeftLeaves(root.right);
        return sum;
    }

    //405 convert a number to hexadecimal
    //第一个，转二进制要小心0的情况 第二个，当前位是carry%2，不是等于2就是0，误判
    public String toHex(int num) {
        //positive and negative number;
        //先转换成二进制，然后再凑齐32位，组成16进制
        char []digit = {'a','b','c','d','e','f'};
        if(num==Integer.MIN_VALUE)
            return "80000000";
        int absNum = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        while(absNum!=0){
            sb.append(absNum%2);
            absNum/=2;
        }

        if(num<0){
            int zeros = 32-sb.length();
            for(int i=0;i<zeros;++i)
                sb.append('0');
            sb=sb.reverse();
            int carry =1;
            for(int i=0;i<32;++i)
                sb.setCharAt(i,sb.charAt(i)=='0'?'1':'0');
            for(int i=31;i>=0;--i){
                int sum = carry + (sb.charAt(i)-'0');
                sb.setCharAt(i,sum%2==0?'0':'1');
                carry=sum/2;
            }
        }else
            sb = sb.reverse();

        int n=sb.length();
        for(int i=n-1;i>=0;i-=4){
            int sum=0;
            for(int j=3;j>=0;--j){
                if(i-j>=0){
                    sum=2*sum+(sb.charAt(i-j)-'0');
                }
            }
            if(sum>=10)
                res.append(digit[sum-10]);
            else
                res.append(sum);
        }

        if(res.length()==0)
            return "0";
        return res.reverse().toString();
    }

    //concise
    public String toHexConcise(int num){
        char []map={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if(num==0)
            return "0";
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            sb.append(map[(num)&15]);
            num=(num>>>4);
        }
        return sb.reverse().toString();
    }

    public String toBinary(int num){
        if(num==0)
            return "0";
        if(num==Integer.MIN_VALUE)
            return "10000000000000000000000000000000";
        int absNum = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(absNum!=0){
            sb.append(absNum%2);
            absNum/=2;
        }
        if(num<0){
            int zeros = 32-sb.length();
            for(int i=0;i<zeros;++i)
                sb.append('0');
            sb=sb.reverse();
            int carry =1;
            for(int i=0;i<32;++i)
                sb.setCharAt(i,sb.charAt(i)=='0'?'1':'0');
            for(int i=31;i>=0;--i){
                carry +=(sb.charAt(i)-'0');
                sb.setCharAt(i,carry%2==0?'0':'1');
                carry=carry/2;
            }
        }else
            sb.reverse();
        return sb.toString();
    }

    public String toBinaryConcise(int num){
        char []map={'0','1'};
        if(num==0)
            return "0";
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            sb.append(map[(num)&1]);
            num=(num>>>1);
        }
        return sb.reverse().toString();
    }
}
