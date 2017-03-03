package _03_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by taobupt on 3/3/2017.
 */
public class ThirtyOneToForty {

    //35 search insert position
    //binary search lower_bound
    //this is what I want , the concise version
    //in my binary search, they can arrive end when they out of loop
    public int searchInsert(int[] nums, int target) {
        int begin=0,end=nums.length;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]>=target){
                end=mid;
            }else{
                begin=mid+1;
            }
        }
        return begin;
    }

    //recursive way

    public int recursive(int[]nums,int begin,int end,int target){
        if(begin==end)
            return begin;
        int mid=(end-begin)/2+begin;
        if(nums[mid]>=target)
            return recursive(nums,begin,mid,target);
        else
            return recursive(nums,mid+1,end,target);

    }
    public int serachIntRecursive(int []nums,int target){
        return recursive(nums,0,nums.length,target);
    }


    //extend upper bound
    public int upperBound(int[]nums,int target){

        int begin=0,end=nums.length;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]<=target){
                begin=mid+1;
            }else{
                end=mid;
            }
        }
        return begin;
    }


    //34 search for a range
    //two binary search to find
    public int[] searchRange(int[] nums, int target) {
        int n=nums.length;
        int low=searchInsert(nums,target);
        if(low==n||nums[low]!=target)
            return new int[]{-1,-1};
        int hi=upperBound(nums,target);
        return new int[]{low,hi-1};
    }

    //if we want a one binary, that why I failed in the linkedin interview, sign
    public int[] searchRangeOneBinarySearch(int[] nums, int target){
        return new int[]{};
    }






    //38 count and say
    //记住，是从0~n-2，这样的菜可以，然后用count去记录，这样才能不会出错。
    //当然，
    public String countAndSay(int n) {
        if(n==0)
            return "";
        StringBuilder res=new StringBuilder("1");
        for(int i=1;i<n;++i){
            StringBuilder sb=new StringBuilder("");
            int j=0,count=1;
            for(;j<res.length()-1;++j){
                if(res.charAt(j)!=res.charAt(j+1)){
                    sb.append(count).append(res.charAt(j));
                    count=1;
                }else
                    count++;
            }
            sb.append(count).append(res.charAt(j));
            res=sb;
        }
        return res.toString();
    }




    //39 combination sum
    //经典回溯题
    //其实这些都是套路,有个模板就可以了，找到限制条件，如果满足限制条件那么就进行地推，
    public void backtrack(List<List<Integer>>res,int[]candidates,int pos,int target,List<Integer>path){
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=pos;i<candidates.length;++i){
            if(target>=candidates[i]){
                path.add(candidates[i]);
                backtrack(res,candidates,i,target-candidates[i],path);
                path.remove(path.size()-1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(res, candidates, 0, target, path);
        return res;
    }


    //40 combination sum II

    public void backtrack(List<List<Integer>>res,int[]candidates,int pos,List<Integer>path,int target){
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=pos;i<candidates.length;++i){
            if(i>pos && candidates[i]==candidates[i-1])//delete duplicates
                continue;
            if(target>=candidates[i]){
                path.add(candidates[i]);
                backtrack(res,candidates,i+1,path,target-candidates[i]);
                path.remove(path.size()-1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(res, candidates, 0, path,target);
        return res;
    }

    //听说还可以用dp,等下可以用dp搞搞搞搞
    //Remark 2: Note this question actually is the subset sum problem which is NP-complete. It means that there is no efficient
    // polynomial time algorithm to solve it for now.

}
