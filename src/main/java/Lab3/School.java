package Lab3;

import java.util.Scanner;


public class School {
    String name;
    int group;
    int[] subjects;

    School() {}

    School(String name, int group, int[] subjects) {
        this.name = name;
        this.group = group;
        this.subjects = subjects;
    }

    public School[] input() {
        System.out.println("Enter amount of learners:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        School[] learners = new School[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter data for learner " + (i + 1));

            System.out.print("Name: ");
            name = scanner.next();
            System.out.print("Group: ");
            group = scanner.nextInt();

            System.out.print("Marks for subjects: ");
            subjects = new int[5];
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

    public boolean hasTwo() {
        for (int mark : subjects) {
            if (mark == 2) {
                return true;
            }
        }
        return false;
    }

    public void printLearnersWithTwos(School[] learners) {
        for (School learner : learners) {
            if (learner.hasTwo()) {
                System.out.println("Learner " + learner.name + " from group " + learner.group + " has two.");
            }
            else {
                System.out.println("Learner " + learner.name + " from group " + learner.group + " has no two.");
            }
        }
    }
}
