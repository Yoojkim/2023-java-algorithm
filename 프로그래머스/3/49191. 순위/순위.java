import java.util.*;

class Player{
    int number;
    List<Integer> wins = new ArrayList<>(); //player가 얘네한테 이김
    List<Integer> loses = new ArrayList<>(); //player가 얘네한테 짐 
    
    public Player(int number){
        this.number = number;
    }
}

class Solution {
    public int solution(int n, int[][] results) {
        Player[] players = new Player[n+1];
        for(int i=1;i<=n;i++){
            players[i] = new Player(i);
        }
        
        for(int[] result: results){
            int winner = result[0];
            int looser = result[1];
            
            players[winner].wins.add(looser);
            players[looser].loses.add(winner);
        }
        
        //계산
        int cnt = 0;
        for(int i=1;i<=n;i++){
         if(getAllScorePlayers(players, i, n) == n-1){
             cnt++;
         }   
        }
        
        return cnt;
    }
    
    int getAllScorePlayers(Player[] players, int player, int n){
        boolean visited[] = new boolean[n+1];
        visited[player] = true;
            
        //winner
        Queue<Integer> winnerQueue = new LinkedList<>();
        winnerQueue.addAll(players[player].wins);
        
        int cnt=0;
        while(!winnerQueue.isEmpty()){
            int winner = winnerQueue.poll();
            
            if(visited[winner]){
                continue;
            }
            
            visited[winner] = true;
            cnt++;
            
            for(int win:players[winner].wins){
                if(visited[win]){
                    continue;
                }
                
                winnerQueue.add(win);
            }
        }
        
        //looser
        Queue<Integer> looserQueue = new LinkedList<>();
        looserQueue.addAll(players[player].loses);
        
        while(!looserQueue.isEmpty()){
            int looser = looserQueue.poll();
            
            if(visited[looser]){
                continue;
            }
            visited[looser] = true;
            cnt++;
            
            for(int lose:players[looser].loses){
                if(visited[lose]){
                    continue;
                }
                
                looserQueue.add(lose);
            }
        }
        
        return cnt;
    }
}