package Lab11;

import java.util.*;

public class Lab11 {

    static class Graph {
        private static final int INF = Integer.MAX_VALUE;
        private final int numVertices;
        private final int[][] adjacencyMatrix;

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            this.adjacencyMatrix = new int[numVertices][numVertices];
            for (int[] row : adjacencyMatrix) {
                Arrays.fill(row, INF);
            }
            for (int i = 0; i < numVertices; i++) {
                adjacencyMatrix[i][i] = 0;
            }
        }

        public void addEdge(int src, int dest, int weight) {
            adjacencyMatrix[src][dest] = weight;
            adjacencyMatrix[dest][src] = weight;
        }

        public List<Edge> getEdges() {
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                for (int j = i + 1; j < numVertices; j++) {
                    if (adjacencyMatrix[i][j] != INF) {
                        edges.add(new Edge(i, j, adjacencyMatrix[i][j]));
                    }
                }
            }
            return edges;
        }


        public void kruskalMST() {
            List<Edge> edges = getEdges();
            edges.sort(Comparator.comparingInt(Edge::getWeight));

            Subset[] subsets = new Subset[numVertices];
            for (int v = 0; v < numVertices; v++) {
                subsets[v] = new Subset(v, 0);
            }

            List<Edge> mst = new ArrayList<>();
            int e = 0;
            int i = 0;
            while (e < numVertices - 1 && i < edges.size()) {
                Edge next = edges.get(i++);
                int x = find(subsets, next.getSrc());
                int y = find(subsets, next.getDest());

                if (x != y) {
                    mst.add(next);
                    union(subsets, x, y);
                    e++;
                }
            }

            mst.forEach(edge ->
                    System.out.println(edge.getSrc() + " -- " + edge.getDest() + " == " + edge.getWeight())
            );
        }


        public void primMST() {
            int[] parent = new int[numVertices];
            int[] key = new int[numVertices];
            boolean[] inMST = new boolean[numVertices];

            Arrays.fill(key, INF);
            key[0] = 0;
            parent[0] = -1;

            for (int count = 0; count < numVertices - 1; count++) {
                int u = minKey(key, inMST);
                inMST[u] = true;

                for (int v = 0; v < numVertices; v++) {
                    int weight = adjacencyMatrix[u][v];
                    if (weight != INF && !inMST[v] && weight < key[v]) {
                        parent[v] = u;
                        key[v] = weight;
                    }
                }
            }

            printMST(parent);
        }

        private int find(Subset[] subsets, int i) {
            if (subsets[i].parent != i) {
                subsets[i].parent = find(subsets, subsets[i].parent);
            }
            return subsets[i].parent;
        }

        private void union(Subset[] subsets, int x, int y) {
            int xRoot = find(subsets, x);
            int yRoot = find(subsets, y);

            if (subsets[xRoot].rank < subsets[yRoot].rank) {
                subsets[xRoot].parent = yRoot;
            } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
                subsets[yRoot].parent = xRoot;
            } else {
                subsets[yRoot].parent = xRoot;
                subsets[xRoot].rank++;
            }
        }

        private int minKey(int[] key, boolean[] inMST) {
            int min = INF, minIndex = -1;
            for (int v = 0; v < numVertices; v++) {
                if (!inMST[v] && key[v] < min) {
                    min = key[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        private void printMST(int[] parent) {
            System.out.println("Edge \tWeight");
            for (int i = 1; i < numVertices; i++) {
                System.out.println(parent[i] + " - " + i + " \t" + adjacencyMatrix[i][parent[i]]);
            }
        }

        private static class Edge {
            private final int src;
            private final int dest;
            private final int weight;

            Edge(int src, int dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }

            public int getSrc() {
                return src;
            }

            public int getDest() {
                return dest;
            }

            public int getWeight() {
                return weight;
            }
        }

        private static class Subset {
            int parent;
            int rank;

            Subset(int parent, int rank) {
                this.parent = parent;
                this.rank = rank;
            }
        }
    }


    public static void lab_demonstration() {
        Graph graph = new Graph(9);

        graph.addEdge(0,1,2);
        graph.addEdge(0,2,3);
        graph.addEdge(0,4,5);
        graph.addEdge(1,6,4);
        graph.addEdge(2,3,4);
        graph.addEdge(2,4,7);
        graph.addEdge(2,6,2);
        graph.addEdge(3,4,4);
        graph.addEdge(3,5,3);
        graph.addEdge(3,6,1);
        graph.addEdge(3,7,1);
        graph.addEdge(3,8,7);
        graph.addEdge(4,5,6);
        graph.addEdge(6,7,6);
        graph.addEdge(7,8,4);

        System.out.println("--- Kruskal's MST ---");
        graph.kruskalMST();
        System.out.println();

        System.out.println("--- Prim's MST ---");
        graph.primMST();
    }
}
