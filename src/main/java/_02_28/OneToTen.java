package _02_28;

import common.ListNode;
import common.Tuple;

import java.util.*;

/**
 * Created by Tao on 2/28/2017.
 */
public class OneToTen {

    // 1 Two Sum

    //method one hashmap
    public int[] twoSum(int[] nums, int target) {
        int n=nums.length;
        Map<Integer,Integer> map=new HashMap<>();
        int []res=new int[2];
        for(int i=0;i<n;++i){
            if(map.containsKey(target-nums[i])){
                res[0]=map.get(target-nums[i]);
                res[1]=i;
                break;
            }
            map.put(nums[i],i);
        }
        return res;
    }

    //Two pointers
    public int[]twoSumWithTwoPointers(int []nums,int target){
        int n=nums.length;
        List<Tuple>li=new ArrayList<>();
        for(int i=0;i<n;++i)
            li.add(new Tuple(nums[i],i));
        //sort by value
        li.sort(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                if((int)o1.x==(int)o2.x)
                    return 0;
                else if((int)o1.x>(int)o2.x)
                    return 1;
                else
                    return -1;
            }
        });
        int begin=0,end=n-1;
        int []res=new int[2];
        while(begin<end){
            int val1=(int)li.get(begin).x;
            int val2=(int)li.get(end).x;
            if(val1+val2==target){
                res[0]=(int)li.get(begin).y;
                res[1]=(int)li.get(end).y;
                break;
            }else if(val1+val2>target)
                end--;
            else
                begin++;
        }
        return res;
    }

    //also you can copy the array to an new array and use two pointers to find two number and linear search






    //2 add two numbers
    //as for the linkedlist & tree, there are always two ways, iterative way and recursive
    // no need to add more ways, just one way
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode p=head;
        int carray=0;
        while(l1!=null||l2!=null||carray!=0){
            carray+=(l1!=null?l1.val:0)+(l2!=null?l2.val:0);
            p.next=new ListNode(carray%10);
            carray/=10;
            l1=l1!=null?l1.next:null;
            l2=l2!=null?l2.next:null;
            p=p.next;
        }
        return head.next;
    }

    //recursive way
    //also you can add carry into l1.val
    //something like that next=l1.next
    //if(next!=null)
    //next.val+=c;
    //else if(c>0)
    //next=new ListNode(c);
    //but my way is concise
    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2,int carry){
        if(l1==null && l2==null && carry==0)
            return null;
        carry+=(l1!=null?l1.val:0)+(l2!=null?l2.val:0);
        ListNode head=new ListNode(carry%10);
        head.next=addTwoNumbersRecursive(l1!=null?l1.next:null,l2!=null?l2.next:null,carry/10);
        return head;
    }

    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2){
        return addTwoNumbersRecursive(l1,l2,0);
    }

    //there  is a related question, just represent number in a reverse order
    //the naive way was to reverse first and do the same as above
    //or you can use two stack to store the value first
    //or you can get the two list length, and solve by recursive
    //应该好好想想为啥不能把carry加进去，上次可以加入carry是因为进位本来就是从前到后的，现在是从后往前的，所以要万分小心啊。
    public int getLength(ListNode l1){
        int count=0;
        while(l1!=null){
            count++;
            l1=l1.next;
        }
        return count;
    }
    public ListNode recursive(ListNode l1,ListNode l2,int offset){
        if(l1==null)
            return null;
        ListNode head=offset==0?new ListNode(l1.val+l2.val):new ListNode(l1.val);
        head.next=offset==0?recursive(l1.next,l2.next,0):recursive(l1.next,l2,offset-1);
        if(head.next!=null && head.next.val>9){
            head.val+=1;
            head.next.val=head.next.val%10;
        }
        return head;
    }


    public ListNode addTwoNumberII(ListNode l1,ListNode l2){
        int size1=getLength(l1);
        int size2=getLength(l2);
        ListNode head=new ListNode(1);
        head.next=size1>=size2?recursive(l1,l2,size1-size2):recursive(l2,l1,size2-size1);
        if(head.next!=null && head.next.val>9){
            head.next.val=head.next.val%10;
            return head;
        }
        return head.next;
    }


    //3. Longest Substring Without Repeating Characters

    //use hashtable two pointers
    //耻辱
//    public int lengthOfLongestSubstring(String s) {
//        char []arrs=s.toCharArray();
//        int n=s.length(),maxLength=0,start=0;
//        Map<Character,Integer>map=new HashMap<>();
//        for(int i=0;i<n;++i){
//            if(map.containsKey(arrs[i])){
//                if(start>map.get(arrs[i]))
//                    maxLength=Math.max(maxLength,i-start+1);
//                else
//                    maxLength=Math.max(maxLength,i-start);
//                start=Math.max(start+1,map.get(arrs[i])+1);
//            }
//            map.put(arrs[i],i);
//        }
//
//        return Math.max(maxLength,n-start);
//    }

    //hashtable + two pointers
    public int lengthOfLongestSubstring(String s){
        int start=-1,n=s.length(),maxLength=0;
        char []arrs=s.toCharArray();
        Map<Character,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i){
            if(map.containsKey(arrs[i]))
                start=Math.max(start,map.get(arrs[i]));
            map.put(arrs[i],i);
            maxLength=Math.max(maxLength,i-start);
        }
        return maxLength;

    }

    //window: this is most efficient algo I have ever seen


    //by zjh
//    int findSubstring(string s){
//        vector<int> map(128,0);
//        int counter; // check whether the substring is valid
//        int begin=0, end=0; //two pointers, one point to tail and one  head
//        int d; //the length of substring
//
//        for() { /* initialize the hash map here */ }
//
//        while(end<s.size()){
//
//            if(map[s[end++]]-- ?){  /* modify counter here */ }
//
//            while(/* counter condition */){
//
//                 /* update d here if finding minimum*/
//
//                //increase begin to make it invalid/valid again
//
//                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
//            }
//
//            /* update d here if finding maximum*/
//        }
//        return d;
//    }

    //One thing needs to be mentioned is that when asked to find maximum substring,
    // we should update maximum after the inner while loop to guarantee that the substring is valid.
    // On the other hand, when asked to find minimum substring, we should update minimum inside the inner while loop.

    public int lengthOfLongestSubstringWindow(String s){
        int []cnt=new int[128];
        int n=s.length();
        int begin=0,end=0,maxLength=0,count=0;
        while(end<n){
            if(++cnt[s.charAt(end++)]>1)
                count++;
            while(count>0){
                if(--cnt[begin++]==1)
                    count--;
            }
            maxLength=Math.max(end-begin,maxLength);
        }
        return maxLength;
    }



    //4 median of two sorted arrays
    //you can merge and find in O(1)t ime;
    //this is o(n+m) time
    //LTE
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        int []res=new int[m+n];
        int i=0,j=0,index=0;
        while(i<m && j<n){
            if(nums1[i]<nums2[j])
                res[index++]=nums1[i++];
            else
                res[index++]=nums2[j++];
        }

        return res.length%2!=0?1.0*res[res.length/2]:(res[res.length/2-1]+res[res.length/2])/2.0;
    }

    //O(log(M+n)

    //k is length
    public int findMedian(int[]nums1,int start1,int[]nums2,int start2,int k){
        if(nums1.length-start1>nums2.length-start2)
            return findMedian(nums2,start2,nums1,start1,k);
        if(nums1.length==start1)
            return nums2[start2+k-1];//in case of index boundary overflow
        if(k==1)
            return Math.min(nums1[start1],nums2[start2]);//recursive break condition
        int pa=Math.min(k/2,nums1.length-start1);
        int pb=k-pa;
        if(nums1[start1+pa-1]<nums2[start2+pb-1])
            return findMedian(nums1,start1+pa,nums2,start2,k-pa);
        else if(nums1[start1+pa-1]>nums2[start2+pb-1])
            return findMedian(nums1,start1,nums2,start2+pb,k-pb);
        else
            return nums1[start1+pa-1];
    }

    public double findMedianSortedArraysBetterWays(int[]nums1,int[]nums2){
        int m=nums1.length,n=nums2.length;

        int median1= findMedian(nums1,0,nums2,0,(m+n)/2+1);
        if((m+n)%2==0)
            return median1*1.0;
        else return (median1+findMedian(nums1,0,nums2,0,(m+n)/2));
    }



    //5 longest palindromic substring
    //dp 居然过了，意料之中，毕竟才是1000，O(n^2)妥妥过
    public String longestPalindrome(String s) {
        if(s.isEmpty())
            return s;
        char[]arrs=s.toCharArray();
        int n=arrs.length;
        boolean [][]dp=new boolean[n][n];
        int start=0,end=0,maxLength=1;
        for(int i=0;i<n;++i){
            for(int j=0;j<=i;++j){
                if(arrs[i]==arrs[j] &&(i-j<=2||dp[j+1][i-1])) {
                    dp[j][i] = true;
                    if(maxLength<(i-j+1)){
                        maxLength=i-j+1;
                        start=j;
                        end=i;
                    }
                }
            }
        }
        return s.substring(start,end+1);
    }

    //O(n^2)
    public String longestPalindromeExtend(String s){
        //you should use global variable, but, you can use array here to
        int []res=new int[2];//res[0] is the start index, res[1] is the maxLength;
        int len=s.length();
        if(len<2)
            return s;
        char[]arrs=s.toCharArray();
        for(int i=0;i<len-1;++i){
            extendPalindrome(arrs,i,i,res);//assume odd length;
            extendPalindrome(arrs,i,i+1,res);//assume even length
        }
        return s.substring(res[0],res[0]+res[1]);
    }

    public void extendPalindrome(char[]arrs, int j, int k,int []res){
        while(j>=0 && k<arrs.length && arrs[j]==arrs[k]){
            j--;
            k++;
        }
        if(res[1]<k-j-1){
            res[0]=j+1;
            res[1]=k-j-1;
        }
    }

    //actually you can use O(n)
    public String longestPalindromeManacher(String s){
        char []ss=s.toCharArray();
        int n=ss.length;
        char []news=new char[2*n+3];
        Arrays.fill(news,'#');
        news[0]='$';//in case of overflow;
        news[2*n+2]='\0';//这两个要不一样

        for(int i=0;i<n;++i)
            news[2*i+2]=ss[i];

        int []len=new int[2*n+2];
        int mx=0,id=0,max_pos=0;//mx is max position, id is the center,max_pos is the maxlength palindrome length center
        for(int i=1;i<2*n+2;++i){
            len[i]=i<mx?Math.min(mx-i,len[2*id-i]):1;
            while(news[i-len[i]]==news[i+len[i]])
                len[i]++;
            if(i+len[i]>mx){
                mx=i+len[i];
                id=i;
            }
            max_pos=len[i]>len[max_pos]?i:max_pos;
        }
        //System.out.println(max_pos);
        int length=len[max_pos]-1;
        int start=(max_pos-len[max_pos])/2;
        return s.substring(start,start+length);
    }





    //6 zigzag conversion
    public String convert(String s, int numRows) {
        StringBuilder[]strs=new StringBuilder[numRows];
        for(int j=0;j<numRows;++j)
            strs[j]=new StringBuilder();
        int n=s.length();
        int i=0;
        while(i<n){
            for(int j=0;j<numRows;++j)
                if(i<n)
                    strs[j].append(s.charAt(i++));
            for(int j=numRows-2;j>=1;--j)
                if(i<n)
                    strs[j].append(s.charAt(i++));
        }
        StringBuilder res=new StringBuilder();
        for(int j=0;j<numRows;++j)
            res.append(strs[j]);
        return res.toString();
    }

    //mathmatical ways
    public String convertBetter(String s,int numRows){
        /*n=numRows
Δ=2n-2    1                           2n-1                         4n-3
Δ=        2                     2n-2  2n                    4n-4   4n-2
Δ=        3               2n-3        2n+1              4n-5       .
Δ=        .           .               .               .            .
Δ=        .       n+2                 .           3n               .
Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
Δ=2n-2    n                           3n-2                         5n-4
*/
        StringBuilder res=new StringBuilder();
        if(numRows==1)
            return s;
        int step1=0,step2=0;// step1 is 2 to 2n-2 , 2n-2 to 2n
        int len=s.length();
        for(int i=0;i<numRows;++i){
            step1=(numRows-1-i)*2;
            step2=2*i;
            int pos=i;
            if(pos<len)
                res.append(s.charAt(pos));
            while(true){
                pos+=step1;
                if(pos>=len)
                    break;
                if(step1!=0)
                    res.append(s.charAt(pos));
                pos+=step2;
                if(pos>=len)
                    break;
                if(step2!=0)
                    res.append(s.charAt(pos));
            }
        }
        return res.toString();

    }

    //7 reverse integer
    public int reverse(int x) {
        long res=0;//-2%3=-2;
        while(x!=0){
            res=10*res+x%10;
            x/=10;
        }
        if(res>Integer.MAX_VALUE||res<Integer.MIN_VALUE)
            return 0;
        return (int)res;
    }

    //better
    //if overflow exists, the new result will not equal previous one
    public int reverseBetter(int x){
        int res=0;
        while(x!=0){
            int tail=x%10;
            int newRes=res*10+tail;
            if((newRes-tail)/10!=res)
                return 0;
            res=newRes;
            x/=10;
        }
        return res;
    }

    //8. String to Integer atoi

    public int myAtoi(String str) {
        //not like 1e7, valid number
        str=str.trim();
        char []arrs=str.toCharArray();
        int n=str.length();
        long res=0;
        int negative=1;
        int index=0;
        if(index<n && (arrs[index]=='+'||arrs[index]=='-'))
            if(arrs[index++]=='-')
                negative=-1;
        while(index<n && Character.isDigit(arrs[index])){
            res=10*res+arrs[index++]-'0';
            if(res>=2147483647)
                break;
        }
        res*=negative;
        if(res>=Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if(res<=Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int)res;
    }

    //without long

    public int myAtoiConcise(String str){
        str=str.trim();
        int n=str.length();
        char []arrs=str.toCharArray();
        int sign=1,base=0,index=0;
        if(index<n &&(arrs[index]=='+'||arrs[index]=='-'))
            sign=arrs[index++]=='+'?1:-1;
        while(index<n && Character.isDigit(arrs[index])){
            if(base>Integer.MAX_VALUE/10||(base==Integer.MAX_VALUE/10 && arrs[index]-'0'>7)){
                if(sign==1)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }
            base=10*base+(arrs[index++]-'0');
        }
        return base*sign;
    }




    //9 palindrome number
    //you can change it into string and check whether the string is a valid palindrome
    //but more efficient way is to check
    public boolean isPalindrome(int x) {
        //negative number is not

        if(x<0)
            return false;
        long res=0;
        int copy=x;
        while(x!=0){
            res=10*res+x%10;
            x/=10;
        }
        return res==copy;
    }

    //interesting way
    //faster way,just check to middle
    public boolean isPalindromeFaster(int x){
        if(x<0||(x!=0 && x%10==0))
            return false;
        int rev=0;
        while(rev<x){
            rev=10*rev+x%10;
            x/=10;
        }
        return rev==x||rev==10*x;
    }


    //10. Regular Expression Matching

    //recursive way
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        if(p.length()==1){
            return (s.length()==1)&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        }

        if(s.isEmpty())
            return (p.charAt(1)=='*') && isMatch(s,p.substring(2));
        if(p.charAt(1)=='*'){
            return isMatch(s,p.substring(2))||((s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')&&isMatch(s.substring(1),p));//0 or number of way
        }else
            return (s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')&&isMatch(s.substring(1),p.substring(1));
    }

    //dynamic programming way
    public boolean isMatchDP(String s,String p){
        if(p.isEmpty())//do not forget this sentence
            return s.isEmpty();
        int m=s.length(),n=p.length();
        boolean [][]dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=2;i<=n;++i)
            dp[0][i]=dp[0][i-2] && p.charAt(i-1)=='*';
        for(int i=1;i<=m;++i){
            dp[i][1]=(i==1) && (s.charAt(i-1)==p.charAt(0)||p.charAt(0)=='.');//this would be error if the first sentence does not exist
            for(int j=2;j<=n;++j){
                if(p.charAt(j-1)=='*')
                    dp[i][j]=dp[i][j-2]||((s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.')&&dp[i-1][j]);//this is -2 not -1
                else
                    dp[i][j]=(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.')&&dp[i-1][j-1];
            }
        }
        return dp[m][n];
    }

    //save space
    //but I can't understand; sorry;
    //you can try to contact Haifeng Jin
    public boolean isMatchDPSaveSpace(String s,String p){
        int m=s.length(),n=p.length();
        boolean []dp=new boolean[n+1];
        dp[0]=true;
        for(int i=2;i<=n;++i)
            dp[i]=dp[i-2] && p.charAt(i-1)=='*';
        boolean left_up=false;
        for(int i=0;i<m;++i){
            left_up=dp[0];
            dp[0]=false;
            for(int j=0;j<n;++j){
                boolean up=dp[j+1];
                if(p.charAt(j)!='*')
                    dp[j+1]=left_up&&(s.charAt(i)==p.charAt(j)||p.charAt(j)=='.');
                else
                    dp[j+1]=dp[j-1]||dp[j+1]&&(s.charAt(i)==p.charAt(j-1)||p.charAt(j-1)=='.');
                left_up=up;
            }
        }
        return dp[n];
    }










}
