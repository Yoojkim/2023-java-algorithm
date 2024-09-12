import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        //최소 진법 검사
        int min = 0;
        for(String e:expressions){
            String[] eSplit = e.split(" ");
            
            for(char c:eSplit[0].toCharArray()){
                if(c-'0'>min){
                    min =c-'0'; 
                }
            }
            for(char c:eSplit[2].toCharArray()){
                if(c-'0'>min){
                    min =c-'0'; 
                }
            }
            
            if(eSplit[4].equals("X")){
                continue;
            }
            
            for(char c:eSplit[4].toCharArray()){
                if(c-'0'>min){
                    min =c-'0'; 
                }
            }
            
        }
        min++;
        
        //가능한 진법 범위 줄이기
        List<String> removeds = new ArrayList<>();
        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);
        
        for(String e:expressions){
            String[] eSplit = e.split(" ");

            int a = Integer.parseInt(eSplit[0]);
            char mid = eSplit[1].charAt(0);
            int b = Integer.parseInt(eSplit[2]);
            
            if(eSplit[4].equals("X")){
                removeds.add(e);
                
                continue;
            }
            int res = Integer.parseInt(eSplit[4]);
            
            for(int i=min;i<=9;i++){
                //진법 가능한지 계산 
                if(!possible[i]){
                    continue;
                }
                
                if(!isPossible(a, mid, b, res, i)){
                    possible[i] = false;
                    continue;
                }
            }
        }
        
        //지워진 문장 결과값 계산
        List<String> results = new ArrayList<>();
        for(String removed:removeds){
            int ans = 10000;
            String[] removedStr = removed.split(" ");
            int a = Integer.parseInt(removedStr[0]);
            char mid = removedStr[1].charAt(0);
            int b = Integer.parseInt(removedStr[2]);
            
            for(int i=min;i<=9;i++){
                if(!possible[i]){
                    continue;
                }
                
                int result = getResult(a, mid, b, i);
                if(ans == 10000){
                    ans = result;
                    continue;
                }
                
                if(ans == result){
                    continue;
                }
                
                ans = 10000;
                break;
            }
            
            if(ans==10000){
                results.add(getWrongStr(a, mid, b));
            } else {
                results.add(getResStr(a, mid, b, ans));
            }
        }
        
        String[] resultArr = new String[results.size()];
        for(int i=0;i<results.size();i++){
            resultArr[i] = results.get(i);
        }
        
        return resultArr;
    }
    
    boolean isPossible(int a, char mid, int b, int res, int n){
        a = nToTen(a, n);
        b = nToTen(b, n);
        res = nToTen(res, n);
        if(mid == '+'){
            return a+b == res;
        }
        
        return a-b == res;
    }
    
    private String getResStr(int a, char mid, int b, int res){
        return String.format("%d %c %d = %d",a, mid, b, res);
    }
    
    private String getWrongStr(int a, char mid, int b){
        return String.format("%d %c %d = ?",a, mid, b);
    }
    
    private int getResult(int a, char mid, int b, int n){
        if(mid=='+'){
            return tenToN(nToTen(a, n)+nToTen(b, n), n);
        }
        
        return tenToN(nToTen(a,n)-nToTen(b,n), n);
    }
    
    private int nToTen(int num, int n){
        int result = 0;
        
        int temp = 1;
        while(num!=0){
            result += num%10*temp;
            
            temp*=n;
            num/=10;
        }
        
        return result;
    }
    
    private int tenToN(int num, int n){
        int result = 0;
        
        int temp = 1;
        while(num!=0){
            result += (num%n)*temp;
            
            temp*=10;
            num/=n;
        }
        
        return result;
    }
}