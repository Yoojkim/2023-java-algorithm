import java.util.*;
import java.io.*;

enum Direct{
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    public int dr;
    public int dc;

     Direct(int dr, int dc){
        this.dr = dr;
        this.dc = dc;
    }
}

public class Main{
    static int R;
    static int C;
    static int[][] fields;
    static int fresherTop;
    static int fresherBottom;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        fields = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findFresher();

        for(int i=0;i<T;i++){
            spreadDust();
            runTopFresher();
            runBottomFresher();
        }

        System.out.print(getAllDust());
    }

    private static int getAllDust(){
        fields[fresherTop][0] = 0;
        fields[fresherBottom][0] = 0;

        int sum = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sum+=fields[i][j];
            }
        }

        return sum;
    }

    private static void findFresher(){
        for(int i=0;i<R;i++){
            if(fields[i][0] == -1){
                fresherTop = i;
                fresherBottom = i+1;

                break;
            }
        }
    }

    private static void spreadDust(){
        int[][] amountFields = new int[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int dust = fields[i][j];
                if(dust < 5){
                    continue;
                }

                int amount = fields[i][j]/5;

                int availableDirect = 0;
                for(Direct direct:Direct.values()){
                    int newR = i+direct.dr;
                    int newC = j+direct.dc;

                    if(newR < 0 || newR >= R || newC <0 || newC >=C){
                        continue;
                    }

                    if(fields[newR][newC] == -1){
                        continue;
                    }

                    availableDirect++;
                    amountFields[newR][newC] += amount;
                }

                fields[i][j] -= amount*availableDirect;
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                fields[i][j] += amountFields[i][j];
            }
        }
    }

    private static void runTopFresher(){
        Queue<Integer> dusts = new LinkedList<>();

        dusts.add(0);
        //우
        for(int c=1;c<C;c++){
            dusts.add(fields[fresherTop][c]);
        }

        //상
        for(int r=fresherTop-1; r>=1; r--){
            dusts.add(fields[r][C-1]);
        }

        //좌
        for(int c=C-1;c>=0;c--){
            dusts.add(fields[0][c]);
        }

        //하
        for(int r=1;r<fresherTop;r++){
            dusts.add(fields[r][0]);
        }

        //다시 뿌려주기
        for(int c=1;c<C;c++){
            fields[fresherTop][c] = dusts.poll();
        }
        for(int r=fresherTop-1;r>=1;r--){
            fields[r][C-1] = dusts.poll();
        }
        for(int c=C-1;c>=0;c--){
            fields[0][c] = dusts.poll();
        }
        for(int r=1;r<fresherTop;r++){
            fields[r][0] = dusts.poll();
        }
    }

    private static void runBottomFresher(){
        Queue<Integer> dusts = new LinkedList<>();
        dusts.add(0);

        //우
        for(int c=1;c<C;c++){
            dusts.add(fields[fresherBottom][c]);
        }

        //하
        for(int r=fresherBottom+1;r<R-1;r++){
            dusts.add(fields[r][C-1]);
        }

        //좌
        for(int c=C-1;c>=0;c--){
            dusts.add(fields[R-1][c]);
        }

        //상
        for(int r=R-2;r>fresherBottom;r--){
            dusts.add(fields[r][0]);
        }

        //다시 뿌려주기
        for(int c=1;c<C;c++){
            fields[fresherBottom][c] = dusts.poll();
        }

        for(int r=fresherBottom+1;r<R-1;r++){
            fields[r][C-1] = dusts.poll();
        }

        for(int c=C-1;c>=0;c--){
            fields[R-1][c] = dusts.poll();
        }

        for(int r=R-2;r>fresherBottom;r--){
            fields[r][0] = dusts.poll();
        }
    }
}