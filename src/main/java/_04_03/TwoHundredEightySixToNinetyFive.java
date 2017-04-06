package _04_03;

import java.util.*;

/**
 * Created by tao on 4/3/17.
 */
public class TwoHundredEightySixToNinetyFive {

    //287 find the duplicate number
    //hashmap，这么弱智的就不放了
    //绝对值的做法
    //between 1 and n (inclusive),n+1个数
    //最优的解法应该是快慢指针，打死我都想不到啊
    //真实的做法不应该出现sort，return ind+1就可以了。
    public int findDuplicate(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        for(int i=0;i<n;++i){
            int ind=Math.abs(nums[i])-1;
            if(nums[ind]<0)
                return -nums[ind];
            else
                nums[ind]=-nums[ind];
        }
        return 0;
    }

    //286 walls and gates, 和周赛的某一题是很像的
    //把所有的1进队，不能对每一个点进行bfs找到0
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length==0||rooms[0].length==0)
            return;
        int m=rooms.length,n=rooms[0].length;
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(rooms[i][j]==0)
                    q.offer(new int[]{i,j});
            }
        }

        int ans=0;
        int[]dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        while(!q.isEmpty()){
            ans++;
            int size=q.size();
            for(int i=0;i<size;++i){
                int[]top=q.poll();
                int x=top[0];
                int y=top[1];
                for(int k=0;k<4;++k){
                    int xx=x+dx[k];//好久不写，居然生疏了，我操。
                    int yy=y+dy[k];
                    if(xx>=0 && xx<m && yy>=0 && yy<n && rooms[xx][yy]==Integer.MAX_VALUE){
                        rooms[xx][yy]=ans;
                        q.offer(new int[]{xx,yy});
                    }
                }
            }
        }
    }

    //dfs way
    private static int[] d = {0, 1, 0, -1, 0};
    public void wallsAndGatesDFSway(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0) dfs(rooms, i, j);
    }

    public void dfs(int[][] rooms, int i, int j) {
        for (int k = 0; k < 4; ++k) {
            int p = i + d[k], q = j + d[k + 1];
            if (0<= p && p < rooms.length && 0<= q && q < rooms[0].length && rooms[p][q] > rooms[i][j] + 1) {
                rooms[p][q] = rooms[i][j] + 1;
                dfs(rooms, p, q);
            }
        }
    }

    //绝对是精彩绝伦的对决，很高兴能写出来这道题
    //289 game of life,你要说简单吧，其实也很简单，就是copy另外一个数组搞起，但是这样空间复杂度上去了，肯定是不行的
    //充分利用bit的一些信息，能使得节省空间
    public void gameOfLife(int[][] board) {
        if(board.length==0||board[0].length==0)
            return;
        int m=board.length,n=board[0].length;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){

                int neighbors=countNeightbors(board,i,j);
                if(neighbors==3)
                    board[i][j]+=2;
                if(neighbors==2 && board[i][j]==1)
                    board[i][j]+=2;

            }
        }

        //last 把所有的位向右移动一
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                board[i][j]>>=1;
            }
        }


    }

    //count 统计neighbor
    public int countNeightbors(int[][]board,int x,int y){
        int neighbors=0;
        int m=board.length,n=board[0].length;
        for(int i=Math.max(0,x-1);i<=Math.min(m-1,x+1);++i){
            for(int j=Math.max(0,y-1);j<=Math.min(n-1,y+1);++j){
                if(i==x && j==y)
                    continue;
                if((board[i][j]&0x1)==1)
                    neighbors++;
            }
        }
        return neighbors;
    }

    //290 word pattern
    //之前的isomorphic string 最佳是用两个cnt数组
    public boolean wordPattern(String pattern, String[] strs) {
        char[]ss=pattern.toCharArray();
        //String[]strs=str.split("\\s");
        if(ss.length!=strs.length)
            return false;
        int[]cnt=new int[26];
        Map<String,Integer>map=new HashMap<>();
        int count=1;
        int n=pattern.length();
        for(int i=0;i<n;++i){
            int index=map.getOrDefault(strs[i],0);
            if(index!=cnt[ss[i]-'a'])
                return false;
            if(index==0){
                map.put(strs[i],count);
                cnt[ss[i]-'a']=count++;
            }
        }
        return true;
    }

    //two map
    //很简单的思想，正反各来一次
    public boolean wordPatternTwoMap(String pattern, String str) {
        String[]strs=str.split(" ");
        Map<String,Character>s2c=new HashMap<>();
        Map<Character,String >c2s=new HashMap<>();
        if(pattern.length()!=strs.length)
            return false;
        int n=strs.length;
        for(int i=0;i<n;++i){
            boolean existString=s2c.containsKey(strs[i]);
            boolean existCharacter=c2s.containsKey(pattern.charAt(i));
            if(!existCharacter && !existString){
                s2c.put(strs[i],pattern.charAt(i));
                c2s.put(pattern.charAt(i),strs[i]);
                continue;
            }
            if((existCharacter && !existString)||(!existCharacter && existString))
                return false;
            if(existCharacter && existString){
                String sub=c2s.get(pattern.charAt(i));
                char c=s2c.get(strs[i]);
                if(!sub.equals(strs[i])||c!=pattern.charAt(i))
                    return false;
            }
        }
        return true;
    }


    //291 word pattern II TLE version
//    public boolean backtrack(String[]strs,int index,String pattern,String str,int pos){
//        if(index==pattern.length() && str.length()==pos){
//            return wordPattern(pattern,strs);
//        }
//        if(index>=pattern.length())
//            return false;
//        for(int i=pos+1;i<=str.length();++i){
//            String sub=str.substring(pos,i);
//            strs[index]=sub;
//            if(backtrack(strs,index+1,pattern,str,i))
//                return true;
//        }
//        return false;
//    }
//    public boolean wordPatternMatch(String pattern, String str) {
//        if(pattern.length()>str.length())
//            return false;
//        int n=pattern.length();
//        String[]strs=new String[n];
//        return backtrack(strs,0,pattern,str,0);
//    }


    //pass with lower efficiency
    public boolean wordPattern(String pattern, String[] strs,int index1) {
        char[]ss=pattern.toCharArray();
        //String[]strs=str.split("\\s");
        int[]cnt=new int[26];
        Map<String,Integer>map=new HashMap<>();
        int count=1;
        int n=index1+1;
        for(int i=0;i<n;++i){
            int index=map.getOrDefault(strs[i],0);
            if(index!=cnt[ss[i]-'a'])
                return false;
            if(index==0){
                map.put(strs[i],count);
                cnt[ss[i]-'a']=count++;
            }
        }
        return true;
    }
    public boolean backtrack(String[]strs,int index,String pattern,String str,int pos){
        if(index==pattern.length() && str.length()==pos){
            return true;//wordPattern(pattern,strs);
        }
        if(index>=pattern.length())
            return false;
        for(int i=pos+1;i<=str.length();++i){
            String sub=str.substring(pos,i);
            strs[index]=sub;
            if(wordPattern(pattern,strs,index) &&backtrack(strs,index+1,pattern,str,i))//提前剪枝
                return true;
        }
        return false;
    }
    public boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length()>str.length())
            return false;
        int n=pattern.length();
        String[]strs=new String[n];
        return backtrack(strs,0,pattern,str,0);
    }

    //看了下最近做这道题的时间是2个月前，不会的还是不会，嗯，就是这么sb，你越是怕，越是不敢挑战，越会欺负你。
    //看了下高分答案,这种backtracking的题应该是很明显的,还要添加set，"ab" "aa"
    //递归+map好像是最爱啊，纯暴力好像出力不讨好
    public boolean wordPatternMatchBetter(String pattern, String str){
        if(pattern.length()>str.length())
            return false;
        Map<Character,String>map=new HashMap<>();
        Set<String>set=new HashSet<>();
        return isMatch(str,0,pattern,0,map,set);
    }
    public boolean isMatch(String str,int i,String pattern,int j,Map<Character,String>map,Set<String>set){
        if(i==str.length() && j==pattern.length())
            return true;
        if(i==str.length()||j==pattern.length())
            return false;
        char c=pattern.charAt(j);
        if(map.containsKey(c)){
            String sub=map.get(c);
            if(!str.startsWith(sub,i))
                return false;
            return isMatch(str,i+sub.length(),pattern,j+1,map,set);
        }else{
            for(int k=i;k<str.length();++k){
                String sub=str.substring(i,k+1);
                if(set.contains(sub))
                    continue;
                map.put(c,sub);
                set.add(sub);
                if(isMatch(str,k+1,pattern,j+1,map,set))
                    return true;
                map.remove(c);
                set.remove(sub);
            }
            return false;
        }
    }






    //292 nim game
    //如果是4的倍数，那我肯定就跪了，但是反之，如果是轮到他是4的倍数，那么他就跪了，4n,4n+1,4n+2,4n+3;只要不是4的倍数，我稳赢
    public boolean canWinNim(int n) {
        return n%4!=0;
    }


    //293 flip game
    public List<String> generatePossibleNextMoves(String s) {
        List<String>res=new ArrayList<>();
        int n=s.length(),start=0;
        while(start<n){
            int index=s.indexOf("++",start);
            if(index==-1)
                break;
            res.add(s.substring(0,index)+"--"+s.substring(index+2));
        }
        return res;
    }

    //294 flip game II


    //想想为啥hashmap回溯不需要删除原来的object，要是删除还缓存个p啊。
    Map<String,Boolean>map=new HashMap<>();
    public boolean canWin(String s) {
        if(map.containsKey(s))
            return map.get(s);
        int index=s.indexOf("++");
        if(index==-1)
            return false;
        StringBuilder sb=new StringBuilder(s);
        int start=0,n=s.length();
        while(start<n){
            index=s.indexOf("++",start);
            if(index==-1)
                break;
            sb.setCharAt(index,'-');
            sb.setCharAt(index+1,'-');
            if(!canWin(sb.toString())){
                map.put(s,true);//不是sb.toString()
                return true;
            }
            sb.setCharAt(index,'+');
            sb.setCharAt(index+1,'+');//少加了个1，卧槽
            start=index+1;
        }
        map.put(s,false);
        return false;

    }

    //用hashmap cache


    //295 in the design
}
