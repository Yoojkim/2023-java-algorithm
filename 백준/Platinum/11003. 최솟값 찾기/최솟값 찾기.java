import java.util.*;
import java.io.*;

class Node {
    int index;
    int value;

    public Node(int index, int value){
        this.index = index;
        this.value = value;
    }
}

class Main{
    static int N;
    static int L;
    static Deque<Node> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int D[] = new int[N+1];
        for(int i=1;i<=N;i++){
            Node newNode = new Node(i, Integer.parseInt(st.nextToken()));
            insert(newNode);
            D[i] = queue.peekFirst().value;
        }

        StringJoiner sj = new StringJoiner(" ");
        for(int i=1;i<=N;i++){
            sj.add(Integer.toString(D[i]));
        }

        System.out.println(sj);
    }

    private static void insert(Node A){
        //queue의 맨 앞은 항상 작고 오래된 거
        if(!queue.isEmpty() && queue.peekFirst().index == A.index-L+1-1){
            queue.pollFirst();
        }

        while(!queue.isEmpty()){
            if(queue.peekLast().value < A.value){
                break;
            }

            queue.pollLast();
        }

        queue.addLast(A);
    }
}