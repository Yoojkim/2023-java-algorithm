import java.util.*;
import java.io.*;

class Planet{
    int x, y, z;
    int num;

    public Planet(int x, int y, int z, int num){
        this.x=x; this.y=y; this.z=z;
        this.num=num;
    }
}

class Tunnel implements Comparable<Tunnel>{
    int num;
    int cost;

    public Tunnel(int num, int cost){
        this.num=num;
        this.cost=cost;
    }

    //오름차순
    public int compareTo(Tunnel tunnel){
        return this.cost- tunnel.cost;
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        List<Planet> planets=new ArrayList<>();

        for(int i=0;i<n;i++){
            String[] nums=br.readLine().split(" ");

            int x=Integer.parseInt(nums[0]);
            int y=Integer.parseInt(nums[1]);
            int z=Integer.parseInt(nums[2]);

            planets.add(new Planet(x, y, z, i));
        }

        //각 행성마다 인접한 간선 저장할 리스트 생성
        List<Tunnel>[] nears=new LinkedList[n];

        //Comparator
        Comparator<Planet> cpX=new Comparator<Planet>(){
            public int compare(Planet p1, Planet p2){
                return p1.x-p2.x;
            }
        };

        Comparator<Planet> cpY=new Comparator<Planet>(){
            public int compare(Planet p1, Planet p2){
                return p1.y-p2.y;
            }
        };
        Comparator<Planet> cpZ=new Comparator<Planet>(){
            public int compare(Planet p1, Planet p2){
                return p1.z-p2.z;
            }
        };


        Comparator<Planet>[] comparators=new Comparator[3];
        comparators[0]=cpX;
        comparators[1]=cpY;
        comparators[2]=cpZ;

        for(int i=0;i<n;i++){
            nears[i]=new LinkedList<>();
        }

        //간선 생성하여 배부
        for(int j=0;j<3;j++){
            Collections.sort(planets, comparators[j]);

            for(int e=1;e<n;e++){
                Planet p1=planets.get(e-1); Planet p2=planets.get(e);
                int cost=calCost(p1, p2, j);

                //간선 배부
                nears[p1.num].add(new Tunnel(p2.num, cost));
                nears[p2.num].add(new Tunnel(p1.num, cost));
            }
        }

        int cnt=0; int sum=0;
        PriorityQueue<Tunnel> queue=new PriorityQueue();
        boolean[] visited=new boolean[n];

        queue.add(new Tunnel(0, 0));
        while(true){
            Tunnel now=queue.poll();

            if(visited[now.num])
                continue;

            visited[now.num]=true;
            sum+=now.cost;
            cnt++;

            if(cnt==n)
                break;

            for(Tunnel t:nears[now.num]){
                if(!visited[t.num]){
                    queue.add(t);
                }
            }
        }

        System.out.print(sum);
    }

    private static int calCost(Planet p1, Planet p2, int standard){
        int res;

        if(standard==0)
            res=p1.x-p2.x;
        else if(standard==1)
            res=p1.y-p2.y;
        else
            res=p1.z-p2.z;

        return res<0?res*-1:res;
    }
}