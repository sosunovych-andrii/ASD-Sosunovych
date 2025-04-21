package Lab9;

import java.util.ArrayList;
import java.util.Arrays;

public class Lab9 {

    public static class Node {
        char data;

        Node(char data) {
            this.data = data;
        }
    }

    public static class Graph {
        ArrayList<Node> nodes;
        int[][] matrix;
        final int INF = Integer.MAX_VALUE;

        Graph(int size) {
            nodes = new ArrayList<>();
            matrix = new int[size][size];
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public void addEdge(int src, int dst) {
            matrix[src][dst] = 1;
            matrix[dst][src] = 1;
        }

        public void print() {
            System.out.println("Граф:");
            System.out.print("  ");
            for (Node node: nodes) {
                System.out.print(node.data + " ");
            }
            System.out.println();

            for (int i = 0; i < nodes.size(); i++) {
                System.out.print(nodes.get(i).data + " ");
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }


        public void dijkstra(int start) {
            int size = nodes.size();
            int[] distance = new int[size];
            boolean[] visited = new boolean[size];

            Arrays.fill(distance, INF);
            distance[start] = 0;

            for (int i = 0; i < size; i++) {
                int minDist = INF;
                int minIndex = -1;

                // знаходимо невідвідану вершину з мінімальною відстанню
                for (int j = 0; j < size; j++) {
                    if (!visited[j] && distance[j] < minDist) {
                        minDist = distance[j];
                        minIndex = j;
                    }
                }

                if (minIndex == -1)
                    break; // усі досяжні вершини вже відвідані

                visited[minIndex] = true;

                // оновлюємо відстані до сусідів
                for (int j = 0; j < size; j++) {
                    if (matrix[minIndex][j] != 0 && !visited[j]) {
                        int newDist = distance[minIndex] + matrix[minIndex][j];
                        if (newDist < distance[j]) {
                            distance[j] = newDist;
                        }
                    }
                }
            }

            System.out.println("\nНайкоротші відстані від вершини " + nodes.get(start).data + ": (алгоритм Дейкстри)");
            for (int i = 0; i < size; i++) {
                System.out.println(nodes.get(start).data + " -> " + nodes.get(i).data + ": " +
                        (distance[i] == INF ? "∞" : distance[i]));
            }
        }


        public void floyd() {
            int size = nodes.size();
            int[][] dist = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else if (matrix[i][j] != 0) {
                        dist[i][j] = matrix[i][j];
                    } else {
                        dist[i][j] = INF;
                    }
                }
            }

            for (int k = 0; k < size; k++) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        // перевірка, чи існує шлях від i до k і від k до j,
                        // і чи шлях через k коротший за поточний шлях від i до j
                        if (dist[i][k] != INF && dist[k][j] != INF &&
                                dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            System.out.println("\nМатриця найкоротших відстаней (алгоритм Флойда):");
            System.out.print("  ");
            for (Node node : nodes) {
                System.out.print(node.data + " ");
            }
            System.out.println();

            for (int i = 0; i < size; i++) {
                System.out.print(nodes.get(i).data + " ");
                for (int j = 0; j < size; j++) {
                    if (dist[i][j] == INF) {
                        System.out.print("∞ ");
                    } else {
                        System.out.print(dist[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }


    public static void lab_demonstration() {
        Graph graph = new Graph(6);
        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addNode(new Node('D'));
        graph.addNode(new Node('E'));
        graph.addNode(new Node('F'));

        graph.addEdge(0, 1); // A - B
        graph.addEdge(0, 3); // A - D
        graph.addEdge(0, 4); // A - E
        graph.addEdge(0, 5); // A - F
        graph.addEdge(1, 2); // B - C
        graph.addEdge(1, 3); // B - D
        graph.addEdge(1, 4); // B - E
        graph.addEdge(1, 5); // B - F
        graph.addEdge(2, 3); // C - D
        graph.addEdge(2, 4); // C - E
        graph.addEdge(2, 5); // C - F
        graph.addEdge(3, 4); // D - E
        graph.addEdge(4, 5); // E - F

        graph.print();
        graph.dijkstra(0);
        graph.floyd();
    }
}
