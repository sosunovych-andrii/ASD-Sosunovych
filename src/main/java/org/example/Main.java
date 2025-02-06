package org.example;
import Lab1.Lab1;
import Lab2.Lab2;
import Lab3.Lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lab ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Lab1.lab_demonstration();
                break;
            case 2:
                Lab2.lab_demonstration();
                break;
            case 3:
                Lab3.lab_demonstration();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}