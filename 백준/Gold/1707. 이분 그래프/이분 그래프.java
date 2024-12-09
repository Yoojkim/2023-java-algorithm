import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0){
            String[] sizes = br.readLine().split(" ");

            int V = Integer.parseInt(sizes[0]);
            int E = Integer.parseInt(sizes[1]);

            int[] nodes = new int[V+1]; //그룹 여부 0-미그룹 1-a 2-b
            List<Integer>[] relates = new List[V+1];

            for(int i=1;i<=V;i++){
                relates[i] = new ArrayList<>();
            }

            while(E-->0){
                String[] edge = br.readLine().split(" ");
                int a = Integer.parseInt(edge[0]);
                int b = Integer.parseInt(edge[1]);

                relates[a].add(b);
                relates[b].add(a);
            }

            boolean result = false;
            for(int i=1;i<=V;i++){
                if(nodes[i] == 0){
                    result = bfs(i, nodes, relates);
                    if(!result){
                        break;
                    }
                }
            }

            sj.add((result)?"YES":"NO");
        }

        System.out.print(sj);
    }

    static boolean bfs(int start, int[] nodes, List<Integer>[] relates){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        nodes[start] = 1;

        while(!queue.isEmpty()){
            for(int i=0;i<queue.size();i++){
                int nowNode = queue.poll();

                for(int relate:relates[nowNode]){
                    if(nodes[relate] == 0){
                        queue.add(relate);
                        nodes[relate] = (nodes[nowNode]==1)?2:1;

                        continue;
                    }

                    if(nodes[relate] == nodes[nowNode]){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}