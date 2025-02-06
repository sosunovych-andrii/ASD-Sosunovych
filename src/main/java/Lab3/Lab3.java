package Lab3;


import java.util.Scanner;

public class Lab3 {

    public static class School {
        String name;
        int group;
        int[] subjects;

        School(String name, int group, int[] subjects) {
            this.name = name;
            this.group = group;
            this.subjects = subjects;
        }

        public boolean hasTwo() {
            for (int mark : subjects) {
                if (mark == 2) {
                    return true;
                }
            }
            return false;
        }
    }

    public School[] input() {
        System.out.println("Enter amount of learners:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        School[] learners = new School[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter data for learner " + (i + 1));

            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Group: ");
            int group = scanner.nextInt();

            System.out.print("Marks for subjects: ");
            int[] subjects = new int[5];
            for (int j = 0; j < 5; j++) {
                subjects[j] = scanner.nextInt();
            }

            learners[i] = new School(name, group, subjects);
        }
        return learners;
    }

    public void sortInAlphabeticalOrder(School[] learners) {
        for (int i = 0; i < learners.length; i++) {
            for (int j = i + 1; j < learners.length; j++) {
                if (learners[i].name.compareTo(learners[j].name) > 0) {
                    School temp = learners[i];
                    learners[i] = learners[j];
                    learners[j] = temp;
                }
            }
        }
    }

    public void printLearnersWithTwos(School[] learners) {
        for (School learner : learners) {
            if (learner.hasTwo()) {
                System.out.println("Learner " + learner.name + " from group " + learner.group + " has two.");
            } else {
                System.out.println("Learner " + learner.name + " from group " + learner.group + " has no two.");
            }
        }
    }

    public static void lab_demonstration() {
        Lab3 lab = new Lab3();
        School[] learners = lab.input();
        lab.sortInAlphabeticalOrder(learners);
        lab.printLearnersWithTwos(learners);
    }
}
