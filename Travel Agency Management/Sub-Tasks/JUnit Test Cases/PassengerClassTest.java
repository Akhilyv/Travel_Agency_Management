package travel.management;

import org.junit.Test;
import static org.junit.Assert.*;

public class PassengerClassTest {

    @Test
    public void testEnrollInActivityWithCapacity() {
        PassengerClass passenger = new PassengerClass("Venkat", 1001, "Standard", 100.0);
        Activity activity = new Activity("Sightseeing","Enjoy the views", 20.0, 5 );

        passenger.enrollInActivity(activity);

        // Check the state of the passenger after enrolling in the activity
        assertEquals(1, passenger.getEnrolledActivities().size());

    }

    @Test
    public void testEnrollInActivityWithoutCapacity() {
        // Arrange
        PassengerClass passenger = new PassengerClass("Rohit", 1002, "Gold", 500.0);
        TravelPackage travelPackage = new TravelPackage("Summer_Tour", 1);
        Destination destination = new Destination("Kaafu");
        Activity activity = new Activity("Sightseeing","Enjoy the views", 70.0, 0 );  // Capacity set to 0 

        // Act
        travelPackage.addDestination(destination);
        destination.addActivity(activity);
        passenger.enrollInPackage(travelPackage);
        passenger.enrollInActivity(activity);

        // Assert
        //assertFalse(result);  // Expecting enrollment to fail due to activity capacity being 0
        assertEquals(0, passenger.getEnrolledActivities().size());  // Ensure no activities are enrolled
    }

}
