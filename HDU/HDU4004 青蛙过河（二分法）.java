/*
HDU4004
Problem Description
The annual Games in frogs' kingdom started again. The most famous game is the Ironfrog
Triathlon. One test in the Ironfrog Triathlon is jumping. This project requires the frog
athletes to jump over the river. The width of the river is L (1<= L <= 1000000000). There
are n (0<= n <= 500000) stones lined up in a straight line from one side to the other side
of the river. The frogs can only jump through the river, but they can land on the stones.
If they fall into the river, they
are out. The frogs was asked to jump at most m (1<= m <= n+1) times. Now the frogs want to
know if they want to jump across the river, at least what ability should they have. (That is
the frog's longest jump distance).

Input
The input contains several cases. The first line of each case contains three positive integer
L, n, and m.
Then n lines follow. Each stands for the distance from the starting banks to the nth stone,
two stone appear in one place is impossible.

Output
For each case, output a integer standing for the frog's ability at least they should have.
*/

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static boolean canJump(int L, int[] stonesPos, int m, int interval) {
        int jumpLen = 0;
        for (int i = -1, j = 0; j <= stonesPos.length; i++, j++) {
            int curLen = (i != -1 && j != stonesPos.length) ? (stonesPos[j] - stonesPos[i]) : i == -1 ? stonesPos[0] : L - stonesPos[stonesPos.length - 1];
            if (curLen > interval) return false;
            if (jumpLen + curLen > interval) {
                m--;
                jumpLen = 0;
            }
            jumpLen += curLen;
            if (m < 0) return false;
        }
        return m - 1 >= 0;
    }

    public static int search(int L, int[] stonesPos, int m) {
        int left = 1;
        int right = L;
        int ans = 1;
        int middle;
        while (left <= right) {
            middle = (left + right) >> 1;
            if (canJump(L, stonesPos, m, middle)) {
                ans = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return ans;
    }

    public static void show(int L, int[] stonesPos, int m) {
        System.out.println(search(L, stonesPos, m));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int L = input.nextInt();
            int[] stonesPos = new int[input.nextInt()];
            int m = input.nextInt();
            for (int i = 0; i < stonesPos.length; i++) {
                stonesPos[i] = input.nextInt();
            }
            Arrays.sort(stonesPos);
            show(L, stonesPos, m);
        }
    }
}