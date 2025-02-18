package Lab6;

public class Lab6 {

    private static class Node {
        Node next;
        String data;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class Stack {
        private Node head;

        public Stack() {
            head = null;
        }

        public void push(String data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public void pop() {
            if (head == null) {
                System.out.println("Stack is empty");
                return;
            }

            if (head.next == null) {
                head = null;
                return;
            }

            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }

        public int countTwoCharStrings() {
            int count = 0;
            Node current = head;
            while (current != null) {
                if (current.data.length() == 2) {
                    count++;
                }
                current = current.next;
            }
            return count;
        }

        public void display() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }


    public static void lab_demonstration() {
        Stack stack = new Stack();
        stack.push("abc");
        stack.push("fx");
        stack.push("hi");
        stack.push("Gogo");

        System.out.println("Stack before");
        stack.display();

        System.out.println("\nStack after");
        stack.pop();
        stack.push("the end");
        stack.display();

        System.out.println("Amount of elements that has two chars in string: " + stack.countTwoCharStrings());
    }
}
