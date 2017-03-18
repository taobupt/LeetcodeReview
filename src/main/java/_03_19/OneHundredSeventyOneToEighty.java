package _03_19;

import java.util.*;

/**
 * Created by tao on 3/18/17.
 */
public class OneHundredSeventyOneToEighty {

    //171. Excel Sheet Column Number
    //和168 是姊妹题
    public int titleToNumber(String s) {
        char []ss=s.toCharArray();
        int n=ss.length;
        int res=0;
        for(int i=0;i<n;++i)
            res=26*res+(ss[i]-'A'+1);
        return res;
    }
    //recursive way
    //可以好好想想，其实不一定得从头部开始，递归一般从尾部开始
    public int titleToNumberRecursive(String s) {
        if(s.isEmpty())
            return 0;
        if(s.length()==1)
            return s.charAt(0)-'A'+1;
        int n=s.length();
        return 26*titleToNumberRecursive(s.substring(0,n-1))+(s.charAt(n-1)-'A'+1);
    }

    //172 factorial trailing zeros
    //数学原理不一要搞懂啊
    //如何理解呢，就是n/5 是你自己本来的5，但是你还需要看看（n/5）之后还包含多少个5把
    // 100 ／5 =20 ，但是里面有个25啊，所以
    public int trailingZeroes(int  n) {
        return n<5?0:trailingZeroes(n/5)+n/5;
    }

    //没事千万不要用递归，给人抓住小辫子不放
    public int trailingZeroesIterative(int n){
        int res=0;
        while(n>=5){
            res+=n/5;
            n/=5;
        }
        return res;
    }
    //解释比较清楚的code
    int trailingZeroesexplain(int n) {
        int result = 0;
        for( long i=5; n/i>0; i*=5){
            result += (n/i);
        }
        return result;
    }

    //173 design

    //179 largest number
    //Arrays.sort(s_num, comp);也是可以用comparator
    //大家基本都是这个思路，就是排个序
    /*
    Let's assume:
the length of input array is n,
average length of Strings in s_num is k,
Then, compare 2 strings will take O(k).
Sorting will take O(nlgn)
Appending to StringBuilder takes O(n).
So total will be O(nklgnk) + O(n) = O(nklgnk).

Space is pretty straight forward: O(n).
     */
    public String largestNumber(int[] nums) {
        //就是排序
        List<String>res=new ArrayList<>();
        for(int x:nums)
            res.add(String.valueOf(x));
        res.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        String ans=String.join("",res);

        //最后要记得去掉lead zero
        //sb 了，直接检测第一个是不是0就可以了。
        if(ans.length()>0 && ans.charAt(0)=='0')
            return "0";
        else
            return ans;

    }

    //来看看 quciksort
    private void quickSort(String[] num,int l,int r)
    {
        if(l>=r) return;
        int i=l,j=r;
        String x=num[l];

        while(i<j)
        {
            while(i<j&&(x+num[j]).compareTo(num[j]+x)>=0)
                j--;
            if(i<j)
                num[i++] = num[j];
            while(i<j&&(x+num[i]).compareTo(num[i]+x)<0)
                i++;
            if(i<j)
                num[j--] = num[i];
        }
        num[i] = x;
        quickSort(num,l,i-1);
        quickSort(num,i+1,r);
    }

    //174 Dungeon Game
    //这题说实在的，和一般的并无两样，只是要注意负数之类的，还是挺值的研究的，毕竟作为看到这种模拟题就心虚的我


}
