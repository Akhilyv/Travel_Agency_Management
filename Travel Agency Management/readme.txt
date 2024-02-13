#Software Design Task (JAVA)

Task: Design, implement and test a software system that allows travel agencies to maintain their travel packages' itinerary and passengers.

## Overview
Travel Agency Management is a Java-based console application that simulates the interactions between a travel agency and its passengers. 
The system allows passengers to explore travel packages, enroll in activities, and manage their bookings.

Project: Travel Agency Management


##Flow of the Program:

1) Interaction.java:

This file contains the main method, making it the starting point of the program.
It presents a menu to the user, allowing them to choose between being a Travel Agency or a Passenger.
Based on the user's choice, it invokes the corresponding class's mainMenu method (TravelAgency.mainMenu() or Passenger.mainMenu()).

2) TravelAgency.java:

If the user chooses to be a Travel Agency, the mainMenu method in TravelAgency.java is called.
The mainMenu method presents options for the Travel Agency to perform tasks such as:
i) Creating travel packages, destinations, and activities.
	-- User creates a new package and provides the passenger capacity for package, destinations, activities, description, passengers capacity for activities and cost per activity.
ii) Display available packages
	-- If user have'nt created package. No package is displayed
	-- Displays all packages along with updated capacities, even if passenger's enrolls into any package.
	-- Displays updated details with available passenger capacities in package and in activities.
iii) Display Enrolled Passengers
	-- If passengers have not enrolled, no passengers data is displayed
	-- If passengers have enrolled, the passenger name and id are displayed only under the respective enrolled package
iv) Exit
	-- Exits the travel agency menu and returns back to interaction menu.


3) Passenger.java:

If the user chooses to be a Passenger, the mainMenu method in Passenger.java is called.
The mainMenu method provides options for the Passenger to sign up as a new Passenger.
-> User enters name, type of passenger(Standard, Premium, Gold) and enters the available balance.
-> New Passenger is created and the mainMenu method presents options for passenger to choose the option.
i) Choose a Tour Package
	-- After selecting 1st Option. Passenger should enter the package number (1 or 2 or 3...) based on available projects.
	-- If passenger capacity of selected package number is (zero). the passenger has to look for other package.
	-- Package displays desinations covered under the tour and activities available under each destination with avaialable capacity.
	-- Passenger should enter the "activity name" that he likes to book. if the capacity of the activity is (zero). program does'nt allow him to book the activity. 
	-- Based on availablilty, passenger should choose the activity and enter the name.
	-- Based on the type of passenger, the costs are deducted from the balance. A standard user will be deducted with given cost, while golden user is provided 10% discount and premium user gets free activity to enroll.
	-- If a standard passenger selects any activity which costs beyond their balance. Activity will not be enrolled.
	-- Once passenger enrolls in required activities, he/she should type "confirm" to confirm the booking.
	-- Program displays the enrolled package and activities

ii) Display Enrolled Packages
	-- If passenger enrolled in any package, the program displays all the enrolled packages.
	-- If passenger didnt enroll in any packaghe. No package is displayed

iii) Display Final Details
	-- This option displays only required information such as the package, destination and activities (activities selected by passenger but not the rest) that passenger enrolled.	

iv) Passenger Details
	-- Displays the passenger details (Name, Id, Type of passenger, Available balance)
	-- Balance is updated after every package booking.

v) Exit
	-- Exits the passenger menu and returns back to interaction menu


4) User can switch to travel agency and check the enrolled passengers. The program displays the passenger name and id enrolled in respective package. 
5) User can choose (display available packages) to check the updated passengers capacity.
