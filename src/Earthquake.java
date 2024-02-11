import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Earthquake Class
public class Earthquake {
    private final String date;
    private final String time;
    private final double latitude;
    private final double longitude;
    private final double magnitude;
    private final String location;
    final static String FILENAME = "quakes.txt";


    // Constructor
    public Earthquake(String date, String time, double latitude, double longitude, double magnitude, String location) {
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.location = location;
    }


    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    // Read Earthquake Data
    // Read the earthquake data from the file and return an array of Earthquake objects
    // The file is named quakes.txt and is located in the src folder
    // The file contains earthquake data in the following format:
    // 2019-01-01T00:00:00|19.246|145.616|4.7|Northern Mariana Islands
    // T + | are the deliminators for the data
    public static Earthquake[] readEarthquakeData() {
        final int MAX_RECORDS = 121853; //
        // Create an array of Earthquake objects
        // The array size is set to the maximum number of records in the file
        Earthquake[] earthquakeRecords = new Earthquake[MAX_RECORDS];

        // Counter
        // Count the number of records in the file
        // This is used to create a new array of the correct size
        int count = 0;

        try {
            File earthquakeFile = new File(FILENAME);
            Scanner scanner = new Scanner(earthquakeFile);

            // Read the file line by line
            // Split each line into its parts
            // Create a new Earthquake object and add it to the array
            // Increment the counter
            // If the record is invalid, print an error message
            // Close the scanner
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String datetime = parts[0];
                    String[] dateTimeParts = datetime.split("T");
                    String date = dateTimeParts[0];
                    String time = dateTimeParts[1];
                    double latitude = Double.parseDouble(parts[1]);
                    double longitude = Double.parseDouble(parts[2]);
                    double magnitude = Double.parseDouble(parts[3]);
                    String location = parts[4];
                    earthquakeRecords[count] = new Earthquake(date, time, latitude, longitude, magnitude, location);
                    count++;
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Earthquake[] result = new Earthquake[count];
        System.arraycopy(earthquakeRecords, 0, result, 0, count);
        return result;
    }

    // Query by Date
    // queryByDate()
    // This method should print out the earthquake data for the given date
    // The date is a string
    // The date is in the format YYYY-MM-DD
    // This query does not include time
    public void queryByDate(String date) {
        Earthquake[] earthquakeRecords = readEarthquakeData();
        for (Earthquake earthquake : earthquakeRecords) {
            if (earthquake.getDate().equals(date)) {
                System.out.println("Date: " + earthquake.getDate() + ", Time: " + earthquake.getTime() + ", Latitude: " + earthquake.getLatitude() + ", Longitude: " + earthquake.getLongitude() + ", Magnitude: " + earthquake.getMagnitude() + ", Location: " + earthquake.getLocation());
            }
        }
    }

    // Query by Longitude/Latitude
    // queryByLongitudeLatitude()
    // This method should print out the earthquake data for the given longitude and latitude
    // The longitude and latitude are doubles
    public void queryByLongitudeLatitude(double latitude, double longitude) {
        Earthquake[] earthquakeRecords = readEarthquakeData();
        for (Earthquake earthquake : earthquakeRecords) {
            if (earthquake.getLatitude() == latitude && earthquake.getLongitude() == longitude) {
                System.out.println("Date: " + earthquake.getDate() + ", Time: " + earthquake.getTime() + ", Latitude: " + earthquake.getLatitude() + ", Longitude: " + earthquake.getLongitude() + ", Magnitude: " + earthquake.getMagnitude() + ", Location: " + earthquake.getLocation());
            }
        }
    }

    // Query by Magnitude
    // queryByMagnitude()
    // This method should print out the earthquake data for the given magnitude
    // The magnitude is a double
    public void queryByMagnitude(double magnitude) {
        Earthquake[] earthquakeRecords = readEarthquakeData();
        for (Earthquake earthquake : earthquakeRecords) {
            if (earthquake.getMagnitude() == magnitude) {
                System.out.println("Date: " + earthquake.getDate() + ", Time: " + earthquake.getTime() + ", Latitude: " + earthquake.getLatitude() + ", Longitude: " + earthquake.getLongitude() + ", Magnitude: " + earthquake.getMagnitude() + ", Location: " + earthquake.getLocation());
            }
        }
    }

    // Query by Location
    // queryByLocation()
    // This method should print out the earthquake data for the given location
    // The location is a string
    public void queryByLocation(String location) {
        Earthquake[] earthquakeRecords = readEarthquakeData();
        for (Earthquake earthquake : earthquakeRecords) {
            if (earthquake.getLocation().equals(location)) {
                System.out.println("Date: " + earthquake.getDate() + ", Time: " + earthquake.getTime() + ", Latitude: " + earthquake.getLatitude() + ", Longitude: " + earthquake.getLongitude() + ", Magnitude: " + earthquake.getMagnitude() + ", Location: " + earthquake.getLocation());
            }
        }
    }
}