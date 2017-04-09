package common;

/**
 * Created by tao on 4/8/17.
 */
public class FenwickTree {
    private int[]sum=null;
    private int n;
    public FenwickTree(int n){
        sum=new int[n+1];
        this.n=n;
    }

    public int lowbit(int x){
        return x&(-x);
    }

    public void add(int index,int val){
        while(index<=n){
            sum[index]+=val;
            index+=lowbit(index);
        }
    }

    public int sum(int index){
        int res=0;
        while(index>0){
            res+=sum[index];
            index-=lowbit(index);
        }
        return res;
    }
}
