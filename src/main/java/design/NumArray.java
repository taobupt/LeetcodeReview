package design;

/**
 * Created by tao on 4/6/17.
 */
public class NumArray {

    private int[]sums=null;
    //private int[]copy=null;
    public NumArray(int[] nums) {
        int n=nums.length;
        //copy=nums.clone();
        sums=new int[n];
        for(int i=0;i<n;++i){
            if(i==0)
                sums[i]=nums[i];
            else
                sums[i]=sums[i-1]+nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if(i==0)
            return sums[j];
        return sums[j]-sums[i-1];//sb了，不应该加个copy数组的
    }
}
