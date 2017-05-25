package _04_21;

import common.TreeNode;
import common.Tuple;

import java.util.*;

/**
 * Created by tao on 5/24/17.
 */
public class FourHundredFourtySixToFourHundredFiftyFive {

    //446 也不太会，这是怎么了。。。Arithmetic Slices II - Subsequence

    //447. Number of Boomerangs

    public int getDist(int[]point1,int[]point2){
        return (point1[0]-point2[0])*(point1[0]-point2[0])+(point1[1]-point2[1])*(point1[1]-point2[1]);
    }

    //还是挺好玩的
    //居然过了，一个hashmap一般能降一级 复杂度
    public int numberOfBoomerangs(int[][] points) {
        int n=points.length,cnt=0;
        for(int i=0;i<n;++i){
            Map<Integer,Integer>map=new HashMap<>();
            for(int j=0;j<n;++j){
                if(i==j)
                    continue;
                int dist = getDist(points[i],points[j]);
                if(!map.containsKey(dist))
                    map.put(dist,1);
                else
                    map.put(dist,map.get(dist)+1);
            }
            for(Map.Entry<Integer,Integer>entry:map.entrySet()){
                int val = entry.getValue();
                cnt +=val*(val-1);
            }
        }
        return cnt;
    }

    //用set估计每个人都会吧
    //448…
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer>res=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            int index =Math.abs(nums[i])-1;
            if(nums[index]>0)
                nums[index]=-nums[index];
        }
        for(int i=1;i<=n;++i){
            if(nums[i-1]>0)
                res.add(i);
        }
        return res;
    }

    //449 in design

    //450 delete node in bst
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return null;
        if(root.val>key)
            root.left=deleteNode(root.left,key);
        else if(root.val<key)
            root.right=deleteNode(root.right,key);
        else{

            //leaf node
            if(root.left==null && root.right==null)
                return null;

            //one child
            if(root.left==null){
                root=root.right;
                return root;
            }
            if(root.right==null){
                root=root.left;
                return root;
            }

            //two children
            //find the next larger element and change its value;
            TreeNode node=root.right;
            while(node.left!=null){
                node=node.left;
            }
            root.val=node.val;
            root.right=deleteNode(root.right,root.val);
        }
        return root;
    }


    //451 sort character by frequency
    //bucket sort
    public String frequencySort(String s) {
        int []cnt=new int[128];
        char []ss=s.toCharArray();
        for(char c:ss)
            cnt[c]++;
        List<Tuple<Character,Integer>>res=new ArrayList<>();
        for(int i=0;i<128;++i){
            if(cnt[i]>0){
                res.add(new Tuple((char)i,cnt[i]));
            }
        }
        res.sort(new Comparator<Tuple<Character, Integer>>() {
            @Override
            public int compare(Tuple<Character, Integer> o1, Tuple<Character, Integer> o2) {
                if(o1.y!=o2.y)
                    return o2.y-o1.y;
                else
                    return o2.x-o1.x;
            }
        });
        StringBuilder sb=new StringBuilder();
        for(Tuple tuple:res){
            int xx=(int)tuple.y;
            for(int i=0;i<xx;++i)
                sb.append(tuple.x);
        }
        return sb.toString();
    }

    //452 Minimum Number of Arrows to Burst Balloons，很有意思的一道题刚开始可能感觉有点难，到那时后面就好多了
    //感觉是合并区间的事情啊，能合并就尽量合并,又不是叫你找位置，能尽量合并的就合并，这样即使1+3变成2+2也是一样的。
    public int findMinArrowShots(int[][] points) {
        //先排个序
        int n=points.length;
        if(n==0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return o1[0]-o2[0];
                else
                    return o1[1]-o2[1];
            }
        });
        int start=points[0][0],end=points[0][1],cnt=0;
        for(int i=1;i<n;++i){
            if(points[i][0]>end){
                cnt++;
                start=points[i][0];
                end=points[i][1];
            }else{
                start=points[i][0];
                end=Math.min(end,points[i][1]);
            }
        }
        return cnt+1;
    }

    //453  Minimum Moves to Equal Array Elements
    public int minMoves(int[] nums) {
        int n=nums.length;
        if(n==0)
            return 0;
        int minVal=Integer.MAX_VALUE,sum=0;
        for(int i=0;i<n;++i){
            sum+=nums[i];
            minVal=Math.min(minVal,nums[i]);
        }
        return sum-n*minVal;
    }

    //454 4Sumii
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //hashmap, 好像很经典的题目
        int n=A.length;
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                int sum=A[i]+B[j];
                if(!map.containsKey(sum))
                    map.put(sum,1);
                else
                    map.put(sum,map.get(sum)+1);
            }
        }
        int cnt=0;
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                int sum=C[i]+D[j];
                if(map.containsKey(-sum)){
                    cnt+=map.get(-sum);
                }
            }
        }
        return cnt;
    }

    //455 assign cookies
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m=g.length,n=s.length;
        int i=0,j=0,cnt=0;
        while(i<m && j<n){
            if(g[i]<=s[j]){
                cnt++;
                i++;
                j++;
            }else if(g[i]>s[j]){
                j++;
            }
        }
        return cnt;
    }
}

