package Lab3;


public class Lab3 {
    public static void lab_demonstration() {
        School school = new School();
        School[] learners = school.input();
        school.sortInAlphabeticalOrder(learners);
        school.printLearnersWithTwos(learners);
    }
}
