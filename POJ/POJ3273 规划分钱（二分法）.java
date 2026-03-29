/*
POJ3273
Description
Farmer John is an astounding accounting wizard and has realized he might run out of money
to run the farm. He has already calculated and recorded the exact amount of money
(1 ≤ moneyi ≤ 10,000) that he will need to spend each day over the next
N (1 ≤ N ≤ 100,000) days.
FJ wants to create a budget for a sequential set of exactly M (1 ≤ M ≤ N) fiscal periods
called "fajomonths". Each of these fajomonths contains a set of 1 or more consecutive days.
Every day is contained in exactly one fajomonth.
FJ's goal is to arrange the fajomonths so as to minimize the expenses of the fajomonth with
the highest spending and thus determine his monthly spending limit.

Input
Line 1: Two space-separated integers: N and M
Lines 2..N+1: Line i+1 contains the number of dollars Farmer John spends on the ith day

Output
Line 1: The smallest possible monthly limit Farmer John can afford to live with.
*/

import java.util.Scanner;

class Main {
    public static boolean canSplit(int[] money,int M,int edge) {
        int curSum = 0;
        for(int m: money){
            if(m > edge) return false;
            if(curSum + m > edge){
                M--;
                curSum = 0;
            }
            curSum += m;
            if(M < 0) return false;
        }
        return M - 1 >= 0;
    }

    public static int sum(int[] money){
        int sum = 0;
        for(int m : money) sum += m;
        return sum;
    }

    public static int search(int[] money,int M) {
        int left = money[0];
        int right = sum(money);
        int middle ;
        int ans = left;
        while(left <= right){
            middle = (left + right) >> 1;
            if(canSplit(money,M,middle)){
                ans = middle;
                right = middle - 1;
            }else left = middle + 1;
        }
        return ans;
    }

    public static void show(int[] money,int M) {
        System.out.println(search(money,M));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] money = new int[input.nextInt()];
        int M = input.nextInt();
        for (int i = 0; i < money.length; i++) {
            money[i] = input.nextInt();
        }
        show(money,M);
    }
}