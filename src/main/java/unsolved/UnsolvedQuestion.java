package unsolved;

import java.util.Arrays;
import java.util.Stack;

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
}
