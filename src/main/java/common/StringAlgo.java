package common;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by tao on 3/13/17.
 */
public class StringAlgo {

    public int[] buildNext(String pattern){
        char []p=pattern.toCharArray();
        int n=p.length;
        int []next=new int[n+1];
        next[0]=-1;
        int i=0,j=-1;
        while(i<n){
            while(j!=-1 && p[i]!=p[j])
                j=next[j];
            next[++i]=++j;
        }
        return next;
    }

    //所以不能允许p[j] = p[ next[j ]]。如果出现了p[j] = p[ next[j] ]咋办呢？如果出现了，则需要再次递归，即令next[j] = next[ next[j] ]。
    public int[]buildNextOptimal(String pattern){
        char []p=pattern.toCharArray();
        int n=p.length;
        int []next=new int[n+1];
        next[0]=-1;
        int i=0,j=-1;
        while(i<n){
            while(j!=-1 && p[i]!=p[j])
                j=next[j];
            ++i;
            ++j;
            if(i<n-1 && j<n-1 && p[i]==p[j])
                next[i]=next[j];
            else
                next[i]=j;
        }
        return next;
    }

    //return all index of pattern in the text;
    public List<Integer> indexof(String text,String pattern){
        int []next=buildNext(pattern);
        char[]texts=text.toCharArray();
        char []pat=pattern.toCharArray();
        int m=text.length(),n=pat.length;
        int i=0,j=0;
        List<Integer>res=new ArrayList<>();
        while(i<m){
            while(j!=-1 && texts[i]!=pat[j])
                j=next[j];
            i++;
            j++;
            if(j>=n){
                res.add(i-j);
                j=next[j];
            }
        }
        return res;
    }

    //manacher algo
    public int manacher(String p){
        char []pp=p.toCharArray();
        int n=pp.length;
        char []newp=new char[2*n+3];//多一点space；
        Arrays.fill(newp,'#');
        newp[0]='$';//防止越界
        newp[2*n+2]='\0';

        int []len=new int[2*n+2];
        for(int i=0;i<n;++i)
            newp[2*i+2]=pp[i];

        int mx=0,id=0,ans=0;
        for(int i=1;i<2*n+2;i++) {
            len[i]=mx>i?Math.min(len[2*id-i],mx-i):1;
            while(newp[i+len[i]]==newp[i-len[i]])
                len[i]++;
            if(i+len[i]>mx){
                mx=i+len[i];
                id=i;
            }
            if(len[i]>ans){
                ans=len[i];
                System.out.println(i);
            }
        }
        return ans-1;
    }
}
