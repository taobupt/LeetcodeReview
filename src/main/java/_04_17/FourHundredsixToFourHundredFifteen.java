package _04_17;

import java.util.*;

/**
 * Created by tao on 5/21/17.
 */
public class FourHundredsixToFourHundredFifteen {

    //406 queue construct by height
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return Integer.compare(o2[0],o1[0]);
                else
                    return Integer.compare(o1[1],o2[1]);
            }
        });
        int n=people.length;
        List<int[]>res=new ArrayList<>();
        for(int i=0;i<n;++i){
            res.add(people[i][1],people[i]);
        }
        return res.toArray(new int[people.length][]);
    }

    //408 Valid Word Abbreviation
    //"a" "01"
    public boolean validWordAbbreviation(String word, String abbr) {
        int m=word.length(),n=abbr.length();
        if(word.equals(abbr))
            return true;
        int i=0,j=0;
        while(i<m && j<n){
            if(word.charAt(i)==abbr.charAt(j)){
                i++;
                j++;
            }else if(word.charAt(i)!=abbr.charAt(j)){
                if(!Character.isDigit(abbr.charAt(j)))
                    return false;
                else{
                    int sum=0;
                    if(abbr.charAt(j)=='0')//leading zero
                        return false;
                    while(j<n && Character.isDigit(abbr.charAt(j))){
                        sum=10*sum+(abbr.charAt(j++)-'0');
                    }
                    i+=sum;
                }
            }
        }
        return i==m && j==n;
    }

    //409 longest palindrome
    public int longestPalindrome(String s) {
        int []cnt=new int[128];
        char []ss=s.toCharArray();
        for(char c:ss){
            cnt[c]++;
        }
        int maxSize=0,oddCnt=0;
        for(int i=0;i<128;++i){
            if(cnt[i]>0){
                if(cnt[i]%2!=0){
                    oddCnt++;
                }
                maxSize+=(cnt[i]/2)*2;
            }
        }
        return oddCnt>0?maxSize+1:maxSize;
    }


    //412 fizz and buzz
    public List<String> fizzBuzz(int n) {
        List<String>res=new ArrayList<>();
        for(int i=1;i<=n;++i){
            if(i%3==0 && i%5==0){
                res.add("FizzBuzz");
            }else if(i%3==0){
                res.add("Fizz");
            }else if(i%5==0){
                res.add("Buzz");
            }else{
                res.add(String.valueOf(i));
            }
        }
        return res;
    }


    //413 Arithmetic Slices
    public int numberOfArithmeticSlices(int[] A) {
        int n=A.length;
        int cnt=0,sum=0;
        for(int i=2;i<n;++i){
            if(A[i]-A[i-1]==A[i-1]-A[i-2]){
                cnt++;
                sum+=cnt;
            }else
                cnt=0;
        }
        return sum;
    }


    //414
    public int thirdMax(int[] nums) {
        Set<Integer> set=new HashSet<>();
        int minVal=Integer.MAX_VALUE;
        for(int x:nums){
            set.add(x);
            minVal=Math.min(minVal,x);
        }
        int maxVal=minVal,secMax=minVal,thirdMax=minVal;
        for(int x:set){
            if(x>maxVal){
                thirdMax=secMax;
                secMax=maxVal;
                maxVal=x;
            }else if(x>secMax){
                thirdMax=secMax;
                secMax=x;
            }else if(x>thirdMax){
                thirdMax=x;
            }
        }
        return set.size()>=3?thirdMax:maxVal;
    }

    //optimal
    public int thirdMaxOptimal(int[] nums) {
        int minVal=Integer.MAX_VALUE;
        for(int x:nums){
            minVal=Math.min(minVal,x);
        }
        int maxVal=minVal,secMax=minVal,thirdMax=minVal,cnt=1;
        for(int x:nums){
            if(x>maxVal){
                thirdMax=secMax;
                secMax=maxVal;
                maxVal=x;
            }else if(x==maxVal){
                continue;
            }else if(x>secMax){
                thirdMax=secMax;
                secMax=x;
            }else if(x==secMax){
                continue;
            }else if(x>thirdMax){
                thirdMax=x;
            }else if(x==thirdMax)
                continue;
            cnt++;
        }
        return cnt>=3?thirdMax:maxVal;
    }


    //415 add strings
    //无脑过
    public String addStrings(String num1, String num2) {
        char []nums1=num1.toCharArray();
        char []nums2=num2.toCharArray();
        StringBuilder sb=new StringBuilder();
        int i=nums1.length-1,j=nums2.length-1,carry=0;
        while(carry>0||i>=0||j>=0){
            int a=i>=0?nums1[i--]-'0':0;
            int b=j>=0?nums2[j--]-'0':0;
            carry+=a+b;
            sb.append(carry%10);
            carry/=10;
        }
        return sb.reverse().toString();
    }




}
