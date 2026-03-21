/*
HDU1969
My birthday is coming up and traditionally I'm serving pie. Not just one pie, no,
I have a number N of them, of various tastes and of various sizes. F of my friends
are coming to my party and each of them gets a piece of pie. This should be one piece
of one pie, not several small pieces since that looks messy. This piece can be one whole
 pie though.
My friends are very annoying and if one of them gets a bigger piece than the others, they
start complaining. Therefore all of them should get equally sized (but not necessarily
equally shaped) pieces, even if this leads to some pie getting spoiled (which is better
than spoiling the party). Of course, I want a piece of pie for myself too, and that piece
should also be of the same size.
What is the largest possible piece size all of us can get? All the pies are cylindrical in
shape and they all have the same height 1, but the radii of the pies can be different.

One line with a positive integer: the number of test cases. Then for each test case:
---One line with two integers N and F with 1 <= N, F <= 10 000: the number of pies and
the number of friends.
---One line with N integers ri with 1 <= ri <= 10 000: the radii of the pies.

For each test case, output one line with the largest possible volume V such that me and
my friends can all get a pie piece of size V. The answer should be given as a floating
point number with an absolute error of at most 10^(-3).
*/

import java.util.Scanner;

public class Main {
    public static boolean canCut(double x, int F, double[] r) {
        int partCount = 0;
        for (double radius : r) {
            partCount += (int) (radius * radius / x);
            if (partCount >= F) return true;
        }
        return partCount >= F;
    }

    public static double search(int F, double[] r) {
        double left = 0;
        double right = 0;
        for (double radius : r) {
            right += radius * radius;
        }
        double middle;
        while (right - left >= 1e-6) {
            middle = (right + left) / 2.0;
            if (canCut(middle, F, r)) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return (left + right) / 2.0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (T-- > 0) {
            double[] r = new double[input.nextInt()];
            int F = input.nextInt() + 1;
            for (int i = 0; i < r.length; i++) {
                r[i] = input.nextDouble();
            }
            System.out.printf("%.4f\n", search(F, r) * Math.PI);
        }
    }
}