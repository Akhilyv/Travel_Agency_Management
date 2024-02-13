package travel.management;

import java.util.ArrayList;
import java.util.List;

public class PassengerClass {
    private String name;
    private int number;
    private String type;
    private double balance;
    private List<TravelPackage> enrolledPackages;
    private List<Activity> enrolledActivities;

    public PassengerClass(String name, int number, String type, double balance) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.enrolledPackages = new ArrayList<>();
        this.enrolledActivities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
    
    public String getType() {
        return type;
    }
    
    public double getBalance() {
        return balance;
    }

    public List<TravelPackage> getEnrolledPackages() {
        return enrolledPackages;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEnrolledPackages(List<TravelPackage> enrolledPackages) {
        this.enrolledPackages = enrolledPackages;
    }
    
    public List<Activity> getEnrolledActivities() {
        return enrolledActivities;
    }

   
    public void enrollInActivity(Activity activity) {
        if (activity.getActivityCapacity() > 0) {
            enrolledActivities.add(activity);
        }
    }

    
    public void enrollInPackage(TravelPackage travelPackage) {
        enrolledPackages.add(travelPackage);
    }
    
    
}
