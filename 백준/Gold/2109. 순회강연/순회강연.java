import java.util.*;
import java.io.*;

//todo: d일이 아니라 d일 안 <- 이걸 고려해야만 함.

/**
 * [반례]
 * 1 10
 * 2 10
 *
 * 3 1000
 * 3 2000
 * 3 3000
 *
 * -> 1000 + 2000 + 3000
 * */
class Suggest implements Comparable<Suggest>{
    int days;
    int pay;

    public Suggest(int days, int pay){
        this.days=days;
        this.pay=pay;
    }

    //pay 내림차순
    public int compareTo(Suggest s){
        return s.pay-this.pay;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Suggest> queue=new PriorityQueue();
        boolean[] visited = new boolean[10000+1];

        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());

            int pay=Integer.parseInt(st.nextToken());
            int days=Integer.parseInt(st.nextToken());

            queue.add(new Suggest(days, pay));
        }

        long paySum=getAllPay(queue, visited);

        System.out.print(paySum);
    }

    private static long getAllPay(PriorityQueue<Suggest> queue, boolean[] visited){
        int sum=0;

        while(!queue.isEmpty()){
            Suggest suggest=queue.poll();

            int visitDate=calVisitDate(visited, suggest);

            if(visitDate==0) {
                continue;
            }

            visited[visitDate]=true;
            sum+=suggest.pay;
        }

        return sum;
    }

    private static int calVisitDate(boolean[] visited, Suggest suggest){
        int idx=suggest.days;

        while(idx>=1){
            if(!visited[idx]){
                break;
            }

            idx--;
        }

        return idx;
    }
}