package _03_04;

import java.util.*;

/**
 * Created by tao on 3/4/17.
 */
public class FortyOneToFifty {

    //41 first missing positive
    //sort first and binary search
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int index=1,n=nums.length,j=1,k=1;
        if(n==0)
            return 1;
        //去重操作
        while(k<n){
            if(nums[k]!=nums[k-1])
                nums[j++]=nums[k];
            k++;
        }
        for(int i=0;i<k;++i){
            if(nums[i]<=0)//也可以用i>0 && nums[i-1]==nums[i]去重
                continue;
            if(nums[i]!=index)
                return index;
            index++;
        }
        return index;
    }


    //concise way
    public void swap(int []nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    public int firstMissingPositiveConcise(int[] nums){
        int n=nums.length;
        for(int i=0;i<n;++i){
            while(nums[i]>=1 && nums[i]<=n && nums[nums[i]-1]!=nums[i]){
                swap(nums,nums[i]-1,i);
            }
        }
        for(int x=0;x<n;++x){
            if(nums[x]!=x+1)
                return x+1;
        }
        return n+1;
    }

    //count sort 晚上复习一把


    //42 trap rain water
    public int trap(int[] height) {
        //三板斧，用左边数组存最大值，用右边数组存最大值。
        int n=height.length;
        int []left=new int[n];
        int []right=new int[n];
        for(int i=1;i<n;++i){
            left[i]=Math.max(left[i-1],height[i-1]);
            right[n-1-i]=Math.max(right[n-i],height[n-i]);
        }

        int res=0;
        for(int i=0;i<n;++i){
            int minHeight=Math.min(left[i],right[i]);
            res+=Math.max(0,minHeight-height[i]);
        }
        return res;
    }

    //two pointers
    //我靠，都忘了
    //你想啊，如果当前的left高度小于right的高度，那么我们就不必管right的高度了，只需要关注当前的left左边的高度了。
    // 非常巧妙
    public int trapSaveSpace(int []A){
        //two pointers;
        int n=A.length;
        int left=0; int right=n-1;
        int res=0;
        int maxleft=0, maxright=0;
        while(left<=right){
            if(A[left]<=A[right]){
                if(A[left]>=maxleft) maxleft=A[left];
                else res+=maxleft-A[left];
                left++;
            }
            else{
                if(A[right]>=maxright) maxright= A[right];
                else res+=maxright-A[right];
                right--;
            }
        }
        return res;
    }

    //单调栈
    public int trapStack(int []height){
        Stack<Integer>stk=new Stack<Integer>();
        int n=height.length;
        int maxWater=0,bottomWater=0;
        //全部存递减，若是遇到增加，就出栈
        for(int i=0;i<n;++i){
            while(!stk.isEmpty() && height[i]>height[stk.peek()]){
                //出栈
                int bot=stk.pop();
                bottomWater=stk.isEmpty()?0:(Math.min(height[stk.peek()],height[i])-height[bot])*(i-stk.peek()-1);
                maxWater+=bottomWater;
            }
            stk.push(i);
        }
        return maxWater;
    }

    //but trapping rain waterII can't do this way
    //[[12,13,1,12],
//[13,4,13,12],
//   [13,8,10,12],
//   [12,13,12,12],
//   [13,13,13,13]]4
// will leak from the path of 4 -> 8 -> 10 -> 12, so it can not reach to 13, but only 12, so it can only store 8 instead of 9.

    //43 multiply string
    public String multiply(String num1, String num2) {
        int m=num1.length(),n=num2.length();
        int []res=new int[m+n];
        for(int i=m-1;i>=0;--i){
            for(int j=n-1,index=m-1-i;j>=0;--j){
                res[index++]+=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
            }
        }
        StringBuilder sb=new StringBuilder();
        int carry=0;
        for(int i=0;i<m+n;++i){
            carry+=res[i];
            sb.append(carry%10);
            carry/=10;
        }
        sb.reverse();
        int index=0;
        //delete the leading zero
        while(index<m+n-1 &&sb.charAt(index)=='0')
            index++;
        return sb.toString().substring(index);
    }

    //concise
    public String multiplyConcise(String num1,String num2){
        char[]num1s=num1.toCharArray();
        char[]num2s=num2.toCharArray();
        int m=num1s.length,n=num2s.length;
        char []res=new char[m+n];
        for(int i=m-1;i>=0;--i){
            int carry=0;
            for(int j=n-1;j>=0;--j){
                carry+=(res[i+j+1]-'0')+(num1s[i]-'0')*(num2s[i]-'0');
                res[i+j+1]=(char)(carry%10+'0');
                carry/=10;
            }
            res[i]+=carry;
        }
        String ans=new String(res);
        int index=0;
        while(index<m+n-1 && ans.charAt(index)=='0')
            index++;
        return ans.substring(index);

    }

    //44. Wildcard Matching
    //recursive way and dp way

    //recursive way
    //'?' Matches any single character.
    //'*' Matches any sequence of characters
    //including the empty sequence

    //LTE
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        if(s.isEmpty())
            return p.charAt(0)=='*' && isMatch(s,p.substring(1));
        if(p.charAt(0)=='*'){
            //zero or number of se
            return isMatch(s,p.substring(1))||isMatch(s.substring(1),p);
        }else
            return (s.charAt(0)==p.charAt(0)||p.charAt(0)=='?') && isMatch(s.substring(1),p.substring(1));
    }

    //一发击中
    public boolean isMatchDP(String s,String p){
        int m=s.length(),n=p.length();
        boolean [][]dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=n;++i)
            dp[0][i]=dp[0][i-1] && p.charAt(i-1)=='*';
        for(int i=1;i<=m;++i){
            for(int j=1;j<=n;++j){
                if(p.charAt(j-1)=='*')
                    dp[i][j]=dp[i][j-1]||dp[i-1][j];
                else
                    dp[i][j]=(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?') && dp[i-1][j-1];
            }
        }
        return dp[m][n];
    }




    //45 jump games II
    //You can assume that you can always reach the last index.
    //思路非常清晰
    public int jump(int[] nums) {
        //greedy algo
        //always choose jump fast
        int n=nums.length;
        int optimalPos=0,index=0;
        int cnt=0;
        while(index<n-1){
            if(nums[index]+index>=n-1){
                cnt++;
                break;
            }
            //look for the optimal position
            int maxvalue=nums[index+1]+index+1;//first jump position
            optimalPos=index+1;
            for(int j=2;j<=nums[index];++j){
                if(index+j>=n-1){
                    optimalPos=index+j;
                    break;
                }
                if(nums[index+j]+index+j>=maxvalue){
                    maxvalue=nums[index+j]+index+j;
                    optimalPos=j+index;
                }
            }
            index=optimalPos;
            cnt++;
        }
        return cnt;
    }

    //46 permutations
    //好像还可以用swap交换，然后进行回溯
    public void dfs(List<List<Integer>>res,int[]nums,List<Integer>path){
        if(path.size()==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<nums.length;++i){
            if(!path.contains(nums[i])){
                path.add(nums[i]);
                dfs(res,nums,path);
                path.remove(path.size()-1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        dfs(res,nums,path);
        return res;
    }



    //47 permutation II
    //contains duplicates

    public  void dfs(int[]nums,List<List<Integer>>res,List<Integer>path){
        if(path.size()==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<nums.length;++i){
            if(i>0 && nums[i]==nums[i-1])
                continue;
            int c1=0,c2=0;//count the number of nums[i] in nums[i]
            for(int j=0;j<nums.length;++j)
                if(nums[j]==nums[i])
                    c1++;
            for(int j=0;j<path.size();++j)
                if(nums[i]==path.get(j))
                    c2++;
            if(c1>c2){
                path.add(nums[i]);
                dfs(nums,res,path);
                path.remove(path.size()-1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        dfs(nums,res,path);
        return res;
    }


    //48 rotate image
    //123      7 4  1
    //456 to   8  5 2
    //789      9  6 3

    public void rotateClockwise(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return;
        int m=matrix.length,n=matrix[0].length;
        for(int i=0;i<m/2;++i){
            int []tmp=matrix[0];
            matrix[0]=matrix[m-i-1];
            matrix[m-i-1]=tmp;
        }
        for(int i=0;i<m;++i){
            for(int j=0;j<i;++j){
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
    }

    public void reverse(int []nums){
        int begin=0,end=nums.length-1;
        while(begin<end){
            int tmp=nums[begin];
            nums[begin++]=nums[end];
            nums[end--]=tmp;
        }
    }
    public void rotateAnticlockWise(int[][]matrix){
        if(matrix.length==0||matrix[0].length==0)
            return;
        int m=matrix.length,n=matrix[0].length;
        for(int i=0;i<m;++i){
            reverse(matrix[i]);
        }
        for(int i=0;i<m;++i){
            for(int j=0;j<i;++j){
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
    }



    //49 Group Anagrams
    //没啥好说的，直接hashmap搞起
    //The idea is clear: we need to generate a unique key for each group of Strings.
    // A product of several primes can only have one combination of factors, which makes the product a
    // unique key for the Strings consisted with these certain chars.
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>>res=new ArrayList<>();
        Map<String,List<String>>map=new HashMap<>();
        for(String str:strs) {
            char[] arrs = str.toCharArray();
            Arrays.sort(arrs);
            String tmp =String.valueOf(arrs);
            if (!map.containsKey(tmp))
                map.put(tmp, new ArrayList<>());
            map.get(tmp).add(str);
        }
        res.addAll(map.values());
        return res;
    }
    //还有一种方法是用prime 数搞的
    public List<List<String>>groupAnagramsPrime(String[]strs){
        int []primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        Map<Integer,List<String>>map=new HashMap<>();
        int n=strs.length;
        for(int i=0;i<n;++i){
            int ans=1;
            for(int j=0;j<strs[i].length();++j){
                ans*=primes[strs[i].charAt(j)-'a'];
            }
            if(!map.containsKey(ans))
                map.put(ans,new ArrayList<>());
            map.get(ans).add(strs[i]);

        }
        List<List<String>>res=new ArrayList<>(map.values());
        return res;
    }


    //50 pow(x,y)
    //iterative
    public double myPow(double x, int n){
        if(n==0)
            return 1.0;
        boolean negative=n<0;
        long nn=Math.abs((long)n);
        double res=1.0;
        while(nn>0){
            if(nn%2==1)
                res*=x;
            x*=x;
            nn>>=1;
        }
        return negative?1.0/res:res;
    }

    //recursive way
    public double myPowRecursive(double x, int n) {
        return n<0?1.0/myPowRe(x,-(long)n):myPowRe(x,(long)n);
    }

    public double myPowRe(double x,long n){
        if(n==0)
            return 1.0;
        return n%2==0?myPowRe(x*x,n/2):x*myPowRe(x*x,n/2);
    }

    //bitwise
    public double myPowBitwise(double x,int n){
        boolean negate=n<0;
        long nn=n<0?-(long)n:n;
        double []table=new double[32];
        table[0]=x;
        for(int i=1;i<32;++i)
            table[i]=table[i-1]*table[i-1];
        double res=1.0;
        for(int i=0;i<32;++i){
            if(((nn>>i)&0x1)!=0)
                res*=table[i];
        }
        return negate?1.0/res:res;
    }



}
