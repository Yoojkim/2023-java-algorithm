import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int node;
    int cost;
    
    public Node(int node, int cost){
        this.node=node;
        this.cost=cost;
    }
    
    public int compareTo(Node o){
        //오름차순 this.node가 n.node보다 작아야
        if(this.cost>o.cost){
            return 1; //자리 바꾸기
        }else if(this.cost==o.cost){
            return 0; //유지 
        }else{
            return -1; //유지 
        }
    }
    
}
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]);
        int m=Integer.parseInt(nums[1]);
        
        LinkedList<Node>[] fields=new LinkedList[n+1];
        boolean[] visited = new boolean[n+1];
        for(int i=0;i<=n;i++){
            fields[i]=new LinkedList();
        }
        
        for(int i=0;i<m;i++){
            nums=br.readLine().split(" ");
            int n1=Integer.parseInt(nums[0]);
            int n2=Integer.parseInt(nums[1]);
            int cost=Integer.parseInt(nums[2]);
            
            LinkedList<Node> field1=fields[n1];
            LinkedList<Node> field2=fields[n2];
            
            //양방향 
            field1.addLast(new Node(n2, cost));
            field2.addLast(new Node(n1, cost));
        }
        
        PriorityQueue<Node> queue=new PriorityQueue();
        queue.add(new Node(1, 0));
        int sum=0;
        while(!queue.isEmpty()){
            Node node=queue.poll();
            
            if(visited[node.node])
                continue;
            
            visited[node.node]=true;
            sum+=node.cost;
            
            for(Node next:fields[node.node]){
                queue.add(next);
            }
        }
        
        System.out.println(sum);
    }
}