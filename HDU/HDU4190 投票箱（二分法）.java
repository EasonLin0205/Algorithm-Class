/*HDU4190
Today, besides SWERC'11, another important event is taking place in Spain which rivals
it in importance: General Elections. Every single resident of the country aged 18 or
over is asked to vote in order to choose representatives for the Congress of Deputies
and the Senate. You do not need to worry that all judges will suddenly run away from
their supervising duties, as voting is not compulsory.
The administration has a number of ballot boxes, those used in past elections. Unfortunately,
the person in charge of the distribution of boxes among cities was dismissed a few months
ago due to nancial restraints. As a consequence, the assignment of boxes to cities and the
lists of people that must vote in each of them is arguably not the best. Your task is to
show how efficiently this task could have been done.
The only rule in the assignment of ballot boxes to cities is that every city must be
assigned at least one box. Each person must vote in the box to which he/she has been
previously assigned. Your goal is to obtain a distribution which minimizes the maximum
number of people assigned to vote in one box.
In the first case of the sample input, two boxes go to the first city and the rest to
the second, and exactly 100,000 people are assigned to vote in each of the (huge!) boxes
in the most efficient distribution. In the second case, 1,2,2 and 1 ballot boxes are
assigned to the cities and 1,700 people from the third city will be called to vote in
each of the two boxes of their village, making these boxes the most crowded of all in
the optimal assignment.

The first line of each test case contains the integers N (1<=N<=500,000), the number
of cities, and B(N<=B<=2,000,000), the number of ballot boxes. Each of the following N
lines contains an integer ai,(1<=ai<=5,000,000), indicating the population of the ith city.
A single blank line will be included after each case. The last line of the input will
contain -1 -1 and should not be processed.

For each case, your program should output a single integer, the maximum number of people
assigned to one box in the most efficient assignment.
*/


import java.util.Scanner;

public class Main {
    public static int max(int[] arr) {
        int max = 0;
        for (int temp : arr) {
            max = Math.max(max, temp);
        }
        return max;
    }

    public static boolean canPlace(int x, int B, int[] people) { // 最大值不超过x时,检查B个投票箱够不够
        int sum = 0;
        for (int p : people) {
            sum += (int) Math.ceil((double) p / x);
            if (sum > B) return false;
        }
        return sum <= B;
    }

    public static int twoPointSearch(int B, int[] people) {
        int left = 1;
        int right = max(people);
        int middle;
        while (right != left) {
            middle = left + (right - left) / 2;
            if (canPlace(middle, B, people)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public static void show(int B, int[] people) {
        System.out.println(twoPointSearch(B, people));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int B = input.nextInt();
        while (N != -1 && B != -1) {
            int[] people = new int[N];
            input.nextLine();
            for (int i = 0; i < people.length; i++) {
                people[i] = input.nextInt();
            }
            show(B, people);
            N = input.nextInt();
            B = input.nextInt();
        }
    }
}