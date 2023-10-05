import java.util.*;
import java.io.*;

class Computer{
    int time=0;
    boolean isVisited=false;
    List<int[]> nears=new LinkedList<>();

    public Computer(){}
    public void addNears(int idx, int time){
        int[] near={idx, time};
        nears.add(near);
    }

    public void updateTime(int time){
        this.time=time;
    }

    public void visit(){
        this.isVisited=true;
    }

    public boolean updateTimeWithCompare(int time){
        if(this.time>time) {
            this.time = time;
            return true;
        }

        return false;
    }
}

class Main{

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int tc=Integer.parseInt(br.readLine());

        for(int i=0;i<tc;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            Computer[] computers=new Computer[n+1];
            for(int com=0;com<=n;com++){
                computers[com]=new Computer();
            }

            for(int j=0;j<d;j++){
                st=new StringTokenizer(br.readLine());

                //양방향 x
                int c1=Integer.parseInt(st.nextToken());
                int c2=Integer.parseInt(st.nextToken());
                int time=Integer.parseInt(st.nextToken());

                //c1이 c2를 의존. c2가 감염되면 time초 후 c1도 감염 
                computers[c2].addNears(c1, time);
            }

            bfs(c, computers, sb);
        }

        System.out.print(sb);
    }

    private static void bfs(int start, Computer[] computers, StringBuilder sb){

        computers[start].visit(); //방문처리

        LinkedList<Integer> idxQueue=new LinkedList<>();
        idxQueue.addLast(start);

        while(!idxQueue.isEmpty()){
            int nowIdx= idxQueue.poll();
            List<int[]> nowNears= computers[nowIdx].nears;

            int nowTime=computers[nowIdx].time;
            for(int[] near:nowNears){
                int newIdx=near[0];
                int newValue=nowTime+near[1];

                Computer newComputer=computers[newIdx];
                //방문하지 않은 경우
                if(!newComputer.isVisited){
                    newComputer.visit();
                    newComputer.updateTime(newValue);
                    idxQueue.addLast(newIdx);

                    continue;
                }

                //방문했던 경우
                if(newComputer.updateTimeWithCompare(newValue)){
                    //방문했던 경우 update 했으면 큐에 넣기
                    idxQueue.addLast(newIdx);
                }
            }
        }

        //현재 computers 중 제일 큰 time 값 출력
        int max=Integer.MIN_VALUE;
        int cnt=0;

        for(Computer c:computers){

            if(c.isVisited)
                cnt++;

            if(c.time>max)
                max=c.time;
        }

        sb.append(cnt).append(" ").append(max).append("\n");
    }

}