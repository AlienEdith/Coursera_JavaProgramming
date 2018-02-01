/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public String countryInfo(CSVParser parser, String country){
        String result="NOT FOUND";
        for (CSVRecord record : parser) {
            //Look at the "Country" column
            String countryname = record.get("Country");
            //Check if it contains exportOfInterest
            if (countryname.contains(country)) {
                //If so, write down the Country information from that row
                String export = record.get("Exports");
                String dollar = record.get("Value (dollars)");
                result = countryname +": " +export +": " +dollar;
            }
        }
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count=0;
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        int count=0;
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String value = record.get("Value (dollars)");
            //Check if it contains exportOfInterest
            if (value.length()> amount.length()) {
                String country = record.get("Country");
                System.out.println(country +" " +value);
            }
        }
   
    }
    
    public void tester() {
       //Each time you want to use the parser with another method, 
       //you will need to RESET the parser by calling fr.getCSVParser() again to get a new parser.
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "coffee");
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton","flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
}
