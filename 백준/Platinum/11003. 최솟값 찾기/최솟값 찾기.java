import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int index;
    int value;

    public Node(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int compareTo(Node n){
        return this.value-n.value;
    }
}
class Main{
    static int N;
    static int L;
    static PriorityQueue<Node> queue;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        queue = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int res[] = new int[N+1];
        for(int i=1;i<=N;i++){
            queue.add(new Node(i, Integer.parseInt(st.nextToken())));

            int start = getStartIndex(i); int end = i;
            int value = getMinValue(start, end);
            res[i] = value;
        }

        StringJoiner sj = new StringJoiner(" ");
        for(int i=1;i<=N;i++){
            sj.add(Integer.toString(res[i]));
        }

        System.out.println(sj);
    }

    static private int getMinValue(int start, int end){
        while(!queue.isEmpty()){
            Node now = queue.peek();

            if(now.index >= start && now.index<=end){
                return now.value;
            }

            queue.poll();
        }

        return -1; //실패 경우
    }

    static private int getStartIndex(int i){
        int res = i-L+1;

        if(res<=0){
            res = 1;
        }

        return res;
    }
}