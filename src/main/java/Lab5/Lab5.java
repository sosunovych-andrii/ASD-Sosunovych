package Lab5;

public class Lab5 {

    public static class Node {
        Node next;
        int data;
        Node prev;

        public Node(int data) {
            this.next = null;
            this.data = data;
            this.prev = null;
        }
    }

    public static class LinkedList {
        Node head;
        Node tail;

        public LinkedList() {
            head = null;
            tail = null;
        }

        public void add(int data) {
            Node new_node = new Node(data);

            if (head == null) {
                head = new_node;
                tail = new_node;
            } else {
                tail.next = new_node;
                new_node.prev = tail;
                tail = new_node;
            }
        }

        public void duplicateOdd() {
            Node current = head;
            while (current != null) {
                if (current.data % 2 != 0) {
                    // додаємо новий вузол у список
                    Node duplicate = new Node(current.data);
                    duplicate.next = current.next;
                    duplicate.prev = current;

                    // змінюємо посилання
                    if (current.next != null) {
                        current.next.prev = duplicate;
                    } else {
                        // якщо ми на хвості, оновлюємо його
                        tail = duplicate;
                    }
                    current.next = duplicate;

                    // переходимо до наступного елемента після дубліката
                    current = duplicate.next;
                } else {
                    current = current.next;
                }
            }
        }

        public void print() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }



    public static void lab_demonstration() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("LinkedList before duplication");
        list.print();

        System.out.println("LinkedList after duplication");
        list.duplicateOdd();
        list.print();
    }
}
