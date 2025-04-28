package org.example;
import Lab1.Lab1;
import Lab10.Lab10;
import Lab2.Lab2;
import Lab3.Lab3;
import Lab4.Lab4;
import Lab5.Lab5;
import Lab6.Lab6;
import Lab7.Lab7;
import Lab8.Lab8;
import Lab9.Lab9;
import Lab10.Lab10;

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
            case 4:
                Lab4.lab_demonstration();
                break;
            case 5:
                Lab5.lab_demonstration();
                break;
            case 6:
                Lab6.lab_demonstration();
                break;
            case 7:
                Lab7.lab_demonstration();
                break;
            case 8:
                Lab8.lab_demonstration();
                break;
            case 9:
                Lab9.lab_demonstration();
                break;
            case 10:
                Lab10.lab_demonstration();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
