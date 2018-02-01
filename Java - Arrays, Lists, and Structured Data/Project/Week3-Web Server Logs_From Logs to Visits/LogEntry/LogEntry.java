
/**
 * Write a description of class LogRecord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import  java.util.*;
public class LogEntry
{
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     
   public LogEntry(String ip, Date time, String req, int status, int bytes) {
       ipAddress = ip;
       accessTime = time;
       request = req;
       statusCode = status;
       bytesReturned = bytes;
       
   }
   
   public String getIpAddress() {
         return ipAddress;
    }
    public Date getAccessTime() {
         return accessTime;
   }   
   public String getRequest() {
         return request;
   }
   public int getStatusCode() {
         return statusCode;
   }
   public int getBytesReturned() {
         return bytesReturned;
   }
   
   public String toString() { //ORIGINAL ONE-print out the log string
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
   }
    
   //REASON: each class has a toString method by default, only knows to print out the memory 
   //       address of an object, unless you actually specify a toString method.
   //        When print the object without calling any method else, toString will be called
   
   /*public String getLogInfo() { //MODIFIED-print out the object location in memory, the default
                                //         toString method was called
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
   }*/
}
