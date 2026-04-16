/*
HDU6011
Problem Description
Lotus has n kinds of characters,each kind of characters has a value and a amount.She wants to construct a string using some
of these characters.Define the value of a string is:its first character's value*1+its second character's value *2+...She
wants to calculate the maximum value of string she can construct.
Since it's valid to construct an empty string,the answer is always ≥0。

Input
First line is T(0≤T≤1000) denoting the number of test cases.
For each test case,first line is an integer n(1≤n≤26),followed by n lines each containing 2 integers vali,cnti
(|vali|,cnti≤100),denoting the value and the amount of the ith character.

Output
For each test case.output one line containing a single integer,denoting the answer.
*/

import java.util.*;

class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int t = input.nextInt();
        while (t-- > 0) {
            System.out.println(Char.getMaxValue(Char.getValueArray(Char.getChar(input))));
        }
    }
}

class Char implements Comparable<Char> {
    int value;
    int amount;

    Char() {
    }

    Char(int value, int amount) {
        this.value = value;
        this.amount = amount;
    }

    static Char[] getChar(Scanner input) {
        int n = input.nextInt();
        Char[] temp = new Char[n];
        for (int i = 0; i < n; i++) {
            int value = input.nextInt();
            int amount = input.nextInt();
            temp[i] = new Char(value, amount);
        }
        return temp;
    }

    static int[] getValueArray(Char[] chars) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(chars);
        for (Char c : chars) {
            for (int i = 0; i < c.amount; i++) {
                list.add(c.value);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    static int getMaxValue(int[] valueArr) {
        int sum = 0;
        int preSum = 0;
        for (int i = 0; i < valueArr.length; i++) {
            if (sum + preSum + valueArr[i] >= sum) {
                sum += preSum + valueArr[i];
                preSum += valueArr[i];
            }
        }
        return sum;
    }

    @Override
    public int compareTo(Char o) {
        return -Integer.compare(this.value, o.value);
    }
}