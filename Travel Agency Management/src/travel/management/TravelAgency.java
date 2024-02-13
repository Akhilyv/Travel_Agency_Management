package travel.management;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelAgency {
    public static final List<TravelPackage> travelPackages = new ArrayList<>();

    public static void mainMenu() {
        System.out.println("\nSystem successfully initialized for the 'Travel Agency'");
 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTravel Agency Main Menu:");
            System.out.println("1. Create Packages");
            System.out.println("2. Display Package Details");
            System.out.println("3. Display Passengers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPackages();       //To create new packages by travel agency
                    break;
                case 2:
                    displayDetails();       //Display's the created packages
                    break;
                case 3:
                    printPassengerList();   //Display's the package and passengers details enrolled in it
                    break;
                case 4:
                    System.out.println("Exiting Travel Agency. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }


    private static void createPackages() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the New Tour Package Name: ");
        String packageName = scanner.nextLine();

        System.out.println("Enter the passenger capacity of the travel package (Note: Only number): ");
        int passengerCapacity = scanner.nextInt();
        scanner.nextLine(); 

        TravelPackage newPackage = new TravelPackage(packageName, passengerCapacity);   //Creating the instance for every new package and save details 
        addDestinations(newPackage, scanner);   
        
        travelPackages.add(newPackage);                 // Adding the new package to the Arraylist
        System.out.println("Travel package created successfully!!!!");
    }
    
    
    private static void addDestinations(TravelPackage travelPackage, Scanner scanner) {
    System.out.println("How many destinations does the package have? (Note: Only number)");
    int numDestinations = scanner.nextInt();
    scanner.nextLine(); 

    for (int i = 1; i <= numDestinations; i++) {
        System.out.println("Enter the name of destination " + i + ": ");
        String destinationName = scanner.nextLine();

        System.out.println("How many activities does the destination have? (Note: Only number)");
        int numActivities = scanner.nextInt();
        scanner.nextLine(); 

        Destination destination = new Destination(destinationName);     

        for (int j = 1; j <= numActivities; j++) {
            System.out.println("Enter the name of activity " + j + " for destination " + destinationName + ": ");
            String activityName = scanner.nextLine();

            System.out.println("Enter the description of activity " + j + " for destination " + destinationName + ": ");
            String activityDescription = scanner.nextLine();

            System.out.println("Enter the cost of activity " + j + " for destination " + destinationName + ": ");
            double activityCost = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.println("Enter the capacity of activity " + j + " for destination " + destinationName + ": ");
            int activityCapacity = scanner.nextInt();
            scanner.nextLine(); 

            Activity activity = new Activity(activityName, activityDescription, activityCost, activityCapacity);

            // Adding the activity to the arraylist in Destination.java
            destination.addActivity(activity);
        }

        // Adding the destination to the travel package
        travelPackage.addDestination(destination);
    }
}

    private static void displayDetails() {
    System.out.println("Displaying details...");

    for (TravelPackage travelPackage : travelPackages) {
        System.out.println("Package Name: " + travelPackage.getPackageName());
        System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());

        for (Destination destination : travelPackage.getDestinations()) {
            System.out.println("\tDestination Name: " + destination.getDestinationName());

            for (Activity activity : destination.getActivities()) {
                System.out.println("\t\tActivity Name: " + activity.getActivityName());
                System.out.println("\t\tDescription: " + activity.getActivityDescription());
                System.out.println("\t\tCost: " + activity.getActivityCost());
                System.out.println("\t\tCapacity: " + activity.getActivityCapacity());
            }
        }

        System.out.println(); 
        } 
    
    }
    
    private static void printPassengerList() {
        System.out.println("Printing Passenger List for Travel Packages...");

        for (TravelPackage travelPackage : travelPackages) {
            System.out.println("Travel Package Name: " + travelPackage.getPackageName());
            System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
            System.out.println("Number of Passengers Enrolled: " + travelPackage.getPassengers().size());

            for (PassengerClass passenger : travelPackage.getPassengers()) {
                System.out.println("\tPassenger Name: " + passenger.getName());
                System.out.println("\tPassenger Number: " + passenger.getNumber());
            }

            System.out.println(); 
        }
    }
    
    
}
