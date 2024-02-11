import java.util.Scanner;

// CST-283
// Aaron Pelto
// Winter 2024
// Program 1

// Write a program that will allow scientific queries to be performed on (actual) earthquake data.
// The (very large) file quakes.zip is provided and includes all earthquakes worldwide with magnitudes 4.0 (on Richter Scale) from 2010 through Summer 2018.

// Requirements
// 1. No Arraylist or Hashmaps

// Interface Requirements
/*

For your interface,use simple console input (via the Scanner class).
Similarly, output can be directed to the Java console to restrict your earthquake data system to a command-line application.
A simple query will likely generate a long list of earthquake events.
Pull all matching quakes from you data (stored in the array-based data structure) and list all that satisfy the query.
Once all information has been displayed, be sure to return to the menu to give the user the option to perform another query or quit.
Formatting of the "raw" quake data is expected. For example, if the following data line from the file matches
the query:
2015-05-02T16:23:07.580Z|42.2357|-85.4285|4.2|5km S of Galesburg; Michigan
reformat it for your output to look like:
02 MAY 15 1623Z, (42.24,-85.43), Mag: 4.2, 5km S of Galesburg; Michigan


 */

public class EarthquakeReader {
    public static void main(String[] args) {
        earthquakeMenu();
    }

    public static void earthquakeMenu() {
        Scanner menuInput = new Scanner(System.in);

        do {
            System.out.println("Earthquake Data Query");
            System.out.println("1. Query by Date");
            System.out.println("2. Query by Query by Longitude/Latitude");
            System.out.println("3. Query by Magnitude");
            System.out.println("4. Query by Location");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            // Scanner Input
            int choice = menuInput.nextInt();

            // Earthquake Object
            // Initialize to null
            Earthquake earthquake = null;

            // Menu Options
            switch (choice) {
                case 1:
                    // Create new Earthquake object
                    // Query by Date
                    // Call queryByDate method
                    System.out.println("Query by Date");
                    String date = dateInput();
                    earthquake = new Earthquake(date, "", 0.0, 0.0, 0.0,"");
                    earthquake.queryByDate(date);
                    break;
                case 2:
                    // Create new Earthquake object
                    // Query by Longitude/Latitude
                    // Call queryByLongitudeLatitude method
                    System.out.println("Query by Longitude/Latitude");
                    double[] longitudeLatitude = longitudeLatitudeInput();
                    earthquake = new Earthquake("","", longitudeLatitude[0], longitudeLatitude[1], 0.0, "");
                    earthquake.queryByLongitudeLatitude(longitudeLatitude[0], longitudeLatitude[1]);
                    break;
                case 3:
                    // Create new Earthquake object
                    // Query by Magnitude
                    // Call queryByMagnitude method
                    System.out.println("Query by Magnitude");
                    double magnitude = magnitudeInput();
                    earthquake = new Earthquake("", "", 0.0,0.0, magnitude, "");
                    earthquake.queryByMagnitude(magnitude);
                    break;
                case 4:
                    // Create new Earthquake object
                    // Query by Location
                    // Call queryByLocation method
                    System.out.println("Query by Location");
                    String location = locationInput();
                    earthquake = new Earthquake("", "", 0.0, 0.0,0.0, location);
                    earthquake.queryByLocation(location);
                    break;
                case 5:
                    // Exit Program
                    // Close Scanner
                    // Exit Program
                    System.out.println("Exiting Program");
                    menuInput.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, 4, or 5.");
                    break;
            }
        } while (true);
    }

    // Scanner Input Methods

    // Date Input
    // Captures Date input by YYYY-MM-DD
    public static String dateInput() {
        Scanner dateInput = new Scanner(System.in);
        System.out.println("Enter the date (YYYY-MM-DD): ");
        String date = dateInput.nextLine();
        System.out.println("You entered: " + date);
        return date;
    }

    // Longitude/Latitude Input
    // Captures Longitude and Latitude input
    public static double[] longitudeLatitudeInput() {
        Scanner locationInput = new Scanner(System.in);
        System.out.println("Enter the latitude (ex., '-12.9364'): ");
        double latitude = locationInput.nextDouble();
        System.out.println("Enter the longitude (ex., '45.7323'): ");
        double longitude = locationInput.nextDouble();
        System.out.println("You entered latitude: " + latitude + " and longitude: " + longitude);
        return new double[]{latitude, longitude};
    }

    // Magnitude Input
    // Captures Magnitude input
    public static double magnitudeInput() {
        Scanner magnitudeInput = new Scanner(System.in);
        System.out.println("Enter the magnitude: ");
        double magnitude = magnitudeInput.nextDouble();
        System.out.println("You entered magnitude: " + magnitude);
        return magnitude;
    }

    // Location Input
    // Captures Location input
    public static String locationInput() {
        Scanner locationInput = new Scanner(System.in);
        System.out.println("Enter the location (ex., '95km W of San Antonio de los Cobres; Argentina'): ");
        String location = locationInput.nextLine();
        System.out.println("You entered location: " + location);
        return location;
    }

}