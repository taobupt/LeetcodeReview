package design;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tao on 5/12/17.
 */

//高级一点的办法就是蓄水次抽样
//蓄水池的方法非常搞笑，为啥每次都要走一次循环才能确定最终的值呢
//非常经典的题目。值得一做.
public class Solution {

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    private List<Integer> nums=null;
    Random random =null;
    public Solution(ListNode head) {
        ListNode node=head;
        nums=new ArrayList<>();
        while(node!=null){
            nums.add(node.val);
            node=node.next;
        }
        random=new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {

        int index=random.nextInt(nums.size());
        return nums.get(index);
    }

    private int []arr=null;
    private int n=0;
    public Solution(int[] nums) {
        arr=nums.clone();
        n=nums.length;
        random =new Random();
    }

    public int pick(int target) {
        int index =-1,count=0;
        for(int i=0;i<n;++i){
            if(arr[i]==target){
                count++;
                if(random.nextInt(count)==0){
                    index=i;
                }
            }
        }
        return index;
    }


    private ListNode node=null;
    public void Solution1(ListNode head) {
        node=head;
        random =new Random();
    }

    /** Returns a random node's value. */
    public int getRandom1() {
        int res=-1;
        ListNode p=node;
        for(int i=1;p!=null;p=p.next){
            if(random.nextInt(i)==0){
                res=p.val;
            }
            //p=p.next;
            i++;
        }
        return res;
    }


    private int []copy=null;
    public void Solution1(int[] nums) {
        copy=nums.clone();
        random =new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return copy;
    }


    public void swap(int[]arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        //从后到前面
        int []arr = copy.clone();
        int n=arr.length;
        for(int i=n-1;i>=0;--i){
            int index = random.nextInt(i+1);
            if(index!=i){
                swap(arr,i,index);
            }
        }
        return arr;
    }
}