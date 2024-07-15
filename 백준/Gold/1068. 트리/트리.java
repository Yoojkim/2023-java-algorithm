import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] hasChildren = new int[N];
        int[] myParent = new int[N];
        ArrayList<Integer>[] nodes = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<Integer>();
            myParent[i] = -1;
        }

        String[] parents = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(parents[i]);

            if (parent == -1) {
                continue;
            }

            hasChildren[parent]++;
            myParent[i] = parent;
            nodes[parent].add(i);
        }

        int removedNode = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(removedNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();

            if(myParent[node] != -1){
                hasChildren[myParent[node]]--;
            }
            hasChildren[node]--;

            for (int newNode : nodes[node]) {
                queue.add(newNode);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (hasChildren[i] == 0) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }
}