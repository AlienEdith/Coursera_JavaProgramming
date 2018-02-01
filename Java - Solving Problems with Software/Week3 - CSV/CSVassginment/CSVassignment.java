
/**
 * 在这里给出对类 CSVassignment 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVassignment {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
    CSVRecord coldestinhour = null;
    for (CSVRecord currRow : parser){
        if (coldestinhour == null){
            coldestinhour = currRow;
        }
        else{
            double currentTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestinhour.get("TemperatureF"));
            if (currentTemp < coldestTemp) {
                    coldestinhour = currRow;
                }
        }
    }
    return coldestinhour;
    }
    
    public void testColdestHourInFile(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldest = coldestHourInFile(parser);
            System.out.println("The coldest temperature was " + coldest.get("TemperatureF"));
                     //+" at " + coldest.get("TimeEST"));
        }
    }
    
    public String fileWithColdestTemperature(){
        String coldest_filename = "";
        CSVRecord coldestever = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = coldestHourInFile(parser);
                if (currRow.get("TemperatureF").equals("-9999")){
                continue;
                }
                if (coldestever == null){
                    coldestever = currRow;
                    coldest_filename = f.getPath();
                    
                }
                else{
                    double currentTemp = Double.parseDouble(currRow.get("TemperatureF"));
                    double coldestTemp = Double.parseDouble(coldestever.get("TemperatureF"));
                    if (currentTemp < coldestTemp) {
                        coldestever = currRow;
                        coldest_filename = f.getPath();
                    }
                }    
            
        }
        
        return coldest_filename;
    
    }
    
    public void testFileWithColdestTemperature(){
        String coldestday_filename = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " +coldestday_filename);
        FileResource fr = new FileResource(coldestday_filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestday = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " +coldestday.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        System.out.println("All the Temperatures on the coldest day were: ");
        for (CSVRecord record : fr.getCSVParser()){ 
            //everytime after use Parser,need to reset Parser for next use
            System.out.println(record.get("DateUTC") +": " 
                                    +record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHum = null;
        for (CSVRecord currRow : parser){
        if (currRow.get("Humidity").equals("N/A")){
            continue; //结束本次循环，读取下一行
        }
        if (lowestHum == null){
            lowestHum = currRow;
        }
        else{
            double currentHum =Double.parseDouble(currRow.get("Humidity"));
            double lowestHumTemp = Double.parseDouble(lowestHum.get("Humidity"));
            if (currentHum < lowestHumTemp) {
                    lowestHum = currRow;
                }
        }
    }
        return lowestHum;
    }
    
    public void testLowestHumidityInFile() {
        CSVRecord lowestever = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord currRow = lowestHumidityInFile(parser);
        
        if (lowestever == null){
                    lowestever = currRow;
                }
                else{
                    double currentHum = Double.parseDouble(currRow.get("Humidity"));
                    double coldestTemp = Double.parseDouble(lowestever.get("Humidity"));
                    if (currentHum < coldestTemp) {
                        lowestever = currRow;
                        
                    }
                }    
        }
        System.out.println(" the lowest humidity in that year " +lowestever.get("Humidity"));
        System.out.println(" at  " +lowestever.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for (CSVRecord currRow : parser){
            sum = sum + Double.parseDouble(currRow.get("TemperatureF"));
            count++;
        }
        double average = (double)(sum / count);
        return average;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " +averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        double average=0;
        int count = 0;
        for (CSVRecord currRow : parser){
            if (currRow.get("Humidity").equals("N/A")){
            continue; //结束本次循环，读取下一行
            }
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double currHum = Double.parseDouble(currRow.get("Humidity"));
            if (currHum >= value){
            sum = sum + currTemp;
            count++;
            }
        }
        if (sum != 0){
            average = (double)(sum / count);
        }
        return average;
    }
    
        public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = averageTemperatureWithHighHumidityInFile(parser,80);
        System.out.println(result);
        if (result == 0) {System.out.println("No temperatures with that humidity");}
        else{
            System.out.println("Average Temp when high Humidity is " +result);
        }
    }
}
