import java.util.*;

class Solution {
    final int tempSize = 50;
    final int boardSize = 1000;
    int[][] dp;
    int[][] withAirConditional;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        dp = new int[boardSize+1][tempSize+1];
        for (int i = 0; i <= boardSize; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][temperature + 10] = 0;

        t1 += 10;
        t2 += 10;
        temperature += 10;

        withAirConditional = new int[][]{
                {-1, a},
                {1, a},
                {0, b}
        };

        for(int i=1;i<onboard.length;i++){
            if(onboard[i] == 1){
                calc(temperature, i, t1, t2);
                continue;
            }

            calc(temperature, i, 0, tempSize);
        }

        int result = Integer.MAX_VALUE;
        for(int value:dp[onboard.length-1]){
            if(value < result){
                result = value;
            }
        }

        return result;
    }

    private void calc (int outside, int sec, int start, int end){
        for(int temp=start;temp<=end;temp++){
            int with = getMinWithAirConditional(sec, temp);
            int without = getMinWithOutAirConditional(outside, sec, temp);

            dp[sec][temp] = with < without ? with : without;
        }
    }

    private int getMinWithAirConditional(int sec, int temp){
        List<Integer> pool = new LinkedList<>();

        for(int[] change:withAirConditional){
            if(isPossible(sec-1, temp+change[0])){
                pool.add(dp[sec-1][temp+change[0]]+change[1]);
            }
        }

        if(pool.isEmpty()){
            return Integer.MAX_VALUE;
        }

        Collections.sort(pool);

        return pool.get(0);
    }

    private int getMinWithOutAirConditional(int outside, int sec, int temp){
        List<Integer> pool = new LinkedList<>();
        if(outside == temp && isPossible(sec-1, temp)){
            pool.add(dp[sec-1][temp]);
        }

        if(outside >= temp && isPossible(sec-1, temp-1)){
            pool.add(dp[sec-1][temp-1]);
        }

        if(outside <= temp && isPossible(sec-1, temp+1)){
            pool.add(dp[sec-1][temp+1]);
        }

        if(pool.isEmpty()){
            return Integer.MAX_VALUE;
        }

        Collections.sort(pool);

        return pool.get(0);
    }

    private boolean isPossible(int sec, int temp){
        if(sec < 0){
            return false;
        }

        if(temp <0 || temp > 50){
            return false;
        }

        int value = dp[sec][temp];

        if(value == Integer.MAX_VALUE){
            return false;
        }

        return true;
    }
}