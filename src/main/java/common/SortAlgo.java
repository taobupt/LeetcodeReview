package common;

import java.util.Arrays;

/**
 * Created by tao on 4/9/17.
 */
public class SortAlgo {

    //merge sort solution general
    //merge sort 求逆序对，也算是告一段落了，我们知道，其实真正的插入逆序对，应该是在nums[i]比较小，nums[j]比较大的时候，这样才是j-mid
    int reverseNum=0;
    public void merge(int[]nums,int begin,int mid,int end){
        int[]copy=nums.clone();
        //start to mege
        int i=begin,j=mid+1,index=begin;
        while(i<=mid && j<=end){//这里是需要等号的，谨记
            if(copy[i]>copy[j]){
                //nums[index++]=copy[j++];
                j++;
            }
            else{
                reverseNum+=j-mid-1;
                //nums[index++]=copy[i++];
                i++;
            }
        }
        while(i<=mid){
            reverseNum+=end-mid;
            //nums[index++]=copy[i++];
            i++;
        }
//        while (j<=end)
//            nums[index++]=copy[j++];
        Arrays.sort(nums,begin,end+1);

    }
    public void mergeSort(int[]nums){
        int n=nums.length;
        int begin=0,end=n-1;
        mergeSort(nums,0,end);
        System.out.println(reverseNum);
    }
    public void mergeSort(int[]nums,int begin,int end){
        if(begin>=end)
            return;
        int mid=(end-begin)/2+begin;
        mergeSort(nums,begin,mid);
        mergeSort(nums,mid+1,end);
        merge(nums,begin,mid,end);
    }
}
