package travel.management;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import static travel.management.TravelAgency.travelPackages;

public class Passenger {
    private static int passengerCounter = 1000;

    public static void mainMenu(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Passenger Interaction System");
        System.out.println("1. New Passenger");
//        System.out.println("2. Existing Passenger");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // New Passenger Flow
                newPassengerFlow(scanner);
                break;
            /***case 2:
                // Existing Passenger Flow
                existingPassengerFlow(scanner);
                break;***/
            default:
                System.out.println("Invalid choice. Exiting.");
        }
    }

    private static void newPassengerFlow(Scanner scanner) {         //Creating new passenger
        System.out.print("Enter your name: ");
        String passengerName = scanner.next();        
        int passengerNumber = generateUniqueID();
        
        System.out.print("Enter passenger type (Standard, Gold, Premium): ");
        String passengerType = scanner.next();
        
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        
        PassengerClass passenger = new PassengerClass(passengerName, passengerNumber, passengerType, initialBalance);

        System.out.println("Hello, " + passenger.getName() + "! Welcome to our travel agency.");
        passengerOptionsFlow(scanner, passenger);
    }
/***
    private static void existingPassengerFlow(Scanner scanner) {
        System.out.print("Enter your name: ");
        String passengerName = scanner.next();
        
        System.out.print("Enter your passenger number: ");
        int passengerNumber = scanner.nextInt();

        PassengerClass existingPassenger = findExistingPassenger(passengerName, passengerNumber);

        if (existingPassenger != null) {
            System.out.println("Welcome back, " + existingPassenger.getName() + "!");
            passengerOptionsFlow(scanner, existingPassenger);
        } else {
            System.out.println("Passenger not found. Exiting.");
        }
    }***/

    private static void passengerOptionsFlow(Scanner scanner, PassengerClass passenger) {
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Choose a Tour Package");
            System.out.println("2. Display Enrolled Packages");
            System.out.println("3. Display Final Details");
            System.out.println("4. Print Passenger Details"); 
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    displayTravelPackages();    // Displays the available packages
                    
                    System.out.print("Select a Travel Package (enter package number): ");
                    int selectedPackageNumber = scanner.nextInt();
                    
                    TravelPackage selectedPackage = getSelectedPackage(selectedPackageNumber);
                    if (selectedPackage != null && selectedPackage.hasCapacityForPassenger())  //Validates the availablilty of existing passengers capacity
                    {
                        selectedPackage.enrollPassenger(passenger);    //Decrements the Passengercapacity and enrolls the name and id of passenger for selected package
                        passengerFlowForSelectedPackage(scanner, selectedPackage, passenger);   //Function which accepts the "activities" selected by the passenger
                    }
                    else
                    {
                        System.out.println("Sorry, the selected package is already full. Please choose another package.");
                    }
                    break;
                    
                case 2:
                    displayEnrolledPackages(passenger);     //Displays the enrolled packages of the passenger
                    break;
                    
                case 3:
                    displayFinalDetails(passenger);     //Simple summary display of the package and only activites enrooled by passenger
                    break;
                    
                case 4:
                    printPassengerDetails(passenger);   //Displays the Passenger information
                    break;
                    
                case 5:
                    System.out.println("Exiting.....");
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static int generateUniqueID() {          //Passenger Id function, increments after every new passenger is created
        return ++passengerCounter;
    }

private static void displayTravelPackages() {       //Displays the packages and its itinerary
    System.out.println("Available Travel Packages:");    
    
    for (TravelPackage travelPackage : travelPackages) {
        System.out.println(travelPackage.getPackageNumber()+")"+"Package Name: " + travelPackage.getPackageName());
        System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
        System.out.println("Destinations:");

        for (Destination destination : travelPackage.getDestinations()) {
            System.out.println(" - Destination Name: " + destination.getDestinationName());
            System.out.println("   Activities:");

            for (Activity activity : destination.getActivities()) {
                System.out.println("   - Activity Name: " +activity.getActivityName());
                System.out.println("     Description: " + activity.getActivityDescription());
                System.out.println("     Capacity: " + activity.getActivityCapacity());
                System.out.println("     Cost: " + activity.getActivityCost());
            }
        }

        System.out.println("------------------------------");
    }
}



    private static TravelPackage getSelectedPackage(int packageNumber) {        //Validating the passenger's entered package number with existing packages 
    for (TravelPackage travelPackage : travelPackages) {
        if (travelPackage.getPackageNumber() == packageNumber) {
            return travelPackage;
        }else {
            System.out.println("Entered number doesnt match the package");
        }
    }
    return null; // Package with the specified number not found
}


    private static void passengerFlowForSelectedPackage(Scanner scanner, TravelPackage selectedPackage, PassengerClass passenger) {
    System.out.println("You have selected the package: " + selectedPackage.getPackageName());
    System.out.println("Passenger: " + passenger.getName() + " (ID: " + passenger.getNumber() + ")");

    while (true) {
        
        displayAvailableActivities(selectedPackage);

        System.out.print("Select an Activity (enter activity name or 'confirm' to confirm booking): ");
        String selectedActivityName = scanner.next();                   //Passenger enters the activity name that he/she like to chose

        if (selectedActivityName.equalsIgnoreCase("confirm")) {         // User chose to confirm booking
            displayFinalDetails(passenger);
            break;
        }

        enrollInSelectedActivity(scanner, selectedPackage, passenger, selectedActivityName);       //Checking the passenger type and balance to enroll
    }
}

private static void enrollInSelectedActivity(Scanner scanner, TravelPackage selectedPackage, PassengerClass passenger, String selectedActivityName) {
    Activity selectedActivity = getSelectedActivity(selectedPackage, selectedActivityName);

    if (selectedActivity != null && selectedActivity.getActivityCapacity() > 0) {
        // Activity is available, and there is enough capacity
        double activityCost = selectedActivity.getActivityCost();

        switch (passenger.getType()) {
                case "Standard":
                    enrollStandardPassenger(passenger, selectedPackage, selectedActivity, activityCost);
                    break;
                case "Gold":
                    enrollGoldPassenger(passenger, selectedPackage, selectedActivity, activityCost);
                    break;
                case "Premium":
                    enrollPremiumPassenger(passenger, selectedPackage, selectedActivity);
                    break;
                default:
                    System.out.println("Invalid passenger type.");
                    return;
            }
        } else {
            // Either activity not found or no capacity
            System.out.println("Invalid activity selection. Please try again.");
        }
    }

    private static void enrollStandardPassenger(PassengerClass passenger, TravelPackage selectedPackage, Activity selectedActivity, double activityCost) {
        if (passengerHasSufficientBalance(passenger, activityCost)) {
            enrollInActivity(passenger, selectedPackage, selectedActivity);
            deductBalance(passenger, activityCost);
            System.out.println("\nEnrolled in activity: " + selectedActivity.getActivityName());
        } else {
            // Insufficient balance
            System.out.println("\nInsufficient balance. Cannot enroll in the activity.");
        }
    }
    
    private static void enrollGoldPassenger(PassengerClass passenger, TravelPackage selectedPackage, Activity selectedActivity, double activityCost) {
        double discountedCost = applyGoldDiscount(activityCost);
        if (passengerHasSufficientBalance(passenger, discountedCost)) {
            enrollInActivity(passenger, selectedPackage, selectedActivity);
            deductBalance(passenger, discountedCost);
            System.out.println("\nEnrolled in activity: " + selectedActivity.getActivityName() + " (Gold Discount Applied)");
        } else {
            // Insufficient balance
            System.out.println("\nInsufficient balance. Cannot enroll in the activity.");
        }
    }
    
    private static void enrollPremiumPassenger(PassengerClass passenger, TravelPackage selectedPackage, Activity selectedActivity) {
        enrollInActivity(passenger, selectedPackage, selectedActivity);
        System.out.println("\nEnrolled in activity: " + selectedActivity.getActivityName() + " (Premium Passenger - Free)");
    }
    
    private static double applyGoldDiscount(double originalCost) {
        return 0.9 * originalCost; // 10% discount for gold passengers
    }

    
    private static boolean passengerHasSufficientBalance(PassengerClass passenger, double activityCost) {
        // Check if the passenger has sufficient balance for the activity
        return passenger.getBalance() >= activityCost;
    }
    
    private static void enrollInActivity(PassengerClass passenger, TravelPackage selectedPackage, Activity selectedActivity) {
    for (Destination destination : selectedPackage.getDestinations()) {
        for (Activity activity : destination.getActivities()) {
            if (activity.getActivityName().equals(selectedActivity.getActivityName()) && activity.getActivityCapacity() > 0) {
                // Enroll the passenger in the activity
                activity.enrollPassenger();
                passenger.enrollInActivity(activity);
                // Update the enrolled packages for the passenger
                passenger.enrollInPackage(selectedPackage);         // Adding the enrolled passengers details to selected activity
                return;
            }
        }
    }
}

    
    private static void deductBalance(PassengerClass passenger, double activityCost) {
        // Deduct the cost of the activity from the passenger's balance
        double remainingBalance = passenger.getBalance() - activityCost;
        passenger.setBalance(remainingBalance);
    }
    
    
    private static Activity getSelectedActivity(TravelPackage selectedPackage, String activityName) {
    for (Destination destination : selectedPackage.getDestinations()) {
        for (Activity activity : destination.getActivities()) {
            if (activity.getActivityName().equalsIgnoreCase(activityName)) {        //Validate the entered activity by passenger
                return activity;
            }
        }
    }
    return null; // Activity with the specified name not found
}


    private static void displayAvailableActivities(TravelPackage selectedPackage) {
        System.out.println("Available Activities:");
        for (Destination destination : selectedPackage.getDestinations()) {
            System.out.println("Destination: " + destination.getDestinationName());
            for (Activity activity : destination.getActivities()) {
                System.out.println(" - Activity: " + activity.getActivityName());
                System.out.println("   Description: " + activity.getActivityDescription());
                System.out.println("   Capacity: " + activity.getActivityCapacity());
                System.out.println("   Cost: " + activity.getActivityCost());
        }
    }
}


private static void displayEnrolledPackages(PassengerClass passenger) {
    System.out.println("Enrolled Packages for " + passenger.getName() + ":");
    
    // Displays the list of packages enrolled by the passenger
    List<TravelPackage> enrolledPackages = passenger.getEnrolledPackages();

    if (enrolledPackages.isEmpty()) {                   //If no details are not found in list.
        System.out.println("No enrolled packages.");
    } else {
        for (TravelPackage enrolledPackage : enrolledPackages) {
            System.out.println("Package Name: " + enrolledPackage.getPackageName());
            System.out.println("Available Passenger Capacity: " + enrolledPackage.getPassengerCapacity());
            System.out.println("Destinations:");

            for (Destination destination : enrolledPackage.getDestinations()) {
                System.out.println(" - Destination Name: " + destination.getDestinationName());
                System.out.println("   Activities:");

                for (Activity activity : destination.getActivities()) {
                    System.out.println("   - Activity Name: " + activity.getActivityName());
                    System.out.println("     Description: " + activity.getActivityDescription());
                    System.out.println("     Cost: " + activity.getActivityCost());
                    System.out.println("     Available Capacity: " + activity.getActivityCapacity());
                }
            }

            System.out.println("------------------------------");
        }
    }
}


private static void displayFinalDetails(PassengerClass passenger) {             //Method to only display the booked tour and activities.
        System.out.println("Final Details for " + passenger.getName() + ":");

        List<TravelPackage> enrolledPackages = passenger.getEnrolledPackages();

        if (enrolledPackages == null || enrolledPackages.isEmpty()) {
            System.out.println("No enrolled packages.");
        } else {
            for (TravelPackage enrolledPackage : enrolledPackages) {
                System.out.println("Package Name: " + enrolledPackage.getPackageName());
                System.out.println("Destinations:");

                for (Destination destination : enrolledPackage.getDestinations()) {
                    System.out.println(" - Destination Name: " + destination.getDestinationName());
                    System.out.println("   Activities:");

                    for (Activity enrolledActivity : passenger.getEnrolledActivities()) {
                        if (destination.getActivities().contains(enrolledActivity)) {
                        System.out.println("   - Activity Name: " + enrolledActivity.getActivityName());
                        System.out.println("     Description: " + enrolledActivity.getActivityDescription());
                        }
                        else
                        {
                            System.out.println();
                        }
                    }
                }

                System.out.println("------------------------------");
            }
        }
    }

    private static void printPassengerDetails(PassengerClass passenger) {       //Display of Passenger Details
    System.out.println("Passenger Details:");
    System.out.println("Name: " + passenger.getName());
    System.out.println("Passenger Number: " + passenger.getNumber());
    System.out.println("Passenger Type: " + passenger.getType());
    System.out.println("Balance: $" + passenger.getBalance());
}



     private static PassengerClass findExistingPassenger(String passengerName, int passengerNumber) {
        List<PassengerClass> existingPassengers = getExistingPassengers();

        for (PassengerClass existingPassenger : existingPassengers) {
            if (existingPassenger.getName().equals(passengerName) && existingPassenger.getNumber() == passengerNumber) {
                return existingPassenger; // Found existing passenger
            }
        }

        return null; // Passenger not found
    }

     
    private static List<PassengerClass> getExistingPassengers() {
        List<PassengerClass> existingPassengers = new ArrayList<>();
        return existingPassengers;
    }

}
