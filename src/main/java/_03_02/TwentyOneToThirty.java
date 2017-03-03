package _03_02;

import common.ListNode;

import java.util.*;

/**
 * Created by Tao on 3/2/2017.
 */
public class TwentyOneToThirty {


    //21 merge two sorted list
    //完全没啥难度
    //iterative way
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode first=new  ListNode(0);
        ListNode p=first;
        while(l1!=null && l2!=null){
            if(l1.val>l2.val) {
                p.next = l2;
                l2=l2.next;
            }else{
                p.next=l1;
                l1=l1.next;
            }

            p=p.next;
        }

        p.next=l1!=null?l1:l2;
        return first.next;
    }

    //recursive way
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if(l1==null||l2==null)
            return l1==null?l2:l1;
        if(l1.val>l2.val){
            l2.next=mergeTwoListsRecursive(l1,l2.next);
            return l2;
        }else{
            l1.next=mergeTwoListsRecursive(l1.next,l2);
            return l1;
        }
    }

    //22 generate parenthesis
    public void generate(List<String>res,int n,int left,int right,String path){
        if(left==n && right==n){
            res.add(path);
            return ;
        }
        if(left<n)
            generate(res,n,left+1,right,path+"(");
        if(right<left)
            generate(res,n,left,right+1,path+")");
    }
    public List<String>generateParenthesis(int n){
        List<String>res=new ArrayList<>();
        generate(res,n,0,0,"");
        return res;
    }

    //stimulating the stack push and pop
    public void backtracking(List<String>res,Stack<Integer>input,Stack<Integer>stk,String path,int n){
        if(path.length()==2*n && input.isEmpty() && stk.isEmpty()){
            res.add(path);
            return;
        }
        if(!input.isEmpty()){//stk push
            int top=input.pop();
            stk.push(top);
            backtracking(res,input,stk,path+'(',n);
            stk.pop();
            input.push(top);
        }

        if(!stk.isEmpty()){ //stk output
            int top=stk.pop();
            backtracking(res,input,stk,path+')',n);
            stk.push(top);
        }
    }


    public List<String>generateParenthesisByStack(int n){
        List<String>res=new ArrayList<>();
        Stack<Integer>input=new Stack<>();//input stack
        Stack<Integer>stk=new Stack<>();//simulations;
        for(int i=1;i<=n;++i)
            input.push(i);
        backtracking(res,input,stk,"",n);
        return res;
    }

    //dp way, this is a catalan number
    //dp[i]=sum(dp[0]*[i-1]+dp[1]*dp[i-2]+......+dp[n-1]*dp[0])
    //interesting way
    public List<String>generateParenthesisByDP(int n){
        List<List<String>>lists=new ArrayList<>();
        lists.add(Collections.singletonList(""));
        for(int i=1;i<=n;++i){
            final List<String>list=new ArrayList<>();
            for(int j=0;j<i;++j){
                for(final String first:lists.get(j)){
                    for(final String second:lists.get(i-1-j)){
                        list.add("("+first+")"+second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size()-1);
    }


    //23 merge k sorted lists
    //priority queue
    //O(nlogk)
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode>pq=new PriorityQueue<>((ListNode node1,ListNode node2)->node1.val-node2.val);
        for(ListNode node:lists)
            if(node!=null)
                pq.offer(node);
        ListNode first=new ListNode(0);
        ListNode p=first;
        while(!pq.isEmpty()){
            ListNode top=pq.poll();
            p.next=top;
            if(top.next!=null)
                pq.offer(top.next);
        }
        p.next=null;
        return first.next;
    }


    //n log(k)
    //you can also use divide and conquer, something like the merge sort
    public ListNode mergeKListsDivideAndConquer(ListNode[]lists){
        return partition(lists,0,lists.length-1);
    }

    public ListNode partition(ListNode[]lists,int s,int e){
        if(s==e)
            return lists[s];
        if(s<e){
            int mid=(e-s)/2+s;
            ListNode l1=partition(lists,s,mid);
            ListNode l2=partition(lists,mid+1,e);
            return mergeTwoLists(l1,l2);
        }else
            return null;
    }


    //24 swap nodes in pairs
    //recursive way
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode res=head.next;
        head.next=swapPairs(res.next);
        res.next=head;
        return res;
    }

    //iterative way
    //这种题，换相邻节点的，要用俩指针，虽然第一次处理完了，head就往下面走了，但是谁来管接下来改造好的第二部分呢，所以应该多用一个指针来指示head的parent。
    public ListNode swapPairsIterative(ListNode  head){
        if(head==null||head.next==null)
            return head;
        ListNode first=new ListNode(0);
        ListNode p=first;
        while(head!=null && head.next!=null){
            ListNode tmp=head.next;
            head.next=tmp.next;
            tmp.next=head;
            p.next=tmp;
            p=head;
            head=head.next;
        }
        return first.next;
    }


    //reverse Nodes in k-group
    //recursive way


    //reverse list iteratively
    public ListNode reverseList(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode newHead=null;
        while(head!=null){
            ListNode next=head.next;
            head.next=newHead;
            newHead=head;
            head=next;
        }
        return newHead;
    }


    //recursive way

    public ListNode reverseListRecur(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode node=reverseListRecur(head.next);
        head.next.next=head;
        head.next=null;
        return node;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
       //recursive way would be better
        if(head==null||head.next==null||k<=1)
            return head;
        int cnt=1;//如果想p最后指向第一部分的最后一个，那就是1，否则就是0.
        ListNode p=head;
        while(p!=null && cnt<k){
            cnt++;
            p=p.next;
        }
        if(p==null)
            return head;
        else{
            ListNode node=reverseKGroup(p.next,k);
            p.next=null;
            p=reverseList(head);//可以本地进行reverse list
            head.next=node;//这里好像是pass by value
            return p;
        }

    }

    //贴一个稍微有点不一样的
//    public ListNode reverseKGroup(ListNode head, int k) {
//        ListNode curr = head;
//        int count = 0;
//        while (curr != null && count != k) { // find the k+1 node
//            curr = curr.next;
//            count++;
//        }
//        if (count == k) { // if k+1 node is found
//            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
//            // head - head-pointer to direct part,
//            // curr - head-pointer to reversed part;
//            while (count-- > 0) { // reverse current k-group:
//                ListNode tmp = head.next; // tmp - next head in direct part
//                head.next = curr; // preappending "direct" head to the reversed list
//                curr = head; // move head of reversed part to a new node
//                head = tmp; // move "direct" head to the next node in direct part
//            }
//            head = curr;
//        }
//        return head;
//    }



    //iterative way
    //非常高效，应该和swap pairs联合在一块然后总结一下
    public ListNode reverseKGroupIterative(ListNode head,int k){
        if(head==null||k==1)
            return head;
        int num=0;
        ListNode preHead=new ListNode(-1);
        preHead.next=head;
        ListNode p=preHead;
        ListNode pre=null;
        ListNode cur=head;
        while(cur!=null){
            num++;
            cur=cur.next;
        }
        cur=head;
        while(num>=k){

            //reverse;
            for(int i=1;i<=k;++i){
                ListNode tmp=cur.next;
                cur.next=pre;
                pre=cur;
                cur=tmp;
            }
            p.next=pre;
            head.next=cur;
            p=head;
            head=head.next;
            num-=k;
        }
        return preHead.next;
    }


    //26 remove duplicates from sorted array
    //two pointers
    public int removeDuplicates(int[] nums) {
        int n=nums.length;
        if(n==0)
            return 0;
        int i=1,j=1;
        while(i<n){
            if(nums[i]!=nums[i-1])
                nums[j++]=nums[i];
            i++;
        }
        return j;
    }


    //calculate the duplicate
    public int removeDuplicatesByCount(int[]nums){
        int n=nums.length;
        int count=0;
        for(int i=1;i<n;++i){
            if(nums[i]==nums[i-1])
                count++;
            else
                nums[i-count]=nums[i];
        }
        return n-count;
    }


    //27 remove elements
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        int i=0,j=0;
        while(i<n){
            if(nums[i]!=val)
                nums[j++]=nums[i];
            i++;
        }
        return j;
    }

    //by count
    public int removeElementByCount(int[]nums,int val){
        int count=0,n=nums.length;
        for(int i=0;i<n;++i){
            if(nums[i]==val)
                count++;
            else
                nums[i-count]=nums[i];
        }
        return n-count;
    }

    //28 implement strstr()
    public int strStr(String haystack, String needle) {
       // this is not allowed return haystack.indexOf(needle);
        char[]haystacks=haystack.toCharArray();
        char []needles=needle.toCharArray();
        int m=haystack.length(),n=needle.length();
        if(m<n)
            return -1;
        for(int i=0;i<m-n+1;++i){
            if(n==0||haystacks[i]==needles[0]){
                int j=0;
                for(;j<n;++j){
                    if(haystacks[i+j]!=needles[j])
                        break;
                }
                if(j==n)
                    return i;
            }
        }
        return -1;
    }

    //kmp algorithms
    //大材小用，不过kmp属于必须掌握的算法

    //29 divide two integers
    //其实就是分解
    //O(( log N)^2)
    public int divide(int dividend, int divisor) {
        //in case of overflow. convert them into long
        if(dividend==0)
            return 0;
        if(divisor==1)
            return dividend;
        boolean negative=(dividend<0)^(divisor<0);
        long divid=dividend>=0?(long)dividend:-(long)dividend;
        long divsi=divisor>=0?(long)divisor:-(long)divisor;
        long res=0;
        while(divid>=divsi){
            long tmp=divsi;
            int cnt=1;
            while(divid>=tmp){
                divid-=tmp;
                res+=cnt;
                tmp<<=1;
                cnt<<=1;
            }
        }
        res=negative?-res:res;
        if(res>Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int)res;
    }


    //30 substring with concatenation of all words
    //window
    //faster than I have imaged;
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer>res=new ArrayList<>();
        int n=s.length(),cnt=words.length;
        if(cnt==0||n==0)
            return res;

        //init word occurence
        Map<String,Integer>map=new HashMap<>();
        for(String str:words){
            if(map.containsKey(str))
                map.put(str,map.get(str)+1);
            else
                map.put(str,1);
        }

        //traverse all sub string combinations
        int wl=words[0].length();
        for(int i=0;i<wl;++i){
            int left=i,count=0;
            Map<String,Integer>dict=new HashMap<>();
            for(int j=i;j<=n-wl;j+=wl){
                String str=s.substring(j,wl+j);

                //a valid word,accumulate results;
                if(map.containsKey(str)){
                    if(dict.containsKey(str))
                        dict.put(str,dict.get(str)+1);
                    else
                        dict.put(str,1);
                    if(dict.get(str)<=map.get(str))
                        count++;
                    else{
                        //a more word, advance the window left side
                        while(dict.get(str)>map.get(str)){
                            String str1=s.substring(left,wl+left);
                            if(dict.containsKey(str1)){
                                dict.put(str1,dict.get(str1)-1);
                                if(dict.get(str1)<map.get(str1))
                                    count--;
                            }
                            left+=wl;

                        }
                    }

                    //come to a result
                    if(count==cnt){
                        res.add(left);
                        String tmp=s.substring(left,left+wl);
                        dict.put(tmp,dict.get(tmp)-1);
                        count--;
                        left+=wl;
                    }
                }else{
                    count=0;
                    left=j+wl;
                    dict.clear();
                }
            }
        }

        return res;
    }

    //brute force
    //居然也能过，醉了，醉了。
    boolean check(String s,int index,Map<String,Integer>map1,int d,int n){
        int num=d;
        Map<String,Integer>map=new HashMap<>(map1);//记住，集合类的最好要重新new一个出来，否则就是 pass by reference
        while(num-->0){
            String tmp=s.substring(index,index+n);
            index+=n;
            if(!map.containsKey(tmp))
                return  false;
            else if(map.containsKey(tmp) && map.get(tmp)>0){
                map.put(tmp,map.get(tmp)-1);
                --d;
            }else
                return false;
        }
        return d==0;
    }
    public List<Integer> findSubstringBruteForce(String s, String[] words) {
        List<Integer>res=new ArrayList<>();
        if(words.length==0)
            return res;
        int m=s.length(),n=words[0].length(),d=words.length;
        Map<String,Integer>map=new HashMap<>();
        for(String str:words){
            if(map.containsKey(str))
                map.put(str,map.get(str)+1);
            else
                map.put(str,1);
        }
        for(int i=0;i<=m-n*d;++i){
            if(check(s,i,map,d,n)){
                res.add(i);
            }
        }
        return res;
    }







}
