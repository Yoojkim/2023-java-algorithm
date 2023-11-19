import java.util.*;
import java.io.*;


class Main {
    public static int N;
    public static int MIN = Integer.MAX_VALUE;
    public static boolean[] isATeam;
    public static int[][] abilities;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        isATeam = new boolean[N + 1];
        abilities = new int[N+1][N+1];
        StringTokenizer st;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=N;j++){
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        assignTeam(1);
        System.out.print(MIN);
    }

    private static void assignTeam(int person) {
        if (person == N + 1) {
            List<Integer> aTeam = new ArrayList<>();
            List<Integer> bTeam = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (isATeam[i]) {
                    aTeam.add(i);
                } else {
                    bTeam.add(i);
                }
            }

            if(aTeam.size() != N/2){
                return;
            }

            //값 계산
            calculateDifference(aTeam, bTeam);
            return;
        }

        isATeam[person]=true;
        assignTeam(person+1);

        isATeam[person]=false;
        assignTeam(person+1);
    }

    private static void calculateDifference(List<Integer> aTeam, List<Integer> bTeam) {
        int aTeamSum = 0;
        for (int a : aTeam) {
            for (int all : aTeam) {
                aTeamSum += abilities[a][all];
            }
        }

        int bTeamSum = 0;
        for (int b : bTeam) {
            for (int all : bTeam) {
                bTeamSum += abilities[b][all];
            }
        }

        int diff = aTeamSum-bTeamSum;
        diff=diff<0?diff*-1:diff;

        if(MIN>diff){
            MIN=diff;
        }
    }
}