/*
HDU1257
Problem Description
某国为了防御敌国的导弹袭击,发展出一种导弹拦截系统.但是这种导弹拦截系统有一个缺陷:虽然它的第一发炮弹能够到达任意的高度,但是以后每一发炮弹都
不能超过前一发的高度.某天,雷达捕捉到敌国的导弹来袭.由于该系统还在试用阶段,所以只有一套系统,因此有可能不能拦截所有的导弹.
怎么办呢?多搞几套系统呗!你说说倒蛮容易,成本呢?成本是个大问题啊.所以俺就到这里来求救了,请帮助计算一下最少需要多少套拦截系统.

Input
输入若干组数据.每组数据包括:导弹总个数(正整数),导弹依此飞来的高度(雷达给出的高度数据是不大于30000的正整数,用空格分隔)

Output
对应每组数据输出拦截所有导弹最少要配备多少套这种导弹拦截系统.
*/

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static Scanner input = new Scanner(System.in);

    public static boolean canIntercept(int[] missile, int cnt) {
        int[] minHeight = new int[cnt];
        Arrays.fill(minHeight, Integer.MAX_VALUE);

        for (int m : missile) {
            int insertPos = -1;

            for (int i = 0; i < cnt; i++) {
                if (minHeight[i] >= m) {
                    if (insertPos == -1 || minHeight[i] < minHeight[insertPos]) {
                        insertPos = i;
                    }
                }
            }
            if (insertPos == -1) return false;
            minHeight[insertPos] = m;
        }

        return true;
    }

    public static int search(int[] missile) {
        int left = 1;
        int right = missile.length;
        int ans = right;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (canIntercept(missile, middle)) {
                ans = middle;
                right = middle - 1;
            } else left = middle + 1;
        }
        return ans;
    }

    public static int[] getArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = input.nextInt();
        }
        return arr;
    }

    public static void show(int[] missile) {
        System.out.println(search(missile));
    }

    public static void main(String[] args) {
        while (input.hasNext()) {
            int[] missile = getArray(input.nextInt());
            show(missile);
        }
    }
}
