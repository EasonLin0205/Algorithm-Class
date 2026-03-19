/*
HDU2199
Now,given the equation 8*x^4 + 7*x^3 + 2*x^2 + 3*x + 6 == Y,
can you find its solution between 0 and 100;
Now please try your lucky.

The first line of the input contains an integer T(1<=T<=100) which
means the number of test cases. Then T lines follow, each line has
 a real number Y (fabs(Y) <= 1e10);

For each test case, you should just output one real number
(accurate up to 4 decimal places),which is the solution of the
equation,or “No solution!”,if there is no solution for the equation
between 0 and 100.
*/

import java.util.Scanner;

public class Main {
    public static double f(double x) {
        return 8 * Math.pow(x, 4) + 7 * Math.pow(x, 3) + 2 * Math.pow(x, 2) + 3 * x + 6;
    }

    public static double twoPointSearch(double y) {
        if (y < f(0) || y > f(100)) return -1;
        double left = 0;
        double right = 100;
        double middle;
        while (true) {
            middle = left + (right - left) / 2.0;
            if (Math.abs(f(middle) - y) <= 1e-4) {
                break;
            }
            if (f(middle) > y) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return middle;
    }

    public static void show(double res) {
        System.out.printf(res == -1 ? "No solution!\n" : "%.4f\n", res);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        while (n-- > 0) {
            double res = twoPointSearch(input.nextDouble());
            show(res);
        }
    }
}
