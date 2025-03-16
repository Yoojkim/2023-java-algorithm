import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        //nexts
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        List<Integer>[] nexts = new List[N+1];
        int[] levels = new int[N+1]; //정점차수 기재
        for(int i=1;i<=N;i++){
            nexts[i] = new ArrayList<>();
        }

        while(M-->0){
            inputs = br.readLine().split(" ");
            int first = Integer.parseInt(inputs[0]);
            int second = Integer.parseInt(inputs[1]);

            levels[second]++;
            nexts[first].add(second);
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=1;i<=N;i++){
            if(levels[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            int now = queue.poll();

            res.add(now);

            for(int next:nexts[now]){
                levels[next] --;

                if(levels[next] == 0){
                    queue.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int r:res){
            sb.append(r).append(" ");
        }

        System.out.print(sb);
    }
}
