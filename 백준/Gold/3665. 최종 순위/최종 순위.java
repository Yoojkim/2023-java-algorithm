import java.util.*;
import java.io.*;

class Player implements Comparable<Player>{
    int player;
    int rank;

    public Player(int player, int rank){
        this.player=player;
        this.rank=rank;
    }

    public int compareTo(Player p){
        if(this.rank<p.rank){
            return -1;
        } else if(this.rank>p.rank){
            return 1;
        } else{
            throw new RuntimeException();
        }
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i=0;i<t;i++){
            int n=Integer.parseInt(br.readLine());

            int[] prevRanks=new int[n+1];
            int[] ranks=new int[n+1];

            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int player=Integer.parseInt(st.nextToken());

                prevRanks[player]=j;
                ranks[player]=j;
            }

            //본격적 수행 (rank 조정)
            int m=Integer.parseInt(br.readLine());
            for(int j=0;j<m;j++){
                st=new StringTokenizer(br.readLine());
                int p1=Integer.parseInt(st.nextToken()); int p2=Integer.parseInt(st.nextToken());

                if(prevRanks[p1]<prevRanks[p2]){
                    ranks[p1]=ranks[p1]+1;
                    ranks[p2]=ranks[p2]-1;
                }else{
                    ranks[p1]=ranks[p1]-1;
                    ranks[p2]=ranks[p2]+1;
                }
            }

            //논리적 비약 확인 + 출력
            PriorityQueue<Player> queue=new PriorityQueue();

            try{
                //queue에 넣기
                for(int j=1;j<=n;j++){
                    Player player=new Player(j,ranks[j]); //(player 번호, 등수)
                    queue.add(player);
                }

                Comparator<Player> comparator=new Comparator<Player>(){
                    public int compare(Player p1, Player p2){
                        return (p1.rank-p2.rank);
                    }
                };

                PriorityQueue<Player> newQueue=new PriorityQueue<>(comparator);
                newQueue.addAll(queue);

                //queue에서 빼기
                int cnt=0;
                StringBuilder sb2=new StringBuilder();

                while(!newQueue.isEmpty()){
                    Player player=newQueue.poll();

                    if(player.rank!=cnt++)
                        throw new RuntimeException();

                    sb2.append(player.player).append(" ");
                }

                sb.append(sb2);

            } catch(Exception e){
                sb.append("IMPOSSIBLE");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}