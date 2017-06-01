package _04_26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tao on 5/30/17.
 */

public class FourHundredNinetyFiveToFiveHundred {

    //496 next great element
    //单调栈，暴力是n2,单调站能降到n
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int m=findNums.length,n=nums.length;
        int []res=new int[m];
        Arrays.fill(res,-1);
        for(int i=0;i<m;++i){
            int j=0;
            for(j=0;j<n;++j){
                if(findNums[i]==nums[j])
                    break;
            }
            for(;j<n;++j){
                if(nums[j]>findNums[i]){
                    res[i]=nums[j];
                    break;
                }
            }
        }
        return res;
    }


    //498 diagonal traversal
    //效率极为低下
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return new int[0];
        int m = matrix.length,n=matrix[0].length;
        int []res=new int[m*n];
        List<List<Integer>>arr=new ArrayList<>();
        for(int i=0;i<n;++i){
            arr.add(new ArrayList<>());
            int k=i;
            for(int j=0;j<m;++j){
                if(k>=0){
                    arr.get(i).add(matrix[j][k]);
                    k--;
                }else
                    break;
            }
            if(i%2==0)
                Collections.reverse(arr.get(i));
        }
        for(int i=1;i<m;++i){
            arr.add(new ArrayList<>());
            int k = i;
            for(int j=n-1;j>=0;--j){
                if(k<m){
                    arr.get(n+i-1).add(matrix[k][j]);
                    k++;
                }else
                    break;
            }
            if(arr.size()%2==1)
                Collections.reverse(arr.get(n+i-1));

        }
        int ind=0;
        for(List<Integer>li:arr){
            for(int x:li)
                res[ind++]=x;
        }
        return res;
    }
}
