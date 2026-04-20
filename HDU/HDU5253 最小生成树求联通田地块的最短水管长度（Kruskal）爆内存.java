/*
HDU5253
Problem Description
老 Jack 有一片农田，以往几年都是靠天吃饭的。但是今年老天格外的不开眼，大旱。所以老 Jack 决定用管道将他的所有相邻的农田全部都串联起来，这样
他就可以从远处引水过来进行灌溉了。当老 Jack 买完所有铺设在每块农田内部的管道的时候，老 Jack 遇到了新的难题，因为每一块农田的地势高度都不同，
所以要想将两块农田的管道链接，老 Jack 就需要额外再购进跟这两块农田高度差相等长度的管道。
现在给出老 Jack农田的数据，你需要告诉老 Jack 在保证所有农田全部可连通灌溉的情况下，最少还需要再购进多长的管道。另外，每块农田都是方形等大的，
一块农田只能跟它上下左右四块相邻的农田相连通。

Input
第一行输入一个数字T(T≤10)，代表输入的样例组数
输入包含若干组测试数据，处理到文件结束。每组测试数据占若干行，第一行两个正整数 N,M(1≤N,M≤1000)，代表老 Jack 有N行*M列个农田。接下来 N 行，
每行 M 个数字，代表每块农田的高度，农田的高度不会超过100。数字之间用空格分隔。

Output
对于每组测试数据输出两行：
第一行输出："Case #i:"。i代表第i组测试数据。
第二行输出 1 个正整数，代表老 Jack 额外最少购进管道的长度。
*/

import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Edge> edges;
    public static int[] parents;

    public static void initParents(int fieldCount) {
        parents = new int[fieldCount + 1];
        for (int i = 1; i < parents.length; i++) parents[i] = i;
    }

    public static int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    public static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) parents[rootU] = rootV;
    }

    public static void initDistance(int M, int N) {
        int[][] field = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                field[i][j] = input.nextInt();
            }
        }
        edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int weight;
                if (i + 1 <= N) {
                    weight = Math.abs(field[i][j] - field[i + 1][j]);
                    edges.add(new Edge((i - 1) * M + j, i * M + j, weight));
                }
                if (j + 1 <= M) {
                    weight = Math.abs(field[i][j] - field[i][j + 1]);
                    edges.add(new Edge((i - 1) * M + j, (i - 1) * M + j + 1, weight));
                }
            }
        }
    }

    public static int getShortestTube() {
        int sum = 0;
        int need = parents.length - 1 - 1;
        Edge[] tmp = edges.toArray(new Edge[0]);
        Arrays.sort(tmp);
        for (Edge edge : tmp) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                sum += edge.weight;
                need--;
            }
            if (need <= 0) break;
        }
        return sum;
    }

    public static void main(String[] args) {
        int T = input.nextInt();
        int index = 1;
        while (T-- > 0) {
            int N = input.nextInt();
            int M = input.nextInt();
            initParents(N * M);
            initDistance(M, N);
            System.out.println("Case #" + index++ + ":");
            System.out.println(getShortestTube());
        }
    }
}

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int weight;

    Edge() {
    }

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
