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

    //267 Palindrome Permutation II
    //先判断可不可能，然后再用permutation的方法，本质就是一个backtrack
    public void generate(char[]candidates,String path,List<String>res){
        if(path.length()==candidates.length){
            res.add(path);
            return;
        }
        for(int i=0;i<candidates.length;++i){
            if(i>0 && candidates[i]==candidates[i-1])
                continue;
            int c1=0,c2=0;
            for(int j=0;j<candidates.length;++j)
                if(candidates[j]==candidates[i])
                    c1++;
            for(int j=0;j<path.length();++j)
                if(candidates[i]==path.charAt(j))
                    c2++;
            if(c1>c2){
                generate(candidates,path+candidates[i],res);
            }
        }
    }
    public List<String> generatePalindromes(String s) {
        List<String>res=new ArrayList<>();
        int []cnt=new int[256];
        int oddCnt=0;
        char oddCharacter=' ';
        char []ss=s.toCharArray();
        StringBuffer candidates=new StringBuffer();
        for(char c:ss)
            cnt[c]++;
        for(int i=0;i<256;++i){
            if(cnt[i]%2!=0){
                oddCharacter=(char)i;
                oddCnt++;
                if(oddCnt>1)
                    return res;
            }
            //注意这里不是else if，要记住把奇数的那部分也加进去
            if(cnt[i]>=2){
                char []chr=new char[cnt[i]/2];
                Arrays.fill(chr,(char)i);
                candidates.append(String.valueOf(chr));
            }
        }
        char []candid=candidates.toString().toCharArray();
        Arrays.sort(candid);
        generate(candid,"",res);
        int n=res.size();
        for(int i=0;i<n;++i){
            StringBuffer sb=new StringBuffer(res.get(i));
            sb.append(oddCnt==1?oddCharacter:"").append(new StringBuffer(res.get(i)).reverse());
            res.set(i,sb.toString());
        }
        return res;
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

    //269 aliean dictionary



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

    //271 decode and encode string in design

    //272 cloest binary search Tree value II
    //跑一次中序遍历，然后二分查找，然后就是two pointers
    //先把最近的那个root找到，然后左右两边各提取k个数，最后两指针
    public TreeNode getPredecessor(TreeNode root,TreeNode node){
        if(root==null)
            return null;
        if(root.val>=node.val)
            return getPredecessor(root.left,node);
        else{
            TreeNode right=getPredecessor(root.right,node);
            return right!=null?right:root;
        }
    }

    public TreeNode getSuccessor(TreeNode root,TreeNode node){
        if(root==null)
            return null;
        if(root.val<=node.val)
            return getSuccessor(root.right,node);
        else{
            TreeNode left=getSuccessor(root.left,node);
            return left!=null?left:root;
        }
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

        //两边走啊
        List<Integer>res=new ArrayList<>();
        res.add(node.val);
        TreeNode pre=getPredecessor(root,node);
        TreeNode after=getSuccessor(root,node);
        while(res.size()<k){

            //还要考虑pre  after 为null的情况
            if(pre!=null && after!=null){
                if(Math.abs(1.0*pre.val-target)<Math.abs(1.0*after.val-target)){
                    res.add(pre.val);
                    pre=getPredecessor(root,pre);
                }else{
                    res.add(after.val);
                    after=getSuccessor(root,after);
                }
            }else{
                res.add(pre!=null?pre.val:after.val);
                pre=pre!=null?getPredecessor(root,pre):null;
                after=after!=null?getSuccessor(root,after):null;
            }
        }
        return res;
    }

    //当然还有用栈的，第一次就把大于target 和小于target的分开
    public void inorder(TreeNode root,double target,Stack<Integer>stk,boolean isLeft){
        if(root==null)
            return;
        inorder(isLeft?root.left:root.right,target,stk,isLeft);
        if(isLeft && root.val>target||!isLeft && root.val<=target)
            return;
        stk.push(root.val);
        inorder(isLeft?root.right:root.left,target,stk,isLeft);
    }
    public List<Integer> closestKValuesTwoStack(TreeNode root, double target, int k){
        List<Integer>res=new ArrayList<>();
        Stack<Integer>pre=new Stack<>();
        Stack<Integer>after=new Stack<>();
        inorder(root,target,pre,true);//is left;
        inorder(root,target,after,false);
        while(res.size()<k){
            if(pre.isEmpty()||after.isEmpty()){
                res.add(pre.isEmpty()?after.pop():pre.pop());
            }else{
                if(Math.abs(1.0*pre.peek()-target)<Math.abs(1.0*after.peek()-target)){
                    res.add(pre.pop());
                }else
                    res.add(after.pop());
            }
        }
        return res;
    }

    //tag
    //273 Integer to english words
    String[]digits={"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[]Ty={"Zero","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public String num2str(int num){
        if(num>=1000000000)
            return num2str(num/1000000000)+" Billion"+num2str(num%1000000000);//为啥billion只有一个空格，这些都是要好好考虑的
        else if(num>=1000000)
            return num2str(num/1000000)+" Million"+num2str(num%1000000);
        else if(num>=1000)
            return num2str(num/1000)+" Thousand"+num2str(num%1000);
        else if(num>=100)
            return num2str(num/100)+" Hundred"+num2str(num%100);
        else if(num>=20)
            return " "+Ty[num/10]+num2str(num%10);
        else if(num>=1)//为啥这里还要判断一下，这也是好问题
            return " "+digits[num];
        else
            return "";
    }
    public String numberToWords(int num) {
        if(num==0)
            return "Zero";
        String res=num2str(num);
        return res.substring(1);
    }

    //tag
    //274 hindex
    //其实最烦的就是这个，你说暴力吧，显得很没有水平，但是确实属于比较恶心人的那种。
    //n long n, 先排序，然后再二分查找
    public int low_bound(int []citations,int x){
        int begin=0,end=citations.length;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(citations[mid]>=x)
                end=mid;
            else
                begin=mid+1;
        }
        return begin;
    }
    boolean check(int[]citations,int i){
        int n=citations.length;
        int index=low_bound(citations,i);
        if(index==n)
            return false;
        int cnt=n-index;
        return cnt>=i;
    }
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n=citations.length;
        for(int i=n;i>=0;--i){
            if(check(citations,i))
                return i;
        }
        return 0;
    }

    //两次binary search，其实一次就够了。唉，好失败
    public int hIndexTwoBinarySearch(int[] citations) {
        Arrays.sort(citations);
        int n=citations.length;
        if(n==0)
            return 0;
        int end=n,begin=0;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(check(citations,mid))
                begin=mid+1;
            else
                end=mid;
        }
        return check(citations,begin)?begin:begin-1;//和up_bound差不多
    }


    //275 为啥不是正方向呢，很好玩的一件事情。
    //tag
    public int hIndexII(int[] citations) {
        int n=citations.length;
        int end=n,begin=0;
        if(n==0)
            return 0;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(citations[mid]>=(n-mid))
                end=mid;
            else
                begin=mid+1;
        };
        return n-begin;
    }

    //274 concise
    //Hen有意思
    public int hIndexBest(int[]citations){
        //差不多计数排序
        int n=citations.length;
        int []res=new int[n+1];
        for(int i=0;i<n;++i){
            if(citations[i]>=n)
                res[n]++;
            else
                res[citations[i]]++;
        }
        int sum=0;
        for(int i=n;i>=0;--i){
            sum+=res[i];
            if(sum>=i)
                return i;
        }
        return 0;

    }

}
