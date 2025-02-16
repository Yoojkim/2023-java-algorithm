import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=nums.length-1; i>0; i--){
            //현재 i와 i-1 비교
            int front = nums[i-1];
            int back = nums[i];

            if(front> back){
                continue;
            }

            //front<back
            //subNums
            List<Integer> sub = new ArrayList<>();
            boolean[] visited= new boolean[N+1];
            for(int j=i-1;j<nums.length;j++){
                sub.add(nums[j]);
                visited[nums[j]] = true;
            }

            int newFront = 0;
            for(int j = front+1 ; j<= back; j++){
                if(!visited[j]){
                    continue;
                }

                newFront = j;
                break;
            }

            //앞부분 + 뒷부분 출력
            StringJoiner sj = new StringJoiner(" ");

            for(int j=0; j<i-1; j++){
                sj.add(Integer.toString(nums[j]));
            }
            sj.add(Integer.toString(newFront));
            Collections.sort(sub);

            for(int s:sub){
                if(s == newFront){
                    continue;
                }

                sj.add(Integer.toString(s));
            }

            System.out.print(sj);

            return;
        }

        System.out.print(-1);
    }
}