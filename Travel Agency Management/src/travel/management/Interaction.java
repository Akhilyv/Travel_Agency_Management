package travel.management;

import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
              
        //The Main Interaction Screen. The Program starts from here. Enter the number/choice (Recommended to select 1st option)
        while (true) {
            System.out.println("Welcome to the Travel Booking System!");
            System.out.println("Choose your role:");
            System.out.println("1. Travel Agency");
            System.out.println("2. Passenger");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Choose your option: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (userChoice) {
                case 1:
                    //TravelAgency flow
                    TravelAgency.mainMenu();
                    break;
                case 2:
                    // Passenger flow
                    Passenger.mainMenu(args);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
