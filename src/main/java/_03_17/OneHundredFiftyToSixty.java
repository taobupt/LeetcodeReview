package _03_17;

import common.TreeNode;

import java.util.Arrays;

/**
 * Created by tao on 3/16/17.
 */
public class OneHundredFiftyToSixty {

    //151 reverse words in a string
    public String reverseWords(String s) {
        String []strings=s.split("\\s+");
        StringBuilder sb=new StringBuilder();
        int n=strings.length;
        for(int i=n-1;i>=0;--i){
            if(!strings[i].isEmpty())
                sb.append(sb.length()==0?strings[i]:" "+strings[i]);
        }
        return sb.toString();
    }

    //不用split,just reverse every word and then reverse the whole string
    public void reverse(char[]a,int begin,int end){
        while(begin<end){
            char tmp=a[begin];
            a[begin++]=a[end];
            a[end--]=tmp;
        }
    }
    public String reverseWordsWithoutSplit(String s){
        char []ss=s.toCharArray();
        int n=ss.length;
        reverse(ss,0,n-1);
        int i=0;
        while(i<n){
            while(i<n && ss[i]==' ')
                i++;
            int begin=i;
            while(i<n && ss[i]!=' ')
                i++;
            reverse(ss,begin,i-1);
        }

        //clean up the space, 其实就像数组中去掉重复的数;
        int j=0;
        i=0;
        while(i<n){
            while(i<n && ss[i]==' ')
                i++;
            while(i<n && ss[i]!=' ')
                ss[j++]=ss[i++];
            if(i<n &&j<n)//这里记得加上i<n,不然没单词，你也得继续加上空格，坏了规矩
                ss[j++]=' ';//add only one space;
        }

        //delete the last space
        if(j-1>=0 && ss[j-1]==' ')//注意j的取值，不然很容易跪。
            j--;
        return String.valueOf(ss).substring(0,j);
    }


    //152 maximum product array
    public int maxProduct(int[] nums) {
        //two array
        int n=nums.length;
        int []maxi=new int[n+1];
        int []mini=new int[n+1];
        maxi[0]=mini[0]=1;
        int res=Integer.MIN_VALUE;
        for(int i=1;i<=n;++i){
            maxi[i]=Math.max(Math.max(maxi[i-1]*nums[i-1],nums[i-1]),mini[i-1]*nums[i-1]);
            mini[i]=Math.min(Math.min(mini[i-1]*nums[i-1],nums[i-1]),maxi[i-1]*nums[i-1]);
            res=Math.max(res,maxi[i]);//这里一个陷阱就是，maxi[n]其实不是最大的
        }
        return res;
    }

    //save space
    public int maxProductSaveSpace(int[]nums){
        int n=nums.length;
        int maxi=1,mini=1,res=Integer.MIN_VALUE;
        for(int i=1;i<=n;++i){
            int savemax=maxi;
            maxi=Math.max(Math.max(maxi*nums[i-1],nums[i-1]),mini*nums[i-1]);
            mini=Math.min(Math.min(mini*nums[i-1],nums[i-1]),savemax*nums[i-1]);
            res=Math.max(res,maxi);
        }
        return res;
    }

    //153 find minimum in the rotated array
    public int findMin(int[] nums) {
        int n=nums.length;
        int begin=0,end=n-1;
        while(begin<end){
            if(nums[begin]<nums[end])
                return nums[begin];
            int mid=(end-begin)/2+begin;
            if(nums[mid]>nums[end])//有何陷阱，就是要和end比，不是和begin比。能进入到这里，说明中间有个拐点，否则，就在上一步就出去了，除非是>=begin,和之前那道题很像
                begin=mid+1;
            else
                end=mid;
        }
        return nums[begin];
    }

    //154 水题
    //要是问你找最大值，咋办，那就是找到最小值然后取左边的值即可。
    //155 has been solved in the design package

    //156 binary tree upside down
    //其实就是倒着看呗
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root==null||root.left==null && root.right==null)
            return root;
        TreeNode left=upsideDownBinaryTree(root.left);
        if(root.left!=null){
            root.left.left=root.right;
            root.left.right=root;
            root.left=null;
            root.right=null;
        }
        return left;
    }

    //iterative way
    public TreeNode upsideDownBinaryTreeIterative(TreeNode root){
        TreeNode curr=root;
        TreeNode right=null;//right child
        TreeNode prev=null;
        TreeNode next=null;
        while(curr!=null){
            next=curr.left;
            curr.left=right;
            right=curr.right;
            curr.right=prev;
            prev=curr;
            curr=next;
        }
        return prev;

    }
}
