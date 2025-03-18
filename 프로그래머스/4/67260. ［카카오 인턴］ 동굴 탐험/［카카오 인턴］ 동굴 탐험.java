import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean[] visited = new boolean[n];
        List<Integer>[] conns = new List[n];
        int[] orders = new int[n];
        int[] levels = new int[n];

        for(int i=0;i<n;i++){
            conns[i] = new ArrayList<>();
        }

        for (int[] p : path) {
            int start = p[0];
            int end = p[1];

            conns[start].add(end);
            conns[end].add(start);
        }

        for (int[] o : order) {
            int prev = o[0];
            int next = o[1];

            orders[prev] = next;
            levels[next] = 1;
        }

        //0과 연결된 방 방문 설정
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        
        Set<Integer> set = new HashSet<>();

        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            if(levels[now] > 0){
                continue;
            }

            //order 값 있으면 level 감소
            int next = orders[now];
            levels[next]--;
            
            if(!visited[next] && levels[next] == 0 && set.contains(next)){
                queue.add(next);
                visited[next] = true;
            }

            //연결된 값들도 큐에 넣어주기
            for (int end : conns[now]) {
                if (visited[end]) {
                    continue;
                }

                if(levels[end] > 0){
                    set.add(end);
                    continue;
                }
                
                queue.add(end);
                visited[end] = true;
            }
        }


        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                return false;
            }
        }

        return true;
    }
}