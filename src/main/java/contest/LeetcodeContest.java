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
        return diameterOfBinaryTree(root,height);
    }

}
