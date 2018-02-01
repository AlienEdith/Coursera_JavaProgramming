
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany(String stringa,String stringb){
    int count=0;
    int searchIndex = 0;
    while(true){
    searchIndex=stringb.indexOf(stringa,searchIndex);
    if (searchIndex==-1) break;
    count++;
    searchIndex=searchIndex+stringa.length();
    
    }
    return count;
    }
    
    public void testHowMany(){
    String a = "AA";    
    String b = "ATAAAA";
    System.out.println(howMany(a,b));
    }
}
