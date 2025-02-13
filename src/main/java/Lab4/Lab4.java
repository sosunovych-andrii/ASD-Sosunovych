package Lab4;

import java.util.Arrays;

public class Lab4 {

    // TASK 1
    public static class School {
        String name;
        int group;
        int[] subjects;

        School(String name, int group, int[] subjects) {
            this.name = name;
            this.group = group;
            this.subjects = subjects;
        }
    }

    private static class Node {
        School learner;
        Node next;

        public Node(School learner, Node next) {
            this.learner = learner;
            this.next = next;
        }
    }

    public static class LinkedList {
        Node head;

        public LinkedList() {
            head = null;
        }

        // додавання вузла
        public void add(School learner) {
            Node newNode = new Node(learner, null);
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

        // видалення елемента за іменем
        public void remove(School learnerToRemove) {
            if (head == null) {
                return; // якщо список пустий
            }

            Node current = head;

            // якщо це 1-ий вузол
            if (current.learner.name.equals(learnerToRemove.name)) {
                head = current.next;
                return;
            }

            while (current.next != null) {
                if (current.next.learner.name.equals(learnerToRemove.name)) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }

        // пошук елемента за полем(ім'ям)
        public School findByName(String name) {
            Node current = head;
            while (current != null) {
                if (current.learner.name.equals(name)) {
                    return current.learner;
                }
                current = current.next;
            }
            return null;
        }

        // друкування списку
        public void print() {
            Node current = head;
            int i = 1;
            while (current != null) {
                System.out.println("Learner: " + i);
                System.out.println("Name: " + current.learner.name);
                System.out.println("Group: " + current.learner.group);
                System.out.println("Marks from subjects: " + Arrays.toString(current.learner.subjects) + "\n");
                i++;
                current = current.next;
            }
        }
    }




    //TASK 2
     public static class Josephus {
        private static class Node {
            Node next;
            int value;
            Node prev;

            public Node(int value) {
                this.value = value;
                this.next = null;
                this.prev = null;
            }
        }

        public static int solveJosephus(int n, int k) {
            Node head = new Node(1);

            Node current = head;
            for (int i = 2; i <= n; i++) {
                Node newNode = new Node(i);
                current.next = newNode;
                newNode.prev = current;
                current = newNode;
            }

            current.next = head;
            head.prev = current;

            // починаємо видаляти кожного k-го
            current = head;
            while (n > 1) {
                for (int i = 1; i < k; i++) {
                    current = current.next; // переміщаємось на k-ту позицію
                }

                System.out.println("Delete Node: " + current.value);

                // вилучаємо поточного
                current.prev.next = current.next;
                current.next.prev = current.prev;

                current = current.next;
                n--;
            }
            return current.value;
        }
    }


    public static void lab_demonstration() {
        // TASK 1
        School learner1 = new School("Andrii", 12, new int[]{2, 4, 5, 4, 5});
        School learner2 = new School("Bohdan", 12, new int[]{4, 4, 3, 4, 5});
        School learner3 = new School("David", 12, new int[]{4, 4, 3, 4, 5});

        LinkedList list = new LinkedList();
        list.add(learner1);
        list.add(learner2);
        list.add(learner3);

        // список до видалення вузла
        list.print();

        // список після видалення вузла
        System.out.println("LinkedList after removing Node:");
        list.remove(learner3);
        list.print();

        // шукаємо елемент за полем
        School found = list.findByName("Andrii");
        if (found != null) {
            System.out.println("Found element with name: " + found.name);
        } else {
            System.out.println("Element is not found");
        }
        System.out.println("\n\n");


        // TASK 2
        System.out.println("Josephus task:");
        int result = Josephus.solveJosephus(9, 5);
        System.out.println("Josephus last Node is: " + result);
    }
}
