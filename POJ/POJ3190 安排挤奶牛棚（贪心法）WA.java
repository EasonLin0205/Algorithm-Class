/*
POJ3190
Description
Oh those picky N (1 <= N <= 50,000) cows! They are so picky that each one will only be
milked over some precise time interval A..B (1 <= A <= B <= 1,000,000), which includes both
times A and B. Obviously, FJ must create a reservation system to determine which stall each
cow can be assigned for her milking time. Of course, no cow will share such a private moment
with other cows.
Help FJ by determining:
The minimum number of stalls required in the barn so that each cow can have her private
milking period
An assignment of cows to these stalls over time
Many answers are correct for each test dataset; a program will grade your answer.

Input
Line 1: A single integer, N
Lines 2..N+1: Line i+1 describes cow i's milking interval with two space-separated integers.

Output
Line 1: The minimum number of stalls the barn must have.
Lines 2..N+1: Line i+1 describes the stall to which cow i will be assigned for her milking
period.
*/

import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int N = input.nextInt();
        Cow[] cows = Cow.getCows(N, input);
        Cow.getMinStalls(cows);
        Cow.printArrangeList();
    }
}

class Cow implements Comparable<Cow> {
    int index;
    int start;
    int end;

    Cow() {
    }

    Cow(int index, int start, int end) {
        this.index = index;
        this.start = start;
        this.end = end;
    }

    static List<LinkedList<Cow>> arrange = new ArrayList<>();
    static int[] path;

    static Cow[] getCows(int N, Scanner input) {
        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            int A = input.nextInt();
            int B = input.nextInt();
            Cow temp = new Cow(i, A, B);
            cows[i] = temp;
        }
        return cows;
    }

    static void getMinStalls(Cow[] cows) {
        Arrays.sort(cows);

        path = new int[cows.length];

        for (Cow cow : cows) {
            if (arrange.isEmpty()) {
                LinkedList<Cow> temp = new LinkedList<>();
                temp.add(cow);
                arrange.add(temp);
                path[cow.index] = 1;
                continue;
            }

            int pos = -1;
            for (int i = 0; i < arrange.size(); i++) {
                int lastEnd = arrange.get(i).getLast().end;

                if (pos == -1 && lastEnd <= cow.start) pos = i;
                else if (pos != -1) {
                    int bestEnd = arrange.get(pos).getLast().end;
                    if (lastEnd <= bestEnd) pos = i;
                }
            }

            if (pos != -1) {
                path[cow.index] = pos + 1;
                arrange.get(pos).addLast(cow);
            } else {
                LinkedList<Cow> temp = new LinkedList<>();
                temp.add(cow);
                arrange.add(temp);
                path[cow.index] = arrange.size();
            }
        }
    }

    static void printArrangeList() {
        System.out.println(arrange.size());
        for (int i : path) System.out.println(i);
    }

    @Override
    public int compareTo(Cow o) {
        return Integer.compare(this.start, o.start);
    }
}