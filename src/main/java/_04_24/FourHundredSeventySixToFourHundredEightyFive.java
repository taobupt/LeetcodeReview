package _04_24;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredSeventySixToFourHundredEightyFive {


    //476. Number Complement
    public int findComplement(int num) {
        //转成二进制然后在做
        if(num==0)
            return 1;
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            sb.append(num%2==0?1:0);
            num/=2;
        }
        int sum=0,n=sb.length();
        for(int i=0;i<n;++i){
            sum+=(sb.charAt(i)=='1'?(1<<i):0);
        }
        return sum;
    }

    //477 total hamming distance, maximum xor value in array
    public int totalHammingDistance(int[] nums) {
        int n=nums.length,res=0;
        for(int i=31;i>=0;--i){
            int ones=0,mask=(1<<i);
            for(int x:nums){
                if((x&mask)!=0)//不是说等于1就好了，应该是不等于0。
                    ones++;
            }
            res+=Math.min(ones,n-ones);
        }
        return res;
    }


    //这道题确实很经典啊。
    public double getMedian(int k,PriorityQueue<Integer>pq1,PriorityQueue<Integer>pq2){
        //未考虑1的情况；
        double r1 = pq1.isEmpty()?0.0:1.0*pq1.peek();
        double r2 = pq2.isEmpty()?0.0:1.0*pq2.peek();
        return k%2==0?(r1+r2)/2.0:r1;
    }
    public void add(PriorityQueue<Integer>pq1,PriorityQueue<Integer>pq2,int num){
        if(pq1.isEmpty()||num>= pq1.peek())
            pq1.offer(num);
        else
            pq2.offer(num);
        while(pq1.size()>pq2.size()+1){
            pq2.offer(pq1.poll());
        }
        while(pq2.size()>pq1.size()){
            pq1.offer(pq2.poll());
        }
    }
    // Sliding Window Median; two priority queue
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        double []res=new double[n-k+1];
        PriorityQueue<Integer>pq1=new PriorityQueue<>();
        PriorityQueue<Integer>pq2=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<k;++i){
            add(pq1,pq2,nums[i]);
        }
        int ind=0;
        res[ind++]=getMedian(k,pq1,pq2);
        for(int i=k;i<n;++i){
            int val = nums[i-k];
            if(val>=pq1.peek())
                pq1.remove(val);
            else
                pq2.remove(val);
            while(pq1.size()>pq2.size()+1){
                pq2.offer(pq1.poll());
            }
            while(pq2.size()>pq1.size()){
                pq1.offer(pq2.poll());
            }
            add(pq1,pq2,nums[i]);
            res[ind++]=getMedian(k,pq1,pq2);
        }
        return res;
    }

    //481 magical string,根据两部分之间的关系进行互推,但是不知道为啥效率这么低。有原因的
    public int magicalString(int n) {
        StringBuilder sb = new StringBuilder("122");
        StringBuilder res = new StringBuilder("122");
        while(res.length()<n){
            if(sb.charAt(sb.length()-1)=='1')
                res.append(res.charAt(res.length()-1)=='1'?'2':'1');
            else{
                char c1 =res.charAt(res.length()-1);
                res.append(c1=='1'?'2':'1').append(c1=='1'?'2':'1');
            }
            sb.append(res.charAt(sb.length()));
        }
        int cnt=0;
        char []ss=res.toString().toCharArray();
        for(int i=0;i<n;++i){
            if(ss[i]=='1')
                cnt++;
        }
        return cnt;
    }
    //you can use array to optimize, above is the simulation
    public int magicalStringArray(int n) {
        if(n==0)
            return 0;
        if(n<=3)
            return 1;
       int []arr=new int[n];
       arr[0]=1;arr[1]=arr[2]=2;
       int j=3,i=2;
        while(j<n){
            if(arr[i]==1){
                arr[j]=(arr[j-1]==1?2:1);
                j++;
            }
            else{
                arr[j]=(arr[j-1]==1?2:1);
                arr[j+1]=arr[j];
                j+=2;
            }
            i++;
        }
        int cnt=0;
        for(int ii=0;ii<n;++i){
            if(arr[i]==1)
                cnt++;
        }
        return cnt;
    }

    //482 license key formatting
    public String licenseKeyFormatting(String S, int K) {
        char []ss=S.toCharArray();
        StringBuilder sb = new StringBuilder();
        int n=ss.length,cnt=0;
        for(int i=n-1;i>=0;--i){
            if(ss[i]=='-')
                continue;
            sb.append(Character.toUpperCase(ss[i]));
            cnt++;
            if(cnt==K){
                sb.append('-');
                cnt=0;
            }
        }

        //in case of the '-rde',3;
        sb=sb.reverse();
        if(sb.length()!=0 && sb.charAt(0)=='-')
            sb.deleteCharAt(0);
        return sb.toString();
    }

    //483 smallest good base
    //又学了一招，其实和bitmap很像的
    public long search(long nn, int d){
        //1+k+k^2+...+k^d=nn;

        long left=1, right=(long)(Math.pow(nn,1.0/(d-1))+1);
        while(left<right){
            long mid =(right-left)/2+left;
            long sum=1,cur=1;
            for(int i=1;i<=d-1;++i){
                cur*=mid;
                sum+=cur;
            }
            if(sum==nn)
                return mid;
            if(sum<nn)
                left=mid+1;
            else
                right=mid;
        }
        //check right is ok
        long sum=1,cur=1;
        for(int i=1;i<=d-1;++i){
            cur*=left;
            sum+=cur;
        }
        return sum==nn?left:0;
    }
    public String smallestGoodBase(String n) {
        long nn = Long.parseLong(n);
        long x=1;
        //length from 62 to 2 to search, 2 is always ok,
        for(int d=62;d>=2;--d){
            if((x<<(d-1))<nn){
                long res = search(nn,d);
                if(res!=0)
                    return String.valueOf(res);
            }
        }
        return String.valueOf(nn-1);
    }

    //484 find permuatation,居然卡壳了，卧槽,隐隐约约知道用priority queue
    //但是看了看priority queue的做法，卧槽，居然看不懂了，真是醉了，仔细观察下规律你发现
    //For example, given IDIIDD we start with sorted sequence 1234567
   // Then for each k continuous D starting at index i we need to reverse [i, i+k] portion of the sorted sequence.
    public int[] findPermutation(String s) {
        int n=s.length();
        int []res  =new int[n+1];
        for(int i=0;i<=n;++i)
            res[i]=i+1;
        int j=0;
        char []ss=s.toCharArray();
        while(j<n){
            if(ss[j]=='I'){
                j++;
                continue;
            }
            else{
                int start=j;
                while(j<n && ss[j]=='D')
                    j++;
                reverse(res,start,j);
            }
        }
        return res;
    }

    public void reverse(int[]nums,int i,int j){
        while(i<j){
            int x = nums[i];
            nums[i++]=nums[j];
            nums[j--]=x;
        }
    }

    //485 max consecutive one
    public int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length,begin=0,end=0,res=0;
        while(end<n){
            if(nums[end]==1){
                begin=end;
                while(end<n && nums[end]==1)
                    end++;
                res=Math.max(res,end-begin);
            }else
                end++;
        }
        return res;
    }
}
