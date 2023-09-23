import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        Set<String> uniqueGems = new HashSet<>(Arrays.asList(gems));
        int totalUniqueGems = uniqueGems.size();

        Map<String, Integer> gemCount = new HashMap<>();
        int left = 0, right = 0;
        int[] ansRange = { 1, gems.length };
        int minLength = Integer.MAX_VALUE;

        while (right < gems.length) {
            String currentGem = gems[right];

            gemCount.put(currentGem, gemCount.getOrDefault(currentGem, 0) + 1);

            while (gemCount.size() == totalUniqueGems) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    ansRange[0] = left + 1;
                    ansRange[1] = right + 1;
                }

                String leftGem = gems[left];
                gemCount.put(leftGem, gemCount.get(leftGem) - 1);

                if (gemCount.get(leftGem) == 0) {
                    gemCount.remove(leftGem);
                }

                left++;
            }

            right++;
        }

        return ansRange;
    }
}
