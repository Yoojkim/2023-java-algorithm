import java.util.*;

class Node{
    int a;
    int b;
    int idx;
    
    public Node(int a, int b, int idx){
        this.a=a;
        this.b=b;
        this.idx = idx;
    }
}

class Solution {
    public int solution(int[][] scores) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
            (a, b) -> {
                if(a.a > b.a){
                    return -1;
                } else if (a.a == b.a){
                    return Integer.compare(a.b, b.b);
                } else { //a.a < b.a
                    return 1;
                }
            }
        );
        
        for(int i=0;i<scores.length;i++){
            int[] score = scores[i];
            queue.add(new Node(score[0], score[1], i));
        }
        
        //정제
        List<Node> nodes = new ArrayList<>();
        int maxB = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            if(maxB > node.b){
                continue;
            } else {
                maxB = node.b;
            }
            
            nodes.add(node);
        }
        
        Collections.sort(nodes, (a, b)->{
           return Integer.compare(b.a+b.b, a.a+a.b);
        });
        
        //Ranking
        int maxSum = Integer.MAX_VALUE;
        int rank = 1;
        int tempCnt = 0;
        
        int result = -1;
        for(Node node:nodes){
            if(maxSum > node.a+ node.b){
                rank += tempCnt;
                tempCnt = 1;
                maxSum = node.a+node.b;
            } else {
                tempCnt ++;
            }
            
            if(node.idx == 0){
                result = rank;
            }
        }
        
        return result;
    }
}