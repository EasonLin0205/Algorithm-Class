/*
POJ2456
Description
Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls.
The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).
His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each
other once put into a stall. To prevent the cows from hurting each other, FJ want to assign
the cows to the stalls, such that the minimum distance between any two of them is as large
as possible. What is the largest minimum distance?

Input
* Line 1: Two space-separated integers: N and C
* Lines 2..N+1: Line i+1 contains an integer stall location, xi

Output
* Line 1: One integer: the largest minimum distance
*/


import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static boolean canPlace(int C, int[] stallsPos, int interval) {
        C--;
        int curInterval = 0;
        for (int i = 0, j = 1; j < stallsPos.length; i++, j++) {
            curInterval += stallsPos[j] - stallsPos[i];
            if (curInterval >= interval) {
                C--;
                curInterval = 0;
            }
            if (C <= 0) return true;
        }
        return C <= 0;
    }


    public static int getPos(int C, int[] stallsPos) {
        int left = 1;
        int right = stallsPos[stallsPos.length - 1] - stallsPos[0];
        int middle;
        int ans = 1;
        while (right >= left) {
            middle = (left + right) >> 1;
            if (canPlace(C, stallsPos, middle)) {
                ans = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return ans;
    }

    public static void show(int C, int[] stallsPos) {
        System.out.println(getPos(C, stallsPos));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] stallsPos = new int[input.nextInt()];
        int C = input.nextInt();
        for (int i = 0; i < stallsPos.length; i++) {
            stallsPos[i] = input.nextInt();
        }
        Arrays.sort(stallsPos);
        show(C, stallsPos);
    }
}