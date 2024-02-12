import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


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
Formatting of the "raw" quake data is expected.
For example, if the following data line from the file matches the query: 2015-05-02T16:23:07.580Z|42.2357|-85.4285|4.2|5km S of Galesburg; Michigan
reformat it for your output to look like: 02 MAY 15 1623Z, (42.24,-85.43), Mag: 4.2, 5km S of Galesburg; Michigan
 */



    // Method to display the earthquake data query system menu
    // The method reads the user's input and performs the specified query
    // The method calls the queryRegion, queryDate, and queryMagnitude methods based on the user's input
    // The method also validates the user's input and displays an error message for invalid input
    // The method continues to display the menu until the user chooses to exit
    // The method also closes the scanner when the user chooses to exit
    public class EarthquakeReader {
        private static final String FILENAME = "quakes.txt";

        public static void main(String[] args) {
            earthquakeReaderMenu();
        }

        // Method to create the menu for the earthquake data query system
        // The method reads the user's input and performs the specified query
        // The method calls the queryRegion, queryDate, and queryMagnitude methods based on the user's input
        // The method also validates the user's input and displays an error message for invalid input
        // The method continues to display the menu until the user chooses to exit
        // The method also closes the scanner when the user chooses to exit
        public static void earthquakeReaderMenu() {
            Scanner scanner = new Scanner(System.in);

            Earthquake[] earthquakeRecords = readEarthquakeData();

            boolean exit = false;
            while (!exit) {
                System.out.println("Earthquake Data Query System");
                System.out.println("1. Query the Earthquake Data");
                System.out.println("2. Exit");
                System.out.println("Enter your menu choice. 1 or 2:");

                int menuChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (menuChoice) {
                    case 1:
                        System.out.println("Enter your query:");
                        String queryInput = scanner.nextLine();
                        String queryType = String.valueOf(queryInput.charAt(0));
                        switch (queryType) {
                            case "R":
                                queryRegion(queryInput, earthquakeRecords);
                                break;
                            case "D":
                                queryDate(queryInput, earthquakeRecords);
                                break;
                            case "M":
                                queryMagnitude(queryInput, earthquakeRecords);
                                break;
                            default:
                                System.out.println("Invalid query type. Please enter one of the following letters. R, D, or M");
                        }
                        break;
                    case 2:
                        System.out.println("Exiting");
                        scanner.close();
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid menu choice. Please enter one of the following numbers. 1, or 2");
                }
            } while (true);
        }

        // Method to read the earthquake data from the file and store it in an array of Earthquake objects
        // The method reads each line from the file and splits the line into parts using the pipe character as the delimiter
        // The method then creates an Earthquake object using the parts and stores it in the array
        // The method also handles invalid records and returns an array of Earthquake objects
        private static Earthquake[] readEarthquakeData() {
            final int MAX_RECORDS = 121853;
            Earthquake[] earthquakeRecords = new Earthquake[MAX_RECORDS];
            int count = 0;

            try {
                File earthquakeFile = new File(FILENAME);
                Scanner scanner = new Scanner(earthquakeFile);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        String datetime = parts[0];
                        String[] dateTimeParts = datetime.split("T");
                        String date = dateTimeParts[0];
                        String time = dateTimeParts[1].substring(0, 5) + "Z";
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
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
            Earthquake[] result = new Earthquake[count];
            System.arraycopy(earthquakeRecords, 0, result, 0, count);
            return result;
        }


        // Method to query the earthquake data based on the region
        // The method splits the query input into parts using the comma as the delimiter
        // The method then validates the input and displays the earthquakes within the specified region
        // The method also handles invalid input and displays an error message
        private static void queryRegion(String queryInput, Earthquake[] earthquakeRecords) {
            String[] queryParts = queryInput.split(",");
            if (queryParts.length != 5) {
                System.out.println("Invalid query format. Please provide minLat, maxLat, minLon, and maxLon.");
                return;
            }

            try {
                double minLat = Double.parseDouble(queryParts[1]);
                double maxLat = Double.parseDouble(queryParts[2]);
                double minLon = Double.parseDouble(queryParts[3]);
                double maxLon = Double.parseDouble(queryParts[4]);

                System.out.println("Earthquakes within the specified region:");
                for (Earthquake earthquake : earthquakeRecords) {
                    double latitude = earthquake.getLatitude();
                    double longitude = earthquake.getLongitude();
                    if (latitude >= minLat && latitude <= maxLat && longitude >= minLon && longitude <= maxLon) {
                        System.out.println(earthquake);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude format.");
            }
        }

        // Method to query the earthquake data based on the date
        // The method splits the query input into parts using the comma as the delimiter
        // The method then validates the input and displays the earthquakes within the specified date range
        // The method also handles invalid input and displays an error message
        private static void queryDate(String queryInput, Earthquake[] earthquakeRecords) {
            String[] queryParts = queryInput.split(",");
            if (queryParts.length != 3) {
                System.out.println("Invalid query format. Please provide minDate and maxDate.");
                return;
            }

            String minDate = queryParts[1];
            String maxDate = queryParts[2];

            if (Earthquake.validateDateInput(minDate) || Earthquake.validateDateInput(maxDate)) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                return;
            }

            System.out.println("Earthquakes within the specified date range:");
            for (Earthquake earthquake : earthquakeRecords) {
                String earthquakeDate = earthquake.getDate();
                if (earthquakeDate.compareTo(minDate) >= 0 && earthquakeDate.compareTo(maxDate) <= 0) {
                    System.out.println(earthquake);
                }
            }
        }

        // Method to query the earthquake data based on the magnitude
        // The method splits the query input into parts using the comma as the delimiter
        // The method then validates the input and displays the earthquakes with magnitude equal to or greater than the specified magnitude
        // The method also handles invalid input and displays an error message
        private static void queryMagnitude(String queryInput, Earthquake[] earthquakeRecords) {
            String[] queryParts = queryInput.split(",");
            if (queryParts.length != 2) {
                System.out.println("Invalid query format. Please provide minMagnitude.");
                return;
            }

            String minMagnitudeStr = queryParts[1];
            if (Earthquake.validateMagnitudeInput(minMagnitudeStr)) {
                System.out.println("Invalid magnitude format. Please provide a number >= 4.0.");
                return;
            }

            double minMagnitude = Double.parseDouble(minMagnitudeStr);
            System.out.println("Earthquakes with magnitude equal to or greater than " + minMagnitude + ":");
            for (Earthquake earthquake : earthquakeRecords) {
                if (earthquake.getMagnitude() >= minMagnitude) {
                    System.out.println(earthquake);
                }
            }
        }
    }