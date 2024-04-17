import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int node1;
    int node2;
    int cost;

    public Edge(int node1, int node2, int cost){
        this.node1=node1;
        this.node2=node2;
        this.cost=cost;
    }

    //오름차순
    public int compareTo(Edge e){
        if(this.cost<e.cost)
            return -1;
        else if(this.cost==e.cost)
            return 0;
        else
            return 1;
    }
}

//Kruskal
class Main{
    static int n, m;

    //간선 리스트
    static List<Edge> edges=new ArrayList();
    //부모 배열
    static int[] parents;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        n=Integer.parseInt(nums[0]); m=Integer.parseInt(nums[1]);

        parents=new int[n+1];
        //부모 배열 초기화(자기 자신으로)
        for(int i=0;i<=n;i++){
            parents[i]=i;
        }

        //간선 생성
        for(int i=0;i<m;i++){
            nums=br.readLine().split(" ");
            int node1=Integer.parseInt(nums[0]); int node2=Integer.parseInt(nums[1]);
            int cost=Integer.parseInt(nums[2]);

            Edge edge=new Edge(node1, node2, cost);
            edges.add(edge);
        }

        //정렬
        Collections.sort(edges);

        int cnt=0;
        int sum=0;
        //Edge cost 작은 순으로 Kruskal MST 수행
        for(Edge edge:edges){

            if(cnt==n-1)
                break;

            int node1=edge.node1; int node2= edge.node2;

            if(find(node1)==find(node2))
                continue;

            //union
            union(node2, node1);
            sum+=edge.cost;
            cnt++; //간선 추가(간선은 n-1개)
        }

        System.out.print(sum);
    }

    private static int find(int node){
        if(parents[node]==node)
            return node;

        return find(parents[node]);
    }

    private static void union(int node1, int node2){
        //node2 하위의 모든 node들을 node1으로 이동
        int p1=find(node1);
        int p2=find(node2);
        
        //root가 더 작은 값으로 이동
        if(p1>p2)
            parents[p2]=p1;
        else
            parents[p1]=p2;

    }
}