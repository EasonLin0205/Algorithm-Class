/*
POJ2976
Description
In a certain course, you take n tests. If you get ai out of bi questions correct on test i,
your cumulative average is defined to be
Given your test scores and a positive integer k, determine how high you can make your cumulative
average if you are allowed to drop any k of your test scores.
Suppose you take 3 tests with scores of 5/5, 0/1, and 2/6. Without dropping any tests, your
cumulative average is . However, if you drop the third test, your cumulative average becomes .

Input
The input test file will contain multiple test cases, each containing exactly three lines.
The first line contains two integers, 1 ≤ n ≤ 1000 and 0 ≤ k < n. The second line contains n
integers indicating ai for all i. The third line contains n positive integers indicating bi
for all i. It is guaranteed that 0 ≤ ai ≤ bi ≤ 1, 000, 000, 000. The end-of-file is marked by
a test case with n = k = 0 and should not be processed.

Output
For each test case, write a single line with the highest cumulative average possible after
dropping k of the given test scores. The average should be rounded to the nearest integer.
*/

import java.util.Scanner;

class Main {
    static Scanner input = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] correct, int[] total, double avg) {
        double[] value = new double[correct.length];
        for (int i = 0; i < value.length; i++) {
            value[i] = correct[i] - total[i] * avg;
            // value[i]是当前项的平均分权重
            // 正值说明会提高平均分，负值说明拉低平均分
        }
        for (int i = 0; i < value.length; i++) {
            double min = value[i];
            int minPos = i;
            for (int j = i; j < value.length; j++) {
                if (value[j] <= min) {
                    min = value[j];
                    minPos = j;
                }
            }
            swap(value, i, minPos);
            swap(correct, i, minPos);
            swap(total, i, minPos);
        }
    }

    public static boolean canGet(int[] correct, int[] total, int k, double x) {
        sort(correct, total, x);
        double sum = 0;
        for (int i = k; i < correct.length; i++) {
            sum += correct[i] - x * total[i];
        }
        return sum >= 0;
    }

    public static double search(int[] correct, int[] total, int k) {
        double left = 0;
        double right = 1;
        while (right - left >= 1e-6) {
            double middle = (left + right) / 2;
            if (canGet(correct, total, k, middle)) left = middle;
            else right = middle;
        }
        return (left + right) / 2;
    }

    public static int[] getArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = input.nextInt();
        }
        return arr;
    }

    public static void show(int[] correct, int[] total, int k) {
        System.out.println(Math.round(100 * search(correct, total, k)));
    }

    public static void main(String[] args) {
        int n = input.nextInt();
        int k = input.nextInt();
        while (n != 0) {
            int[] correct = getArray(n);
            int[] total = getArray(n);
            show(correct, total, k);
            n = input.nextInt();
            k = input.nextInt();
        }
    }
}