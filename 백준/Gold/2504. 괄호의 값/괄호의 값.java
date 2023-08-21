import java.util.*;
import java.io.*;

public class Main{
    
    public static int calculate(String[] inputs){
        int tempVal=1; int result=0;
        LinkedList<String> stack=new LinkedList();
        
        for(int i=0;i<inputs.length;i++){
            String input=inputs[i];
            
            if (input.equals("(")){
                stack.add("(");
                tempVal*=2;
            } else if(input.equals("[")){
                stack.add("[");
                tempVal*=3;
            } else if(input.equals(")")){
                if(stack.isEmpty()||!stack.peekLast().equals("("))
                    return 0;
                
                if(i-1>=0 && inputs[i-1].equals("("))
                    result+=tempVal;
                tempVal/=2;
                stack.pollLast();
            } else if(input.equals("]")){
                if(stack.isEmpty()||!stack.peekLast().equals("["))
                    return 0;
                
                if(i-1>=0 && inputs[i-1].equals("["))
                    result+=tempVal;
                tempVal/=3;
                stack.pollLast();
                
            } else {
                return 0;
            }
        }
        
        if(stack.size()!=0)
            return 0;
        
        return result;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs=br.readLine().split("");
        System.out.println(calculate(inputs));
    }
}