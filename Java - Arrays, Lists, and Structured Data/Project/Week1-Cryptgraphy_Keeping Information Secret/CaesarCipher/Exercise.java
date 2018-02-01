
/**
 * 在这里给出对类 Exercise 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Exercise {
    public String encrypt(String input,int key){
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //create the shifter alphabet
        String shifted = alphabet.substring(key)+alphabet.substring(0,key);
        //encypt the string s
        int i=0;
        for (i=0 ; i<input.length() ; i++){
            char ch = Character.toUpperCase(input.charAt(i));
            if (Character.isAlphabetic(ch)){
                int index = alphabet.indexOf(ch);
                char newchar = shifted.charAt(index);
                sb.setCharAt(i,newchar);
            }
        }
        return sb.toString();
    }
    
    public void testCaesar(){
        int key=17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(message);
        String encrypted = encrypt(message,key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted,26-key);
        System.out.println(decrypted);
    }
}
