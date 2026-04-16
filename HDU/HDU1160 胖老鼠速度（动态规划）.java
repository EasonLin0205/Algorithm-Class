/*
HDU1160
Problem Description
FatMouse believes that the fatter a mouse is, the faster it runs.
To disprove this, you want to take the data on a collection of mice
and put as large a subset of this data as possible into a sequence so that
the weights are increasing, but the speeds are decreasing.

Input
Input contains data for a bunch of mice, one mouse per line, terminated by end of
file.
The data for a particular mouse will consist of a pair of integers: the first
representing its size in grams and the second representing its speed in centimeters
per second. Both integers are between 1 and 10000. The data in each test case will
contain information for at most 1000 mice.
Two mice may have the same weight, the same speed, or even the same weight and
speed.

Output
Your program should output a sequence of lines of data; the first line should
contain a number n; the remaining n lines should each contain a single positive
integer (each one representing a mouse). If these n integers are m[1], m[2],..., m[n]
then it must be the case that W[m[1]] < W[m[2]] < ... < W[m[n]] and
S[m[1]] > S[m[2]] > ... > S[m[n]] In order for the answer to be correct, n should be as
large as possible.
All inequalities are strict: weights must be strictly increasing, and speeds must be strictly
decreasing. There may be many correct outputs for a given input, your program only needs
to find one.
*/


import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);

    public static Mouse[] getMouseArray() {
        List<Mouse> list = new ArrayList<>();
        while (input.hasNextInt()) {
            Mouse temp = new Mouse();
            temp.weight = input.nextInt();
            temp.speed = input.nextInt();
            list.add(temp);
        }
        return list.toArray(new Mouse[0]);
    }

    public static void show(Deque<Mouse> miceLink) {
        System.out.println(miceLink.size());
        while (!miceLink.isEmpty()) System.out.println(miceLink.pop().index);
    }

    public static void main(String[] args) {
        Mouse[] mice = getMouseArray();
        Deque<Mouse> res = Mouse.getMouseLDSLink(mice);
        show(res);
    }
}

class Mouse implements Comparable<Mouse> {
    static int MouseCount = 0;

    int index;
    int weight;
    int speed;

    Mouse() {
        Mouse.MouseCount++;
        this.index = Mouse.MouseCount;
        this.weight = 0;
        this.speed = 0;
    }

    static Deque<Mouse> getMouseLDSLink(Mouse[] mice) {
        Arrays.sort(mice);
        int[] dp = new int[mice.length];
        int[] prev = new int[mice.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (mice[j].speed > mice[i].speed) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }
        return getMouseLink(dp, prev, mice);
    }

    static Deque<Mouse> getMouseLink(int[] dp, int[] prev, Mouse[] mice) {
        Deque<Mouse> res = new ArrayDeque<>();
        int startPos = 0;
        for (int i = 0; i < dp.length; i++) if (dp[i] > dp[startPos]) startPos = i;
        while (startPos != -1) {
            res.push(mice[startPos]);
            startPos = prev[startPos];
        }
        return res;
    }

    @Override
    public int compareTo(Mouse o) {
        return this.weight != o.weight ? Integer.compare(this.weight, o.weight) : -Integer.compare(this.speed, o.speed);
    }
}