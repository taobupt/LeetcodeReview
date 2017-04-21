package _04_12;
import common.Tuple;
import design.NestedInteger;

import java.util.*;

/**
 * Created by tao on 4/19/17.
 */
public class ThreeHundredFiftysixToSixtyFive {


    //356  Line Reflection
    //不难，但是我翻了跟头
    //秒过,都不需要排序，直接最大值，最小值，对啊，我慢就慢在arraylist线性查找那块
    public boolean isReflected(int[][] points) {
        int n=points.length;
        if(n<=1)
            return true;
        int []xaxis=new int[n];
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        int duichen=points[n-1][0]+points[0][0];
        Map<Integer,List<Integer>>map=new HashMap<>();
        for(int []point:points){
            if(!map.containsKey(point[0]))
                map.put(point[0],new ArrayList<>());
            map.get(point[0]).add(point[1]);
        }

        for(int []point:points){
            if(!map.containsKey(duichen-point[0])||!map.get(duichen-point[0]).contains(point[1]))
                return  false;
        }
        return true;
    }

    //concise
    public boolean isReflectedConcise(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for(int[] p:points){
            max = Math.max(max,p[0]);
            min = Math.min(min,p[0]);
            String str = p[0] + "a" + p[1];
            set.add(str);
        }
        int sum = max+min;
        for(int[] p:points){
            //int[] arr = {sum-p[0],p[1]};
            String str = (sum-p[0]) + "a" + p[1];
            if( !set.contains(str))
                return false;

        }
        return true;
    }
    //357 就是用dp来做的，结合之前的一道题，可以总结一下
    public int countNumbersWithUniqueDigits(int n) {
        int dp[]=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;++i){
            dp[i]=9;
            for(int j=1;j<i;++j){
                dp[i]*=(10-j);
            }
            dp[i]+=dp[i-1];
        }
        return dp[n];
    }
    //358 rearrange string k distance part
    //感觉优先级队列，hashmap，贪心能做出来
    //居然过了，卧槽，不过效率很低啊，少年！
    public String rearrangeString(String s, int k) {
        char []ss=s.toCharArray();
        int []cnt=new int[26];
        for(char c:ss)
            cnt[c-'a']++;
        PriorityQueue<Tuple<Character,Integer>>pq=new PriorityQueue<>(new Comparator<Tuple<Character, Integer>>() {
            @Override
            public int compare(Tuple<Character, Integer> o1, Tuple<Character, Integer> o2) {
                return Integer.compare(o2.y,o1.y);//次数最大的出队列
            }
        });
        for(int c='a';c<='z';++c){
            if(cnt[c-'a']>0)
                pq.add(new Tuple<>((char)c,cnt[c-'a']));
        }
        StringBuilder sb=new StringBuilder();
        Map<Character,Tuple<Integer,Integer>>map=new HashMap<>();
        while(!pq.isEmpty()){
            Tuple tuple=pq.poll();
            char c=(Character) tuple.x;
            int num=(Integer)tuple.y;
            if(num>1)
                map.put(c,new Tuple<>(sb.length(),num-1));
            sb.append(c);
            int length=sb.length();
            Set<Character>set=new HashSet<>();
            for(Map.Entry<Character,Tuple<Integer,Integer>>entry:map.entrySet()){
                if(length-entry.getValue().x>=k && entry.getValue().y>0){
                    pq.add(new Tuple<>(entry.getKey(),entry.getValue().y));
                    //delete in the map;
                    set.add(entry.getKey());
                }
            }
            for(char cc:set)
                map.remove(cc);
        }
        return sb.length()<s.length()?"":sb.toString();
    }


    //359 logger rate limiter in design


    //360 360. Sort Transformed Array
    //非常经典的一道题，俩指针
    //写的太复杂了
    public int getValue(int a,int b,int c,int x){
        return a*x*x+b*x+c;
    }
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //对称轴，头尾俩指针
        int begin=0,end=nums.length-1;
        int []res=nums.clone();
        int index=0;
        if(a==0){
            if(b<0){
                index=end;
                while(begin<=end){
                    res[index--]=getValue(a,b,c,nums[begin++]);
                }
            }else{
                while(index<=end){
                    res[index++]=getValue(a,b,c,nums[index]);
                }
            }
            return res;
        }else if(a>0){
            double axis=-b*1.0/2/a;
            index=end;
            while(begin<=end){
                if(Math.abs(axis-nums[end])>=Math.abs(axis-nums[begin])){
                    res[index--]=getValue(a,b,c,nums[end]);
                    end--;
                }else{
                    res[index--]=getValue(a,b,c,nums[begin]);
                    begin++;
                }
            }
        }else{
            double axis=-b*1.0/2/a;
            while(begin<=end){
                if(Math.abs(axis-nums[end])>=Math.abs(axis-nums[begin])){
                    res[index++]=getValue(a,b,c,nums[end]);
                    end--;
                }else{
                    res[index++]=getValue(a,b,c,nums[begin]);
                    begin++;
                }
            }
        }
        return res;
    }

    //361 Bomb Enemy,save space 挺难的
    //dp[][],行走两遍，列走两遍就可以了
    //为啥走两遍，我们知道的，0ee，如果只走一遍，dp[0][0]=0,实际是2
    //还有一种省空间的办法
    //普通的做法是m*n*n,但是dp却能很好的解决的降维打击
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int[][]dp=new int[m][n];
        int maxRes=0;
        for(int i=0;i<m;++i){
            int cnt=0;
            for(int j=0;j<n;++j){
                if(grid[i][j]=='0'){
                    dp[i][j]=cnt;
                }else if(grid[i][j]=='E')
                    cnt++;
                else
                    cnt=0;
            }

            cnt=0;
            for(int j=n-1;j>=0;--j){
                if(grid[i][j]=='0')
                    dp[i][j]+=cnt;
                else if(grid[i][j]=='E')
                    cnt++;
                else
                    cnt=0;
            }
        }

        for(int j=0;j<n;++j){
            int cnt=0;
            for(int i=0;i<m;++i){
                if(grid[i][j]=='0'){
                    dp[i][j]+=cnt;
                }else if(grid[i][j]=='E')
                    cnt++;
                else
                    cnt=0;
            }
            cnt=0;
            for(int i=m-1;i>=0;--i){
                if(grid[i][j]=='0'){
                    dp[i][j]+=cnt;
                    maxRes=Math.max(maxRes,dp[i][j]);
                }else if(grid[i][j]=='E')
                    cnt++;
                else
                    cnt=0;
            }
        }
        return maxRes;
    }

    //省空间的办法的时间复杂度分析，每行走完所有列，你才多走了一行，所以算不得啥，每个j你有可能走x个，有可能不走，但总的走了一行
    public int maxKilledEnemiesSaveSpace(char[][] grid){
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int rowhits=0,maxRes=0;
        int[]colHits=new int[n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(j==0||grid[i][j-1]=='W'){
                    rowhits=0;
                    for(int k=j;k<n && grid[i][k]!='W';++k)
                        rowhits+=grid[i][k]=='E'?1:0;
                }

                if(i==0||grid[i-1][j]=='W'){
                    colHits[j]=0;
                    for(int k=i;k<m && grid[k][j]!='W';++k)
                        colHits[j]+=grid[k][j]=='E'?1:0;
                }
                if(grid[i][j]=='0'){
                    maxRes=Math.max(maxRes,rowhits+colHits[j]);
                }
            }
        }
        return maxRes;
    }

    //362 design hit counter in design

    //363 Max Sum of Rectangle No Larger Than K
    //先做一道题压压惊
    //maximum sum of rectangle
    //Maximum sum rectangle in a 2D matrix)

    public int[] kadane(int []nums){
        int sum1=0;
        int []res=new int[3];
        res[0]=Integer.MIN_VALUE;//res[0] is maximum value, res[1] is left res[2] is right
        int s=0,n=nums.length;
        for(int i=0;i<n;++i){
            sum1+=nums[i];
            if(sum1>res[0]){
                res[0]=sum1;
                res[1]=s;
                res[2]=i;
            }
            if(sum1<0){
                sum1=0;
                s=i+1;
            }
        }
        //System.out.println("start is "+start+" end is "+end);
        return res;
    }
    public int findMaxSubMatrix(int [][]matrix){
        if(matrix.length==0||matrix[0].length==0)
            return 0;
        int m=matrix.length,n=matrix[0].length,maxleft=0,maxright=0,maxupper=0,maxdown=0;
        int maxSum=0;
        for(int L=0;L<n;++L){
            int []dp=new int[m];
            for(int R=L;R<n;++R){
                for(int row=0;row<m;++row){
                    dp[row]+=matrix[row][R];
                }
                int []optimal=kadane(dp);
                if(optimal[0]>maxSum){
                    maxleft=L;
                    maxright=R;
                    maxSum=optimal[0];
                    maxdown=optimal[2];
                    maxupper=optimal[1];
                }
            }
        }
        System.out.println("left: "+maxleft+" right "+maxright+" upper "+maxupper+" down "+maxdown);
        return maxSum;
    }


    //还好复习的，hashmap实现降维打击
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix.length==0||matrix[0].length==0)
            return Integer.MIN_VALUE;
        int m=matrix.length,n=matrix[0].length;
        int res=Integer.MIN_VALUE;
        for(int l=0;l<n;++l){
            int []colSum=new int[m];
            for(int r=l;r<n;++r){
                for(int i=0;i<m;++i)
                    colSum[i]+=matrix[i][r];

                //正常的步骤是找kadane
                TreeSet<Integer>set=new TreeSet<>();
                set.add(0);//防止退化成一维
                int curSum=0;
                for(int x:colSum){
                    curSum+=x;
                    Integer num=set.ceiling(curSum-k);
                    if(num!=null)
                        res=Math.max(res,curSum-num);
                    set.add(curSum);
                }
            }
        }
        return res;
    }

    //364 Nested List Weight Sum II
    //dfs 或者是bfs，每次都保留值。
    //bfs 也是可以的
    //居然错了一次，我曹
    public int getDepth(List<NestedInteger> nestedList){
        int n=nestedList.size();
        int ans=0;
        for(int i=0;i<n;++i){
            if(!nestedList.get(i).isInteger()){
                //depth[0]++;
                ans=Math.max(ans,1+getDepth(nestedList.get(i).getList()));
            }else{
                ans=Math.max(ans,1);
            }
        }
        return ans;
    }

    public void dfs(List<NestedInteger> nestedList,int[]sum,int level){
        int n=nestedList.size();
        for(int i=0;i<n;++i){
            if(!nestedList.get(i).isInteger()){
                dfs(nestedList.get(i).getList(),sum,level-1);
            }else{
                sum[0]+=level*nestedList.get(i).getInteger();
            }
        }
    }
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //int []depth=new int[1];
        int height=getDepth(nestedList);
        int []sum=new int[1];
        dfs(nestedList,sum,height);
        return sum[0];
    }

    //365 这道题确实是挺好玩的。很有意思，就是gcd的变种
    //z=x*a+y*b, 求x,y的gcd，和欧几里得拓展定理差不多
    //tag，应该还是要理解拓展欧几里得的应用
    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z)
            return false;
        int GCD=gcd(x,y);
        //注意为0 的问题，%后不能为0。
        return GCD==0?z==0:z%GCD==0;
    }

}
