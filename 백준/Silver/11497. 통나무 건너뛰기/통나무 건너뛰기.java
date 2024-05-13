import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<Integer> trees = new ArrayList<>();
            while(N-->0){
                trees.add(Integer.parseInt(st.nextToken()));
            }

            sj.add(Integer.toString(getResult(trees)));
        }

        System.out.println(sj);
    }

    public static int getResult(List<Integer> trees){
        Collections.sort(trees);

        int max = Integer.MIN_VALUE;
        int size = trees.size();

        //짝수
        for(int i=0;i<size;i+=2){
            if(i+2 >= size)
                break;

            int diff = Math.abs(trees.get(i) - trees.get(i+2));
            max = max<diff?diff:max;
        }

        //홀수
        for(int i=1;i<size;i+=2){
            if(i+2 >= size)
                break;

            int diff = Math.abs(trees.get(i) - trees.get(i+2));
            max = max<diff?diff:max;
        }

        //앞, 뒤
        if(size>=2){
            int backDiff = Math.abs(trees.get(size-2) - trees.get(size-1));
            max = max<backDiff?backDiff:max;
        }

        return max;
    }
}
