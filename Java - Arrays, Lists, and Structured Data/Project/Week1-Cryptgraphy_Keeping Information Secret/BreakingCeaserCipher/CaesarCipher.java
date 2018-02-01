
/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class CaesarCipher{
    public String encrypt(String input,int key,int k, int interval){//int k startpoint
        StringBuilder inputsb = new StringBuilder(input);
        String upperalphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String loweralphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedupper = upperalphabet.substring(key)+upperalphabet.substring(0,key);
        String shiftedlower = loweralphabet.substring(key)+loweralphabet.substring(0,key);
        int i=0;
        for (i=k ; i<input.length() ; i=i+interval){ //+1 for part1,I+2 for part2
            char ch = input.charAt(i);
            if (Character.isAlphabetic(ch)){ 
                int upperindex = upperalphabet.indexOf(ch);
                int lowerindex = loweralphabet.indexOf(ch);
                if (upperindex==-1){ //lower
                    int index = loweralphabet.indexOf(ch);
                    char newchar = shiftedlower.charAt(lowerindex);
                    inputsb.setCharAt(i,newchar);
                }
                if (lowerindex==-1){ //upper
                    int index = upperalphabet.indexOf(ch);
                    char newchar = shiftedupper.charAt(upperindex);
                    inputsb.setCharAt(i,newchar);
                }
                
            }
        }
        return inputsb.toString();
    }
    
    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder inputsb = new StringBuilder(input);
        StringBuilder encryptkey1 = new StringBuilder(encrypt(input,key1,0,2));
        StringBuilder encryptkey2 = new StringBuilder(encrypt(input,key2,1,2));
        int i=0;
        for (i=0 ; i<input.length() ; i=i+1){
            if (i%2!=1){
                char newchar = encryptkey1.charAt(i);
                inputsb.setCharAt(i,newchar);
            }
            else{
                char newchar = encryptkey2.charAt(i);
                inputsb.setCharAt(i,newchar);
            }
        }
        return inputsb.toString();
    }
    
    public void testCaesar() {
        //int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encrypt(message, key);
        //System.out.println("key is " + key + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String encrypted = encryptTwoKeys(input,8,21);
        //String encrypted = encrypt(input,key,0);
        //System.out.println(encrypted);
    }
}
