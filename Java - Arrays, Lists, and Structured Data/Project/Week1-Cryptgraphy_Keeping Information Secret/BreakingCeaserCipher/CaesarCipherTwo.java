
/**
 * 在这里给出对类 CaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1,mainKey2;
    
    public CaesarCipherTwo(int key1,int key2){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
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
    
    public String encrypt(String input){
        String halfString1 = halfOfString(input,0);
        String halfString2 = halfOfString(input,1);
        
        OneKeyCC cc1 = new OneKeyCC(mainKey1);
        OneKeyCC cc2 = new OneKeyCC(mainKey2);

        String encrypt1 = cc1.encrypt(halfString1);
        String encrypt2 = cc2.encrypt(halfString2);
        StringBuilder encrypted= new StringBuilder(encrypt1);
        int k=0;
        for (int i=1; i<(encrypt1.length()+encrypt2.length());i=i+2){
            encrypted.insert(i,encrypt2.charAt(k));
            k++;
        }     
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypted= cc.encrypt(input);
        return decrypted;
    }
}
