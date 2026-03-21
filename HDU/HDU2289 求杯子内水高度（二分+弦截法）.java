/*HDU2289
The WHU ACM Team has a big cup, with which every member drinks water.
Now, we know the volume of the water in the cup, can you tell us it height?
The radius of the cup's top and bottom circle is known, the cup's height is also known.

The input consists of several test cases. The first line of input contains an integer T,
indicating the num of test cases.
Each test case is on a single line, and it consists of four floating point numbers: r, R, H, V,
representing the bottom radius, the top radius, the height and the volume of the hot water.
Technical Specification
1. T ≤ 20.
2. 1 ≤ r, R, H ≤ 100; 0 ≤ V ≤ 1000,000,000.
3. r ≤ R.
4. r, R, H, V are separated by ONE whitespace.
5. There is NO empty line between two neighboring cases.

For each test case, output the height of hot water on a single line. Please round it to six
fractional digits.
*/


import java.util.Scanner;

public class Main {
    public static double f(double x, double r, double R, double H, double V) {
        double curR = (x / H) * (R - r) + r;
        return (Math.PI * x * (r * r + r * curR + curR * curR) / 3.0) - V;
    }

    public static double secant(double x, double xx, double r, double R, double H, double V) {
        double xxx;
        while (true) {
            xxx = xx - f(xx, r, R, H, V) * (xx - x) / (f(xx, r, R, H, V) - f(x, r, R, H, V));
            if (Math.abs(f(xxx, r, R, H, V)) < 1e-6 || xxx == xx) break;
            x = xx;
            xx = xxx;
        }
        return xxx;
    }

    public static double search(double r, double R, double H, double V) {
        if (V <= 0) return 0;
        double maxVolume = Math.PI * (R * R + r * r + r * R) * H / 3.0;
        if (Math.abs(V - maxVolume) < 1e-12 || V >= maxVolume) return H;
        double left = 0;
        double right = H;
        double middle;
        int twoMinuteReps = 30;
        while (twoMinuteReps-- > 0) {
            middle = (right + left) / 2.0;
            if (f(middle, r, R, H, V) < 0) {
                left = middle;
            } else if (f(middle, r, R, H, V) > 0) {
                right = middle;
            } else {
                return middle;
            }
        }
        return secant(left, right, r, R, H, V);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (T-- > 0) {
            double r = input.nextDouble();
            double R = input.nextDouble();
            double H = input.nextDouble();
            double V = input.nextDouble();
            System.out.printf("%.6f\n", search(r, R, H, V));
        }
    }
}