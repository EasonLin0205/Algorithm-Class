/*
HDU2000
输入三个字符后，按各字符的ASCII码从小到大的顺序输出这三个字符。

输入数据有多组，每组占一行，有三个字符组成，之间无空格。

对于每组输入数据，输出一行，字符中间用一个空格分开。
*/

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            char[] chs = input.nextLine().toCharArray();
            sortThreeElem(chs);
            System.out.println(chs[0] + " " + chs[1] + " " + chs[2]);
        }
    }

    public static void swap(char[] chs, int i, int j) {
        if (i < 0 || i >= chs.length || j < 0 || j >= chs.length) return;
        chs[i] ^= chs[j];
        chs[j] ^= chs[i];
        chs[i] ^= chs[j];
    }

    public static void sortThreeElem(char[] chs) {
        if (chs[0] > chs[1]) swap(chs, 0, 1);
        if (chs[0] > chs[2]) swap(chs, 0, 2);
        if (chs[1] > chs[2]) swap(chs, 1, 2);
    }
}
