/*
HDU1338
Problem Description
Suppose there are M people, including you, playing a special card game. At the beginning,
each player receives N cards. The pip of a card is a positive integer which is at most N*M.
And there are no two cards with the same pip. During a round, each player chooses one card to
compare with others. The player whose card with the biggest pip wins the round, and then the
next round begins. After N rounds, when all the cards of each player have been chosen, the
player who has won the most rounds is the winner of the game.
Given your cards received at the beginning, write a program to tell the maximal number of
rounds that you may at least win during the whole game.

Input
The input consists of several test cases. The first line of each case contains two integers
m (2 <= m <= 20) and n (1 <= n <= 50), representing the number of players and the number of
cards each player receives at the beginning of the game, respectively. This followed by a line
with n positive integers, representing the pips of cards you received at the beginning. Then
a blank line follows to separate the cases.
The input is terminated by a line with two zeros.

Output
For each test case, output a line consisting of the test case number followed by the number of
rounds you will at least win during the game.
*/

import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);

    public static int[] myCards;
    public static int[] otherCards;

    public static void getMyCards(int n) {
        myCards = new int[n];
        for (int i = 0; i < myCards.length; i++) {
            myCards[i] = input.nextInt();
        }
        input.nextLine();
        Arrays.sort(myCards);
    }

    public static void getOtherCards(int m, int n) {
        boolean[] my = new boolean[m * n];
        for (int i : myCards) {
            my[i - 1] = true;
        }
        otherCards = new int[(m - 1) * n];
        int index = 0;
        for (int i = 0; i < m * n; i++) {
            if (!my[i]) otherCards[index++] = i + 1;
        }
        Arrays.sort(otherCards);
    }

    public static int getWinCnt() {
        int i = 0; // 别人的牌
        int j = 0; // 我的牌
        int cnt = 0;
        while (j < myCards.length) {
            if (i >= otherCards.length) {
                cnt++;
                j++;
                continue;
            }
            if (otherCards[i] < myCards[j]) {
                i++;
                continue;
            }
            if (otherCards[i] > myCards[j]) {
                i++;
                j++;
                continue;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int index = 1;
        while (input.hasNext()) {
            int m = input.nextInt();
            int n = input.nextInt();
            if (m == 0 && n == 0) return;
            getMyCards(n);
            getOtherCards(m, n);
            System.out.println("Case " + index++ + ": " + getWinCnt());
        }
    }
}