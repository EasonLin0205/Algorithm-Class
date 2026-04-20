/*
HDU1272
Problem Description
上次Gardon的迷宫城堡小希玩了很久（见Problem B），现在她也想设计一个迷宫让Gardon来走。但是她设计
迷宫的思路不一样，首先她认为所有的通道都应该是双向连通的，就是说如果有一个通道连通了房间A和B，那么既
可以通过它从房间A走到房间B，也可以通过它从房间B走到房间A，为了提高难度，小希希望任意两个房间有且仅有
一条路径可以相通（除非走了回头路）。小希现在把她的设计图给你，让你帮忙判断她的设计图是否符合她的设计思
路。比如下面的例子，前两个是符合条件的，但是最后一个却有两种方法从5到达8。

Input
输入包含多组数据，每组数据是一个以0 0结尾的整数对列表，表示了一条通道连接的两个房间的编号。房间的编号
至少为1，且不超过100000。每两组数据之间有一个空行。
整个文件以两个-1结尾。

Output
对于输入的每一组数据，输出仅包括一行。如果该迷宫符合小希的思路，那么输出"Yes"，否则输出"No"。
*/


import java.util.*;


class Main {
    public static List<Edge> edges = new ArrayList<>();
    public static Map<Integer, Integer> parents = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNextInt()) {
            edges.clear();
            parents.clear();

            int u = input.nextInt();
            int v = input.nextInt();

            while (true) {
                if (u == -1 && v == -1) return;
                if (u == 0 && v == 0) break;
                edges.add(new Edge(u, v));
                u = input.nextInt();
                v = input.nextInt();
            }

            System.out.println(hasNoCycle() && isUnicom() ? "Yes" : "No");
        }
    }

    public static void initParents() {
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;
            parents.put(u, u);
            parents.put(v, v);
        }
    }

    public static int find(int x) {
        if (x != parents.get(x)) parents.put(x, find(parents.get(x)));
        return parents.get(x);
    }

    public static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) parents.put(rootU, rootV);
    }

    public static boolean hasNoCycle() {
        if (edges.isEmpty()) return true;
        initParents();
        for (Edge e : edges) {
            if (find(e.u) == find(e.v)) return false;
            union(e.u, e.v);
        }
        return true;
    }

    public static boolean isUnicom() {
        boolean isFirst = false;
        int root = 0;
        for (int key : parents.keySet()) {
            if (!isFirst) {
                root = find(key);
                isFirst = true;
            } else if (find(key) != root) return false;
        }
        return true;
    }
}

class Edge {
    int u;
    int v;

    Edge(int u, int v) {
        if (u > v) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        this.u = u;
        this.v = v;
    }
}