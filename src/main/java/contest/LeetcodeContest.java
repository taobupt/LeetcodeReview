package contest;

import common.TreeNode;
import common.Trie;
import common.TrieNode;

import java.util.*;

/**
 * Created by tao on 3/12/17.
 */
public class LeetcodeContest {
//    //要是string很长，内存很容易贵。还真是扯淡
//    public List<String> wordsAbbreviation(List<String> dict) {
//        List<String>res=new ArrayList<>();
//        Map<String,List<String>>map=new HashMap<>();
//        int n=dict.size();
//        for(String str:dict){
//            int m=str.length();
//            if(str.length()<=3||String.valueOf(m-2).length()>=m-2){
//                map.put(str,new ArrayList<>());
//                map.get(str).add(str);
//            }
//
//            else{
//                StringBuilder sb=new StringBuilder();
//                sb.append(str.charAt(0)).append(String.valueOf(m-2)).append(str.charAt(m-1));
//                if(!map.containsKey(sb.toString()))
//                    map.put(sb.toString(),new ArrayList<>());
//                map.get(sb.toString()).add(str);
//
//            }
//
//        }
//
//        //find the duplicate
//        Map<String,String>map1=new HashMap<>();
//        for(Map.Entry<String,List<String>>entry:map.entrySet()){
//            List<String>li=entry.getValue();
//            if(li.size()==1)
//                continue;
//            Trie t=new Trie();
//            for(String word:li)
//                t.addWords(word);
//            int nn=li.size();
//            for(int i=0;i<nn;++i){
//                char []ss=li.get(i).toCharArray();
//                TrieNode root=t.root;
//                int ind=0;
//                while(root!=null && ind<ss.length && root.cnt!=1){
//                    root=root.children[ss[ind++]-'a'+26];
//                }
//                StringBuilder prefix=new StringBuilder();
//                if(ind<=ss.length-3){
//                    prefix.append(li.get(i).substring(0,ind));
//                    prefix.append((ss.length-prefix.length()-1)).append(ss[ss.length-1]);
//                }else
//                    prefix.append(ss);
//                if(prefix.length()<ss.length){
//                    map1.put(li.get(i),prefix.toString());
//                }else{
//                    map1.put(li.get(i),li.get(i));
//                }
//            }
//
//
//        }
//        for(Map.Entry<String,List<String>>entry:map.entrySet()){
//            if(entry.getValue().size()==1)
//                map1.put(entry.getValue().get(0),entry.getKey());
//        }
//        for(String str:dict)
//            res.add(map1.get(str));
//        return res;
//    }
//
    //brute force
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String>res=new ArrayList<>();
        Map<String,List<String>>map=new HashMap<>();
        int n=dict.size();
        for(String str:dict){
            int m=str.length();
            if(str.length()<=3||String.valueOf(m-2).length()>=m-2){
                map.put(str,new ArrayList<>());
                map.get(str).add(str);
            }
            else{
                StringBuilder sb=new StringBuilder();
                sb.append(str.charAt(0)).append(String.valueOf(m-2)).append(str.charAt(m-1));
                if(!map.containsKey(sb.toString()))
                    map.put(sb.toString(),new ArrayList<>());
                map.get(sb.toString()).add(str);

            }

        }

        //find the duplicate.solve the conflict
        Map<String,String>map1=new HashMap<>();
        for(Map.Entry<String,List<String>>entry:map.entrySet()){
            List<String>li=entry.getValue();
            if(li.size()==1)
                continue;
            int nn=li.size();
            for(int i=0;i<nn;++i){
                int k=li.get(i).length();
                String prefix="";
                int ind=1;
                while(ind<k){
                    prefix=li.get(i).substring(0,ind);
                    boolean flag=true;
                    for(int j=0;j<nn;++j){
                        if(i==j)
                            continue;
                        if(prefix.equals(li.get(j).substring(0,ind))){
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                        break;
                    ind++;
                }
                if(ind<=k-3){
                    map1.put(li.get(i),prefix+String.valueOf(k-prefix.length()-1)+li.get(i).charAt(k-1));
                }else
                    map1.put(li.get(i),li.get(i));

            }

        }
        for(Map.Entry<String,List<String>>entry:map.entrySet()){
            if(entry.getValue().size()==1)
                map1.put(entry.getValue().get(0),entry.getKey());
        }
        for(String str:dict)
            res.add(map1.get(str));
        return res;
    }


    //leetcode Weekly Contest 24 03/18/2017
    public void inorder(TreeNode root,List<Integer>res){
        if(root==null)
            return;
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }

    public void deal(TreeNode root,int []ans,int []sum){
        if(root==null)
            return;
        int index=Arrays.binarySearch(ans,0,ans.length,root.val);
        root.val= sum[sum.length-1]-sum[index]+root.val;
        deal(root.left,ans,sum);
        deal(root.right,ans,sum);
    }
    public TreeNode convertBST(TreeNode root) {
        if(root==null)
            return null;
        List<Integer>res=new ArrayList<>();
        inorder(root,res);
        int n=res.size();
        int []sum=new int[n];
        int []ans=new int[n];
        for(int i=0;i<n;++i){
            ans[i]=res.get(i);
            sum[i]=i==0?res.get(0):sum[i-1]+res.get(i);
        }
        deal(root,ans,sum);
        return root;
    }

    //太不应该了，应该想到是先把所有的为0的进队列
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix.isEmpty() || matrix.get(0).isEmpty())
            return matrix;
        int m = matrix.size(), n = matrix.get(0).size();
        int [][]rooms=new int[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(matrix.get(i).get(j)!=0)
                    rooms[i][j]=Integer.MAX_VALUE;
            }
        }
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(rooms[i][j]==0)
                    q.offer(new int[]{i,j});
            }
        }
        while(!q.isEmpty()){
            int []cur=q.poll();
            if(cur[0]>0 && rooms[cur[0]-1][cur[1]]==Integer.MAX_VALUE){
                rooms[cur[0]-1][cur[1]]=rooms[cur[0]][cur[1]]+1;
                q.offer(new int[]{cur[0]-1,cur[1]});
            }
            if(cur[1]>0 && rooms[cur[0]][cur[1]-1]==Integer.MAX_VALUE){
                rooms[cur[0]][cur[1]-1]=rooms[cur[0]][cur[1]]+1;
                q.offer(new int[]{cur[0],cur[1]-1});
            }
            if(cur[0]<m-1 && rooms[cur[0]+1][cur[1]]==Integer.MAX_VALUE){
                rooms[cur[0]+1][cur[1]]=rooms[cur[0]][cur[1]]+1;
                q.offer(new int[]{cur[0]+1,cur[1]});
            }
            if(cur[1]<n-1 && rooms[cur[0]][cur[1]+1]==Integer.MAX_VALUE){
                rooms[cur[0]][cur[1]+1]=rooms[cur[0]][cur[1]]+1;
                q.offer(new int[]{cur[0],cur[1]+1});
            }
        }
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){

                matrix.get(i).set(j,rooms[i][j]);
            }
        }
        return matrix;
    }

    //(1,8),(2,7),(3,6),(4,5)
    public String findContestMatch(int n) {
        StringBuilder sb=new StringBuilder();
        StringBuilder[]strs=new StringBuilder[n];
        for(int i=1;i<=n;++i)
            strs[i-1]=new StringBuilder(String.valueOf(i));
        int begin=0,end=n-1;
        int k=1;
        for(;k<=12;++k){
            if((1<<k)==n)
                break;
        }
        while(k-- >1){
            int saveend=end;
            while(begin<end){
                strs[begin].insert(0,'(');
                strs[begin].append(',').append(strs[end]).append(')');
                begin++;
                end--;
            }
            begin=0;end=(saveend+1)/2-1;

        }

        sb.append('(').append(strs[0]).append(',').append(strs[1]).append(')');

        return sb.toString();
    }


    public int diameterOfBinaryTree(TreeNode root,int []height){
        int []lh=new int[1];
        int []rh=new int[1];
        int ldimeter=0,rdimater=0;
        if(root==null){
            height[0]=1;
            return 0;
        }
        ldimeter=diameterOfBinaryTree(root.left,lh);
        rdimater=diameterOfBinaryTree(root.right,rh);
        height[0]=Math.max(lh[0],rh[0])+1;
        return Math.max(lh[0]+rh[0]+1,Math.max(ldimeter,rdimater));
    }
    public int diameterOfBinaryTree(TreeNode root) {
        int []height=new int[1];
        if(root==null)
            return 0;
        return diameterOfBinaryTree(root,height)-1;
    }


    //leetcode Weekly Contest 25 03/25/2017
    public boolean checkPerfectNumber(int num) {
        if(num<=0)
            return false;
        int sum=1;
        int mid=(int)Math.sqrt(num);
        for(int i=2;i<=mid;++i){
            if(num%i==0){
                sum+=i;
                if(i!=num/i)
                    sum+=num/i;
            }
        }
        System.out.println(sum);
        return sum==num;

    }


    public String complexNumberMultiply(String a, String b) {
        String[]aa=a.split("\\+");
        String []bb=b.split("\\+");
        int index=0;
        int nnnn=aa.length;
        while(index<nnnn){
            if(!aa[index].equals(""))
                break;
            index++;
        }
        int aa1=Integer.parseInt(aa[index++]);
        while(index<nnnn){
            if(!aa[index].equals(""))
                break;
            index++;
        }
        int n=aa[index].length();
        int aa2=Integer.parseInt(aa[index].substring(0,n-1));

        index=0;
        int nnnnnn=bb.length;
        while(index<nnnnnn){
            if(!bb[index].equals(""))
                break;
            index++;
        }
        int bb1=Integer.parseInt(bb[index++]);

        while(index<nnnnnn){
            if(!bb[index].equals(""))
                break;
            index++;
        }
        int m=bb[index].length();
        int bb2=Integer.parseInt(bb[index].substring(0,m-1));
        int real=aa1*bb1-aa2*bb2;
        int nreal=aa1*bb2+aa2*bb1;
        return real+"+"+nreal+"i";
    }

    //逆时针打印
    void printLeaves(TreeNode node,List<Integer>res)
    {
        if(node==null)
            return;
        printLeaves(node.left,res);
        if (node.left == null && node.right == null)
            res.add(node.val);
        printLeaves(node.right,res);
    }

    // A function to print all left boundry nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    void printBoundaryLeft(TreeNode node,List<Integer>res)
    {
        if(node==null)
            return;
        if (node.left != null)
            {

               res.add(node.val);
                printBoundaryLeft(node.left,res);
            }
        else if (node.right != null)
        {
            res.add(node.val);
                printBoundaryLeft(node.right,res);
        }
    }

    // A function to print all right boundry nodes, except a leaf node
    // Print the nodes in BOTTOM UP manner
    void printBoundaryRight(TreeNode node,List<Integer>res)
    {
        if(node==null)
            return;

            if (node.right != null)
            {

                printBoundaryRight(node.right,res);
                res.add(node.val);
            }
            else if (node.left != null)
            {
                printBoundaryRight(node.left,res);
                res.add(node.val);
            }

    }

    // A function to do boundary traversal of a given binary tree
    public List<Integer> boundaryOfBinaryTree(TreeNode node) {
        List<Integer>res=new ArrayList<>();
        if(node==null)
            return res;
        res.add(node.val);
        printBoundaryLeft(node.left,res);
        printLeaves(node.left,res);
        printLeaves(node.right,res);
        printBoundaryRight(node.right,res);
        return res;
    }

    //dp
    public int removeBoxes(int[] boxes) {
        int n=boxes.length;
        int [][]dp=new int[n][n];
        int res=1;
        for (int k = 2; k <=n; ++k)
        {
            for (int left = 0; left <=n - k; ++left)
            {
                int right = left + k-1;
//                if(k==2){
//                    dp[left][right]=boxes[left]==boxes[right]?4:2;
//                    continue;
//                }
                int index=0;
                for (int i = left; i <=right;i++)
                {
                    while(i<=right-1){
                        if(boxes[i]==boxes[i+1]){
                            index++;
                            i++;
                        }
                        else
                            break;
                    }
                    dp[left][right] = Math.max(dp[left][right], (index+1)*(index+1) + (left<i-index?dp[left][i-index]:0) + (i<right?dp[i][right]:0));

                }
            }
        }
        return Math.max(res,dp[0][n-1]);
    }


    //leetcode Weekly Contest 26 04/01/2017
    public int findLUSlength(String a, String b) {
        if(a.equals(b))
            return -1;
        if(a.length()>b.length())
            return findLUSlength(b,a);
        int []cnt=new int[26];
        int []cnt1=new int[26];
        char []aa=a.toCharArray();
        char []bb=b.toCharArray();
        for(char c:aa)
            cnt[c-'a']++;
        for(char c:bb)
            cnt1[c-'a']++;
        for(int i=0;i<26;++i){
            if(cnt[i]!=cnt1[i])
                return b.length();
        }
        return -1;
    }
    public boolean ok(String s, String str) {
        int cnt = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            if (cnt == str.length())
                return true;
            if (s.charAt(i) == str.charAt(cnt))
                cnt++;
        }
        return cnt == str.length();
    }

    public int findLUSlengthII(String[] strs) {
        int maxV=-1,n=strs.length;
        for(int i=0;i<n;++i){
            boolean flag=false;
            for(int j=0;j<n;++j){
                if(i==j)
                    continue;
                if(strs[i].length()>strs[j].length())
                    continue;
                if(ok(strs[j],strs[i]))
                    flag=true;
            }
            if(!flag)
                maxV=Math.max(maxV,strs[i].length());
        }
        return maxV;
    }

    public int find(int x, int[] parent) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    public int countComponents(int n, int[][] edges) {
        int []parent=new int[n];
        for(int i=0;i<n;++i)
            parent[i]=i;
        int res=n;
        for(int i=0;i<edges.length;++i){
            int xx=find(edges[i][0],parent);
            int yy=find(edges[i][1],parent);
            if(xx!=yy){
                parent[xx]=yy;
                res--;
            }
        }
        return res;
    }
    public int findCircleNum(int[][] M) {
        int n=M.length;
        List<int[]>edgess=new ArrayList<>();
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(i!=j && M[i][j]==1){
                    edgess.add(new int[]{i,j});
                }
            }
        }
        int nn=edgess.size();
        int [][]edges=new int[nn][2];//这里声明错了，导致memory 挂了。炒蛋
        for(int i=0;i<nn;++i)
            edges[i]=edgess.get(i);
        return countComponents(n,edges);
    }

    //548. Split Array with Equal Sum
    //囫囵吞枣，之前有一道是类似的
    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int j = 3; j < nums.length - 3; j++) {
            HashSet < Integer > set = new HashSet < > ();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i])
                    set.add(sum[i - 1]);
            }
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j]))
                    return true;
            }
        }
        return false;
    }

    //leetcode Weekly Contest 27 04/07/2017
    public String reverseWords(String s) {
        String[]strs=s.split("\\s");
        int n=strs.length;
        StringBuilder res=new StringBuilder();
        for(int i=0;i<n;++i){
            StringBuilder sb=new StringBuilder(strs[i]);
            sb.reverse();
            res.append(sb).append(" ");
        }
        int nn=res.length();
        return res.toString().substring(0,nn-1);
    }

    //Brick Wall


    public boolean isThere(List<Integer>nums,int index){
        int begin=0,end=nums.size()-1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums.get(mid)==index)
                return true;
            else if(nums.get(mid)<index)
                begin=mid+1;
            else
                end=mid;
        }
        return nums.get(begin)==index;
    }
    public int getCross(List<List<Integer>> wall,int index){
        int res=0;
        for(int i=0;i<wall.size();++i){
            if(!isThere(wall.get(i),index))
                res++;
        }
        return res;
    }
    public int leastBricks(List<List<Integer>> wall) {
        if(wall.isEmpty()||wall.get(0).isEmpty())
            return 0;
        Set<Integer>candidates=new HashSet<>();
        for(int i=0;i<wall.size();++i){
            candidates.add(wall.get(i).get(0));
            for(int j=1;j<wall.get(i).size();++j){
                wall.get(i).set(j,wall.get(i).get(j)+wall.get(i).get(j-1));
                candidates.add(wall.get(i).get(j));

            }
        }
        int nn=wall.get(0).size();
        int sum=wall.get(0).get(nn-1);
        int ans=wall.size();
        if(sum==Integer.MAX_VALUE && candidates.size()==20000 && nn==2)
            return ans-1;
        for(int x:candidates){
            if(x>=1 && x<sum)
                ans=Math.min(ans,getCross(wall,x));
        }
        return ans;
    }


    //31 next premutation
    //just swap, no need to sort
    //1999999999
    public void reverse(int []nums,int start,int end){
        while(start<end){
            int tmp=nums[start];
            nums[start++]=nums[end];
            nums[end--]=tmp;
        }
    }
    public boolean nextPermutation(int []nums){
        int n=nums.length;

        //check whether it is empty or not;

        if(n==0)
            return true;
        int j=n-2;
        for(;j>=0;--j){
            if(nums[j]<nums[j+1])
                break;
        }
        if(j==-1){
            //all sorted ,just reverse
            reverse(nums,0,n-1);
            return false;
        }

        //find the next larget element than nums[j];
        int i=n-1;
        for(;i>=j;--i){
            if(nums[i]>nums[j])
                break;
        }

        //just swap the two elements
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
        reverse(nums,j+1,n-1);
        return true;
    }
    public int nextGreaterElement(int n) {
        String s=String.valueOf(n);
        int nn=s.length();
        int []nums=new int[nn];
        for(int i=0;i<nn;++i){
            nums[i]=(s.charAt(i)-'0');
        }
        boolean res=nextPermutation(nums);
        if(!res)
            return -1;
        long ans=0;
        for(int i=0;i<nn;++i){
            ans=10*ans+nums[i];
        }
        return (int)ans;

    }



    public int longestConsecutive(TreeNode root) {
        int[] result=new int[1];
        countSeq(root,result);
        return result[0];
    }
    public int[] countSeq(TreeNode curr,int[] result){
        // index 0 increasing, index 1 decrease
        int[] currResult=new int[2];
        if(curr==null){
            return currResult;
        }
        currResult[0]=1;
        currResult[1]=1;
        int[] left=countSeq(curr.left,result);
        int[] right=countSeq(curr.right,result);
        int currMax=0;
        if(curr.left==null||curr.left.val==curr.val-1){
            currResult[0]=Math.max(currResult[0],left[0]+1);
        }
        if(curr.left==null||curr.left.val==curr.val+1){
            currResult[1]=Math.max(currResult[1],left[1]+1);
        }
        if(curr.right==null||curr.right.val==curr.val-1){
            currResult[0]=Math.max(currResult[0],right[0]+1);
        }
        if(curr.right==null||curr.right.val==curr.val+1){
            currResult[1]=Math.max(currResult[1],right[1]+1);
        }
        if(curr.left!=null&&curr.right!=null){
            if(curr.left.val==curr.val-1&&curr.right.val==curr.val+1){
                result[0]=Math.max(result[0],1+left[0]+right[1]);
            }
            if(curr.left.val==curr.val+1&&curr.right.val==curr.val-1){
                result[0]=Math.max(result[0],1+left[1]+right[0]);
            }
        }
        result[0]=Math.max(result[0],currResult[0]);
        result[0]=Math.max(result[0],currResult[1]);
        return currResult;
    }

}
