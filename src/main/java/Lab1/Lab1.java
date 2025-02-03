package Lab1;
import java.util.Scanner;

public class Lab1 {

    public static void calculateDistanceBetweenCars() {
        System.out.println("TASK 1");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter speed of first car: ");
        int speed1 = scanner.nextInt();
        System.out.println("Enter speed of second car: ");
        int speed2 = scanner.nextInt();
        System.out.println("Enter time in hours: ");
        int hours = scanner.nextInt();
        System.out.println("Enter initial distance between cars: ");
        int initialDistance = scanner.nextInt();

        int resultDistance = Math.max(0, initialDistance - (speed1 + speed2) * hours);

        System.out.println("The result distance between cars is: " + resultDistance);
    }

    public static void calculateY() {
        System.out.println("\nTASK 2");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an a, b, x:");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double x = scanner.nextDouble();

        double y = Math.pow(x, a / b) - Math.pow((x + b) / a, 1.0 / 3);
        System.out.println("The value of y is: " + y);
    }

    public static void canQueenMove() {
        System.out.println("\nTASK 3");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates: ");
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        boolean canMove = (x1 == x2) || (y1 == y2) || (Math.abs(x1 - x2) == Math.abs(y1 - y2));
        if (canMove) {
            System.out.println("Queen can be moved at " + x2 + ", " + y2);
        } else {
            System.out.println("Queen can't be moved at " + x2 + ", " + y2);
        }
    }


    public static void lab_demonstration() {
        calculateDistanceBetweenCars();
        calculateY();
        canQueenMove();
    }
}
