
/**
 * week1-3:1.One Key
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class OneKeyCC {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public OneKeyCC(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder inputsb = new StringBuilder(input);
        for (int i=0 ; i<input.length() ; i=i+1){ 
            char ch = inputsb.charAt(i);
            if (Character.isAlphabetic(ch)){
                int index = alphabet.indexOf(Character.toUpperCase(ch));
                char newchar = shiftedAlphabet.charAt(index);
                if (ch == Character.toUpperCase(ch)){ //upper case
                    inputsb.setCharAt(i,newchar); 
                }
                else{
                    inputsb.setCharAt(i,Character.toLowerCase(newchar));
                }    
            }
        }
        return inputsb.toString();
    }
    
    public String decrypt(String input){
        OneKeyCC cc = new OneKeyCC(26-mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
