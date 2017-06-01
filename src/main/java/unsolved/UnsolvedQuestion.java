package unsolved;

import common.TreeNode;
import mathQuestions.MathQuestions;

import java.util.*;

/**
 * Created by tao on 3/29/17.
 */
//好好研究的题目
public class UnsolvedQuestion {

    // Sentence Screen Fitting
    //TLE
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n=sentence.length;
        int row=0,col=0,index=0,res=0;
        while(row<rows){
            while(index<n){
                if(sentence[index].length()>cols)
                    return 0;
                if(col==0)
                    col+=sentence[index++].length();
                else
                    col+=sentence[index++].length()+1;
                if(col+sentence[index%n].length()+1>cols){
                    col=0;
                    row++;
                    if(row>=rows)
                        break;
                }
            }
            if(index==n && col<=cols){
                index=0;
                res++;
            }

        }
        return res;
    }

    public int wordsTypingBetter(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }


    //493. Reverse Pairs
    //当然也是可以用fenwick tree做的，但是我们不能说是从后到前面是算小于／2的，因为／2的不一定在map里面啊，还是*2，放进去，然后用总的减去两倍的即可
    int ans=0;
    public void mergePairs(int[]nums,int begin,int mid,int end){
        int i=begin,j=mid+1;
        // for(int i = begin, j = mid+1; i<=mid; i++){
        //     while(j<=end && nums[i]/2.0 > nums[j]) j++;
        //     ans += j-(mid+1);
        // }

        while(i<=mid && j<=end){
            if(nums[i]/2.0 > nums[j])
                j++;
            else{
                ans+=j-mid-1;
                i++;
            }
        }
        while(i<=mid){
            if(nums[i]/2.0 > nums[end]){
                ans+=end-mid;
            }
            i++;
        }
        Arrays.sort(nums,begin,end+1);
    }
    public void reversePairs(int[]nums,int begin,int end){
        if(begin>=end)
            return;
        int mid=(end-begin)/2+begin;
        reversePairs(nums,begin,mid);
        reversePairs(nums,mid+1,end);
        mergePairs(nums,begin,mid,end);
    }
    public int reversePairs(int[] nums) {
        int end=nums.length-1;
        reversePairs(nums,0,end);
        return ans;
    }


    //402 remove k digits
    //get n-k digits
    public String removeKdigits(String num, int k) {
        int n=num.length();
        if(k>=n)
            return "0";
        Stack<Character> stk=new Stack<>();
        k=n-k;
        int i=0;
        while(i<n){
            while((n-i)>(k-stk.size()) && !stk.isEmpty() && stk.peek()>num.charAt(i)){
                stk.pop();
            }
            if(stk.size()<k)
                stk.push(num.charAt(i));
            i++;
        }
        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        String ans=sb.reverse().toString();
        int index=0;
        while(index<ans.length()){
            if(ans.charAt(index)=='0')
                index++;
            else
                break;
        }
        return index==ans.length()?"0":ans.substring(index);
    }

    //560 subarray sum equalsk
    //[0,0,0,0,0,0,0,0,0,0]
    //0
    public int subarraySum(int[] nums, int k) {
        int n=nums.length;
        int sums=0,cnt=0;
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i){
            sums+=nums[i];
            if(map.containsKey(sums-k)){
                cnt+=map.get(sums-k);
            }
            int x =map.getOrDefault(sums,0);
            map.put(sums,x+1);
        }
        return cnt;
    }



    //593 valid square
    public double getDist(double x1,double y1,int x2,int y2){
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int minX=Integer.MAX_VALUE,maxX=Integer.MIN_VALUE;
        int minY=Integer.MAX_VALUE,maxY=Integer.MIN_VALUE;
        minX=Math.min(Math.min(p1[0],p2[0]),Math.min(p3[0],p4[0]));
        minY=Math.min(Math.min(p1[1],p2[1]),Math.min(p3[1],p4[1]));
        maxX=Math.max(Math.max(p1[0],p2[0]),Math.max(p3[0],p4[0]));
        maxY=Math.max(Math.max(p1[1],p2[1]),Math.max(p3[1],p4[1]));
        double midX = (minX+maxX)*1.0/2;
        double midY = (minY+maxY)*1.0/2;
        double dist1 =getDist(midX,midY,p1[0],p1[1]);
        double dist2 =getDist(midX,midY,p2[0],p2[1]);
        double dist3 =getDist(midX,midY,p3[0],p3[1]);
        double dist4 =getDist(midX,midY,p4[0],p4[1]);
        boolean isRectangle= dist1!=0 && dist1==dist2 && dist1==dist3 && dist1==dist4;//只能是长方形
        List<int[]>l=new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        l.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return Integer.compare(o1[0],o2[0]);
                else
                    return Integer.compare(o1[1],o2[1]);
            }
        });

        for(int i=1;i<4;++i){
            for(int j=0;j<i;++j){
                if(l.get(i)[0]==l.get(j)[0] && l.get(i)[1]==l.get(j)[1])
                    return false;
            }
        }
        double dist5=getDist(1.0*l.get(0)[0],1.0*l.get(0)[1],l.get(1)[0],l.get(1)[1]);
        double dist6= getDist(1.0*l.get(0)[0],1.0*l.get(0)[1],l.get(2)[0],l.get(2)[1]);
        return dist5!=0 && dist5==dist6;
    }

    //C42,用set来装，然后不包含0，并且size=2


    //说实话，用map来做的不一定能想到，不过具体关系的用用map也无所谓。我还以为是lis呢
    public int findLHS(int[] nums) {
        Map<Integer,Integer>map=new HashMap<>();
        for(int x:nums){
            if(!map.containsKey(x))
                map.put(x,1);
            else
                map.put(x,map.get(x)+1);
        }
        int n=nums.length;
        int maxRes=0;
        for(int i=0;i<n;++i){
            if(map.containsKey(nums[i]-1)||map.containsKey(nums[i]+1)){
                int a = map.getOrDefault(nums[i]-1,0);
                int b = map.getOrDefault(nums[i]+1,0);
                maxRes = Math.max(maxRes,map.get(nums[i])+Math.max(a,b));
            }
        }
        return maxRes;
    }
    //581
    public int findUnsortedSubarray(int[] nums) {
        //正反扫两次
        int index1=0,n=nums.length,index2=n-1;
        while(index1<n-1){
            if(nums[index1]>nums[index1+1])
                break;
            index1++;
        }

        while(index2>0){
            if(nums[index2]<nums[index2-1])
                break;
            index2--;
        }

        int minVal=Integer.MAX_VALUE,maxVal=Integer.MIN_VALUE;
        for(int i=index1;i<=index2;++i){//注意，必须是从index1 到index2，都要包含
            minVal=Math.min(nums[i],minVal);
            maxVal=Math.max(nums[i],maxVal);
        }
        for(int i=0;i<=index1;++i){
            if(nums[i]>minVal){
                index1=i;
                break;
            }
        }
        for(int j=n-1;j>=index2;--j){
            if(nums[j]<maxVal){
                index2=j;
            }
        }
        return index2-index1+1;
    }

    //582 kill process 主要是队列，一级孩子一级孩子计算下去。
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer,List<Integer>>map=new HashMap<>();
        int n=pid.size();
        for(int i=0;i<n;++i){
            if(!map.containsKey(ppid.get(i)))
                map.put(ppid.get(i),new ArrayList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }

        List<Integer>res=new ArrayList<>();
        Queue<Integer>q=new LinkedList<>();
        q.offer(kill);
        while(!q.isEmpty()){
            int top=q.poll();
            res.add(top);
            List<Integer>child=map.getOrDefault(top,new ArrayList<>());
            if(!child.isEmpty()){
                for(int x:child)
                    q.offer(x);
            }
        }
        return res;
    }

    //543 the diameter of dfs
    int res=1;

    public int dfs(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null)
            return 1;
        int l= dfs(root.left);
        int r =dfs(root.right);
        res=Math.max(res,l+r+1);
        return Math.max(l,r)+1;//返回什么才是应该想清楚的

    }
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res-1;
    }


    //599

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer>map1=new HashMap<>();
        Map<String,Integer>map2=new HashMap<>();
        int m=list1.length,n=list2.length;
        for(int i=0;i<m;++i){
            map1.put(list1[i],i);
        }
        for(int i=0;i<n;++i){
            map2.put(list2[i],i);
        }
        List<String>res=new ArrayList<>();
        int index= Integer.MAX_VALUE;
        if(m<n){
            for(int i=0;i<m;++i){
                if(map2.containsKey(list1[i]) && index>(i+map2.get(list1[i]))){
                    index=i+map2.get(list1[i]);
                }
            }
            for(int i=0;i<m;++i){
                if(map2.containsKey(list1[i]) && index==(i+map2.get(list1[i]))){
                    res.add(list1[i]);
                }
            }


        }else{
            for(int i=0;i<n;++i){
                if(map1.containsKey(list2[i]) && index>(i+map1.get(list2[i]))){
                    index=i+map1.get(list2[i]);
                }
            }

            for(int i=0;i<n;++i){
                if(map1.containsKey(list2[i]) && index==(i+map1.get(list2[i]))){
                    res.add(list2[i]);
                }
            }

        }
        String[]ans =new String[res.size()];
        for(int i=0;i<res.size();++i){
            ans[i]=res.get(i);
        }
        return ans;
    }



    //the maze II
    //505
    class Point {
        int x,y,dist;
        public Point(int _x, int _y,int _di) {x=_x;y=_y;dist=_di;}
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m=maze.length, n=maze[0].length;
        if (start[0]==destination[0] && start[1]==destination[1]) return 0;
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        int [][] visited=new int[m][n];
        for(int i=0;i<m;++i)
            Arrays.fill(visited,Integer.MAX_VALUE);
        LinkedList<Point> list=new LinkedList<>();
        list.offer(new Point(start[0], start[1],0));
        while (!list.isEmpty()) {
            Point p=list.poll();
            int x=p.x, y=p.y;
            if(visited[x][y]<=p.dist)//关键
                continue;
            visited[x][y]=p.dist;
            for (int i=0;i<4;i++) {
                int xx=x, yy=y;
                int di=0;
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                    di++;
                }
                xx-=dir[i][0];
                yy-=dir[i][1];
                if (xx==destination[0] && yy==destination[1]) return p.dist+di-1;
                list.offer(new Point(xx, yy,p.dist+di-1));
            }
        }
        return -1;
    }

    //564 array nesting
    //TLE 不能理解啊,同一个循环内的所有数，都有相同的周期，所以可以用一个数组来记录一个周期内的所有数，这样就省了很多时间
    public int arrayNesting(int[] nums) {
        int maxSize=0,n=nums.length;
        boolean []vis = new boolean[n];
        for(int i=0;i<n;++i){
            int first=nums[i];
            if(vis[first])
                continue;
            int num=first;
            int cnt=1;
            vis[first]=true;
            while(first!=nums[num]){
                cnt++;
                num=nums[num];
                vis[num]=true;
            }
            maxSize=Math.max(maxSize,cnt);
        }
        return maxSize;
    }

    //583. Delete Operation for Two Strings, 幸好过了，不然就是个SB了。

    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        int m=word1.length();
        int n=word2.length();
        int [][]dp=new int[m+1][n+1];
        for(int i=1;i<=m;++i)
            dp[i][0]=i;
        for(int i=1;i<=n;++i)
            dp[0][i]=i;
        for(int i=1;i<=m;++i){
            for(int j=1;j<=n;++j){
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1])+1,dp[i-1][j-1]);
                else
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+1;
            }
        }
        return dp[m][n];
    }
}
