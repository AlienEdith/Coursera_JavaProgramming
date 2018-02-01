
/**
 * 在这里给出对类 BabyName 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyName {
    public void NameNumber(){
        FileResource fr = new FileResource();
        CSVParser parser= fr.getCSVParser(false);
        int numtotal = 0;
        int Fname = 0;
        int Mname = 0;
        for (CSVRecord record : parser){
            numtotal++;
            if (record.get(1).equals("F")){ Fname++; }
            else{ Mname++;}    
        }
        System.out.println("Number of total Name " +numtotal);
        System.out.println("Number of total Girl Name " +Fname);
        System.out.println("Number of total Boy Name " +Mname);
    }
    
    public void totalBirths(FileResource fr){
        CSVParser parser= fr.getCSVParser(false);
        int numtotal = 0;
        int numbF = 0;
        int numbM = 0;
        for (CSVRecord record : parser){
            int numbirth = Integer.parseInt(record.get(2));
            numtotal += numbirth;
            if (record.get(1).equals("F")){ numbF += numbirth; }
            else{ numbM += numbirth;}    
        }
        System.out.println("Number of total birth " +numtotal);
        System.out.println("Number of total Girl birth " +numbF);
        System.out.println("Number of total Boy birth " +numbM);
    }
    
    public void testtotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank=0;
        int count=0;
        String filename = "yob"+year;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/"+filename+".csv");
        CSVParser parser= fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                rank++;
                if (record.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        String filename = "yob"+year;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/"+filename+".csv");
        CSVParser parser= fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
            int nameRank = getRank(year,record.get(0),gender);
            if (nameRank == rank){return record.get(0);} 
            }
        }
        return "NO Name";
    }
    
    public void whatIsNameInYear(String name,int year,int newyear,String gender){
        int nameRank = getRank(year,name,gender);
        String newname = getName(newyear,nameRank,gender);
        System.out.println(name +" born in " + year +" would be " +newname
                                +" if she was born in " + newyear);
    }
    
    public int yearOfHighestRank(String name,String gender){
        int HighestRank=1000000000;
        int HighestYear=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String filename = f.getName().substring(3,7);
            int year = Integer.parseInt(filename);
            int currRank = getRank(year,name,gender);
            if (currRank == -1) {continue;}
            if (currRank < HighestRank){
                HighestRank = currRank;
                HighestYear = year;
            }
        }
         return HighestYear;
    }
    
    public double getAverageRank(String name,String gender){
        double avgRank = 0;
        double sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String filename = f.getName().substring(3,7);
            int year = Integer.parseInt(filename);
            int currRank = getRank(year,name,gender);
            if (currRank == -1){return -1;}
            else{
                sum += currRank;
                count++;
            }
        }
        avgRank = sum / count;
        return avgRank;
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        int totalbirth = 0;
        int nameRank = getRank(year,name,gender);
        System.out.println(nameRank);
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year +".csv");
        CSVParser parser= fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                int currRank = getRank(year,record.get(0),gender);
                System.out.println(currRank);
                if (currRank == -1) {continue;}
                if (currRank < nameRank){
                    totalbirth += Integer.parseInt(record.get(2));
                }
                if (currRank == nameRank) {break;}  //save running time
            }
        }
        return totalbirth;
    }
}
