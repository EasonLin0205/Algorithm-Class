/*
HDU1009
Problem Description
FatMouse prepared M pounds of cat food, ready to trade with the cats guarding the warehouse containing his favorite
food, JavaBean.
The warehouse has N rooms. The i-th room contains J[i] pounds of JavaBeans and requires F[i] pounds of cat food.
FatMouse does not have to trade for all the JavaBeans in the room, instead, he may get J[i]* a% pounds of JavaBeans
if he pays F[i]* a% pounds of cat food. Here a is a real number. Now he is assigning this homework to you: tell him
the maximum amount of JavaBeans he can obtain.

Input
The input consists of multiple test cases. Each test case begins with a line containing two non-negative integers M and
N. Then N lines follow, each contains two non-negative integers J[i] and F[i] respectively. The last test case is
followed by two -1's. All integers are not greater than 1000.

Output
For each test case, print in a single line a real number accurate up to 3 decimal places, which is the maximum amount
of JavaBeans that FatMouse can obtain.
*/

import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (input.hasNext()) {
            int M = input.nextInt();
            int N = input.nextInt();
            if (M == -1 && N == -1) return;
            Warehouse[] warehouses = new Warehouse[N];
            for (int i = 0; i < N; i++) {
                int stock = input.nextInt();
                int price = input.nextInt();
                warehouses[i] = new Warehouse(stock, price);
            }
            System.out.printf("%.3f\n", Warehouse.getMaxJavaBeans(warehouses, M));
        }
    }
}

class Warehouse implements Comparable<Warehouse> {
    int stock; // 仓库里的库存
    int price; // 全部拿走需要花费的猫粮
    double unitPrice; // 一单位猫粮能换多少单位库存JavaBeans

    Warehouse() {
    }

    Warehouse(int stock, int price) {
        this.stock = stock;
        this.price = price;
        this.unitPrice = (double) stock / (double) price;
    }

    static double getMaxJavaBeans(Warehouse[] warehouses, int catFood) {
        double sum = 0;
        Arrays.sort(warehouses);
        for (Warehouse w : warehouses) {
            if (catFood > 0) {
                if (catFood >= w.price) {
                    sum += w.stock;
                    catFood -= w.price;
                } else {
                    sum += catFood * w.unitPrice;
                    catFood = 0;
                }
            } else break;
        }
        return sum;
    }

    @Override
    public int compareTo(Warehouse o) {
        return -Double.compare(this.unitPrice, o.unitPrice);
    }
}