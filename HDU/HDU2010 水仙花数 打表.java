/*HDU2010
春天是鲜花的季节，水仙花就是其中最迷人的代表，数学上有个水仙花数，他是这样定义的：
“水仙花数”是指一个三位数，它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。
现在要求输出所有在m和n范围内的水仙花数。

输入数据有多组，每组占一行，包括两个整数m和n（100<=m<=n<=999）。

对于每个测试实例，要求输出所有在给定范围内的水仙花数，就是说，输出的水仙花数必须大于等于m,
并且小于等于n，如果有多个，则要求从小到大排列在一行内输出，之间用一个空格隔开;
如果给定的范围内不存在水仙花数，则输出no;
每个测试实例的输出占一行。
*/

import java.util.Scanner;

class Main {
    public static int[] a = new int[4];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int m = input.nextInt();
            int n = input.nextInt();
            int index = fillList(m, n);
            show(index);
        }
    }

    public static int fillList(int left, int right) {
        int[] daffodilsNum = new int[]{153, 370, 371, 407};
        int count = 0;
        for (int num : daffodilsNum) {
            if (left <= num && num <= right) {
                a[count++] = num;
            }
        }
        return count;
    }

    public static void show(int count) {
        if (count == 0) System.out.println("no");
        else {
            for (int i = 0; i < count; i++) {
                System.out.print(a[i]);
                if (i + 1 < count) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}