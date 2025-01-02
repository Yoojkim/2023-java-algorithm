import java.util.*;

class Node{
    int screen;
    int board;

    public Node(int screen, int board){
        this.screen = screen;
        this.board = board;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int S = scanner.nextInt();

        Queue<Node> queue = new LinkedList<>();
        boolean[][] calculated = new boolean[2001][2001];

        Node init = new Node(1, 0);
        calculated[1][0] = true;
        int turn = 0;
        queue.add(init);
        while(!queue.isEmpty()){
            int turnSize = queue.size();
            for(int i=0;i<turnSize;i++){
                Node now = queue.poll();

                if(now.screen == S){
                    System.out.print(turn);
                    return;
                }

                List<Node> nextNodes = new ArrayList<>();
                nextNodes.add(createOption3(now));
                if(now.screen < S){
                    nextNodes.add(createOption1(now));
                    nextNodes.add(createOption2(now));
                }

                for(int j=0;j<nextNodes.size();j++){
                    Node nextNode = nextNodes.get(j);

                    if(nextNode.screen<=0){
                        continue;
                    }

                    if(calculated[nextNode.screen][nextNode.board]){
                        continue;
                    }

                    queue.add(nextNode);
                    calculated[nextNode.screen][nextNode.board] = true;
                }
            }

            turn++;
        }

        System.out.print("can not calculate!");
    }

    static public Node createOption1(Node node){
        return new Node(node.screen, node.screen);
    }

    static public Node createOption2(Node node){
        return new Node(node.screen+node.board, node.board);
    }

    static public Node createOption3(Node node){
        return new Node(node.screen-1, node.board);
    }
}