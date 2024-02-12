import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Earthquake {
    private final String date;
    private final String time;
    private final double latitude;
    private final double longitude;
    private final double magnitude;
    private final String location;

    // Constructor to initialize the earthquake details
    // Use the .this keyword to distinguish between the instance variables and the parameters
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

    // Validate the date input
    // Return true if the input is not in the format yyyy-MM-dd or the first date is after the second date
    // Use the String.split method to separate the dates
    // Use the LocalDate.parse method to parse the dates
    // Use the LocalDate.isAfter method to compare the dates
    public static boolean validateDateInput(String input) {
        String[] dates = input.split(",");
        if (dates.length != 2) {
            return true;
        }
        try {
            LocalDate firstDate = LocalDate.parse(dates[0]);
            LocalDate secondDate = LocalDate.parse(dates[1]);
            return firstDate.isAfter(secondDate);
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    // Validate the magnitude input
    // Return true if the input is not a number or the magnitude is less than 4.0
    // Use the Double.parseDouble method to parse the input
    public static boolean validateMagnitudeInput(String input) {
        try {
            double magnitude = Double.parseDouble(input);
            return !(magnitude >= 4.0);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    // Format the date to dd MMM yy
    // Example: formatDate("2020-01-01") returns "01 JAN 20"
    // Use the DateTimeFormatter class to parse the date
    // Use the LocalDate class to format the date
    // Use the DateTimeFormatter.ofPattern method to specify the date format
    private static String formatDate(String rawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(rawDate, formatter);
        return String.format("%02d %s %02d", date.getDayOfMonth(), getMonthAbbreviation(date.getMonthValue()), date.getYear() % 100);
    }

    // Format the time to hhmmZ
    // Example: formatTime("12:34") returns "1234Z"
    // Use the split method to separate the hours and minutes
    // Use the String.format method to format the hours and minutes to two digits
    public static String formatTime(String rawTime) {
        String[] parts = rawTime.split(":");
        if (parts.length >= 2) {
            return parts[0] + parts[1] + "Z";
        } else {
            // Handle invalid time format
            return "";
        }
    }

    // Return the three-letter abbreviation of the month
    // Example: getMonthAbbreviation(1) returns "JAN"
    // Use the month value to index into the months array
    // Return the month abbreviation in uppercase
    // Use the month value - 1 to index into the months array
    private static String getMonthAbbreviation(int month) {
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return months[month - 1];
    }

    // toString to print the earthquake details
    // Format: "dd MMM yy, hhmmZ, (lat,lon), Mag: magnitude, location"
    // Example: "01 JAN 20, 1234Z, (12.34,56.78), Mag: 5.6, California"
    // Use the formatDate, formatTime, and String.format methods to format the output
    // Use the String.format method to format the latitude and longitude to two decimal places
    // Use the String.format method to format the magnitude to one decimal place
    // Use the location string as is
    @Override
    public String toString() {
        String formattedDate = formatDate(date);
        String formattedTime = formatTime(time);
        String formattedLatitude = String.format("%.2f", latitude);
        String formattedLongitude = String.format("%.2f", longitude);

        return formattedDate + ", " + formattedTime + ", (" + formattedLatitude + "," + formattedLongitude + "), Mag: " +
                magnitude + ", " + location;
    }
}