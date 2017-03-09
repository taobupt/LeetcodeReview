package _03_09;

import common.ListNode;

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
        psmall.next=null;//这两句是砍掉藕断丝连
        plarge.next=null;
        psmall=small;
        while(psmall.next!=null)
            psmall=psmall.next;
        psmall.next=large.next;
        return small.next;
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
}
