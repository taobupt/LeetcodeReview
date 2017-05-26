package _04_24;

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
}
