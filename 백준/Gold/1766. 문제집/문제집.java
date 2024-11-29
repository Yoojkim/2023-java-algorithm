import java.util.*;
import java.io.*;

class Main {
    static int N;
    static List<Integer>[] parents;
    static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        parents = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = new ArrayList<>();
        }

        int[] ranks = new int[N + 1];
        while (M-- > 0) {
            String[] nodes = br.readLine().split(" ");
            int parent = Integer.parseInt(nodes[0]); //먼저 풀어야 하는 것
            int child = Integer.parseInt(nodes[1]); //이후 푸는 것

            parents[parent].add(child);
            ranks[child]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (ranks[i]==0) {
                pq.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();

        //pq에는 현재 학습 가능한 node만 들어있음
        while (!pq.isEmpty()) {
            int node = pq.poll();

            //이미 학습한 경우
            if (ranks[node] < 0) {
                continue;
            }
            res.add(node);

            for (int child : parents[node]) {
                if (ranks[child]<0) {
                    continue;
                }

                ranks[child]--;

                if (ranks[child] == 0){
                    pq.add(child);
                }
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int node : res) {
            sj.add(Integer.toString(node));
        }

        System.out.print(sj);
    }
}