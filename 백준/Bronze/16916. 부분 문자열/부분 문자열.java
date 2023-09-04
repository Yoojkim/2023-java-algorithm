import java.util.*;
import java.io.*;

public class Main{
    static public void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] original=br.readLine().toCharArray();
        char[] pattern=br.readLine().toCharArray();
        
        int[] table=lstTable(pattern);
        
        //본격적 계산
        int j=0;
        for(int i=0;i<original.length;i++){
            while(j>0 && original[i]!=pattern[j]){
                j=table[j-1];
            }
            
            if(original[i]==pattern[j]){
                if(j==pattern.length-1){
                    System.out.println(1);
                    return;
                }
                
                j++;
            }
        }
        
        System.out.println(0);
    }
    
    static int[] lstTable(char[] pattern){
        int pSize=pattern.length;
        
        int[] table=new int[pSize];
        int j=0;
        for(int i=1;i<pSize;i++){
            while(j>0 && pattern[i]!=pattern[j]){
                j=table[j-1];
            }
            
            if(pattern[j]==pattern[i])
                table[i]=++j;
        }
        
        return table;
    }
}