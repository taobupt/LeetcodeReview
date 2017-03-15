package _03_01;

import common.ListNode;
import common.Trie;
import common.TrieNode;

import java.util.*;

/**
 * Created by Tao on 3/1/2017.
 */
public class ElevenToTwenty {

    //11 container with most water
    //two pointers
    //O(n)
    public int maxArea(int[] height) {
        int n=height.length;
        int begin=0,end=n-1;
        int res=0;
        while(begin<end){
            if(height[begin]>height[end]){
                res=Math.max(res,height[end]*(end-begin));
                end--;
            }else{
                res=Math.max(res,height[begin]*(end-begin));
                begin++;
            }
        }
        return res;
    }

    //recursive way
    //数据量太大，容易stack overflow
    public int maxAreaRecursive(int[]height,int i,int j){
        if(i==j)
            return 0;
        int minHeight=Math.min(height[i],height[j]);
        return Math.max(minHeight*(j-i),height[i]==minHeight?maxAreaRecursive(height,++i,j):maxAreaRecursive(height,i,--j));
    }
    public int maxAreaRecursive(int[]height){
        return maxAreaRecursive(height,0,height.length-1);
    }


    //12 Integer to Roman
    //没有太多技巧而言
    public String intToRoman(int num) {
        String[][]strs={{"","I","II","III","IV","V","VI","VII","VIII","IX"},{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},{"","M","MM","MMM"}};
        StringBuilder sb=new StringBuilder();
        int []divide={1,10,100,1000};
        for(int i=3;i>=0;--i){
            sb.append(strs[i][num/divide[i]]);
            num%=divide[i];
        }
        return sb.toString();
    }



    //13 13. Roman to Integer
    //find the rule and execute it
    public int romanToInt(String s) {
        int res=0;
        Map<Character,Integer>map=new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int n=s.length();
        char []arrs=s.toCharArray();
        res=map.get(arrs[n-1]);
        for(int j=n-2;j>=0;--j){
            res+=map.get(arrs[j])>map.get(arrs[j+1])?map.get(arrs[j]):-map.get(arrs[j]);
        }
        return res;
    }

    //another way help to think
    public int romanToIntConcise(String s){
        int sum=0;
        if(s.indexOf("IV")!=-1)
            sum-=2;
        if(s.indexOf("IX")!=-1)
            sum-=2;
        if(s.indexOf("XL")!=-1)
            sum-=20;
        if(s.indexOf("XC")!=-1)
            sum-=20;
        if(s.indexOf("CD")!=-1)
            sum-=200;
        if(s.indexOf("CM")!=-1)
            sum-=200;
        char []c=s.toCharArray();
        int count=0,n=s.length();
        for(;count<n;++count){
            if(c[count]=='M')
                sum+=1000;
            if(c[count]=='D')
                sum+=500;
            if(c[count]=='C')
                sum+=100;
            if(c[count]=='L')
                sum+=50;
            if(c[count]=='X')
                sum+=10;
            if(c[count]=='V')
                sum+=5;
            if(c[count]=='I')
                sum+=1;
        }
        return sum;
    }

    //more concise way
    public static int romanToIntmostConcise(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    res += (res >= 5 ? -1 : 1);
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10 * (res >= 50 ? -1 : 1);
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100 * (res >= 500 ? -1 : 1);
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }

    //14 Longest Common Prefix
    //没啥好说的，直接暴力
    public String longestCommonPrefix(String[] strs) {
        int n=strs.length;
        if(n==0)
            return "";
        StringBuilder res=new StringBuilder();
        int m=strs[0].length();
        for(int i=0;i<m;++i){
            res.append(strs[0].charAt(i));
            boolean flag=false;
            for(int j=1;j<n;++j){
                if((strs[j].length()<i+1)||!strs[j].substring(0,i+1).equals(strs[0].substring(0,i+1))){
                    flag=true;
                    break;
                }
            }
            if(flag){
                res.deleteCharAt(res.length()-1);
                break;
            }
        }
        return res.toString();
    }


    //you can also sort the array first and just compar the first one and the last one
    public String longestCommonPrefixSort(String[]strs){
        StringBuilder result=new StringBuilder();
        if(strs!=null && strs.length>0){
            Arrays.sort(strs);
            int n=strs.length;
            char []first=strs[0].toCharArray();
            char []last=strs[n-1].toCharArray();
            for(int i=0;i<first.length;++i){
                if(first[i]!=last[i])//no need to add last.length>i
                    break;
                result.append(first[i]);
            }
        }
        return result.toString();
    }

    //passed the test
    public String longestCommonPrefixByTrie(String []strs){
        Trie t=new Trie();
        for(String str:strs)
            t.addWords(str);
        TrieNode root=t.root;
        while(root.size==1 && !root.isEnd){
            for(TrieNode node:root.children)
                if(node!=null){
                    root=node;
                    break;
                }
        }
        return root.word.toString();
    }


    //you can also use trie
    //insert all the words and start to find the node that has more than one child, that's the end
    //I will implement later



    //15 3sum
    //sort and two pointers
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>res=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n-2;++i){
            if(i>0 && nums[i]==nums[i-1])
                continue;
            if(nums[i]+nums[i+1]+nums[i+2]>0)//最小的都大于0，没戏了，这两个语句是加速器
                break;
            if(nums[i]+nums[n-2]+nums[n-1]<0)//最大的都小于0，只能下一个了；
                continue;
            int begin=i+1,end=n-1;
            while(begin<end){
                int sum=nums[i]+nums[begin]+nums[end];
                if(sum==0){
                    res.add(Arrays.asList(nums[i],nums[begin++],nums[end--]));
                    while(begin<end && nums[begin]==nums[begin-1])
                        begin++;
                    while(begin<end && nums[end]==nums[end+1])
                        end--;
                }else if(sum<0)
                    begin++;
                else
                    end--;
            }
        }
        return res;
    }


    //hashmap version
    //LTE 3000就受不了了
    public List<List<Integer>> threeSumHashMap(int[] nums){
        List<List<Integer>>res=new ArrayList<>();
        if(nums==null||nums.length<3)
            return res;
        Map<Integer,Integer>map=new HashMap<>();
        Set<String>set=new HashSet<>();
        for(int x:nums){
            if(!map.containsKey(x))
                map.put(x,1);
            else
                map.put(x,map.get(x)+1);
        }

        for(int x:nums){
            map.put(x,map.get(x)-1);
            if(map.get(x)==0)
                map.remove(x);
            for(int y:map.keySet()){
                int k=-x-y;
                if(!map.containsKey(k)||(k==y && map.get(k)==1))
                    continue;
                String key=getKey(x,y,k);
                if(!set.contains(key)){
                    res.add(new ArrayList<>(Arrays.asList(x,y,k)));
                    set.add(key);
                }
            }
        }
        return res;
    }

    public String getKey(int x,int y,int z){
        int min=Math.min(Math.min(x,y),z);
        int max=Math.max(Math.max(x,y),z);
        return min+"@"+max;
    }


    //16 3sum cloest
    //sort and two pointers
    public int threeSumClosest(int[] nums, int target) {
        int n=nums.length;
        Arrays.sort(nums);
        int val=nums[0]+nums[1]+nums[2];
        for(int i=0;i<n-2;++i){
            int begin=i+1,end=n-1;
            while(begin<end) {
                int sum = nums[i] + nums[begin] + nums[end];
                if (Math.abs(val - target) > Math.abs(sum - target))
                    val = sum;
                if (sum == target)
                    return target;
                else if (sum < target)
                    begin++;
                else
                    end--;
            }
        }
        return val;
    }

    //another way is to brute force


    //17 Letter Combinations of a Phone Number
    //backtracking

    public void backtracking(List<String>res,String digits,int pos,String path,Map<Character,String>map){
        if(pos==digits.length()){
            res.add(path);
            return;
        }
        String value=map.get(digits.charAt(pos));
        for(int i=0;i<value.length();++i)
            backtracking(res,digits,pos+1,path+value.charAt(i),map);

    }
    public List<String> letterCombinations(String digits) {
        List<String>res=new ArrayList<>();
        if(digits.isEmpty())
            return res;
        Map<Character,String>map=new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        backtracking(res,digits,0,"",map);
        return  res;
    }

    //another way
    //queue
    //iterative way
    public List<String> letterCombinationsIterative(String digits){
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){//这个显得很有技巧
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }



    //18 four sum
    //sort and  find two pointers, O(n3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //记得要加速
        List<List<Integer>>res=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for(int j=0;j<n-3;++j){
            if(j>0 && nums[j]==nums[j-1])
                continue;
            if(nums[j]+nums[j+1]+nums[j+2]+nums[j+3]>0)//最小的都大于0，没戏了，这两个语句是加速器
                break;
            if(nums[j]+nums[n-3]+nums[n-2]+nums[n-1]<0)//最大的都小于0，只能下一个了；
                continue;
            for(int i=j+1;i<n-2;++i){
                if(i>j+1 && nums[i]==nums[i-1])
                    continue;
                int begin=i+1,end=n-1;
                while(begin<end){
                    int sum=nums[j]+nums[i]+nums[begin]+nums[end];
                    if(sum==target){
                        res.add(Arrays.asList(nums[j],nums[i],nums[begin++],nums[end--]));
                        while(begin<end && nums[begin]==nums[begin-1])
                            begin++;
                        while(begin<end && nums[end]==nums[end+1])
                            end--;
                    }else if(sum<target)
                        begin++;
                    else
                        end--;
                }
            }
        }
        return res;
    }

    /*
    1) Create an auxiliary array aux[] and store sum of all possible pairs in aux[]. The size of aux[] will be n*(n-1)/2 where n is the size of A[].

    2) Sort the auxiliary array aux[].

    3) Now the problem reduces to find two elements in aux[] with sum equal to X. We can use method 1 of this post to find the two elements efficiently
     */



    //19    remove Nth node forom end of list
    //要注意corn case， 要是删除的是第一个元素呢，所有一定要小心返回的是first.next,还有，像q.next=q.next.next,这种，要判断q.next!=null才行
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first=new ListNode(0);
        first.next=head;
        ListNode p=head;
        while(n-- >0){
            p=p.next;
        }
        ListNode q=first;
        while(p!=null){
            p=p.next;
            q=q.next;
        }
        if(q.next!=null)
            q.next=q.next.next;
        return head;
    }

    //20 valid parentheses
    //stack way
    //three kind of ( { [
    //if one ,you can use counter
    public boolean isValid(String s) {
        Stack<Character>stk=new Stack<>();
        int n=s.length();
        char[]arrs=s.toCharArray();
        for(char c:arrs){
            switch (c){
                case '[':case '{':case '(':
                    stk.push(c);
                    break;
                case ']':
                    if(stk.isEmpty()||stk.pop()!='[')
                        return  false;
                    break;
                case '}':
                    if(stk.isEmpty()||stk.pop()!='{')
                        return false;
                    break;
                case ')':
                    if(stk.isEmpty()||stk.pop()!='(')
                        return false;
                    break;
                default:
                    break;
            }
        }
        return stk.isEmpty();
    }

    //without switch
    public boolean isValidWithoutSwitch(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    //without stack
    //very interesing idea
    //( 40 )41 [91 ]93 {123 }125
    public boolean isValidWithoutStack(String s){
        int n=s.length();
        char[]arrs=s.toCharArray();
        int top=-1;
        for(int i=0;i<n;++i){
            if((top==-1) &&(arrs[i]==')'||arrs[i]=='}'||arrs[i]==']'))
                return false;
            switch (arrs[i]){
                case ']': case '}': case ')':
                    if(Math.abs(arrs[i]-arrs[top])<3)//you should know the ord of the [ ] { } ( )
                        top--;
                    else
                        return false;
                    break;
                default:
                    arrs[++top]=arrs[i];
                    break;

            }
        }
        return top==-1;
    }

}
