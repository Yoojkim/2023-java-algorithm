코드 조잡해서 chat gpt한테 코드리뷰 맡겨봄 <p>
공감되는 부분이 많아서 추후 수정할 예정..

+항상 IDE 쓰다보니까 내장 함수 자체를 잘 모르는 듯함 (replace 많이 쓰던데 쓸 생각도 없었다.. 해당 부분도 검색해서 알아보기)

```
import java.util.*;

class Solution {
    private final Set<String> possible = new HashSet<>(Arrays.asList("aya", "ye", "woo", "ma"));

    public int solution(String[] babbling) {
        int count = 0;
        for (String babble : babbling) {
            if (isPossible(babble)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPossible(String babbling) {
        int index = 0;
        while (index < babbling.length()) {
            boolean foundMatch = false;
            for (String p : possible) {
                if (babbling.startsWith(p, index)) {
                    index += p.length();
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;
    }
}
```
