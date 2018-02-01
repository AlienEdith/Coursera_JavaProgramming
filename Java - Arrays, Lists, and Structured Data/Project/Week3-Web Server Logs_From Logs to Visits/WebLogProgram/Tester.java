
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        System.out.println("NEW-----------------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        //la.printAll(); 
        //testUniqueIP
        int num = la.countUniqueIPs();
        System.out.println("\n The number of unique IPs = "+num);
        //StatusCode Higher than Num;
        System.out.println("\n The LogEntry that with StatusCode higher than 400:");
        la.printAllHigherThanNum(400);
        //uniqueIPVisitOnDay
        System.out.println("\n The unique IP that visit on day \'Sep 27\':");
        ArrayList<String> IPsOnDay = la.uniqueIPVisitsOnDay("Sep 27");
        for (String s : IPsOnDay){
            System.out.println(s);
        }
        System.out.println("\n The number of IP visted on Sep24= "+IPsOnDay.size());
        //countUniqueIPsInRange
        int numInRange = la.countUniqueIPsInRange(400,499);
        System.out.println("\n The number of unique IP in range [400,499] = "+numInRange);
    }
    
    public void testCounts(){
        System.out.println("NEW-----------------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> countsIP = la.countVisitsPerIP();
        System.out.println(countsIP);//format {key1=value1, ...}
        int mostNum = la.mostNumberVisitsByIP(countsIP);
        System.out.println("The maximum number a single IP= "+mostNum);
        ArrayList<String> mostIP = la.iPsMostVisits(countsIP);
        System.out.println("The IPs visit most: "+mostIP);
        HashMap<String,ArrayList<String>> iPsDay = la.iPsForDays();
        System.out.println(iPsDay);
        String maxdate = la.dayWithMostIPVisits(iPsDay);
        System.out.println("The day with most visited IPs: "+maxdate);
        ArrayList<String> iPsMostOnDay = la.iPsWithMostVisitsOnDay(iPsDay,"Sep 30");
        System.out.println("The IPs visit most on Sep 30: "+iPsMostOnDay);
    }
}
