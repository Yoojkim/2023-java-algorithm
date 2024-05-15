import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> words = new ArrayList<>();
        
        for(int number:numbers){
            String numberStr = Integer.toString(number);
            words.add(numberStr);
        }
    
        Collections.sort(words, (s1, s2)->(s2+s1).compareTo(s1+s2));
        StringBuilder sb = new StringBuilder();
        for(String word:words){
            sb.append(word);
        }
        
        String res = sb.toString();
        if(res.charAt(0)=='0'){
            res="0";
        }
        
        return res;
    }
}