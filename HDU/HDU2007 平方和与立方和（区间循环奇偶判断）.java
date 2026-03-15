/*
HDU2007
给定一段连续的整数，求出他们中所有偶数的平方和以及所有奇数的立方和。

输入数据包含多组测试实例，每组测试实例包含一行，由两个整数m和n组成。

对于每组输入数据，输出一行，应包括两个整数x和y，分别表示该段连续的整数中
所有偶数的平方和以及所有奇数的立方和。
你可以认为32位整数足以保存结果。
*/


import java.util.Scanner;

class Main {
    public static int oddSum = 0;
    public static int evenSum = 0;

    public static void oddPlus(int odd) {
        oddSum += odd * odd * odd;
    }

    public static void evenPlus(int even) {
        evenSum += even * even;
    }

    public static void updateSum(int m, int n) {
        while (m <= n) {
            if (m % 2 == 0) {
                evenPlus(m++);
            } else {
                oddPlus(m++);
            }
        }
    }

    public static void show(int evenSum, int oddSum) {
        System.out.println(evenSum + " " + oddSum);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m, n;
        while (input.hasNext()) {
            evenSum = 0;
            oddSum = 0;
            m = input.nextInt();
            n = input.nextInt();
            if (m > n) {
                m ^= n;
                n ^= m;
                m ^= n;
            }
            updateSum(m, n);
            show(evenSum, oddSum);
        }
    }
}