package _03_28;

import common.ListNode;
import common.TreeNode;

import java.util.*;

/**
 * Created by tao on 3/28/17.
 */

public class TwoHundredThirtySixToFortyFive {

    //236 Lowest Common Ancestor of a Binary T
    //recursive way. there is iterative way
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||q==root)
            return root;
        TreeNode l=lowestCommonAncestor(root.left,p,q);
        TreeNode r=lowestCommonAncestor(root.right,p,q);
        if(l!=null && r!=null)
            return root;
        else
            return l==null?r:l;
    }

    //there is iterative way
    //use stacks


    //237 delete node in a linked list
    public void deleteNode(ListNode node) {
        //int tmp=node.val;
        node.val=node.next.val;
        //node.next.val=tmp;
        node.next=node.next.next;
    }

    //238 product of array except itself;
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int []res=new int[n];//1 a0 a0a1 a0a1a2
        int []after=new int[n];//a1a2a3  a2a3 a3 1
        res[0]=after[n-1]=1;
        for(int i=1;i<n;++i){
            res[i]=res[i-1]*nums[i-1];
            after[n-1-i]=after[n-i]*nums[n-i];
        }
        for(int i=0;i<n;++i)
            res[i]*=after[i];
        return  res;
    }

    //const space
    public int[] productExceptSelfSaveSpace(int[] nums) {
        int n=nums.length;
        int []res=new int[n];//1 a0 a0a1 a0a1a2
        for(int i=1;i<n;++i){
            res[i]=res[i-1]*nums[i-1];
        }
        int right=1;
        for(int i=n-1;i>=0;--i){
            res[i]*=right;
            right*=nums[i];
        }
        return  res;
    }

    //239 sliding window maximum
    //双端队列 o(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        //这种window的还是存index比较好，这样也能及时出window
        int n=nums.length,index=0;
        int []res=new int[n-k+1];
        if(n==0||k==0||n<k)
            return new int[]{};
        Deque<Integer>q=new LinkedList<>();
        for(int i=0;i<n;++i){
            while(!q.isEmpty() && nums[q.peekLast()]<nums[i]){
                q.pollLast();
            }
            q.offerLast(i);
            if(i>=k-1)
                res[index++]=nums[q.peekFirst()];
            if(!q.isEmpty() && q.peekFirst()<=i-k+1)//这里有点奇怪。，仔细分析一下就可以了
                q.pollFirst();
        }
        return res;
    }
   //Nlog(N) segment tree
    //design maximum queue, get max in o(1) time , and 

    //240 search a 2D matrix II
    //暴力就是m*n
    //这是m+n
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)
            return false;
        int m=matrix.length,n=matrix[0].length;
        int i=0,j=n-1;
        while(i<m && j>=0){
            if(matrix[i][j]==target)
                return true;
            else if(matrix[i][j]>target)
                i--;
            else
                j++;
        }
        return false;
    }
    //当然可以divide and conquer,四块，每次能取消一块 n^log(3)
    //也可以是对每一行做binary search

    //241 Different Ways to Add Parentheses 非常有趣，其实catlan 数是一样的
    public List<Integer> diffWaysToCompute(String input) {
        int n=input.length();
        List<Integer>res=new ArrayList<>();
        if(n==0)
            return res;
        for(int i=0;i<n;++i){
            char c=input.charAt(i);
            if(c=='+'||c=='-'||c=='*'){
                List<Integer>left=diffWaysToCompute(input.substring(0,i));
                List<Integer>right=diffWaysToCompute(input.substring(i+1));
                for(int m=0;m<left.size();++m){
                    for(int k=0;k<right.size();++k){
                        if(c=='+')
                            res.add(left.get(m)+right.get(k));
                        else if(c=='-')
                            res.add(left.get(m)-right.get(k));
                        else
                            res.add(left.get(m)*right.get(k));
                    }
                }
            }
        }
        if(res.isEmpty())//这一句至关重要
            res.add(Integer.valueOf(input));
        return res;
    }

    //memory based;
    /*
    List<Integer> l1 = map.getOrDefault(p1, diffWaysToCompute(p1));
    List<Integer> l2 = map.getOrDefault(p2, diffWaysToCompute(p2));
     */

    //242
    public boolean isAnagram(String s, String t) {
        //hash map 搞定
        int m=s.length(),n=t.length();
        if(m!=n)
            return false;
        int []cnt=new int[128];
        for(int i=0;i<n;++i){
            cnt[s.charAt(i)]++;
            cnt[t.charAt(i)]--;
        }
        for(int i=0;i<128;++i)
            if(cnt[i]!=0)
                return false;
        return true;
    }

    //sort is ok first convert it to chararray
    public boolean isAnagramSort(String s, String t) {
        //hash map 搞定
        int m=s.length(),n=t.length();
        if(m!=n)
            return false;
        char[]ss=s.toCharArray();
        char[]tt=t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        return String.valueOf(ss).equals(String.valueOf(tt));
    }

    //243 shortest word distance
    //the naive way is to calculate all index with two arrays

    public int shortestDistance(String[] words, String word1, String word2) {
        int index1=-1,index2=-1;
        int minDist=Integer.MAX_VALUE;
        int n=words.length;
        for(int i=0;i<n;++i){
            if(words[i].equals(word1))
                index1=i;
            else if(words[i].equals(word2))
                index2=i;
            if(index1!=-1 && index2!=-1)
                minDist=Math.min(minDist,Math.abs(index2-index1));
        }
        return minDist;
    }

    //244 short word distance II
    //in design

    //245 short word distance III
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int minValue=Integer.MAX_VALUE;
        int index1=-1,index2=-1,n=words.length;
        for(int i=0;i<n;++i){
            if(words[i].equals(word1)||words[i].equals(word2)){
                if(word1.equals(word2)){
                    if(index1==-1){
                        index1=i;
                        continue;
                    }else{
                        minValue=Math.min(minValue,i-index1);
                        index1=i;
                    }
                }else{
                    if(words[i].equals(word1))
                        index1=i;
                    else if(words[i].equals(word2))
                        index2=i;
                    if(index1!=-1 && index2!=-1)
                        minValue=Math.min(minValue,Math.abs(index1-index2));
                }
            }
        }
        return minValue;
    }

    /*
    for(int i=0;i<n;++i){
            if(words[i].equals(word1)||words[i].equals(word2)){
                if(index!=-1 &&(!words[index].equals(words[i])||word1.equals(word2))){
                    minDistance=Math.min(minDistance,i-index);
                }
                index=i;
            }
        }
     */



}
