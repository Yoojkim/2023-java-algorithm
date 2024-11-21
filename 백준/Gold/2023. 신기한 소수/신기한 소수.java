import java.util.*;
import java.io.*;

class Main{
    static int N;
    static List<Integer> decimals = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        createNum(1, 0);

        Collections.sort(decimals);

        StringJoiner sj = new StringJoiner("\n");
        for(int decimal:decimals){
            sj.add(Integer.toString(decimal));
        }

        System.out.print(sj);
    }

    static void createNum(int pos, int num){
        if(pos == 1){
            createNum(2, 2);
            createNum(2, 3);
            createNum(2, 5);
            createNum(2, 7);

            return;
        }

        if(pos == N+1){
            if(isDecimal(num)){
                decimals.add(num);
            }
            return;
        }

        if(!isDecimal(num)){
            return;
        }

        createNum(pos+1, num*10+1);
        createNum(pos+1, num*10+3);
        createNum(pos+1, num*10+7);
        createNum(pos+1, num*10+9);
    }


    static boolean isDecimal(int num){
        for(int i=2;i<=num/2;i++){
            if(num%i==0){
                return false;
            }
        }

        return true;
    }
}