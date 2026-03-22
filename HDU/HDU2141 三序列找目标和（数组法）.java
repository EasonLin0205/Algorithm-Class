/*
HDU2141
Give you three sequences of numbers A, B, C, then we give you a number X.
Now you need to calculate if you can find the three numbers Ai, Bj, Ck,
which satisfy the formula Ai+Bj+Ck = X.

There are many cases. Every data case is described as followed:
In the first line there are three integers L, N, M, in the second line
there are L integers represent the sequence A, in the third line there
are N integers represent the sequences B, in the forth line there are M integers
represent the sequence C. In the fifth line there is an integer S represents
there are S integers X to be calculated. 1<=L, N, M<=500, 1<=S<=1000. all the integers
are 32-integers.

For each case, firstly you have to print the case number as the form "Case d:",
then for the S queries, you calculate if the formula can be satisfied or not.
If satisfied, you print "YES", otherwise print "NO".
*/


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static long[] A;
    public static long[] B;
    public static long[] C;
    public static long[] set;
    static Scanner input = new Scanner(System.in);

    public static void fillList() {
        int L = input.nextInt();
        int N = input.nextInt();
        int M = input.nextInt();
        A = new long[L];
        B = new long[N];
        C = new long[M];
        for (int i = 0; i < A.length; i++) {
            A[i] = input.nextLong();
        }
        for (int j = 0; j < B.length; j++) {
            B[j] = input.nextLong();
        }
        for (int k = 0; k < C.length; k++) {
            C[k] = input.nextLong();
        }
    }

    public static void fillSet() {
        set = new long[A.length * B.length];
        int index = 0;
        for (long a : A) {
            for (long b : B) {
                set[index++] = a + b;
            }
        }
        Arrays.sort(set);
    }

    public static boolean twoPointSearch(long target) {
        int left = 0;
        int right = set.length - 1;
        int middle;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (set[middle] > target) {
                right = middle - 1;
            } else if (set[middle] < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTargetSum(long target) {
        for (long k : C) {
            if (twoPointSearch(target - k)) return true;
        }
        return false;
    }

    public static void show() {
        System.out.println(hasTargetSum(input.nextInt()) ? "YES" : "NO");
    }

    public static void main(String[] args) {
        int index = 1;
        while (input.hasNext()) {
            fillList();
            fillSet();
            int n = input.nextInt();
            System.out.println("Case " + index++ + ":");
            while (n-- > 0) {
                show();
            }
        }
    }
}
