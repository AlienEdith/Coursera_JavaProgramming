
/**
 * 在这里给出对类 TestCaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class TestCaesarCipher {
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
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "f peljlk dHt y y";
        OneKeyCC cc = new OneKeyCC(24);
        //String encrypted = cc.encrypt(message);
        //System.out.println("The encrypted meassage is "+encrypted);
        String decrypted = cc.decrypt(message);
        System.out.println("The decrypted meassage is "+decrypted);
        //breakCaesarCipher(encrypted);
    }
    
    public void breakCaesarCipher(String input){
        int[] counts=countLetters(input);
        int maxIndex=indexOfMax(counts);
        int key = maxIndex -4 ;
        if (maxIndex < 4){
            key = 26-(4-maxIndex);
        }
        OneKeyCC cc = new OneKeyCC(key);
        System.out.println("The breakCaesarCipher key is "+key);
        System.out.println("Encrypted message:\n" + input);
        String decrypted = cc.decrypt(input);
        System.out.println("The  breakCaesarCipher decrypted meassage is "+decrypted);
    }
}
