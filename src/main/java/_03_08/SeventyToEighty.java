package _03_08;

import java.util.*;

/**
 * Created by tao on 3/8/17.
 */
public class SeventyToEighty {


    //71 simplify path
    //就是简单的分割呗
//    注意. ,".."也算是学到了，切割的话，如果由多个'／'，这种就会产生多个empty，就是空洞。要记住啊。
    public String simplifyPath(String path) {
        String[]strs=path.split("/");
        List<String>li=new ArrayList<>();
        for(String str:strs){
            if(!str.isEmpty() && !str.equals("..")&&!str.equals("."))//empty防止的是一个或多个"／"产生的残余，"."是我们应该省略的。
                li.add(str);
            else if(str.equals("..")){//遇到..我们应该撤回来
                if(!li.isEmpty())
                    li.remove(li.size()-1);
            }
        }
        return "/"+String.join("/",li);//join will be useful only if there are elements in list.
    }

    //如果要求不能用split，如何做？

    //72 word edit distance
    //普通的dp
    //还有一种方法是sace space的
    public int minDistance(String word1, String word2) {
        int m=word1.length(),n=word2.length();
        int [][]dp=new int[m+1][n+1];

        //初始化一定要到位
        for(int i=1;i<=m;++i)
            dp[i][0]=i;
        for(int i=1;i<=n;++i)
            dp[0][i]=i;
        for(int i=1;i<=m;++i){// =m一定要到位
            for(int j=1;j<=n;++j){//=n 一定要到位
//                System.out.println(dp[i][j-1]+1);
//                System.out.println(dp[i-1][j]+1);
                System.out.println(dp[i-1][j-1]);
                System.out.println(dp[i-1][j-1]+(word1.charAt(i-1)==word2.charAt(j-1)?0:1));


                int a=dp[i][j-1]+1;
                int b=dp[i-1][j]+1;
                int c=dp[i-1][j-1]+(word1.charAt(i-1)==word2.charAt(j-1)?0:1);//一定要加括号，不然就容易崩溃
                dp[i][j]=Math.min(a,Math.min(b,c));
            }
        }
        return dp[m][n];
    }

    //有超级省空间的办法。



    //73 set matrix zeros
    //这道题其实还是很有难度的一道，主要是对空间要求太高
    //if an element is 0, set its entire row and column to 0. Do it in place.
    //如果完全是复制一个matrix，我们需要耗费的是O(M*N)
    public void setZeroes(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return;
        int m=matrix.length,n=matrix[0].length;
        Set<Integer>rows=new HashSet<>();
        Set<Integer>cols=new HashSet<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }

            }
        }
        for(int i=0;i<m;++i)
            if(rows.contains(i))
                Arrays.fill(matrix[i],0);
        for(int j=0;j<n;++j){
            if(cols.contains(j)){
                for(int i=0;i<m;++i)
                    matrix[i][j]=0;
            }
        }
    }
    // constant space
    public void setZeroesSaveSpace(int[][] matrix){
        if(matrix.length==0||matrix[0].length==0)
            return;
        int m=matrix.length,n=matrix[0].length;
        boolean col0=false;
        for(int i=0;i<m;++i){
            if(matrix[i][0]==0)
                col0=true;
            for(int j=1;j<n;++j){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        };

        //这是一个很好的问题，为啥要从底部开始呢？如果[0,0]是0，那么第一列全是0，会导致下面所有的都是0
        for(int i=m-1;i>=0;--i){
            for(int j=n-1;j>=1;--j){
                if(matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j]=0;
            }
            if(col0)
                matrix[i][0]=0;
        }

    }




    //74. Search a 2D Matrix
    //3 种方法
    //普通的二分查找
    //从右上角开始查找的话，也是可以的，但那是ii,最坏情况是o(m+n)
    /*
    m * n may overflow for large m and n. I think it is better to binary search by row first,
    then binary search by column. The time complexity is the same but this avoids multiplication overflow
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)
            return false;
        int m=matrix.length,n=matrix[0].length;
        int begin=0,end=m*n-1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(matrix[mid/n][mid%n]==target)
                return true;
            else if(matrix[mid/n][mid%n]>target)
                end=mid;
            else
                begin=mid;
        }
        return matrix[begin/n][begin%n]==target;
    }

    //find the row first and find the col next
    public boolean searchMatrixByRow(int[][]matrix,int target){
        if(matrix.length==0||matrix[0].length==0)
            return false;
        int m=matrix.length,n=matrix[0].length;
        if(matrix[0][0]>target||target>matrix[m-1][n-1])
            return false;
        //find the row first
        int begin=0,end=m-1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(matrix[mid][0]==target)
                return true;
            else if(matrix[mid][0]>target)
                end=mid;
            else
                begin=mid+1;
        }
        //应该和search range 那道题好好联系在一起
        //默认都是lowbound的，但是最后一行又有所区别，因为到不了end，end=n-1，跳出循环的时候才能到达。真的很有意思
        if(matrix[begin][0]>target)
            begin--;
        int start=0;
        end=n-1;
        while(start<end){
            int mid=(end-start)/2+start;
            if(matrix[begin][mid]==target)
                return true;
            else if(matrix[begin][mid]>target)
                end=mid;
            else
                start=mid+1;
        }
        return matrix[begin][start]==target;


    }

    //75 sort color
    //荷兰国旗问题
    //如果是k个就得用count sort了。
    public void swap(int[]nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    public void sortColors(int[] nums) {
        int n=nums.length,begin=0,end=n-1,i=0;
        while(i<=end){
            if(nums[i]==0){
                swap(nums,begin++,i++);
            }else if(nums[i]==2){
                swap(nums,i,end--);
            }else
                i++;
        }

    }
    /*
    // two pass O(m+n) space
void sortColors(int A[], int n) {
    int num0 = 0, num1 = 0, num2 = 0;

    for(int i = 0; i < n; i++) {
        if (A[i] == 0) ++num0;
        else if (A[i] == 1) ++num1;
        else if (A[i] == 2) ++num2;
    }

    for(int i = 0; i < num0; ++i) A[i] = 0;
    for(int i = 0; i < num1; ++i) A[num0+i] = 1;
    for(int i = 0; i < num2; ++i) A[num0+num1+i] = 2;
}
     */


    //76 minimum window string
    public String minWindow(String s, String t) {
        int m=s.length(),n=t.length(),start=0,minLength=Integer.MAX_VALUE;
        int begin=0,end=0;//two pointers
        int[]cnt=new int[128];
        char []ss=s.toCharArray();
        char []tt=t.toCharArray();
        for(char c:tt)
            cnt[c]++;
        while(end<m){
            if(cnt[ss[end]]>0)
                n--;
            --cnt[ss[end++]];
            while(n==0){
                if(end-begin<minLength) {
                    start=begin;
                    minLength = end - begin;
                }
                if(++cnt[ss[begin++]]>0)
                    n++;
            }
        }

        return minLength==Integer.MAX_VALUE?"":s.substring(start,start+minLength);//这里取substring的时候一定要注意，是start+minlength而不是直接的minlength。
    }
    //77 Combinations

    public void dfs(List<List<Integer>>res,List<Integer>path,int n,int k,int pos){
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=pos;i<=n;++i){
            path.add(i);
            dfs(res,path,n,k,i+1);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        dfs(res,path,n,k,1);
        return res;

    }
    //mathematic way
    /*
    the mathematical formula C(n,k)=C(n-1,k-1)+C(n-1,k).
    Here C(n,k) is divided into two situations. Situation one, number n is selected,
    so we only need to select k-1 from n-1 next. Situation two, number n is not selected, and the rest job is selecting k from n-1.
     */
    //虽然很费时间，但是却非常有创意，解释非常有创意
    public List<List<Integer>> combine(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(this.combine(n - 1, k));
        return result;
    }


    //78 subsets
    //there should be dfs way and iterative way
    //dfs way
    //bit way
    //bfs way

    public void dfs(int[]nums,List<List<Integer>>res,List<Integer>path,int pos){
        res.add(new ArrayList<>(path));
        for(int i=pos;i<nums.length;++i){
            path.add(nums[i]);
            dfs(nums,res,path,i+1);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        Arrays.sort(nums);//sort 和sort其实差不多
        dfs(nums,res,path,0);
        return res;
    }

    //iterative way
    public List<List<Integer>> subsetsIterative(int[]nums){
        List<List<Integer>>res=new ArrayList<>();
        res.add(new ArrayList<>());
        int n=nums.length;
        for(int i=0;i<n;++i){
            int m=res.size();
            for(int j=0;j<m;++j){
                List<Integer>li=new ArrayList<>(res.get(j));
                li.add(nums[i]);
                res.add(li);
            }
        }
        return res;
    }
    //bitmap
    public List<List<Integer>> subsetsBitMap(int[]nums){
        int n=nums.length;
        int nn=1<<n;
        List<List<Integer>>res=new ArrayList<>();
        for(int i=0;i<nn;++i){
            res.add(new ArrayList<>());
            for(int j=0;j<n;++j){
                if(((i>>j)&0x1)!=0)
                    res.get(i).add(nums[j]);
            }
        }
        return res;
    }






    //79 word search
    //dfs way
    public boolean dfs(char[][]board,char[] words,int pos,int x,int y){
        //judge this first
        if(pos==words.length){
            return true;
        }
        if(x<0||x>=board.length||y<0||y>=board[0].length||words[pos]!=board[x][y])
            return false;

        board[x][y]='&';//其实标准的做法应该是用vis标记是否visite过，否则的话很容易出现冲突
        boolean res=dfs(board,words,pos+1,x+1,y)||dfs(board,words,pos+1,x-1,y)||dfs(board,words,pos+1,x,y+1)||dfs(board,words,pos+1,x,y-1);
        board[x][y]=words[pos];
        return res;
    }
    public boolean exist(char[][] board, String word) {
        if (board == null || board[0].length == 0||word.isEmpty())
            return false;
        int m=board.length,n=board[0].length;
        char []words=word.toCharArray();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(dfs(board,words,0,i,j))
                    return true;
            }
        }
        return false;
    }


    //80 Remove Duplicates from Sorted Array II
    //O(n)space, but you should configure that if you don't use another array, you can't get what you want.

    public int removeDuplicatesII(int[] nums) {
        //twice
        int n=nums.length;
        if(n<3)
            return n;
        int j=2,i=2;
        while(i<n){
            if(nums[i]!=nums[i-2])//this is the wrong version, you should use the extra arrays.
                nums[j++]=nums[i];
            i++;
        }
        return j;
    }

    //another way
    //和传统的方式发生了根本性的改变。
    public int removeDuplicatesIIbetter(int[]nums){
        int i=0;
        for(int n:nums){
            if(i<2||n!=nums[i-2])
                nums[i++]=n;
        }
        return i;
    }

    //hashamp way, easy
    //count way;

    public int removeDuplicatesIICount(int[]nums){
        int cnt=1;
        int i=1,j=1;
        int n=nums.length;
        while(i<n){
            if(nums[i]==nums[i-1])
                cnt++;
            else
                cnt=1;
            if(cnt<3)
                nums[j++]=nums[i];
            i++;
        }
        return j;
    }
}
