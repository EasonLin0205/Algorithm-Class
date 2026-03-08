import java.util.Arrays;

/*
    P46 1.37
    考虑元素唯一性问题：给出一个整数集合，假定这些整数存储在数组A[1...n]中，
    确定其中书否存在两个相等的元素。请设计出一个有效算法来解决这个问题，你的算法的时间复杂性是多少？

    本解答运用排序+线性遍历
    时间复杂性O(nlogn)  空间复杂性O(1)
*/

class Solution {
    public static boolean hasRepeatElements(int[] nums) {
        Arrays.sort(nums); // 使用库函数的快速排序先将nums升序排列
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}