import java.util.*;
import java.io.*;

class Graph{
    int n;
    int[][] graph;
    LinkedList<Integer> village=new LinkedList<>();
    int[][] displacement={
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public Graph(int n, int[][] graph){
        this.n=n;
        this.graph=graph;
    }

    void dfs(){
        int villageCnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){

                if(graph[i][j]==1){
                    village.add(0);
                    dfsUtil(i, j, villageCnt++);
                }//if문
            }
        } //반복문

        Collections.sort(village);
    }

    /**
     * return 값 더하는 부분에서 문제가 있는 듯함
     * 재귀 호출도 해야되고 1도 반환해야하고
     * */
    void dfsUtil(int i, int j, int idx){

        //방문 할 수 없으면 0 반환
        if(graph[i][j]==0)
            return;

        //방문
        graph[i][j]=0;
        village.set(idx, village.get(idx)+1);

        for(int[] dP:displacement){
            int newI=i+dP[0]; int newJ=j+dP[1];

            //Null 처리
            if(!(newI>=0 && newI<n && newJ>=0 && newJ<n))
                continue;

            dfsUtil(newI, newJ, idx);
        }
    }

    void printVillage(){
        System.out.println(village.size());
        Iterator<Integer> it=village.listIterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[][] graphArr=new int[n][n];

        for(int i=0;i<n;i++){
            String[] arrs=br.readLine().split("");
            for(int j=0;j<n;j++){
                graphArr[i][j]=Integer.parseInt(arrs[j]);
            }
        }

        Graph graph=new Graph(n, graphArr);
        graph.dfs();
        graph.printVillage();
    }
}