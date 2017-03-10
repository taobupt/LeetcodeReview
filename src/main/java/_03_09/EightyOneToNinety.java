package _03_09;

import common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by tao on 3/9/17.
 */
public class EightyOneToNinety {


    //81 search in a rotated array II
    public boolean searchII(int[] nums, int target) {
        int n=nums.length,begin=0,end=n-1;
        if(n==0)
            return false;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]==target)
                return true;
            if(nums[mid]>nums[end]){
                if(nums[mid]>target && target>=nums[begin])
                    end=mid;
                else
                    begin=mid+1;
            }else if(nums[mid]<nums[end]){
                if(nums[mid]<target && target<=nums[end])
                    begin=mid+1;
                else
                    end=mid;
            }else
                end--;
        }
        return nums[begin]==target;
    }

    //82 82. Remove Duplicates from Sorted List II
    //Given a sorted linked list, delete all nodes that have duplicate numbers,
    // leaving only distinct numbers from the original list.
    public ListNode deleteDuplicatesII(ListNode head) {
        //刚开始可以用hashmap存起来，然后在map里的都删除，但这样显得没啥技术含量
        //这个版本感觉写的不简洁啊
        if(head==null||head.next==null)
            return head;
        ListNode first=new ListNode(Integer.MIN_VALUE);
        ListNode p=first;
        ListNode q=head;
        ListNode qq=head.next;
        while(qq!=null){
            if(qq.val==q.val){
                while(qq!=null && qq.next!=null && qq.val==qq.next.val)
                    qq=qq.next;
                p.next=qq.next;
                q=qq!=null?qq.next:null;
                qq=q!=null?q.next:null;
            }else{
                p.next=q;
                p=p.next;
                q=qq;
                qq=qq.next;
            }
        }
        return first.next;

    }


    //83 Remove Duplicates from Sorted List
    //没啥难度，和数组的那道其实差不多
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode first=new ListNode(0);
        first.next=head;
        ListNode q=head.next;
        while(q!=null){
            if(head.val==q.val)
                head.next=q.next;
            else
                head=head.next;
            q=q.next;
        }
        return first.next;
    }

    //84 largest rectangle in historgram
    //都可以理解为单调栈的问题
    public int largestRectangleArea(int[] heights) {
        Stack<Integer>stk=new Stack<>();
        int n=heights.length;
        int largest=0;
        for(int i=0;i<n;++i){
            while(!stk.isEmpty() && heights[i]<heights[stk.peek()]){
                int h=heights[stk.pop()];
                int width=i-1-(stk.isEmpty()?-1:stk.peek());
                largest=Math.max(largest,h*width);
            }
            stk.push(i);
        }
        //其实最后加一个哨兵是最好的，就省掉了以下的code
        while(!stk.isEmpty()){
            int h=heights[stk.pop()];
            int width=n-1-(stk.isEmpty()?-1:stk.peek());
            largest=Math.max(largest,h*width);
        }
        return largest;
    }


    //85. Maximal Rectangle

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return 0;
        int m=matrix.length,n=matrix[0].length;
        int [][]dp=new int[m][n];
        int largest=0;
        for(int j=0;j<n;++j){
            dp[0][j]=matrix[0][j]=='0'?0:1;
        }
        largest=Math.max(largest,largestRectangleArea(dp[0]));
        for(int i=1;i<m;++i){
            for(int j=0;j<n;++j){
                dp[i][j]=matrix[i][j]=='0'?0:dp[i-1][j]+1;
            }
            largest=Math.max(largest,largestRectangleArea(dp[i]));
        }
        return largest;

    }
    //86 partition list
    /*
    Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

    You should preserve the original relative order of the nodes in each of the two partitions.
     */
    public ListNode partition(ListNode head, int x) {
        //naive way was two list
        if(head==null||head.next==null)
            return head;
        ListNode small=new ListNode(0);
        ListNode large=new ListNode(0);
        ListNode psmall=small;
        ListNode plarge=large;
        while(head!=null){
            if(head.val>=x){
                plarge.next=head;
                plarge=plarge.next;
            }else{
                psmall.next=head;
                psmall=psmall.next;
            }
            head=head.next;
        }
        //这句是砍掉藕断丝连
        plarge.next=null;
        psmall.next=large.next;
        return small.next;
    }


    //87 Scramble String
    //dp and recursive
    //TLE,要加cnt来判断，感觉这个能极大的加速，不然就得跪了
    //当然还有dp，感觉dp是个好东西，你试试能不能做出来
    public boolean isScramble(String s1, String s2) {
        if(s1.isEmpty() ||s2.isEmpty())
            return false;
        if(s1.equals(s2))
            return true;
        int m=s1.length();
        int []cnt=new int[26];
        for(int i=0;i<m;++i){
            cnt[s1.charAt(i)-'a']++;
            cnt[s2.charAt(i)-'a']--;
        }
        for(int i=0;i<m;++i){
            if(cnt[s2.charAt(i)-'a']!=0)
                return false;
        }
        for(int i=1;i<m;++i){
            boolean res=isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))||isScramble(s1.substring(0,i),s2.substring(m-i,m)) && isScramble(s1.substring(i),s2.substring(0,m-i));
            if(res)
                return true;
        }
        return false;
    }


    //dp way
    public boolean isScrambledp(String s1, String s2){
        return true;//好像是有点难搞
    }



    //88 merge sorted array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1 has enough space
        //大小方向还是要注意的，注意这是逆着来的，所以是挑大的
        int i=m-1,j=n-1,index=m+n-1;
        while(i>=0 && j>=0){
            if(nums1[i]>nums2[j])
                nums1[index--]=nums1[i--];
            else
                nums1[index--]=nums2[j--];
        }

        while(j>=0){
            nums1[index--]=nums2[j--];
        }
    }


    //89 gray code
    //一种是利用数学公式
    //一种是观察规律
    public List<Integer> grayCode(int n) {
        List<Integer>res=new ArrayList<>();
        res.add(0);
        for(int i=0;i<n;++i){
            int m=res.size();
            for(int j=m-1;j>=0;--j){
                res.add(res.get(j)|(1<<i));
            }
        }
        return res;
    }

    //90 subsets II
    //两种方法。一种回溯，一种迭代
    public void dfs(int[]nums,List<List<Integer>>res,List<Integer>path,int pos){
        res.add(new ArrayList<>(path));
        for(int i=pos;i<nums.length;++i){
            path.add(nums[i]);
            dfs(nums,res,path,i+1);
            while(i<nums.length-1 && nums[i]==nums[i+1])
                i++;
            path.remove(path.size()-1);
        }
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer>path=new ArrayList<>();
        List<List<Integer>>res=new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,res,path,0);
        return res;
    }


    //another way
    public List<List<Integer>>subsetsWithDupIterative(int[]nums){
        Arrays.sort(nums);
        List<List<Integer>>res=new ArrayList<>();
        res.add(new ArrayList<>());
        int n=nums.length,m=res.size();
        for(int i=0;i<n;++i){
            int start=(i>0 && nums[i]==nums[i-1])?0:m;
            m=res.size();
            for(int j=start;j<m;++j){
                List<Integer>tmp=new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }
}
