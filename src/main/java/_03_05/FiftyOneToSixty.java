package _03_05;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by tao on 3/5/17.
 */
public class FiftyOneToSixty {



    //这两题和solve suduko,做个比较，希望能到一些经验吧
    //51 NQueen

    public void dfs(List<List<String>>res,int[]ans,int pos){
        if(pos==ans.length){
            List<String>path=new ArrayList<>();
            for(int i=0;i<pos;++i){
                StringBuilder sb=new StringBuilder();
                for(int j=1;j<=pos;++j)
                    sb.append(ans[i]==j?'Q':'.');
                path.add(sb.toString());
            }
            res.add(path);
            return;
        }

        for(int i=1;i<=ans.length;++i){
            if(check(ans,pos,i)){
                ans[pos]=i;
                dfs(res,ans,pos+1);//forget about plus one
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>>res=new ArrayList<>();
        int []ans=new int[n];
        dfs(res,ans,0);
        return res;
    }


    //52 nqueensII
    //backtracking algo
    //actually you can use the min conflict
    //also you can use bit
    public boolean check(int[]res,int pos,int x){
        for(int i=0;i<pos;++i){
            if(res[i]==x ||Math.abs(res[i]-x)==pos-i)
                return false;
        }
        return true;
    }
    public void dfs(int[]res,int []cnt,int pos){
        if(pos==res.length){
            cnt[0]++;
            return;
        }

        for(int i=1;i<=res.length;++i){
            if(check(res,pos,i)){
                res[pos]=i;
                dfs(res,cnt,pos+1);
            }
        }
    }

    public int totalNQueens(int n) {
        int []res=new int[n];
        int []cnt=new int[1];//result;
        dfs(res,cnt,0);
        return cnt[0];
    }


}
