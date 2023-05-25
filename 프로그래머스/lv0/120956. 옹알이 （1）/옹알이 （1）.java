import java.util.*;

class Solution {
    

    private final List<String> possible = Arrays.asList("aya", "ye", "woo", "ma");

    public int solution(String[] babbling) {
        int cnt=0;
        for (String b:babbling){
            if (isPossible(b))
                cnt++;
        }

        return cnt;
    }

    private boolean isPossible(String babbling) {

        int i = 0; //babbling index
        boolean keepGoing = true;
        while (keepGoing) {

            boolean keepCompare=false;
            for (String p : possible) {

                //exception 선처리
                if (babbling.length() - i < p.length())
                    continue;

                boolean isCorrect = true;
                for (int pIdx = 0; pIdx < p.length(); pIdx++) {
                    if (babbling.charAt(i + pIdx) != p.charAt(pIdx)) {
                        isCorrect = false;
                    }
                }

                if (isCorrect) {
                    i += p.length();
                    keepCompare=true;
                }
            }

            keepGoing=keepCompare;
        }

        return i==babbling.length();
    }
}