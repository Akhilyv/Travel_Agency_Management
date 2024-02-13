package travel.management;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private String destinationName;
    private List<Activity> activities;

    public Destination(String destinationName) {
        this.destinationName = destinationName;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    
   
    // Getter methods for destinationName and activities
    public String getDestinationName() {
        return destinationName;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
