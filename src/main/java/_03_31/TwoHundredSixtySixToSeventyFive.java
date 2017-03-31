package _03_31;

import common.TreeNode;

import java.util.*;

/**
 * Created by Tao on 3/31/2017.
 */
public class TwoHundredSixtySixToSeventyFive {

    //266 palindrome permutation
    //统计计数，奇数最多有一个
    public boolean canPermutePalindrome(String s) {
        int []cnt=new int[256];
        char []ss=s.toCharArray();
        for(char c:ss)
            cnt[c]++;
        int oddcnt=0;
        for(int i=0;i<256;++i){
            if(cnt[i]%2==1){
                oddcnt++;
                if(oddcnt>1)
                    return  false;
            }
        }
        return true;
    }

    //you can use set
    public boolean canPermutePalindromeBySet(String s){
        Set<Character>set=new HashSet<>();
        char []ss=s.toCharArray();
        for(char c:ss){
            if(set.contains(c))
                set.remove(c);
            else
                set.add(c);
        }
        return set.size()<=1;
    }

    //好像可以用bitset
    public boolean canPermutateByBitSet(String s){
        BitSet bs=new BitSet();
        byte[]bits=s.getBytes();
        for(byte b:bits)
            bs.flip(b);
        return bs.cardinality()<2;
    }


    //268 missing number
    //xor
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int res=n;
        for(int i=0;i<n;++i){
            res^=i;
            res^=nums[i];
        }
        return res;
    }

    //sum
    //^ 处理起来更快
    public int missingNumberForSum(int []nums){
        int n=nums.length;
        int res=n;
        for(int i=0;i<n;++i){
            res+=i;
            res-=nums[i];
        }
        return res;
    }
    //binary search
    //if the input array is sorted, then I prefer sort
    public int missingNumberBinary(int[]nums){
        int n=nums.length;
        Arrays.sort(nums);
        int begin=0,end=n;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]==mid)
                begin=mid+1;
            else
                end=mid;
        }
        return begin;
    }

    //270 cloest binary serach tree value
    //暴力解，直接放在数组里面，然后求最近的，但是没啥意思
    //iterative way and recursive way
    //dfs 遍历一遍所有的
    //这是O(n)的解法，小心overflow
    public void dfs(TreeNode root,double target,int []res){
        if(root==null)
            return;
        if(Math.abs(1.0*res[0]-target)>Math.abs(1.0*root.val-target))
            res[0]=root.val;
        dfs(root.left,target,res);
        dfs(root.right,target,res);
    }
    public int closestValue(TreeNode root, double target) {
        int []res=new int[1];
        res[0]=root.val;
        dfs(root,target,res);
        return res[0];
    }

    //
    int ans=0;
    public void dfs(TreeNode root,double target){
        if(root==null)
            return;
        if(Math.abs(1.0*ans-target)>Math.abs(1.0*root.val-target))
            ans=root.val;
        if(root.val>target)
            dfs(root.left,target);
        else if(root.val<target)
            dfs(root.right,target);

    }
    public int cloestValueBST(TreeNode root,double target){
        ans=root.val;
        dfs(root,target);
        return ans;
    }

    //iterative way
    public int cloestValueBSTIterative(TreeNode root,double target){
        //assume there is always one
        int res=root.val;
        TreeNode cur=root;
        while(cur!=null){
            if(Math.abs(res*1.0-target)>Math.abs(cur.val*1.0-target))
                res=cur.val;
            if(cur.val>target)
                cur=cur.left;
            else if(cur.val<target)
                cur=cur.right;
            else
                break;
        }
        return res;
    }

    //272 cloest binary search Tree value II
    //跑一次中序遍历，然后二分查找，然后就是two pointers
    //先把最近的那个root找到，然后左右两边各提取k个数，最后两指针
    public void inorder(TreeNode root,TreeNode node,List<Integer>res,int k,boolean isLeft){

    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        TreeNode cur=root;
        TreeNode node=root;
        while(cur!=null){
            if(Math.abs(1.0*node.val-target)>Math.abs(1.0*cur.val-target))
                node=cur;
            else if(cur.val>target)
                cur=cur.left;
            else if(cur.val<target)
                cur=cur.right;
            else
                break;
        }

        //node is the cloest tree node;
        List<Integer>left=new ArrayList<>();//<=node.val k node
        List<Integer>right=new ArrayList<>();//>node.val k node;
        inorder(root,node,left,k,false);
        inorder(root,node,right,k,true);
        List<Integer>ans=new ArrayList<>();
        int i=k-1,j=0;
        while(i>=0 && j<k){
            if(Math.abs(left.get(i)*1.0-target)<=Math.abs(right.get(j)*1.0-target)){
                ans.add(left.get(i--));
            }else
                ans.add(right.get(j++));
            if(ans.size()==k)
                break;
        }
        return ans;
    }
}
