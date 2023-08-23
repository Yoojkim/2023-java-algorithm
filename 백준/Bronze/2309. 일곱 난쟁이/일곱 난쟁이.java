import java.util.*;
import java.io.*;

public class Main{
    static int[] heights=new int[9];
    static boolean[] visited=new boolean[9];
    static boolean success=false;
    
    public static void cal(int idx, int cnt, int weight){
        if(cnt==7){
            if(weight!=100) return;
            
            //오름차순으로 출력 
            LinkedList<Integer> result=new LinkedList();
            for(int i=0;i<9;i++){
                if(!visited[i]) continue;
                
                int height=heights[i];
                
                //result에 넣기 
                int j=0;
                for(;j<result.size();j++){
                    if(result.get(j)>height)
                        break;
                }
                result.add(j, height);
            }
            
            for(int r:result){
                System.out.println(r);
            }
            
            success=true;
            return;
        }
        
        for(int i=idx; i<9;i++){
            visited[i]=true;
            
            cal(i+1, cnt+1, weight+heights[i]);
            if(success)
                return;
            
            visited[i]=false;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=0;i<9;i++){
            heights[i]=Integer.parseInt(br.readLine());
        }
        
        cal(0, 0, 0);
    }
}