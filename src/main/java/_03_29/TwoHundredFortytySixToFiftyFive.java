package _03_29;

import common.Interval;
import common.TreeNode;

import java.util.*;

/**
 * Created by tao on 3/29/17.
 */
public class TwoHundredFortytySixToFiftyFive {



    //246  Strobogrammatic number
    public boolean isStrobogrammatic(String num) {
        // 0 1 8 6-9,9-6
        StringBuilder sb=new StringBuilder(num);
        int n=sb.length();
        for(int i=0;i<n;++i){
            if(sb.charAt(i)=='0'||sb.charAt(i)=='1'||sb.charAt(i)=='8')
                continue;
            else if(sb.charAt(i)=='6')
                sb.setCharAt(i,'9');
            else if(sb.charAt(i)=='9')
                sb.setCharAt(i,'6');
            else
                return false;
        }
        return  sb.reverse().toString().equals(num);
    }

    //俩指针
    public boolean isStrobogrammaticTwoPointer(String num){
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }

    //247 StrobogrammaticII
    public List<String> findStrobogrammaticHelp(int n){
        List<String>res=new ArrayList<>();
        if(n==0){
            res.add("");
            return res;
        }else if(n==1){
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }else{
            List<String>ans=findStrobogrammaticHelp(n-2);
            for(String str:ans){
                res.add('0'+str+'0');//只有n<最初的value的时候才可以干，传俩参数呗。
                res.add('1'+str+'1');
                res.add('6'+str+'9');
                res.add('8'+str+'8');
                res.add('9'+str+'6');
            }
            return res;
        }
    }
    public List<String> findStrobogrammatic(int n) {
        List<String>res=new ArrayList<>();
        List<String>ans=findStrobogrammaticHelp(n);
        for(String str:ans){
            if(str.startsWith("0") && str.length()>1)//想方设法把这一步去掉把
                res.add(str);
        }
        return res;

    }

    //248  Strobogrammatic Number III

    //find the number of special number lower than or euqal low
    //不管是不是暴力，先提出一个解法再说，死耗着没啥意思，虽然知道有更好的方法
    public int strobogrammaticInRange(String low, String high) {
        //直接用第二问的解法来做
        List<String>res=new ArrayList<>();
        for(int len=low.length();len<=high.length();++len){
            res.addAll(findStrobogrammatic(len));
        }
        int count=0;
        for(String num : res){
            if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||(num.length() == high.length() && num.compareTo(high) > 0)) continue;
            count++;
        }
        return count;
    }

    //249 Group Shifted Strings 和 group anagrams
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>>res=new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strings){
            StringBuilder tmp=new StringBuilder(str);

            //transform the tmp
            int shift=tmp.charAt(0)-'a';
            for(int i=0;i<tmp.length();++i)
                tmp.setCharAt(i,(char)((tmp.charAt(i)-'a'-shift+26)%26+'a'));//核心，一般是先求出一个value，再说把。
            if(!map.containsKey(tmp.toString()))
                map.put(tmp.toString(),new ArrayList<>());
            map.get(tmp.toString()).add(str);
        }
        for(Map.Entry<String,List<String>>entry:map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }

    //250 count the univalue tree
    int cnt=0;

    public boolean isUnival(TreeNode root){
        if(root==null||root.left==null&&root.right==null){
            if(root!=null)
                cnt++;
            return true;
        }

        boolean l=isUnival(root.left);
        boolean r=isUnival(root.right);
        if(l && r){
            if(root.left!=null && root.right!=null){
                if((root.left.val==root.val)&&(root.val==root.right.val)){
                    cnt++;
                    return true;
                }else
                    return false;
            }
            else if(root.left!=null){
                if(root.val==root.left.val){
                    cnt++;
                    return true;
                }else
                    return false;
            }
            else{
                if(root.val==root.right.val){
                    cnt++;
                    return true;
                }else
                    return false;
            }
        }
        return false;
    }
    public int countUnivalSubtrees(TreeNode root) {
        isUnival(root);
        return cnt;
    }

    //251. Flatten 2D Vector
    //in design

    //252 meeting rooms
    //sort and scan
    public boolean canAttendMeetings(Interval[] intervals) {
        int n=intervals.length;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start,o2.start);
            }
        });
        for(int i=1;i<n;++i){
            if(intervals[i].start<intervals[i-1].end)
                return false;
        }
        return true;
    }

    //sort 俩数组
    //beat 92.67
    public boolean canAttendMeetingsSort(Interval[] intervals){
        int n=intervals.length;
        int []starts=new int[n];
        int []ends=new int[n];
        for(int i=0;i<n;++i){
            starts[i]=intervals[i].start;
            ends[i]=intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int i=0,j=0;
        while(i<n && j<n){
            while(i<n && starts[i]<ends[j])
                i++;
            if(i-j>1)
                return false;
            j++;
        }
        return true;
    }

    //253 Meeting Rooms II
    //tag
    //优先级队列
    //sort 数组两边跑
    //好好反思一下子。
    public int minMeetingRooms(Interval[] intervals) {
        int n=intervals.length;
        int []starts=new int[n];
        int []ends=new int[n];
        for(int i=0;i<n;++i){
            starts[i]=intervals[i].start;
            ends[i]=intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int i=0,j=0,res=0;
        while(i<n){
            if(starts[i]<ends[j]){
                res++;
            }else{
                j++;
            }
            i++;
        }
        return res;
    }

    //优先级队列的做法
    //priority queue
    public int minMeetingRoomsPriotityQueue(Interval[] intervals){
        int n=intervals.length;
        if(n==0)
            return 0;
        PriorityQueue<Interval>pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.end,o2.end);
            }
        });
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start,o2.start);
            }
        });
        pq.offer(intervals[0]);
        for(int i=1;i<n;++i){
            Interval interval=pq.poll();
            if(interval.end<=intervals[i].start){
                interval.end=Math.max(interval.end,intervals[i].end);
            }else{
                pq.offer(intervals[i]);
            }
            pq.offer(interval);
        }
        return pq.size();
    }

    //254 Factor Combinations

    public void backtrack(List<Integer>factors,int n,List<List<Integer>>res,List<Integer>path,int pos){
        if(n==1){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=pos;i<factors.size();++i){
            int num=factors.get(i);
            if(n>=num && n%num==0){
                path.add(factors.get(i));
                backtrack(factors,n/factors.get(i),res,path,i);
                path.remove(path.size()-1);
            }
        }
    }
    public List<List<Integer>> getFactors(int n) {
        //first get all factors;
        List<Integer>factors=new ArrayList<>();
        int mid=(int)Math.sqrt(n);
        for(int i=2;i<=mid;++i){
            if(n%i==0){
                factors.add(i);
                if(i!=n/i)
                    factors.add(n/i);
            }
        }
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        backtrack(factors,n,res,path,0);
        return res;
    }

    //255 verify preorder sequence in bst
    //o(1) space
    public boolean verifyPreorder(int[] preorder) {
        int n=preorder.length;
        int index=-1;
        int minValue=Integer.MIN_VALUE;
        for(int i=0;i<n;++i){
            if(preorder[i]<minValue)
                return false;
            while(index>=0 && preorder[i]>preorder[index]){
                minValue=preorder[index--];
            }
            preorder[++index]=preorder[i];
        }
        return true;
    }

    //actually you can use monotonically stack
    public boolean verifyPreorderByStack(int[]preorder){
        int n=preorder.length;
        Stack<Integer>stk=new Stack<>();
        int minValue=Integer.MIN_VALUE;
        for(int x:preorder){
            if(x<minValue)
                return false;
            while(!stk.isEmpty() && stk.peek()<x){
                minValue=stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
}
