package _04_20;

import common.Interval;
import common.ListNode;
import common.TreeNode;

import java.util.*;

/**
 * Created by tao on 5/23/17.
 */
public class FourHundredThirtySixToFourHundredFortyFive {


    //436 find the right interval
    //看起来有点像单调站，就是next great element
    //晚上和next great element 对比一下应该就能发现差异了
    public int binarySearch(int end,Interval[]index){
        int n=index.length;
        if(end>index[n-1].start||end<index[0].start)
            return -1;
        int begin=0,endn=n;
        while(begin<endn){
            int mid=(endn-begin)/2+begin;
            if(index[mid].start>=end){
                endn=mid;
            }else
                begin=mid+1;
        }
        return index[begin].start>=end?index[begin].end:-1;
    }
    public int[] findRightInterval(Interval[] intervals) {
        int n=intervals.length;
        int []res=new int[n];
        Arrays.fill(res,-1);
        if(n<=1)
            return res;
        Interval []index=new Interval[n];
        for(int i=0;i<n;++i){
            index[i]=new Interval(intervals[i].start,i);
        }
        Arrays.sort(index, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start!=o2.start)
                    return o1.start-o2.start;
                else
                    return o1.end-o2.end;
            }
        });
        for(int i=0;i<n;++i){
            res[i]=binarySearch(intervals[i].end,index);
        }
        return res;
    }

    //437 path sum III
    //两次dfs


    public int dfs1(TreeNode root,int sum){
        if(root==null)
            return 0;
        int ans=0;
        if(root.val==sum)
            ans++;
        ans+=dfs1(root.left,sum-root.val)+dfs1(root.right,sum-root.val);
        return ans;
    }

    //枚举所有的起点，然后做dfs
    public int dfs(TreeNode root,int sum){
        if(root==null)
            return 0;
        return dfs1(root,sum)+dfs(root.left,sum)+dfs(root.right,sum);
    }
    public int pathSum(TreeNode root, int sum) {
        return dfs(root,sum);
    }


    //438 find all anagrams in string
    //效率很低，超级低
    public boolean check(String ss, String p){
        int []cnt=new int[26];
        char []sss=ss.toCharArray();
        char []pp=p.toCharArray();
        int n=p.length();
        for(int i=0;i<n;++i){
            cnt[sss[i]-'a']++;
            cnt[pp[i]-'a']--;
        }
        for(int i=0;i<26;++i)
            if(cnt[i]!=0)
                return false;
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        //一遍一遍扫吧
        int m=s.length(),n=p.length();
        if(m<n)
            return new ArrayList<>();
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<=m-n;++i){
            if(check(s.substring(i,i+n),p))
                res.add(i);
        }
        return res;
    }

    //two window;
    //tag two window
    //非常精妙
    public List<Integer> findAnagramsSaveTime(String s, String p){
        List<Integer>res=new ArrayList<>();
        if(s.isEmpty())
            return res;
        int []cnt=new int[26];
        char []pp=p.toCharArray();
        char []ss=s.toCharArray();
        for(char c:pp)
            cnt[c-'a']++;
        int start=0,end=0,n=s.length(),m=pp.length;
        int count=p.length();
        while(end<n){
            if(cnt[ss[end++]-'a']-- >0)
                count--;
            if(count==0)
                res.add(start);
            if(end-start==m && ++cnt[ss[start++]-'a']>0)
                count++;
        }
        return res;
    }



    //439 ternary expression parser
    //不用递归了
    //关键是查找到问号
    //递归是栈溢出了。还是用stack吧,知道数据范围你还这么搞，真是作死
    public String parseTernary(String expression) {
        if(expression.length()==1)
            return expression;
        if(expression.length()==5){
            return ""+(expression.charAt(0)=='T'?expression.charAt(2):expression.charAt(4));
        }
        int index = expression.lastIndexOf('?');
        if(expression.charAt(index-1)=='T'){
            return parseTernary(expression.substring(0,index-1)+expression.charAt(index+1)+expression.substring(index+4));
        }else
            return parseTernary(expression.substring(0,index-1)+expression.charAt(index+3)+expression.substring(index+4));
    }

    //
    public String parseTernaryByStack(String expression) {
        Stack<Character>stk=new Stack<>();
        char []ss=expression.toCharArray();
        int n=ss.length,i=n-1;
        while(i>=0){
            if(ss[i]==':'){
                i--;
                continue;
            }
            if(ss[i]!='?'){
                stk.push(ss[i--]);
            }else{
                char a=stk.pop();
                char b=stk.pop();
                stk.push(ss[i-1]=='T'?a:b);
                i-=2;
            }
        }
        return ""+stk.peek();
    }

    //440 K-th Smallest in Lexicographical Order   挺难的
    //等晚上在做吧

    //441 arranging coins

    public int binarySearch(long[]dp,int n){
        int begin=0,end=dp.length;
        while(begin<end){
            int mid =(end-begin)/2+begin;
            if((int)dp[mid]==n)
                return mid;
            else if(dp[mid]>n)
                end=mid;
            else
                begin=mid+1;
        }
        return dp[begin]==n?begin:begin-1;
    }
    public int arrangeCoins(int n) {
        long []dp=new long[65537];
        for(int i=1;i<65537;++i)
            dp[i]+=dp[i-1]+i;
        return binarySearch(dp,n);
    }

    //即使是这样，binary search 也是很低的
    //能线性的就不要nlogn，能nlogn就不要n2
    // 当然你可以直接用公式二分查找，这样效率会更高
    public int arrangeCoinsConcise(int n){
        int i=1,cnt=0;
        while(true){
            n-=i;
            i++;
            if(n<0)
                break;
            cnt++;
        }
        return cnt;
    }

    //442 find all duplicates in the array
    //好像是用绝对值的
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer>res=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            int index= Math.abs(nums[i])-1;
            if(nums[index]<0)
                res.add(index+1);//为啥不能是-nums[index], 我们每次遍历的都是index啊，不是按照 nums[index]来的
            if(nums[index]>0)
                nums[index]=-nums[index];
        }
        return res;
    }

    // add two numberII 445
    //reverse it first and do the same thing as before. But you can use the recursive way, hard to think

    public ListNode reverseList(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode newHead=null;
        ListNode nextNode=null;
        while(head!=null){
            nextNode=head.next;
            head.next=newHead;
            newHead=head;
            head=nextNode;
        }
        return newHead;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1=reverseList(l1);
        l2=reverseList(l2);
        int carry=0;
        ListNode dummy=new ListNode(0);
        ListNode p=dummy;
        while(l1!=null||l2!=null||carry!=0){
            int a = l1!=null?l1.val:0;
            int b = l2!=null?l2.val:0;
            carry+=a;
            carry+=b;
            l1=l1!=null?l1.next:l1;
            l2=l2!=null?l2.next:l2;
            p.next=new ListNode(carry%10);
            carry/=10;
            p=p.next;
        }
        return reverseList(dummy.next);
    }

    ///444 sequence recognition
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Set<Integer>set=new HashSet<>();
        Map<Integer,Set<Integer>>adj=new HashMap<>();
        int m=seqs.size(),n=org.length;
        int []degree=new int[n+1];
        for(int i=0;i<m;++i){
            if(seqs.get(i).isEmpty())
                continue;
            set.add(seqs.get(i).get(0));
            for(int j=1;j<seqs.get(i).size();++j){
                set.add(seqs.get(i).get(j));
                if(!adj.containsKey(seqs.get(i).get(j-1)))
                    adj.put(seqs.get(i).get(j-1),new HashSet<>());
                adj.get(seqs.get(i).get(j-1)).add(seqs.get(i).get(j));
                //degree[seqs.get(i).get(j)]++;//各种数据都可能发生，简直是没有人性
            }
        }
        if(set.size()!=org.length)
            return false;
        for(int i=1;i<=n;++i){
            Set<Integer>neighbors=adj.getOrDefault(i,new HashSet<>());
            for(int x:neighbors){
                if(x<1||x>n)
                    return false;
                ++degree[x];
            }
        }

        Queue<Integer>q=new LinkedList<>();
        for(int i=1;i<=n;++i){
            if(degree[i]==0)
                q.add(i);
        }
        int cnt=0;
        while(!q.isEmpty()){
            if(q.size()!=1)
                return false;
            int top=q.poll();
            if(top!=org[cnt++])
                return false;
            Set<Integer>neighbors=adj.getOrDefault(top,new HashSet<>());
            for(int neighbor:neighbors){
                --degree[neighbor];
                if(degree[neighbor]==0)
                    q.add(neighbor);
            }
        }
        return cnt==n;
    }
}
