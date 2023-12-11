import java.util.*;
import java.io.*;

class Node {
    int cost;
    int index;

    public Node(int cost, int index) {
        this.cost = cost;
        this.index = index;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Node> nodes = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nodes.add(new Node(Integer.parseInt(st.nextToken()), i));
        }

        List<Integer> answers = getResult(nodes);
        StringJoiner sj = new StringJoiner(" ");
        for (int answer : answers) {
            sj.add(Integer.toString(answer));
        }

        System.out.println(sj);
    }

    private static List<Integer> getResult(List<Node> nodes) {
        final int NON = 0;
        Stack<Node> stack = new Stack<>();
        List<Integer> answer = new ArrayList<>();

        for (Node node : nodes) {
            while(true){
                if(stack.isEmpty()){
                    answer.add(NON);
                    stack.add(node);
                    break;
                }

                Node peekNode = stack.peek();
                if(peekNode.cost<node.cost){
                    stack.pop();
                } else{
                    stack.add(node);
                    answer.add(peekNode.index);
                    break;
                }
            }
        }

        return answer;
    }
}