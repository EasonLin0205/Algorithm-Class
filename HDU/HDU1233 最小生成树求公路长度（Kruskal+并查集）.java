/*
HDU1233
Problem Description
某省调查乡村交通状况，得到的统计表中列出了任意两村庄间的距离。省政府“畅通工程”的目标是使全省任何两个村庄
间都可以实现公路交通（但不一定有直接的公路相连，只要能间接通过公路可达即可），并要求铺设的公路总长度为最
小。请计算最小的公路总长度。

Input
测试输入包含若干测试用例。每个测试用例的第1行给出村庄数目N ( < 100 )；随后的N(N-1)/2行对应村庄间的距离，
每行给出一对正整数，分别是两个村庄的编号，以及此两村庄间的距离。为简单起见，村庄从1到N编号。
当N为0时，输入结束，该用例不被处理。

Output
对每个测试用例，在1行里输出最小的公路总长度。
*/

import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Edge> edges;
    public static int[] parents;

    public static void initParents(int villageCount) {
        parents = new int[villageCount + 1];
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

    public static void initDistance(int villageCount) {
        edges = new ArrayList<>();
        for (int i = 0; i < villageCount * (villageCount - 1) / 2; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int weight = input.nextInt();
            edges.add(new Edge(u, v, weight));
        }
    }

    public static int getShortestRoad() {
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
        while (input.hasNext()) {
            int N = input.nextInt();
            if (N == 0) {
                return;
            }
            initParents(N);
            initDistance(N);
            System.out.println(getShortestRoad());
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
