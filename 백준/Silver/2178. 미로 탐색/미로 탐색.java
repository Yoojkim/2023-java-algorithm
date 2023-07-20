import java.io.*;
import java.util.*;

class Graph{
    int n;
    int m;
    int[][] graph;

    //초기화 시점 알기
    int[][] addVars=new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    Graph(int n, int m){
        this.n=n;
        this.m=m;

        //배열 생성
        graph=new int[n][m]; //초기화 모두 0
    }

    void setGraph(int[][] graph){
        this.graph=graph;
    }

    int bfs(){
        LinkedList<int[]> queue=new LinkedList();
        int tryNum=0;
        boolean keepGoing=true;

        queue.add(new int[]{0, 0});
        tryNum++;

        while(keepGoing) {
            LinkedList<int[]> newQueue = new LinkedList();

            for (int[] p : queue) {
                if (this.visited(p, newQueue))
                    keepGoing = false;
            }

            queue=newQueue;
            tryNum++;
        }

            return tryNum;

    }

    boolean visited(int[] point, LinkedList<int[]> queue){
        int pn=point[0];
        int pm=point[1];

        for(int[] addVar: addVars){
            int newPn=pn+addVar[0];
            int newPm=pm+addVar[1];

            if (newPn>=0 && newPn<n && newPm>=0 && newPm<m){
                if(graph[newPn][newPm]==1){
                    queue.add(new int[] {newPn, newPm});
                    graph[newPn][newPm]++; //방문 완료 표시

                    if(newPn==n-1 && newPm==m-1)
                        return true; //특이사항: 종착지 방문
                }
            }
        }

        return false; //특이사항 없음.
    }

    }

    public class Main{
        static public void main(String[] args) throws Exception{
            BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st= new StringTokenizer(bf.readLine());

            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());

            Graph graph=new Graph(n, m);

            int[][] maze=new int[n][m];
            for(int i=0;i<n;i++){
                String[] words=bf.readLine().split("");

                int j=0;
                for(String word:words){
                    maze[i][j++]=Integer.parseInt(word);
                }

            }

            graph.setGraph(maze);
            int tryNum=graph.bfs();

            System.out.print(tryNum);
        }
    }