package _03_17;

import common.ListNode;
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


    public int read4(char[] buf){
        return 0;
    }

    //其实这道题是非常经典的，当c++可以直接用buf+res这样读过去，java却需要一步一步来，先copy到一个buffer，然后再到目的buffer。
    //157 read N characters
    public int read(char[] buf, int n) {
        int res=0;
        char []buffer=new char[4];
        while(res<n){
            int c=read4(buffer);
            for(int i=0;i<c;++i)
                buf[res+i]=buffer[i];
            res+=c;
            if(c!=4)
                break;
        }
        return Math.min(res,n);
    }

    //158 Read N Characters Given Read4 II - Call multiple times
    //要用变量记住上次有没有读完，读完了接着调函数读，否则就是读以前的
    private int cnt=0;
    private int curEnd=0;
    private char []buffer=new char[4];
    public int readII(char[] buf, int n) {
        int res=0;
        boolean hasNext=true;
        while(res<n && hasNext){
            //only if we there is no word in last time
            if(curEnd==0)
                cnt=read4(buffer);
            if(cnt<4)
                hasNext=false;
            for(;curEnd<cnt && res<n;++curEnd)
                buf[res++]=buffer[curEnd];
            if(curEnd==cnt)
                curEnd=0;
        }
        return Math.min(res,n);
    }



    //159 longest substring with at most two distinct characters
    //一看就知道是two window
    //得和以前的题目总结一下，好像都只是需要end-begin，而不是需要+1或者-1
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int []cnt=new int[256];
        int n=s.length();
        int res=0,count=0;
        char []ss=s.toCharArray();
        int begin=0,end=0;
        while(end<n){
            if(cnt[ss[end]]++==0)
                count++;
            end++;
            while(count==3){
                if(--cnt[ss[begin++]]==0)
                    count--;
            }
            res=Math.max(res,end-begin);//end 已经不是有效的value了
        }
        return res;
    }

    //160 intersection of two linked list
    //普通做法是算长度差，然后一边走完长度差，再同时走。
    // 真实的做法是只管走，走到尽头就换着走。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null)
            return null;
        ListNode p=headA;
        ListNode q=headB;
        while(p!=q){
            p=p.next;
            q=q.next;
            if(p==q)
                break;
            if(p==null)
                p=headB;
            if(q==null)
                q=headA;
        }
        //或者你可以换一种方式写
        //a = a == NULL ? headB : a->next;
        //b = b == NULL ? headA : b->next;
        return p;
    }
}
