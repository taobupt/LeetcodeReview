package _03_23;

import common.ListNode;

import java.util.*;

/**
 * Created by tao on 3/23/17.
 */


public class TwoHundredSixToFifteen {

    //206 reverse linked list
    //没啥好说的，iterative and recursive way
    public ListNode reverseList(ListNode head) {
        ListNode newHead=null;
        ListNode tmp=null;
        while(head!=null){
            tmp=head.next;
            head.next=newHead;
            newHead=head;
            head=tmp;
        }
        return newHead;
    }

    //recursive way
    public ListNode reverseListRecursive(ListNode head){
        if(head==null||head.next==null)//该判断的还是得判断，这样不仅快，而且也避免了很多麻烦，一个节点的确实不能用递归来解决，
            return head;
        ListNode node=reverseListRecursive(head.next);
        head.next.next=head;
        head.next=null;
        return node;
    }

    //course schedule 207
    //典型的拓扑排序，bfs，dfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //一个数组存indegree，map存节点和边；
        Map<Integer,List<Integer>>edges=new HashMap<>();
        int []res=new int[numCourses];
        int n=prerequisites.length;
        int []indegree=new int[numCourses];
        for(int i=0;i<n;++i){
            ++indegree[prerequisites[i][0]];
            if(!edges.containsKey(prerequisites[i][1]))
                edges.put(prerequisites[i][1],new ArrayList<>());
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        //入度为零的进队列
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<numCourses;++i){
            if(indegree[i]==0)
                q.offer(i);
        }
        int count=0;
        while(!q.isEmpty()){
            int top=q.poll();
            res[count++]=top;
            List<Integer>neighbors=edges.getOrDefault(top,new ArrayList<>());
            for(int neighbor:neighbors){
                --indegree[neighbor];
                if(indegree[neighbor]==0)
                    q.offer(neighbor);
            }
        }
        return count==numCourses;//注意是numCourses 而不是其他的
    }


    //210 course reschedule II , iteartive way is the same as 207
    //recursive way dfs

    public boolean dfs(Stack<Integer>stk,Map<Integer,List<Integer>>edges,boolean[]vis,int node){
        if(vis[node])
            return false ;
        vis[node]=true;
        List<Integer>neighbors=edges.getOrDefault(node,new ArrayList<>());
        for(int neightbor:neighbors){
            if(!dfs(stk, edges, vis, neightbor))
                return false;
            stk.push(neightbor);
        }
        vis[node]=false;//这句忘了,什么时候该加，什么时候不该加，这都是很有学问的。
        return true;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Stack<Integer>stk=new Stack<>();
        boolean []vis=new boolean[numCourses];
        Map<Integer,List<Integer>>edges=new HashMap<>();
        int []res=new int[numCourses];
        int n=prerequisites.length;
        for(int i=0;i<n;++i){
            if(!edges.containsKey(prerequisites[i][1]))
                edges.put(prerequisites[i][1],new ArrayList<>());
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for(int i=0;i<numCourses;++i){
            if(!vis[i]){
                if(!dfs(stk,edges,vis,i))
                    return new int[]{};
                stk.push(i);
            }
        }
        int cnt=0;
        while(!stk.isEmpty()){
            res[cnt++]=stk.pop();
        }
        return res;
    }
}
