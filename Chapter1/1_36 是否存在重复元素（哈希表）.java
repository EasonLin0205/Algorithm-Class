import java.util.HashSet;
import java.util.Set;

/*
    P46 1.37
    考虑元素唯一性问题：给出一个整数集合，假定这些整数存储在数组A[1...n]中，
    确定其中书否存在两个相等的元素。请设计出一个有效算法来解决这个问题，你的算法的时间复杂性是多少？

    本解答运用哈希表，创建一个哈希集合来存放已经出现过的元素。
    时间复杂性O(n)  空间复杂性O(n)
*/

class Solution {
    public static boolean hasRepeatElements(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}