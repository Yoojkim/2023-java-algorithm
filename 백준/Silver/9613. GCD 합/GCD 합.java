import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringJoiner sj = new StringJoiner("\n");

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] numbers = new int[N];
            
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            Map<Integer, List<Integer>> divisorCache = new HashMap<>();

            for (int i = 0; i < N; i++) {
                int a = numbers[i];
                List<Integer> divisors = divisorCache.computeIfAbsent(a, Main::getDivisors);
                
                for (int j = 1; i + j < N; j++) {
                    int b = numbers[i + j];
                    
                    for (int k = divisors.size() - 1; k >= 0; k--) {
                        int aDivisor = divisors.get(k);
                        if (b % aDivisor == 0) {
                            sum += aDivisor;
                            break;
                        }
                    }
                }
            }

            sj.add(Long.toString(sum));
        }

        System.out.print(sj);
    }

    static List<Integer> getDivisors(int num) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                ans.add(i);
                if (i != num / i) {
                    ans.add(num / i);
                }
            }
        }

        Collections.sort(ans); // 내림차순으로 사용하려면 정렬
        return ans;
    }
}
