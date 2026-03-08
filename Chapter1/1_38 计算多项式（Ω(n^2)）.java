/*
    P46 1.38
    请设计一个算法，对于给定x的值，在时间
    (a) Ω(n^2)  (b) O(n)
    求输入x的多项式的值

    本解答使用逐项计算
    时间复杂性Ω(n^2)  空间复杂性O(1)
*/

class Solution {
    public static int calPolynomials(int[] a, int x) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            int cur = 1;
            for (int j = 0; j < i; j++) {
                cur *= x;
            }
            res += cur * a[i];
        }
        return res;
    }
}