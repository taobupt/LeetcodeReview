package design;

import common.FenwickTree;

/**
 * Created by tao on 4/8/17.
 */
public class NumArrayChangeAble {

    //几个点需要注意一下，Fenwick tree 的几个功能不要分离开来，就是那三个函数，不要手动去更改它，否则后果自负
    // 离散化才能用，并且是从1开始的，这点谨记，update的时候，注意是从外部更新，最后别忘了更新本地数组，
    private FenwickTree tree=null;
    private int[]arr=null;
    public NumArrayChangeAble(int[] nums) {
        int n=nums.length;
        arr=nums.clone();
        tree=new FenwickTree(n);
        for(int i=0;i<n;++i){
            tree.add(i+1,nums[i]);
        }
    }

    public void update(int i, int val) {
        int delta=val-arr[i];
        tree.add(i+1,delta);
    }

    public int sumRange(int i, int j) {
        return tree.sum(j+1)-tree.sum(i);
    }
}
