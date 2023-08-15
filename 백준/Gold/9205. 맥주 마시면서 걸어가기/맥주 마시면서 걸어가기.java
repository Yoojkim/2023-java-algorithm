import java.util.*;
import java.io.*;

class Graph{
    List<int[]> stores=new ArrayList();
    int n;

    public Graph(int n){
        this.n=n;
    }

    void addEdge(int x, int y){
        int[] edge={x, y};
        stores.add(edge);
    }

    int getDist(int[] e1, int[]e2){
        return getAbs(e1[0]-e2[0])+getAbs(e1[1]-e2[1]);
    }

    int getAbs(int val){
        if (val<0)
            return val*-1;

        return val;
    }

    String bfs(int[] start, int[] target){
        LinkedList<int[]> queue=new LinkedList();
        boolean[] visited=new boolean[n]; //0~n-1까지 stores에 적재된 순으로

        queue.add(start);

        while(queue.size()!=0){
            int[] now=queue.poll();
            int distToTarget=getDist(now, target);

            if(distToTarget<=1000)
                return "happy";

            for(int i=0;i<n;i++){
                int[] candidate=stores.get(i);

                //1000m이내 + 가보지 않은 곳
                int dist=getDist(now, candidate);
                if(dist<=1000 && !visited[i]){
                    queue.add(candidate);
                    visited[i]=true;
                }
            }
        }

        return "sad";
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        List<String> result=new ArrayList<>();

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i=0;i<t;i++){
            int n=Integer.parseInt(br.readLine());

            st=new StringTokenizer(br.readLine());
            int hx=Integer.parseInt(st.nextToken());
            int hy=Integer.parseInt(st.nextToken());
            int[] start={hx, hy};

            Graph graph=new Graph(n);
            for(int j=0;j<n;j++){
                st=new StringTokenizer(br.readLine());
                int x=Integer.parseInt(st.nextToken());
                int y=Integer.parseInt(st.nextToken());
                graph.addEdge(x, y);
            }

            st=new StringTokenizer(br.readLine());
            int fx=Integer.parseInt(st.nextToken());
            int fy=Integer.parseInt(st.nextToken());
            int[] target={fx, fy};

            result.add(graph.bfs(start, target));
        }

        Iterator<String> it=result.listIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}