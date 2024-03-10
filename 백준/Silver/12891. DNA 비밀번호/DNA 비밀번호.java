import java.util.*;
import java.io.*;

class Main{
    static char[] arrs;
    static int S, P;
    static Map<Character, Integer> counts=new HashMap<>();
    static Map<Character, Integer> minimums=new HashMap<>();
    static char[] dna = {
            'A', 'C', 'G', 'T'
    };
    static int count=0;
    static int start, end;

    public static void main(String[] args) throws Exception{
        initializeMap(counts);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        arrs = br.readLine().toCharArray();

        start = 0; end = P-1;
        for(int i=start;i<=end;i++){
            addMap(arrs[i]);
        }

        st = new StringTokenizer(br.readLine());
        for(char c:dna){
            minimums.put(c, Integer.parseInt(st.nextToken()));
        }

        while(end<S){
            count += isNowPossible();

            if(end == S-1){
                break;
            }

            nextStep();
        }

        System.out.print(count);
    }

    private static void nextStep(){
        counts.put(arrs[start], counts.get(arrs[start])-1);
        start++;
        end++;
        counts.put(arrs[end], counts.get(arrs[end])+1);
    }

    private static int isNowPossible(){
        for(char c:dna){
            if(!(counts.get(c)>=minimums.get(c))){
                return 0;
            }
        }

        return 1;
    }

    private static void initializeMap(Map map){
        for(char c:dna){
            map.put(c, 0);
        }
    }

    private static void addMap(char c){
        counts.put(c, counts.get(c)+1);
    }
}