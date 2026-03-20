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

public class BisectionSecant_Method {
    public static double f(double x, double y) {
        return 8 * Math.pow(x, 4) + 7 * Math.pow(x, 3) + 2 * Math.pow(x, 2) + 3 * x + 6 - y;
    }

    public static double secant(double x, double xx, double y) {
        double xxx;
        while (true) {
            xxx = xx - f(xx, y) * (xx - x) / (f(xx, y) - f(x, y));
            if (Math.abs(f(xxx, y)) < 1e-6 || xxx == xx) break;
            x = xx;
            xx = xxx;
        }
        return xxx;
    }

    public static double search(double y) {
        double left = 0;
        double right = 100;
        double middle;
        while (!(right - left <= 0.1)) {
            middle = (right + left) / 2.0;
            if (f(middle, y) < 0) {
                left = middle;
            } else if (f(middle, y) > 0) {
                right = middle;
            } else {
                return middle;
            }
        }
        return secant(left, right, y);
    }

    public static void show(int y) {
        if (f(100, y) < 0 || f(0, y) > 0) {
            System.out.println("No solution!");
        } else {
            System.out.printf("%.4f\n", search(y));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        while (n-- > 0) {
            show(input.nextInt());
        }
    }
}