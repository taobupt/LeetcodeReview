package _03_06;

import common.ListNode;

import java.util.Arrays;

/**
 * Created by tao on 3/6/17.
 */
public class SixtyOneToSeventy {

    //61 rotate list
    public ListNode reverse(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode newHead=null;
        while(head!=null){
            ListNode next=head.next;
            head.next=newHead;
            newHead=head;
            head=next;
        }
        return newHead;
    }
    public ListNode rotateRight(ListNode head, int k) {
        //reverse 3 times
        if(head==null||head.next==null||k==0)
            return head;
        //split the list into two parts
        ListNode p=head;
        int cnt=0;
        while(p!=null){
            cnt++;
            p=p.next;
        }
        k=cnt-(k%cnt);
        p=head;
        while(k-->1){
            p=p.next;
        }
        ListNode second=p.next;
        p.next=null;
        second=reverse(second);
        p=reverse(head);
        head.next=second;
        head=reverse(p);
        return head;
    }


    //actually, just keep some points silent
    public ListNode rotateRightAnother(ListNode head,int k){
        if(head==null||head.next==null||k==0)
            return head;
        ListNode p=head;
        int cnt=0;
        while(p!=null){
            cnt++;
            p=p.next;
        }
        k=cnt-(k%cnt);
        if(k==cnt)//this is important
            return head;
        p=head;
        while(k-->1){
            p=p.next;
        }
        ListNode second=p.next;
        p.next=null;
        p=second;
        while(p!=null && p.next!=null)
            p=p.next;
        if(p!=null)
            p.next=head;
        return second;
    }

    //62 unique paths

    //会溢出，除非是用python
    public int C(int a,int b){
        //我擦，计算公式粗了
        if(b<a-b)
            b=a-b;
        long res=1;
        for(int i=b+1;i<=a;++i)
            res*=(long)i;
        for(int i=1;i<=a-b;++i)
            res/=i;
        return (int)res;
    }
    public int uniquePaths(int m, int n) {
        //组合公式  c(m+n-2,m-1)；
        if(m<=1||n<=1)
            return 1;
        return C(m+n-2,m-1);
    }

    //dp
    public int uniquePathsCata(int m,int n){
        if(m<=1||n<=1)
            return 1;
        int [][]dp=new int[m][n];
        for(int i=0;i<m;++i)
            dp[i][0]=1;
        for(int i=0;i<n;++i)
            dp[0][i]=1;
        for(int i=1;i<m;++i){
            for(int j=1;j<n;++j)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];//+= 其实是一样的，因为未到达的状态永远是0
        }
        return dp[m-1][n-1];
    }

    //this way you can always save space



    //66 plus one

    public int[] plusOne(int[] digits) {
        int n=digits.length;
        for(int i=n-1;i>=0;--i){
            if(digits[i]!=9){
                digits[i]++;
                return digits;
            }else
                digits[i]=0;//差点就忘了加上这一句了。真是扯淡
        }
        int []ans=new int[n+1];
        ans[0]=1;
        return ans;
    }

    //recursive way

    public int []recursive(int[]digits,int index){
        if(index<0)
            return digits;
        if(digits[index]!=9){
            digits[index]++;
            return digits;
        }else{
            if(index!=0){
                digits[index]=0;
                return recursive(digits,index-1);
            }
            int []res=new int[digits.length+1];//这是一个很奇怪的点
            res[0]=1;
            return res;
        }
    }
    public  int[]plusOneRecur(int []digits){
        return recursive(digits,digits.length-1);
    }


    //67 add binary
    //iterative way
    //和那个linkedlist 相加相差无几
    public String addBinary (String a, String b) {
        if(a.isEmpty()||b.isEmpty())
            return a.isEmpty()?b:a;
        int m=a.length(),inda=m-1,n=b.length(),indb=n-1;
        char []as=a.toCharArray();
        char []bs=b.toCharArray();
        StringBuilder sb=new StringBuilder();
        int carry=0;
        while(inda>=0 ||indb>=0||carry>0){

            carry=carry+(inda>=0?as[inda]-'0':0)+(indb>=0?bs[indb]-'0':0);
            sb.append(carry==2?'0':'1');
            carry/=2;
            inda--;
            indb--;

        }
        return sb.toString();

    }

    //recursive way

    public StringBuilder addBinaryRecursive(String a,int inda,String b,int indb,int carry){
        if(inda<0 && indb<0 && carry==0)
            return new StringBuilder("");
        carry+=(inda>=0?a.charAt(inda)-'0':0)+(indb>=0?b.charAt(indb)-'0':0);
        StringBuilder sb=new StringBuilder();
        sb.append(carry%2).append(addBinaryRecursive(a,inda-1,b,indb-1,carry/2));
        return sb;
    }
    public String addBinaryRecur(String a, String b){
        return addBinaryRecursive(a,a.length()-1,b,b.length()-1,0).reverse().toString();
    }

    //69  my sqrt

    //first way binary search
    public int mySqrt(int x) {
        if(x<=1)
            return x;
        int begin=1,end=x;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(mid>=46341){//sqrt(2147483647)=46340.99
                end=mid;
                continue;
            }


            /*
            应该改成
            if(mid<=x/mid && x/(mid+1)<mid+1)return mid;
             */
            long sum1=(long)mid*(long)mid;//这两部分老是觉得不好
            long sum2=sum1+2*mid+1;
            if(sum1<=x && x<sum2)
                return mid;
            if(sum1<x)
                begin=mid+1;
            else if(sum1>x)
                end=mid;

        }
        return begin;
    }

    //newton solution

    public int mySqrtNewton(int x){
        if(x<=1)
            return x;
        double first=x*1.0;
        while(Math.abs(first*first-x)>1e-6){
            first=first-(first-x/first)/2;
        }
        return (int)first;
    }


    //70 斐波那契数列
    public int climbStairs(int n) {
        int res=1;
        int a=1;
        if(n<=1)
            return res;
        int index=1;
        while(index<n){
            res+=a;
            a=res-a;
        }
        return res;
        /*
        static double root5 = Math.sqrt(5);
        static double phi = (1+root5)/2;
        return (int)Math.floor( (Math.pow(phi,n+1)/root5) + 0.5 );/／四舍五入
         */
    }

}
