package travel.management;


import org.junit.Test;
import static org.junit.Assert.*;

public class TravelPackageTest {

    @Test
    public void testEnrollPassengerWithCapacity() {
        // Create a travel package with initial capacity of 2
        TravelPackage travelPackage = new TravelPackage("Test Package", 2);

        // Create two passengers
        PassengerClass passenger1 = new PassengerClass("Passenger 1", 1, "Type1", 1000.0);
        PassengerClass passenger2 = new PassengerClass("Passenger 2", 2, "Type2", 1200.0);

        // Enroll passengers in the travel package
        travelPackage.enrollPassenger(passenger1);
        travelPackage.enrollPassenger(passenger2);

        // Check if passenger capacity is correctly decremented
        assertEquals(0, travelPackage.getPassengerCapacity());
    }

    @Test
    public void testEnrollPassengerWithoutCapacity() {
        // Create a travel package with initial capacity of 1
        TravelPackage travelPackage = new TravelPackage("Test Package", 1);

        // Create two passengers
        PassengerClass passenger1 = new PassengerClass("Passenger 1", 1, "Type1", 1000.0);
        PassengerClass passenger2 = new PassengerClass("Passenger 2", 2, "Type2", 1200.0);

        // Enroll one passenger to fill the capacity
        travelPackage.enrollPassenger(passenger1);

        // Try to enroll another passenger
        travelPackage.enrollPassenger(passenger2);

        // Check if passenger capacity remains at 0
        assertEquals(0, travelPackage.getPassengerCapacity());
    }
}
