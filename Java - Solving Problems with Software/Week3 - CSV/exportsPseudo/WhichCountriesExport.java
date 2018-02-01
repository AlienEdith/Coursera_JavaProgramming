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
                for (CSVRecord record : parser){
                    String export = record.get("Exports");
                    if (export.contains(exportOfInterest)){
                    System.out.println("Name of Country= "+record.get("Country"));
                    }
                }
            //Look at the "Exports" column

            //Check if it contains exportOfInterest

                //If so, write down the "Country" from that row

    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
