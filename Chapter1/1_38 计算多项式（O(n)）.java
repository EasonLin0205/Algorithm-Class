/*
    P46 1.38
    请设计一个算法，对于给定x的值，在时间
    (a) Ω(n^2)  (b) O(n)
    求输入x的多项式的值

    本解答使用迭代法
    时间复杂性O(n)  空间复杂性O(1)
*/

class Solution {
    public static int calPolynomials(int[] a, int x) {
        int res = a[a.length - 1];
        for (int i = a.length - 1; i > 0; i--) {
            res = res * x + a[i - 1];
        }
        return res;
    }
}