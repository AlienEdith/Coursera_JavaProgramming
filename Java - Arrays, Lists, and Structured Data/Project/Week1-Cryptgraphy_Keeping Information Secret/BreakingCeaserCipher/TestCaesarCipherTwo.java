
/**
 * 在这里给出对类 TestCaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        StringBuilder halfString = new StringBuilder(message);
        int k=0;
        for (int i=start; i< message.length(); i=i+2){
            char ch=message.charAt(i);
            halfString.setCharAt(k,ch);
            k++;
        }
        String half = halfString.toString().substring(0,k);
        return half;
    }
    
    private int[] countLetters(String message){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        int i=0;
        for (i=0; i<message.length(); i++){
            char currCh = Character.toLowerCase(message.charAt(i));
            int index = alpha.indexOf(currCh);
            if (index != -1){
            counts[index]++;
            }
        }
        return counts;
    }
    
    private int indexOfMax(int[] values){
        int maxIndex = 0;
        for (int i=1; i<values.length; i++){
            if (values[maxIndex] < values[i]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private int getkey(String s){
        int key=0;
        int[] counts=countLetters(s);
        int maxIndex=indexOfMax(counts);
        if (maxIndex < 4){
            key = 26-(4-maxIndex);
        }
        else{
            key = maxIndex - 4;
        }
        return key;
    }
    
    public void breakCaesarCipher(String input){
        String halfString1 = halfOfString(input,0);
        String halfString2 = halfOfString(input,1);
        int key1 = getkey(halfString1);
        int key2 = getkey(halfString2);
        System.out.println("The breakCaesarCipher key is "+key1 +" " +key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);
        String decrypted = cc.decrypt(input);
        System.out.println("The breakCaesarCipher decrypted meassage is "+decrypted);
    }    
    
    public void simpleTests(){
        System.out.println("NEW---------- ");
        //FileResource fr = new FileResource();
        //tring message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwo cctwo = new CaesarCipherTwo(21,8);
        String encrypted = cctwo.encrypt(message);
        System.out.println("The encrypted meassage is "+encrypted);
        //String decrypted = cctwo.decrypt(message);
        //System.out.println("The decrypted meassage is "+decrypted);
        //breakCaesarCipher(message);
    }
}
