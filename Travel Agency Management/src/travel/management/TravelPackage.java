package travel.management;

import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
    private static int packageNumberCounter = 1;
    private int packageNumber;
    private String packageName;
    private int passengerCapacity;
    private List<Destination> destinations;
    private List<PassengerClass> passengers;

    public TravelPackage(String packageName, int passengerCapacity) {
        this.packageNumber = packageNumberCounter++;
        this.packageName = packageName;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>(); 
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }
    
    
    public String getPackageName() {
        return packageName;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
    
    public int getPackageNumber() {
        return packageNumber;
    }
    
    public List<PassengerClass> getPassengers() {
        return passengers;
    }
    
    public void enrollPassenger(PassengerClass passenger) {       
        if (hasCapacityForPassenger()) {
            passengers.add(passenger);
            passenger.enrollInPackage(this);
            passengerCapacity--; // Decrement passenger capacity
        } else {
            System.out.println("Passenger capacity reached for this package: " + this.getPackageName());
        }
    }
     
    
    public boolean hasCapacityForPassenger() {
        return passengerCapacity > 0;
    }
    
     public boolean selectActivity(PassengerClass passenger, String activityName) {
        // Iterate through each destination in the travel package
        for (Destination destination : destinations) {
            for (Activity activity : destination.getActivities()) {
                if (activity.getActivityName().equalsIgnoreCase(activityName) && activity.getActivityCapacity()>0) {
                    activity.decreaseCapacity();
                    passenger.enrollInActivity(activity);
                    return true;
                }
            }
        }
        return false;
    }
    
    
}

