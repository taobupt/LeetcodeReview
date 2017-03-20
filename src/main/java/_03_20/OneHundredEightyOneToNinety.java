package _03_20;

import java.util.*;

/**
 * Created by tao on 3/20/17.
 */
public class OneHundredEightyOneToNinety {



    //186 reverse string ii in place

    public void reverse(char[]ss,int begin,int end){
        while(begin<end){
            char tmp=ss[begin];
            ss[begin++]=ss[end];
            ss[end--]=tmp;
        }
    }
    public void reverseWords(char[] s) {
        int n=s.length;
        int i=0;
        while(i<n){
            if(Character.isSpaceChar(s[i]))
                i++;
            int begin=i;
            while(i<n && s[i]!=' ')
                i++;
            reverse(s,begin,i-1);
        }
        reverse(s,0,n-1);
        System.out.println(String.valueOf(s));
    }

    //187 直接hash 解决,注意重复问题
    //好像可以用bitmap
    //A T C G, 最后3位不同啊,所以10个字母可以用30位来表示，这就是一个转换的过程
    public List<String> findRepeatedDnaSequences(String s) {
        int n=s.length();
        Set<String>set=new HashSet<>();
        Set<String>ans=new HashSet<>();
        for(int i=0;i<n-9;++i){
            String sub=s.substring(i,i+10);
            if(set.contains(sub))
                ans.add(sub);
            else
                set.add(sub);
        }
        List<String>res=new ArrayList<>();
        res.addAll(ans);
        return res;
    }
    // bitmap
    public List<String>findRepeatedDnaSequencesBit(String s){
        Map<Integer,Integer>map=new HashMap<>();
        List<String>res=new ArrayList<>();
        int t=0,n=s.length();
        for(int i=0;i<9 && i<n;++i)//corn case "" ,否则就会死的很快哈
            t=t<<3|s.charAt(i)&7;
        for(int i=9;i<n-9;++i){
            t=t<<3&0x3fffffff|s.charAt(i)&7;
            if(!map.containsKey(t))
                map.put(t,1);
            else{
                if(map.get(t)==1)
                    res.add(s.substring(i-9,i+1));
                map.put(t,map.get(t)+1);
            }
        }
        return res;
    }

    //189 rotate array
    //想当初就是跪在这里，心痛啊
    public void reverse(int[]nums,int begin,int end){
        while(begin<end){
            int tmp=nums[begin];
            nums[begin++]=nums[end];
            nums[end--]=tmp;
        }
    }
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        if(k%n==0)
            return;
        k=n-1-k%n;
        reverse(nums,0,k);
        reverse(nums,k+1,n-1);
        reverse(nums,0,n-1);
    }
    //extra space

    public void rotateExtra(int[]nums,int k){
        int n=nums.length;
        if(k%n==0)
            return;
        int[]copy=nums.clone();
        for(int i=0;i<n;++i){
            nums[(i+k)%n]=copy[i];//右移位，左移是nums[i]=copy[(i+k)%n];
        }
    }

    //gcd way
    //left k
    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    public void rotateGCD(int []nums,int k){
        int n=nums.length;
        k=k%n;
        int GCD=gcd(k,n);
        for(int i=0;i<GCD;++i){
            int tmp=nums[i];
            int j=i;
            while(true){
                int m=(j-k+n)%n;//左边的话就是(j+k)%n;
                if(m==i)
                    break;
                nums[j]=nums[m];
                j=m;
            }
            nums[j]=tmp;
        }
    }

    //190 reverse bits
    //先转换成32 bit
    //这道题对java很不友好，明显输入不进去啊，还做个毛线，居然出个2147483648的case
    public int reverseBits(int n) {
        StringBuilder sb=new StringBuilder();
        while(n!=0){
            sb.append(n%2);
            n/=2;
        }
        int res=0;
        int nn=sb.length();
        for(int i=nn-1;i>=0;--i){
            res+=(sb.charAt(i)-'0')<<(31-i);
        }
        return res;
    }

    //concise
    public int reverseBitsConcise(int n){
        if(n==0)
            return 0;
        int res=0;
        for(int i=0;i<32;++i){
            res <<=i;
            if((n&0x1)!=0)
                res++;
            n>>=1;
        }
        return res;
    }
}
