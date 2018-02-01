
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {

    public String twoOccurrences(String stringa, String stringb){
        String result="";
        int firstindex = stringb.indexOf(stringa);
            if (firstindex==-1){
            result = "false";
            }
            else{
            int secondindex=stringb.indexOf(stringa,firstindex+1);
            if (secondindex==-1){
            result = "false";
            }
            else{
            result = "true";
            }
        }
        return result;
    }
    
    public String lastPart(String stringa, String stringb){
        String result="";
        int straindex = stringb.indexOf(stringa);
            if (straindex==-1){
            result = stringb;
            }
            else{
            result = stringb.substring(straindex+stringa.length());
            }
        return result;
    }
    
    public void testing(){
    String sa1 = "by";
    String sb1 = "A story by Abby Long";
    String sa2 = "atg";
    String sb2 = "ctgtatgta";
    
    String la1 = "an";
    String lb1 = "banana";
    String la2 = "zoo";
    String lb2 = "forest";
    
    String result1 = twoOccurrences(sa1, sb1);
    System.out.println("The stringa of s1 is "+ sa1);
    System.out.println("The stringb of s1 is "+ sb1);
    System.out.println("The result of s1 is "+ result1);
    
    String result2 = twoOccurrences(sa2, sb2);
    System.out.println("The stringa of s2 is "+ sa2);
    System.out.println("The stringb of s2 is "+ sb2);
    System.out.println("The result of s2 is "+ result2);
    
    String result3 = lastPart(la1, lb1);
    System.out.println("The stringa of l1 is "+ la1);
    System.out.println("The stringb of l1 is "+ lb1);
    System.out.println("The result of l1 is "+ result3);
    
    String result4 = lastPart(la2, lb2);
    System.out.println("The stringa of l2 is "+ la2);
    System.out.println("The stringb of l2 is "+ lb2);
    System.out.println("The result of l2 is "+ result4);
    }
}
