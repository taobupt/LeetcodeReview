package _03_18;

import java.util.*;

/**
 * Created by tao on 3/17/17.
 */



/*
Maximum and minimum of an array using minimum number of comparisons
判断长度是奇数还是偶数，是奇数的话就把最大值，最小值赋值给第一个元素，若不是，就赋值给前两个元素，
然后每次比较2个，每两个要比较3次，所以是3*（n）／2级别的
 */
public class OneHundredSixtyOnetOSeventy {


    //161 one edit distance
    //挺有意思的，第一感觉是dp直接求出最小编辑距离，然后判断是否是1，但是这样时间复杂度太大，
    // 无非三种情况，第一种，替换，长度想等，
    // 第二种删除，长度相差1，第三种插入，相差1

    public boolean isOneEditDistance(String s, String t) {
        int m=s.length(),n=t.length();
        if(Math.abs(m-n)>1)
            return false;
        if(m==n){
            if(s.equals(t))
                return false;
            for(int i=0;i<m;++i){
                if(s.charAt(i)!=t.charAt(i))
                    return s.substring(i+1).equals(t.substring(i+1));
            }
        }
        for(int i=0;i<m && i<n;++i){
            if(s.charAt(i)!=t.charAt(i)){
                return m>n?s.substring(i+1).equals(t.substring(i)):s.substring(i).equals(t.substring(i+1));
            }
        }
        return true;
    }
    //concise
    public boolean isOneEditDistanceConcise(String s,String t){
        int m=s.length(),n=t.length();
        if(Math.abs(m-n)>1)
            return false;
        if(m>n)
            return isOneEditDistanceConcise(t,s);
        boolean misMatch=false;
        for(int i=0;i<m;++i){
            if(s.charAt(i)!=t.charAt(i)){
                if(m==n)
                    return s.substring(i+1).equals(t.substring(i+1));
                else
                    return s.substring(i).equals(t.substring(i+1));
            }
        }
        return n-m==1;
    }


    //162 find peak element
    //典型的二分法
    //垃圾一点的就是线性查找了
    public int findPeakElement(int[] nums) {
        int n=nums.length;
        int begin=0,end=n-1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]<nums[mid+1])
                begin=mid+1;
            else
                end=mid;
        }
        return begin;
    }

    //163 missing ranges
    //糙一点的做法就是先比较lower，nums[0], 最后还要比较upper,nums[n-1]的大小
    //long ,int 会爆
    public List<String> findMissingRanges(int[] nums, int lowe, int upper) {
        List<String>res=new ArrayList<>();
        int n=nums.length;
        long lower=(long)lowe;
        for(int i=0;i<n;++i){
            long before=(long)nums[i]-1;
            if(lower<=before)
                res.add(lower==before?lower+"":lower+"->"+before);
            lower=(long)nums[i]+1;
        }
        if(lower<=upper)
            res.add(lower==upper?lower+"":lower+"->"+upper);
        return res;
    }
    //暴力一点的办法就是
    //大概意思就是比较数组的俩元素，看有没有空隙，当然lower,nums[0], nums[n-1] 和upper之间的要提前和最后处理，因为不包含在循环里
    public List<String> findMissingRangesMessy(int[] nums, int lower, int upper) {
        int n=nums.length,index=1;
        long start=lower,end=lower;
        List<String>res=new ArrayList<>();
        if(n==0){
            res.add(lower==upper?""+lower:lower+"->"+upper);
            return res;
        }
        if(nums[0]!=lower){
            res.add(nums[0]==lower+1?""+lower:lower+"->"+(nums[0]-1));

        }
        while(index<n){
            if((long)nums[index]!=(long)(nums[index-1])+1){
                start=(long)nums[index-1]+1;
                end=(long)nums[index]-1;
                if(start<=end)//important
                    res.add(start==end?""+start:start+"->"+end);
            }
            index++;
        }
        //important
        if(nums[n-1]!=upper){
            res.add(nums[n-1]==upper-1?""+upper:(nums[n-1]+1)+"->"+upper);
        }
        return res;
    }

    //164 maximum gap
    //先sort，再找，o(nlogn)
   //只能是radix sort排序了
    public int maximumGap(int[] nums) {
        int n=nums.length;
        int maxGap=0;
        if(n<2)
            return 0;
        Arrays.sort(nums);
        for(int i=1;i<n;++i){
            maxGap=Math.max(nums[i]-nums[i-1],maxGap);
        }
        return maxGap;
    }

    //radix sort
    //10 bit
    public int maximumGap10(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }

        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits

        int[] aux = new int[nums.length];

        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];

            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }

            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }

        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }

        return max;
    }

    //32 bit
    private int BITS_PER_BYTE=8;
    public int maximumGap32(int[] a) {
        int n = a.length;
        if(n<2)
            return 0;
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes


        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
        int maxGap=0;
        for(int i=1;i<n;++i){
            maxGap=Math.max(maxGap,a[i]-a[i-1]);
        }
        return maxGap;
    }

    //bucket sort 可以研究一下，挺好玩的

    //165 compare version number
    //很多corn case
    //non empty and contains digits and .
    //一发击中
    //倘若不允许用split，那么只能先把version 1 按dot来分割了
    public int compareVersion(String version1, String version2) {
        //split
        String []ver1=version1.split("\\.");
        String[]ver2=version2.split("\\.");
        int m=ver1.length,n=ver2.length;
        int i=0;
        for(;i<m && i<n;++i){
            int val1=Integer.valueOf(ver1[i]);
            int val2=Integer.valueOf(ver2[i]);
            if(val1>val2 )
                return 1;
            else if(val1<val2)
                return -1;
        }
        for(;i<m;++i){
            if(Integer.valueOf(ver1[i])>0)
                return 1;
        }
        for(;i<n;++i){
            if(Integer.valueOf(ver2[i])>0)
                return -1;
        }
        return 0;
    }

    //concise
    public int compareVersionConcise(String version1, String version2) {
        String[] levels1 = version1.split("\\.");//若是普通办法行不通，可以采取加上\\
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;//trick
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

    //166 fraction to recurring decimal
    //和标准答案居然是一摸一样，我的哥。
    //主要思想是余数乘10继续除
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb=new StringBuilder();
        if(numerator==0)
            return "0";
        boolean negative=(numerator>0)^(denominator>0);
        long nume=Math.abs((long)numerator);
        long deno=Math.abs((long)denominator);
        sb.append(negative?"-":"");
        sb.append(nume/deno);
        if(nume%deno==0)
            return sb.toString();
        sb.append(".");
        Map<Long,Integer>map=new HashMap<>();//store the number and length
        map.put(nume%deno,sb.length());//这里就map就应该加上数据，否则就晚了。主要是判断余数有没有重复，如果余数有重复，那么必定是重复了。
        nume=10*(nume%deno);
        while(true){
            long val=nume/deno;
            long mod=nume%deno;
            sb.append(val);
            if(mod==0)
                break;
            if(map.containsKey(mod)){
                int index=map.get(mod);
                sb.insert(index,'(');
                sb.append(')');
                break;
            }
            map.put(mod,sb.length());
            nume=10*(mod);
        }
        return sb.toString();
    }



    //167 two sum II- input array is sortd
    //return index;
    //hashmap 太渣了，一般来说无序用hashmap，有序，就用two pointers
    // My idea is to use binary search to find the largest number less than target and then use two pointers.
    public int[] twoSum(int[] numbers, int target) {
        int n=numbers.length;
        int begin=0,end=n-1;
        while(begin<end){
            int sum=numbers[begin]+numbers[end];
            if(target==sum){
                return new int[]{begin+1,end+1};
            }else if(target>sum){
                begin++;
            }else
                end--;
        }
        return new int[]{0,0};
    }

    //if input is positive
    public int []twoSumBinarySearch(int[]nums,int target){
        int n=nums.length;
        int begin=0,end=n-1;
        while(begin<end){
            int mid=(end-begin)/2+begin;
            if(nums[mid]>=target)
                end=mid;
            else
                begin=mid;
        }
        end=begin;
        begin=0;
        while(begin<end){
            int sum=nums[begin]+nums[end];
            if(target==sum){
                return new int[]{begin+1,end+1};
            }else if(target<sum){
                end--;
            }else
                begin++;
        }
        return new int[]{0,0};
    }

    //168 excel sheet title
    //iterative way
    public String convertToTitle(int n) {
        StringBuilder sb=new StringBuilder();
        while(n>0){
            char c=(char)((n-1)%26+'A');
            sb.append(c);
            n=(n-1)/26;
        }
        return sb.reverse().toString();
    }
    //recursive way
    //NB
    public String convertToTitleRecursive(int n){
        return n==0?"":convertToTitleRecursive(n-1)+(char)((n-1)%26+'A');
    }


    //169 majority element
    //投票法
    //其实用hashmap的话也是可以的，但是太垃圾了，我不写了。
    //和229 ii 差不多
    public int majorityElement(int[] nums) {
        int n=nums.length;
        int cnt=0,res=0;
        for(int i=0;i<n;++i){
            if(res==nums[i])
                cnt++;
            else if(cnt==0){
                cnt=1;
                res=nums[i];
            }else
                cnt--;
        }
        return res;
    }

    //229
    //Majority Element II
    //好像是要把什么条件写在前面，不然容易跪
    //撑死了就俩元素
    //如果把cnt——a这种写前面的话，那么a，b可能一样，然后第三个元素不想等的话，就会把之前的元素抵消，这是不能忍的，一个元素只能和不想等的元素抵消
    public List<Integer> majorityElementII(int[] nums) {
        int n=nums.length;
        int a=0,b=0;
        int cnt_a=0,cnt_b=0;
        for(int i=0;i<n;++i){
            if(nums[i]==a)
                cnt_a++;
            else if(nums[i]==b)
                cnt_b++;
            else if(cnt_a==0){
                cnt_a=1;
                a=nums[i];
            }else if(cnt_b==0){
                cnt_b=1;
                b=nums[i];
            }
            else{
                cnt_a--;
                cnt_b--;
            }
        }

        //check whether they are valid;
        cnt_a=cnt_b=0;
        for(int i=0;i<n;++i){
            if(nums[i]==a)
                cnt_a++;
            else if(nums[i]==b)
                cnt_b++;
        }
        List<Integer>ans=new ArrayList<>();
        if(cnt_a>n/3)
            ans.add(a);
        if(cnt_b>n/3)
            ans.add(b);
        return ans;
    }

    //拓展，若是k 次，那么就要用一个数组来表示了，其实是一样的道理
    public List<Integer> majorityKElement(int[] nums) {
        int n = nums.length, k = 3;  //in this question, k=3 specifically
        List<Integer> result = new ArrayList<Integer>();
        if (n==0 || k<2) return result;
        int[] candidates = new int[k-1];
        int[] counts = new int[k-1];
        for (int num: nums) {
            boolean settled = false;
            for (int i=0; i<k-1; i++) {
                if (candidates[i]==num) {
                    counts[i]++;
                    settled = true;
                    break;
                }
            }
            if (settled) continue;
            for (int i=0; i<k-1; i++) {
                if (counts[i]==0) {
                    counts[i] = 1;
                    candidates[i] = num;
                    settled = true;
                    break;
                }
            }
            if (settled) continue;
            for (int i=0; i<k-1; i++) counts[i] = (counts[i] > 0) ? (counts[i]-1) : 0;
        }
        Arrays.fill(counts, 0);
        for (int num: nums) {
            for (int i=0;i<k-1; i++) {
                if (candidates[i]==num) {
                    counts[i]++;
                    break;
                }
            }
        }
        for (int i=0; i<k-1; i++) if (counts[i]>n/k) result.add(candidates[i]);
        return result;
    }

    //170 was in the design



}
