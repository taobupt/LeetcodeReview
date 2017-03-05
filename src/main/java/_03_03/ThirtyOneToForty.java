package _03_03;

import java.util.*;

/**
 * Created by taobupt on 3/3/2017.
 */
public class ThirtyOneToForty {


    //31 next premutation
    //just swap, no need to sort
    public void reverse(int []nums,int start,int end){
        while(start<end){
            int tmp=nums[start];
            nums[start++]=nums[end];
            nums[end--]=tmp;
        }
    }
    public void nextPermutation(int []nums){
        int n=nums.length;

        //check whether it is empty or not;

        if(n==0)
            return;
        int j=n-2;
        for(;j>=0;--j){
            if(nums[j]<nums[j+1])
                break;
        }
        if(j==-1){
            //all sorted ,just reverse
            reverse(nums,0,n-1);
            return;
        }

        //find the next larget element than nums[j];
        int i=n-1;
        for(;i>=j;--i){
            if(nums[i]>nums[j])
                break;
        }

        //just swap the two elements
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
        reverse(nums,j+1,n-1);
    }


    //32 Longest Valid Parentheses
    //stack
    //这道题确实不太会啊
    public int longestValidParentheses(String s) {
        char[]arrs=s.toCharArray();
        int n=s.length();
        Stack<Integer>stk=new Stack<>();
        //sential
        //stk.push(-1);
        int maxLength=0;
        for(int i=0;i<n;++i){
            if(!stk.isEmpty() && arrs[stk.peek()]=='(' && arrs[i]==')'){
                stk.pop();
                maxLength=Math.max(maxLength,i-(stk.isEmpty()?-1:stk.peek()));
            }else
                stk.push(i);
        }
        return maxLength;
    }

    //还可以用dp来写




    //33 Search in Rotated Sorted Array
    //just normal binary search
    public int search(int[] nums, int target) {
        int n=nums.length;
        int begin=0,end=n-1;
        if(n==0)
            return -1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]>nums[end]){
                if(nums[mid]>target && target>=nums[begin])
                    end=mid;
                else
                    begin=mid+1;//think it a lot
            }
            else if(nums[mid]<nums[end]){
                if(nums[mid]<target && target<=nums[end])//this is end: think a lot
                    begin=mid+1;
                else
                    end=mid;
            }
        }
        return nums[begin]==target?begin:-1;
    }

    //actually you can find the pivot first ,and then use the normal search


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


    //36 valid sudoku

    public boolean checkRowOrCol(char[][]board,boolean isRow,int []cnt){
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                if(isRow){
                    if(board[i][j]!='.' && cnt[board[i][j]]++==1)
                        return false;
                }else{
                    if(board[j][i]!='.' && cnt[board[j][i]]++==1)
                        return false;
                }
            }
            Arrays.fill(cnt,0);
        }
        Arrays.fill(cnt,0);
        return true;
    }
    public boolean isValidSudoku(char[][] board) {
        //横竖，每个9宫格都要保持合法

        int []cnt=new int[128];
        //first check the rows;
        if(!checkRowOrCol(board,true,cnt))
            return false;
        if(!checkRowOrCol(board,false,cnt))
            return false;
        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                for(int m=i;m<i+3;++m){
                    for(int n=j;n<j+3;++n){
                        if(board[m][n]!='.' &&cnt[board[m][n]]++==1)
                            return false;
                    }
                }
                Arrays.fill(cnt,0);
            }
        }
        return true;
    }


    //37 Sudoku Solver
    //和n queens 差不多，都是回溯，试探
    //晚上记得复习，两道hard，这道的话其实和tree的那道很像，dfs都是带boolean,能极大的剪枝，什么时候用boolean, 什么时候用void呢？
    //可以问下大神
    private boolean valid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
    public boolean dfs(char[][]board,int pos){
        if(pos==81){
            return true;
        }
        for(int i=pos;i<81;++i){
            if(board[i/9][i%9]=='.'){
                //search for a possible value
                for(int k='1';k<='9';++k){

                    if(valid(board,i/9,i%9,(char)k)){
                        board[i/9][i%9]=(char)k;
                        if(dfs(board,i+1))
                            return true;
                        else
                            board[i/9][i%9]='.';
                    }
                }
                return false;

            }
        }
        return true;
    }
    public void solveSudoku(char[][] board) {
        dfs(board,0);
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
