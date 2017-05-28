package _04_23;

import java.util.*;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredSixtySixToFourHundredSeventyFive {




    // 467. Unique Substrings in Wraparound String
    //都是dp，都不会啊，稍微难一点的dp就跪了，真是个忧伤的故事
    public int findSubstringInWraproundString(String p) {
        return 0;
    }

    //471 encode string with shortest length
    public String encode(String s) {
        //不能再逃避了，dp 字符串的得不停的链接，不停的和过去的经验connected
        int n=s.length();
        String[][]dp=new String[n][n];
        for(int l=0;l<n;++l){
            for(int i=0;i<n-l;++i){
                int j=i+l;
                String substr=s.substring(i,j+1);
                if(j-i<4){
                    dp[i][j]=substr;
                }//if string length<5,encode is uncessary
                else{
                    dp[i][j]=substr;
                    for(int k=i;k<j;++k){
                        if(dp[i][k].length()+dp[k+1][j].length()<dp[i][j].length()){
                            dp[i][j]=dp[i][k]+dp[k+1][j];
                        }
                    }

                    //loop for checking if string can itself found some pattern
                    for(int k=0;k<substr.length();++k){
                        String repeatStr=substr.substring(0,k+1);
                        if(repeatStr!=null && substr.length()%repeatStr.length()==0 && substr.replaceAll(repeatStr,"").length()==0){
                            String ss =substr.length()/repeatStr.length()+"["+dp[i][i+k]+"]";
                            if(ss.length()<dp[i][j].length())
                                dp[i][j]=ss;
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    //473 Matchsticks to Square
    //dfs 查4次，看看能不能组成,逆序查，效率更高
    //我说为啥想不出来之前的做法，没啥想法，嗯嗯，发现之前是套用前面一到题的思路，讲一个数组分成和相等的n份。
    //分成2份是背包，分成多份是dfs

    List<Integer>paths=null;
    public boolean dfs(List<Integer>indexs,List<Integer>arr,int sum,int index){
        if(sum==0){
            paths=new ArrayList<>(indexs);
           return true;
        }
        if(index>=arr.size())
            return false;
        for(int i=index;i<arr.size();++i){
            if(sum>=arr.get(i)){
                indexs.add(i);
                if(dfs(indexs,arr,sum-arr.get(i),i+1))
                    return true;
                indexs.remove(indexs.size()-1);
            }
        }
        return false;
    }
    public boolean makesquare(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int x:nums)
            sum+=x;
        if(sum%4!=0)
            return false;
        int cnt=0;
        List<Integer>indexs=new ArrayList<>();
        List<Integer>arr=new ArrayList<>();
        for(int i=0;i<n;++i){
            arr.add(nums[i]);
            if(nums[i]==sum/4){
                cnt++;
                indexs.add(i);
            }
        }
        int ind=0;
        List<Integer>copy=new ArrayList<>();
        for(int j=0;j<arr.size();++j){
            if(ind==indexs.size()||j!=indexs.get(ind))
                copy.add(arr.get(j));
            else
                ind++;

        }
        arr=new ArrayList<>(copy);
        Collections.sort(arr,Collections.reverseOrder());
        for(int i=0;i<4-cnt;++i){
            paths=new ArrayList<>();
            dfs(new ArrayList<>(),arr,sum/4,0);
            if(paths.isEmpty())
                return false;
            copy=new ArrayList<>();
            ind=0;
            for(int j=0;j<arr.size();++j){
                if(ind==paths.size()||j!=paths.get(ind)){
                    copy.add(arr.get(j));
                }else{
                    ind++;
                }
            }
            arr=new ArrayList<>(copy);
        }
        return true;
    }
    //分成多份的

    //only positive way

    public boolean isKPartitionPossibleRec(int []arr,int subsetSum[],boolean []taken,int subset,int K,int N,int curIdx,int limit){
        if(subsetSum[curIdx]==subset){

            ///*  current index (K - 2) represents (K - 1) subsets of equal
            //sum last subsetition will already remain with sum 'subset'*/
            if(curIdx==K-2)
                return true;
            return isKPartitionPossibleRec(arr,subsetSum,taken,subset,K,N,curIdx+1,N-1);
        }

        for(int i=limit;i>=0;--i){
            if(taken[i])
                continue;
            int tmp=subsetSum[curIdx]+arr[i];
            if(tmp<=subset){
                taken[i]=true;
                subsetSum[curIdx]+=arr[i];
                boolean next = isKPartitionPossibleRec(arr,subsetSum,taken,subset,K,N,curIdx,i-1);
                taken[i]=false;
                subsetSum[curIdx]-=arr[i];
                if(next)
                    return true;
            }
        }
        return false;
    }

    public boolean isKPartitionPossible(int[]arr,int K){
        int N= arr.length;
        if(K==1)
            return true;
        if(N<K)
            return false;

        int sum=0;
        for(int i=0;i<N;++i)
            sum+=arr[i];
        if(sum%K!=0)
            return false;

        int subset=sum/K;
        int []subsetSum=new int[K];
        boolean []taken=new boolean[N];

        subsetSum[0]=arr[N-1];
        taken[N-1]=true;
        if(subset<subsetSum[0])
            return false;
        return isKPartitionPossibleRec(arr,subsetSum,taken,subset,K,N,0,N-1);
    }


    //474 ones and zeros
    public int findMaxForm(String[] strs, int m, int n) {
        int k=strs.length;
        int [][]dp=new int[m+1][n+1];//m is zero and n is 1
        for(int i=0;i<k;++i){
            char []ss=strs[i].toCharArray();
            int ones = 0,length=ss.length;
            for(char c:ss){
                if(c=='1')
                    ones++;
            }
            for(int v1=m;v1>=length-ones;--v1){
                for(int v2=n;v2>=ones;--v2){

                    dp[v1][v2]=Math.max(dp[v1][v2],dp[v1-length+ones][v2-ones]+1);

                }
            }
        }
        return dp[m][n];
    }

     // 475 heaters, 我记得还是挺难的,wrong answer
     public int findRadius(int[] houses, int[] heaters) {
        int m=houses.length,n=heaters.length;
         Arrays.sort(houses);
         Arrays.sort(heaters);
         //相差不大，唉，就是没想到，我这种方法好歹也过了一半的测试样栗子
//        int res=0,i=0,j=0;
//        while(i<m && j<n-1){
//            res=Math.max(res,Math.min(Math.abs(houses[i]-heaters[j]),Math.abs(houses[i]-heaters[j+1])));
//            i++;
//            if(i<m &&houses[i]>heaters[j+1])
//                j++;
//        }
//        while(i<m){
//            res=Math.max(res,Math.abs(houses[i]-heaters[n-1]));
//            i++;
//        }
//        return res;
         //总而言之就是找到一个合适的heater来帮助取暖
         int res=Integer.MIN_VALUE;
         int j=0;
         for(int i=0;i<houses.length;++i){
             while(j<heaters.length-1 && Math.abs(heaters[j]-houses[i])>=Math.abs(heaters[j+1]-houses[i]))//少了个等于就坏事了
                 j++;
             res=Math.max(res,Math.abs(heaters[j]-houses[i]));
         }
         return res;
     }
}
