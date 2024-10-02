import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int nowHealth = health;
        
        //첫 공격
        nowHealth -= attacks[0][1];
        
        for(int i=1;i<attacks.length;i++){
            //회복 계산
            int[] previous = attacks[i-1];
            int[] now = attacks[i];
            
            int time = now[0] - previous[0] -1; //총 회복할 수 있는 시간
            int recovery = calRecovery(bandage, time);
            
            nowHealth = Math.min(health, nowHealth+recovery);
            
            //이번 공격 ㄱㄱ
            nowHealth -= now[1];
            if(nowHealth<=0){
                return -1;
            }
        }
        
        return nowHealth;
    }
    
    private int calRecovery(int[] bandage, int time){
        //1초부터 time까지
        int coolTime = bandage[0];
        int heal = bandage[1];
        int bonus = bandage[2];
        
        int now = 1;
        int sum = 0;
        while(now <= time){
            if(now % coolTime == 0){
                sum += bonus;
            }
            
            sum+=heal;
            now++;
            
        }
        
        return sum;
    }
}