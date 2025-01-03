import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int start;
    int end;
    int cost;

    public Node(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    //내림차순
    public int compareTo(Node node){
        return Integer.compare(node.cost, this.cost);
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] heights = new int[N];

        for(int i=0;i<N;i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        for(int i=1;i<N;i++){
            queue.add(new Node(i-1, i, heights[i]-heights[i-1]));
        }

        int[] separators = new int[K-1];
        for(int i=0;i<K-1;i++){
            Node seperatedNode = queue.poll();
            separators[i] = seperatedNode.start;
        }

        Arrays.sort(separators);

        //비용 계산
        int sum = 0;
        int start = 0;
        for(int separator:separators){
            sum+=heights[separator]-heights[start];
            start = separator+1;
        }
        sum+=heights[N-1]-heights[start];

        System.out.print(sum);
    }
}