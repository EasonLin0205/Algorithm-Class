/*
HDU2008
统计给定的n个数中，负数、零和正数的个数。

输入数据有多组，每组占一行，每行的第一个数是整数n（n<100），表示需要统计的数值的个数，
然后是n个实数；如果n=0，则表示输入结束，该行不做处理。

对于每组输入数据，输出一行a,b和c，分别表示给定的数据中负数、零和正数的个数。
*/

import java.util.Scanner;

class Main {
    public static int negative = 0;
    public static int zero = 0;
    public static int positive = 0;

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        while (n != 0) {
            negative = 0;
            zero = 0;
            positive = 0;
            while (n-- > 0) {
                updateCount(input.nextDouble());
            }
            System.out.println(negative + " " + zero + " " + positive);
            n = input.nextInt();
        }
    }

    public static void updateCount(double num) {
        if (num == 0) zero++;
        else if (num < 0) negative++;
        else positive++;
    }
}
