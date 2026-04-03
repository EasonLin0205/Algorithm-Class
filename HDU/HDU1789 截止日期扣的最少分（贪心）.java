/*
HDU1789
Problem Description
Ignatius has just come back school from the 30th ACM/ICPC. Now he has a lot of homework to do.
Every teacher gives him a deadline of handing in the homework. If Ignatius hands in the homework
after the deadline, the teacher will reduce his score of the final test. And now we assume that
doing everyone homework always takes one day. So Ignatius wants you to help him to arrange the
order of doing homework to minimize the reduced score.

Input
The input contains several test cases. The first line of the input is a single integer T that
is the number of test cases. T test cases follow.
Each test case start with a positive integer N(1<=N<=1000) which indicate the number of
homework.. Then 2 lines follow. The first line contains N integers that indicate the deadlines
of the subjects, and the next line contains N integers that indicate the reduced scores.

Output
For each test case, you should output the smallest total reduced score, one line per test case.
*/

import java.util.Scanner;

class Main {
    public static Scanner input = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void selectSort(int[] deadLine, int[] score) {
        for (int i = 0; i < deadLine.length; i++) {
            int maxPos = i;
            for (int j = i; j < deadLine.length; j++) {
                if (score[j] >= score[maxPos]) maxPos = j;
            }
            swap(score, i, maxPos);
            swap(deadLine, i, maxPos);
        }
    }

    public static boolean arrange(boolean[] arrangement, int deadLine) {
        for (int i = deadLine - 1; i >= 0; i--) {
            if (!arrangement[i]) {
                arrangement[i] = true;
                return true;
            }
        }
        return false;
    }

    public static int getMinDeduction(int[] deadLine, int[] score) {
        boolean[] arrangement = new boolean[max(deadLine)];
        selectSort(deadLine, score);
        int cnt = 0;
        for (int i = 0; i < deadLine.length; i++) {
            if (cnt == arrangement.length) break;
            if (arrange(arrangement, deadLine[i])) {
                cnt++;
                score[i] = 0;
            }
        }
        return sum(score);
    }

    public static int[] getArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = input.nextInt();
        }
        return arr;
    }

    public static int max(int[] arr) {
        int ans = arr[0];
        for (int i : arr) ans = Math.max(ans, i);
        return ans;
    }

    public static int sum(int[] arr) {
        int ans = 0;
        for (int i : arr) ans += i;
        return ans;
    }

    public static void show(int[] deadLine, int[] score) {
        System.out.println(getMinDeduction(deadLine, score));
    }

    public static void main(String[] args) {
        int T = input.nextInt();
        while (T-- > 0) {
            int N = input.nextInt();
            int[] deadLine = getArray(N);
            int[] score = getArray(N);
            show(deadLine, score);
        }
    }
}
