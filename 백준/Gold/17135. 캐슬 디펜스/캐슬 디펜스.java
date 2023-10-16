import java.util.*;
import java.io.*;

//아니 막 어렵진 않은데 코드가 더러움;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

class Enemy implements Comparable<Enemy>{
    int idx;
    int j;
    int dist;

    public Enemy(int idx, int j, int dist){
        this.idx=idx;
        this.j=j;
        this.dist=dist;
    }

    public int compareTo(Enemy e){
        int res=this.dist-e.dist;
        if(res<0){
            return -1;
        } else if(res==0)
            return this.j-e.j;
        else{
            return 1;
        }
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());

        LinkedList<Point> points=new LinkedList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int val=Integer.parseInt(st.nextToken());

                if(val==1)
                    points.add(new Point(i, j));
            }
        }

        List<Integer> archers=new LinkedList<>();
        int ans=backTracking(0, n, m, points, archers, d);
        System.out.print(ans);
    }

    private static int backTracking(int idx, int n, int m, List<Point> points, List<Integer> archers, int d){
        if(archers.size()==3){
            List<Point> newPoints=new LinkedList<>();

            for(Point p:points){
                newPoints.add(new Point(p.i, p.j));
            }
            return getAns(n, newPoints, archers, d);
        }

        int max=Integer.MIN_VALUE;
        for(int i=idx;i<m;i++){
            archers.add(i);
            int ans=backTracking(i+1, n, m, points, archers, d);

            if(max<ans)
                max=ans;

            archers.remove(archers.size()-1);
        }

        return max;
    }

    private static int getAns(int n, List<Point> points, List<Integer> archers, int d){
        int cnt=0;

        while(true){
            if(points.size()==0)
                break;

            Set<Integer> set=new HashSet<>();
            for(int archer:archers){

                int enemyIdx=getEnemyIdx(n, points, archer, d);
                if(enemyIdx<0)
                    continue;

                set.add(enemyIdx);
            }

            List<Integer> newList=new ArrayList<>(set);
            Collections.sort(newList);

            Iterator<Point> it = points.listIterator();
            int idx=0; int removedIdx=0;

            while(it.hasNext()){
                Point p=it.next();

                if(!newList.isEmpty() && removedIdx<newList.size() && idx==newList.get(removedIdx)){
                    it.remove();
                    removedIdx++;
                } else{
                    p.i+=1;
                }

                if(p.i==n)
                    it.remove();

                idx++;
            }

            cnt+=newList.size();
        }

        return cnt;
    }

    private static int getEnemyIdx(int n, List<Point> points, int archer, int d){
        int ans=-1;
        PriorityQueue<Enemy> queue=new PriorityQueue<>();

        for(int i=0;i<points.size();i++){
            int dist=getDist(points.get(i), n, archer);

            if(dist>d)
                continue;

            queue.add(new Enemy(i, points.get(i).j, dist));
        }

        if(!queue.isEmpty())
            ans=queue.poll().idx;

        return ans;
    }

    private static int getDist(Point point, int i, int j){
        int di= point.i-i;
        int dj=point.j-j;

        di=(di<0)?di*-1:di;
        dj=(dj<0)?dj*-1:dj;

        return di+dj;
    }
}