/*
HDU1789
Problem Description
Ignatius has just come back school from the 30th ACM/ICPC. Now he has a lot of homework to do.
Every teacher gives him a deadline of handing in the homework. If Ignatius hands in the homework
after the deadline, the teacher will reduce his score of the final test. And now we assume that
doing everyone homework always takes one day. So Ignatius wants you to help him to arrange the
order of doing homework to minimize the reduced score.

Input
The input contains several test cases. The first line of the input is a single integer T that
is the number of test cases. T test cases follow.
Each test case start with a positive integer N(1<=N<=1000) which indicate the number of
homework.. Then 2 lines follow. The first line contains N integers that indicate the deadlines
of the subjects, and the next line contains N integers that indicate the reduced scores.

Output
For each test case, you should output the smallest total reduced score, one line per test case.
*/

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static Scanner input = new Scanner(System.in);

    public static boolean arrange(boolean[] arrangement, int deadLine) {
        for (int i = deadLine - 1; i >= 0; i--) {
            if (!arrangement[i]) {
                arrangement[i] = true;
                return true;
            }
        }
        return false;
    }

    public static int getMinDeduction(Homework[] homeworks) {
        boolean[] arrangement = new boolean[Homework.maxDeadline(homeworks)];
        Arrays.sort(homeworks);
        int cnt = 0;
        for (Homework homework : homeworks) {
            if (cnt == arrangement.length) break;
            if (arrange(arrangement, homework.deadline)) {
                cnt++;
                homework.score = 0;
            }
        }
        return Homework.sumScore(homeworks);
    }

    public static void show(Homework[] homeworks) {
        System.out.println(getMinDeduction(homeworks));
    }

    public static void main(String[] args) {
        int T = input.nextInt();
        while (T-- > 0) {
            int N = input.nextInt();
            Homework[] homeworks = Homework.getHomeworks(N, input);
            show(homeworks);
        }
    }
}

class Homework implements Comparable<Homework> {
    int deadline;
    int score;

    Homework() {
        this.deadline = 0;
        this.score = 0;
    }

    static int maxDeadline(Homework[] homeworks) {
        int max = homeworks[0].deadline;
        for (Homework h : homeworks) max = Math.max(max, h.deadline);
        return max;
    }

    static int sumScore(Homework[] homeworks) {
        int sum = 0;
        for (Homework h : homeworks) sum += h.score;
        return sum;
    }

    static Homework[] getHomeworks(int len, Scanner input) {
        Homework[] homeworks = new Homework[len];
        for (int i = 0; i < len; i++) {
            homeworks[i] = new Homework();
            homeworks[i].deadline = input.nextInt();
        }
        for (int i = 0; i < len; i++) {
            homeworks[i].score = input.nextInt();
        }
        return homeworks;
    }

    @Override
    public int compareTo(Homework h) {
        return -Integer.compare(this.score, h.score);
    }
}