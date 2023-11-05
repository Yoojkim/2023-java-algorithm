import java.util.*;
import java.io.*;

class Assign implements Comparable<Assign>{
    int days;
    int Score;

    public Assign(int days, int pay){
        this.days=days;
        this.Score =pay;
    }

    //score 내림차순
    public int compareTo(Assign s){
        return s.Score -this.Score;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Assign> queue=new PriorityQueue();
        boolean[] assignDates = new boolean[1000+1];

        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());

            int days=Integer.parseInt(st.nextToken());
            int score=Integer.parseInt(st.nextToken());

            queue.add(new Assign(days, score));
        }

        long paySum= getAllScores(queue, assignDates);

        System.out.print(paySum);
    }

    private static long getAllScores(PriorityQueue<Assign> queue, boolean[] assignDates){
        int sum=0;

        while(!queue.isEmpty()){
            Assign assign=queue.poll();

            int visitDate= calAssignDate(assignDates, assign);

            if(visitDate==0) {
                continue;
            }

            assignDates[visitDate]=true;
            sum+=assign.Score;
        }

        return sum;
    }

    private static int calAssignDate(boolean[] visited, Assign assign){
        int idx=assign.days;

        while(idx>=1){
            if(!visited[idx]){
                break;
            }

            idx--;
        }

        return idx;
    }
}