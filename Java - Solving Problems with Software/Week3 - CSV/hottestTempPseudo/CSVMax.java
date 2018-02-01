/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        //For each row (currentRow) in the CSV File
        //If largestSoFar is nothing
        //Otherwise
        //Check if currentRow’s temperature > largestSoFar’s
        //If so update largestSoFar to currentRow
        //The largestSoFar is the answer
        CSVRecord largestSofar = null;
        for(CSVRecord record : parser){
            if (largestSofar == null){  //IMPORTANT
                largestSofar = record;}
            else{
                double currTemp = Double.parseDouble(record.get("TemperatureF"));
                if (currTemp > Double.parseDouble(largestSofar.get("TemperatureF"))){
                    largestSofar = record;
                }
            }
        }
        return largestSofar;
        }
 
    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
	CSVRecord largest = hottestHourInFile(fr.getCSVParser());
	System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}
}
