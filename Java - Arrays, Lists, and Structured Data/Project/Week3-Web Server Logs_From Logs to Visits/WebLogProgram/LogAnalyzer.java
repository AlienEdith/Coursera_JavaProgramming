
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     //private ArrayList<String> uniqueIPs;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
         //uniqueIPs = new ArrayList<String>();
     }
        
     public void readFile(String filename) {
         // determine the filename to read from and then add log entries to record field
         FileResource fr = new FileResource();
         for (String s : fr.lines()){
            WebLogParser wp = new WebLogParser();
            LogEntry le = wp.parseEntry(s);
            records.add(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
            String currIP = le.getIpAddress();
            if (!uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
         }
         int ipNum = uniqueIPs.size();
         return ipNum;
        }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records){
            int currStatus = le.getStatusCode();
            if (currStatus > num){
                System.out.println(le);
            }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){ //MMM DD format
         ArrayList<String> IPsOnDay = new ArrayList<String>();
         for (LogEntry le : records){
            String currTime = le.getAccessTime().toString();
            String currIP = le.getIpAddress();
            if (currTime.contains(someday) && !IPsOnDay.contains(currIP)){
                IPsOnDay.add(currIP);   
            }
         }
         return IPsOnDay;
     }
     
     public int countUniqueIPsInRange(int low,int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
            int currStatus = le.getStatusCode();
            String currIP = le.getIpAddress();
            if (currStatus>=low && currStatus<=high && !uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);   
            }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> countsIP = new HashMap<String,Integer>();
         for (LogEntry le : records){
             String currIP = le.getIpAddress();
             if (countsIP.containsKey(currIP)){
                 countsIP.put(currIP,countsIP.get(currIP)+1);
             }
             else {
                 countsIP.put(currIP,1);
             }
         }
         return countsIP;
     }
     
     public int countUniqueIPswithHM(){ //use countVisitsPerIP to get unique IP,another method
         HashMap<String,Integer> countsIP = countVisitsPerIP();
         return countsIP.size();
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int mostNum=0;
         for (String key : counts.keySet()){
             int currNum = counts.get(key);
             if (currNum > mostNum){
                 mostNum = currNum;
             }
         }
         return mostNum;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         int mostNum = mostNumberVisitsByIP(counts);
         ArrayList<String> mostIPs = new ArrayList<String>();
         for (String key : counts.keySet()){
             int currNum = counts.get(key);
             if (currNum==mostNum){
                 mostIPs.add(key);
             }
         }
         return mostIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> iPsDay = new HashMap<String,ArrayList<String>>();
         ArrayList<String> dates = new ArrayList<String>();
         for (LogEntry le : records){
            String someday = le.getAccessTime().toString().substring(4,10);
            //including repeated IPs
            if (!dates.contains(someday)){
                dates.add(someday);
            }
         }
         for (String date : dates){
             ArrayList<String> visitIP = new ArrayList<String>();
             for (LogEntry le : records){
                 if (le.getAccessTime().toString().contains(date)){
                     String currIP = le.getIpAddress();
                     visitIP.add(currIP);
                 }
             }
             iPsDay.put(date,visitIP);
         }
               
            //only unique IP
            //ArrayList<String> visitIP = uniqueIPVisitsOnDay(someday);
            //if (!iPsDay.containsKey(someday)){
            //    iPsDay.put(someday,visitIP);
            //}
       
         return iPsDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> iPsDay){
         int maxIPs = 0;
         String maxdate ="";
         for (String date: iPsDay.keySet()){
             int currnumOfIPs = iPsDay.get(date).size();
             if (currnumOfIPs > maxIPs){
                 maxdate = date;
                 maxIPs = currnumOfIPs;
             }
         }
         return maxdate;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> iPsDay,String date){
           ArrayList<String> IPsOnDay = iPsDay.get(date); 
           HashMap<String,Integer> counts = new HashMap<String,Integer>();
           for (String ip : IPsOnDay){
               if (counts.containsKey(ip)){
                   counts.put(ip,counts.get(ip)+1);
               }
               else {
                   counts.put(ip,1);
             }
           }
           ArrayList<String> iPsMostOnDay = iPsMostVisits(counts);
           return iPsMostOnDay;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le); //called the toString method implicitly
         }
     }
     
     
}
