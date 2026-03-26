/*
POJ3258
Description
Every year the cows hold an event featuring a peculiar version of hopscotch that involves
carefully jumping from rock to rock in a river. The excitement takes place on a long,
straight river with a rock at the start and another rock at the end, L units away from
the start (1 ≤ L ≤ 1,000,000,000). Along the river between the starting and ending rocks,
N (0 ≤ N ≤ 50,000) more rocks appear, each at an integral distance Di from the
start (0 < Di < L).
To play the game, each cow in turn starts at the starting rock and tries to reach the finish
at the ending rock, jumping only from rock to rock. Of course, less agile cows never make it
to the final rock, ending up instead in the river.
Farmer John is proud of his cows and watches this event each year. But as time goes by, he
tires of watching the timid cows of the other farmers limp across the short distances between
rocks placed too closely together. He plans to remove several rocks in order to increase
the shortest distance a cow will have to jump to reach the end. He knows he cannot remove
the starting and ending rocks, but he calculates that he has enough resources to remove up
to M rocks (0 ≤ M ≤ N).
FJ wants to know exactly how much he can increase the shortest distance *before* he starts
removing the rocks. Help Farmer John determine the greatest possible shortest distance a
cow has to jump after removing the optimal set of M rocks.

Input
Line 1: Three space-separated integers: L, N, and M
Lines 2..N+1: Each line contains a single integer indicating how far some rock is away from
the starting rock. No two rocks share the same position.

Output
Line 1: A single integer that is the maximum of the shortest distance a cow has to jump
after removing M rocks1
*/

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static boolean canRemove(int L, int[] stonesPos, int M, int interval) {
        int lastPos = 0;
        for (int s : stonesPos) {
            if (s - lastPos >= interval) lastPos = s;
            else M--;
            if (M < 0) return false;
        }
        if (L - lastPos < interval) M--;
        return M >= 0;
    }

    public static int search(int L, int[] stonesPos, int M) {
        int left = 1;
        int right = L;
        int ans = 1;
        int middle;
        while (left <= right) {
            middle = (left + right) >> 1;
            if (canRemove(L, stonesPos, M, middle)) {
                ans = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return ans;
    }

    public static void show(int L, int[] stonesPos, int M) {
        System.out.println(search(L, stonesPos, M));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int L = input.nextInt();
        int[] stonesPos = new int[input.nextInt()];
        int M = input.nextInt();
        for (int i = 0; i < stonesPos.length; i++) {
            stonesPos[i] = input.nextInt();
        }
        Arrays.sort(stonesPos);
        show(L, stonesPos, M);
    }
}