/*
HDU1102
Problem Description
There are N villages, which are numbered from 1 to N, and you should build some roads such that every two villages
can connect to each other. We say two village A and B are connected, if and only if there is a road between A and B,
or there exists a village C such that there is a road between A and C, and C and B are connected.
We know that there are already some roads between some villages and your job is the build some roads such that all
the villages are connect and the length of all the roads built is minimum.

Input
The first line is an integer N (3 <= N <= 100), which is the number of villages. Then come N lines, the i-th of
which contains N integers, and the j-th of these N integers is the distance (the distance should be an integer
within [1, 1000]) between village i and village j.
Then there is an integer Q (0 <= Q <= N * (N + 1) / 2). Then come Q lines, each line contains two integers a and
b (1 <= a < b <= N), which means the road between village a and village b has been built.

Output
You should output a line contains an integer, which is the length of all the roads to be built such that all the
villages are connected, and this value is minimum.
*/
import java.util.*;


class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Edge> edges;
    public static int[] parents;

    public static void initParents(int villageCount){
        parents = new int[villageCount + 1];
        for(int i = 1;i<parents.length;i++) parents[i] = i;
    }

    public static int find(int x){
        if(parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    public static void union(int u,int v){
        int rootU = find(u);
        int rootV = find(v);
        if(rootU != rootV) parents[rootU] = parents[rootV];
    }

    public static void initDistance(int villageCount){
        edges = new ArrayList<>();
        for(int i = 1;i<=villageCount;i++){
            for(int j = 1;j<=villageCount;j++){
                int weight = input.nextInt();
                if(j > i){
                    edges.add(new Edge(i,j,weight));
                }
            }
        }
    }

    public static void updateParents(int unionCount){
        while(unionCount-- > 0){
            int u = input.nextInt();
            int v = input.nextInt();
            union(u,v);
        }
    }

    public static int getShortestRoad(){
        int sum = 0;
        Set<Integer> alreadyUnion = new HashSet<>();
        for(int i = 1;i< parents.length;i++) find(i);
        for(int i = 1;i<parents.length;i++) alreadyUnion.add(find(i));
        int need = alreadyUnion.size() - 1;
        Edge[] tmp = edges.toArray(new Edge[0]);
        Arrays.sort(tmp);
        for(Edge edge : tmp){
            if(find(edge.u) != find(edge.v)){
                union(edge.u,edge.v);
                sum += edge.weight;
                need--;
            }
            if(need <= 0) break;
        }
        return sum;
    }

    public static void main(String[] args) {
        while(input.hasNext()){
            int N = input.nextInt();
            initParents(N);
            initDistance(N);
            int Q = input.nextInt();
            updateParents(Q);
            System.out.println(getShortestRoad());
        }
    }
}

class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;

    Edge(){}
    Edge(int u,int v,int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight,o.weight);
    }
}
