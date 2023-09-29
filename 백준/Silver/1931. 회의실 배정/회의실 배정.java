import java.util.*;
import java.io.*;

//아이디어: 종료 시간으로 정렬해서 그때그때 빼주기 (안될수도)

class Meeting implements Comparable<Meeting>{
    long start;
    long end;

    public Meeting(long start, long end){
        this.start=start;
        this.end=end;
    }

    public int compareTo(Meeting m){
        if(this.end<m.end)
            return -1;
        else if(this.end==m.end){
            //start 기준으로 정렬
            if(this.start<m.start)
                return -1;
            else if(this.start==m.start)
                return 0;
            else 
                return 1;
        }
        else
            return 1;
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        PriorityQueue<Meeting> queue=new PriorityQueue();
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());

            long start=Long.parseLong(st.nextToken());
            long end=Long.parseLong(st.nextToken());

            queue.add(new Meeting(start, end));
        }

        long nowEnd=0; int cnt=0;

        while(!queue.isEmpty()){
            Meeting m=queue.poll();

            if(m.start<nowEnd)
                continue;

            nowEnd=m.end;
            cnt++;
        }

        System.out.print(cnt);
    }
}