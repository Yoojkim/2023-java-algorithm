import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size=Integer.parseInt(br.readLine());
        int[] rank=new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=size;i++){
            setRank(rank, i, Integer.parseInt(st.nextToken()));
        }

        print(rank);
    }

    private static void setRank(int[] rank, int height, int higherCnt){
        int index=0;
        int limit=rank.length;

        while(index!=limit){
            if(higherCnt==0 && rank[index]==0){
                rank[index]=height;
                break;
            }

            if(higherCnt>0 && rank[index]==0){
                higherCnt--;
            }

            index++;
        }
    }

    private static void print(int[] rank){
        StringJoiner sj = new StringJoiner(" ");
        for(int r:rank){
            sj.add(Integer.toString(r));
        }

        System.out.print(sj);
    }
}