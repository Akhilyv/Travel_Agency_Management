package travel.management;


public class Activity {
    private String activityName;
    private String activityDescription;
    private double activityCost;
    private int activityCapacity;
    private int enrolledPassengers;

    public Activity(String activityName, String activityDescription, double activityCost, int activityCapacity) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.activityCost = activityCost;
        this.activityCapacity = activityCapacity;
        this.enrolledPassengers = 0;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public double getActivityCost() {
        return activityCost;
    }

    public int getActivityCapacity() {
        return activityCapacity;
    }
    
    public void decreaseCapacity() {
        if (activityCapacity > 0) {
            activityCapacity--;
        }
    }
    
    public int getEnrolledPassengers() {
        return enrolledPassengers;
    }

    public void setEnrolledPassengers(int enrolledPassengers) {
        this.enrolledPassengers = enrolledPassengers;
    }
    
    // Method to decrease the activity capacity when a passenger enrolls
    public void enrollPassenger() {
        if (enrolledPassengers < activityCapacity) {
            enrolledPassengers++;
            decreaseCapacity();
        }
    }
    
}
