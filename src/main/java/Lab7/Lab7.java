package Lab7;

public class Lab7 {

    private static class Vertex {
        double value;
        Vertex left;
        Vertex right;

        public Vertex(double value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static class BST {
        Vertex root;

        public BST() {
            root = null;
        }

        public void insert(double value) {
            root = insertRecursive(root, value);
        }
        private Vertex insertRecursive(Vertex vertex, double value) {
            if (vertex == null) {
                vertex = new Vertex(value);
                return vertex;
            }

            if (value < vertex.value) {
                vertex.left = insertRecursive(vertex.left, value);
            } else if (value > vertex.value) {
                vertex.right = insertRecursive(vertex.right, value);
            }
            return vertex;
        }

        public void inorderTraversalInt() {
            inorderRecursiveInt(root);
        }
        private void inorderRecursiveInt(Vertex vertex) {
            if (vertex == null) {
                return;
            }
            inorderRecursiveInt(vertex.left);
            System.out.print((int)vertex.value + " ");
            inorderRecursiveInt(vertex.right);
        }

        public void inorderTraversalDouble() {
            inorderRecursiveDouble(root);
        }
        private void inorderRecursiveDouble(Vertex vertex) {
            if (vertex == null) {
                return;
            }
            inorderRecursiveDouble(vertex.left);
            System.out.print(vertex.value + " ");
            inorderRecursiveDouble(vertex.right);
        }

        public Vertex search(double value) {
            return searchRecursive(root, value);
        }
        private Vertex searchRecursive(Vertex vertex, double value) {
            if (vertex == null || vertex.value == value) {
                return vertex;
            }
            if (value < vertex.value) {
                return searchRecursive(vertex.left, value);
            }
            return searchRecursive(vertex.right, value);
        }

        public void delete(double value) {
            root = deleteRecursive(root, value);
        }
        private Vertex deleteRecursive(Vertex vertex, double value) {
            if (vertex == null) {
                return vertex;
            }

            if (value < vertex.value) {
                vertex.left = deleteRecursive(vertex.left, value);
            } else if (value > vertex.value) {
                vertex.right = deleteRecursive(vertex.right, value);
            } else {

                // 1 випадок — нема дочірніх вершин
                if (vertex.left == null && vertex.right == null) {
                    return vertex;
                }

                // 2 випадок — одна дочірня вершина
                else if (vertex.left == null) {
                    return vertex.right;
                } else if (vertex.right == null) {
                    return vertex.left;
                }

                // 3 випадок — дві дочірні вершини
                Vertex successor = findMin(vertex.right);
                vertex.value = successor.value;
                vertex.right = deleteRecursive(vertex.right, successor.value);
            }
            return vertex;
        }
        private Vertex findMin(Vertex vertex) {
            while (vertex.left != null) {
                vertex = vertex.left;
            }
            return vertex;
        }

        public void deleteNegative() {
            root = deleteNegativeRecursive(root);
        }
        private Vertex deleteNegativeRecursive(Vertex vertex) {
            if (vertex == null) {
                return vertex;
            }

            vertex.left = deleteNegativeRecursive(vertex.left);
            vertex.right = deleteNegativeRecursive(vertex.right);

            if (vertex.value < 0) {
                if (vertex.left == null) {
                    return vertex.right;
                }
                if (vertex.right == null) {
                    return vertex.left;
                }
                Vertex successor = findMin(vertex.right);
                vertex.value = successor.value;
                vertex.right = deleteRecursive(vertex.right, successor.value);
            }
            return vertex;
        }

        public boolean isLucky(double ticket_number) {
            return ticket_number % 2 == 0;
        }
    }


    public static void lab_demonstration() {
        BST bst = new BST();

        double[] numbers = {5.3, -2.1, 10.8, -7.5, 15.0, -3.9, 8.2, 12.6, -1.4, 20.7};

        for (double num : numbers) {
            bst.insert(num);
        }

        System.out.println("Tree before deleting negatives:");
        bst.inorderTraversalDouble();
        System.out.println();

        bst.deleteNegative();

        System.out.println("Tree after deleting negatives:");
        bst.inorderTraversalDouble();
        System.out.println("\n");

        BST bst2 = new BST();
        double[] tickets = {124512, 342351, 765891, 453122, 431350, 876432, 734626, 238651, 455734, 234987};

        for (double ticket : tickets) {
            if (bst2.isLucky(ticket)) {
                bst2.insert(ticket);
            }
        }
        System.out.println("Lucky tickets in ASC order:");
        bst2.inorderTraversalInt();
    }
}
