import java.util.*;
import java.io.*;

class A{
    int a;
    LinkedList<Character> queue;

    public A(int a, LinkedList<Character> queue){
        this.a=a;
        this.queue=queue;
    }
}
class Main {
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] nums = br.readLine().split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);

            bfs(a, b);
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int a, int b) throws Exception{
        A newA = new A(a, new LinkedList());

        LinkedList<A> queue = new LinkedList();
        boolean[] visited=new boolean[100000];
        visited[a]=true;
        queue.addLast(newA);
        while (!queue.isEmpty()) {
            A nowA = queue.poll();

            //조건 만족 종료
            if (nowA.a == b) {
                for (char op : nowA.queue) {
                    bw.write(op);
                }
                bw.write("\n");
                break;
            }

            for (int i = 0; i < 4; i++) {
                int changeA;
                char operation;
                if (i == 0) {
                    changeA = (2 * nowA.a > 9999 ? (2 * nowA.a) % 10000 : 2 * nowA.a);
                    operation = 'D';
                } else if (i == 1) {
                    changeA = (nowA.a == 0 ? 9999 : nowA.a - 1);
                    operation = 'S';
                } else {
                    int tempA=nowA.a;
                    int d1 = tempA / 1000;
                    tempA -= d1 * 1000;
                    int d2 = tempA / 100;
                    tempA -= d2 * 100;
                    int d3 = tempA / 10;
                    tempA -= d3 * 10;
                    int d4 = tempA;

                    if (i == 2) {
                        changeA=d2*1000+d3*100+d4*10+d1;
                        operation='L';
                    } else {
                        changeA=d4*1000+d1*100+d2*10+d3;
                        operation='R';
                    }
                }

                if(visited[changeA])
                    continue;

                visited[changeA]=true;

                //현재 nowA를 복사한 리스트에 operation 추가
                LinkedList<Character> newOperations=new LinkedList<>();
                newOperations.addAll(nowA.queue);
                newOperations.addLast(operation);

                A nextA=new A(changeA, newOperations);
                queue.addLast(nextA);
            }
        }
    }
}