package Lab10;

import java.util.*;

public class Lab10 {
    private Map<Integer, List<Edge>> adjList;

    public Lab10() {
        adjList = new HashMap<>();
    }

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        addVertex(from);
        addVertex(to);
        adjList.get(from).add(new Edge(to, weight));
    }

    public void dijkstra(int source) {
        final int INF = Integer.MAX_VALUE;
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjList.keySet()) {
            distance.put(vertex, INF);
        }
        distance.put(source, 0);
        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int vertex = current.to;
            if (visited.contains(vertex)) continue;
            visited.add(vertex);

            for (Edge edge : adjList.get(vertex)) {
                if (distance.get(vertex) + edge.weight < distance.getOrDefault(edge.to, INF)) {
                    distance.put(edge.to, distance.get(vertex) + edge.weight);
                    pq.add(new Edge(edge.to, distance.get(edge.to)));
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
            System.out.println("Відстань від " + source + " до " + entry.getKey() + " становить " + (entry.getValue() == INF ? "INF" : entry.getValue()));
        }
    }

    public void floydWarshall() {
        final int V = adjList.size();
        int[][] dist = new int[V][V];
        final int INF = Integer.MAX_VALUE;

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < V; i++) {
            dist[i][i] = 0;
        }
        for (int vertex : adjList.keySet()) {
            for (Edge edge : adjList.get(vertex)) {
                dist[vertex][edge.to] = edge.weight;
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void lab_demonstration() {
        Lab10 graph = new Lab10();

        graph.addEdge(0,2, 7);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 0, 5);
        graph.addEdge(1,2,3);
        graph.addEdge(1, 4, 2);
        graph.addEdge(1,5, 4);
        graph.addEdge(1, 6, 4);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 6,1);
        graph.addEdge(3, 7, 8);
        graph.addEdge(4, 5, 3);
        graph.addEdge(4, 8, 4);
        graph.addEdge(4, 9, 10);
        graph.addEdge(5, 6,5);
        graph.addEdge(5, 8, 1);
        graph.addEdge(6, 7, 10);
        graph.addEdge(8, 7,5);
        graph.addEdge(9,8, 9);
        graph.addEdge(9,11,4);
        graph.addEdge(10,9, 4);
        graph.addEdge(10,11, 5);
        graph.addEdge(11, 7, 5);

        System.out.println("Алгоритм Дейкстри для вершини 0:");
        graph.dijkstra(0);

        System.out.println("\nАлгоритм Флойда-Уоршала:");
        graph.floydWarshall();
    }
}
