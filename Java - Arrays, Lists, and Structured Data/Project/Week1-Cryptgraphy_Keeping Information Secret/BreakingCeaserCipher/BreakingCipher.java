
/**
 * 在这里给出对类 BreakingCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class BreakingCipher {
    public int[] countLetters(String message){
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
    
    public int indexOfMax(int[] values){
        int num = values.length;
        int i=0;
        int maxIndex = 0;
        for (i=1; i<num; i++){
            if (values[maxIndex] < values[i]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public String decrypt(String encrypted){
        String decrypted=null;
        int enkey = getkey(encrypted);
        /*int[] counts = countLetters(encrypted);
        int maxIndex = indexOfMax(counts);
        if (maxIndex < 4){
            enkey = 26-(4-maxIndex);
        }
        else{
            enkey = maxIndex - 4;
        }
        */
        CaesarCipher cc = new CaesarCipher();
        decrypted =cc.encrypt(encrypted,26-enkey,0,1) ;
        
        return decrypted;
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
    
    public int getkey(String s){
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
    
    public String decryptTwoKeys(String encrypted){
        String halfstr1 = halfOfString(encrypted,0);
        String halfstr2 = halfOfString(encrypted,1);
        int key1 = getkey(halfstr1);
        int key2 = getkey(halfstr2);
        System.out.println("The keys are "+key1 +" " +key2);
        StringBuilder decrypted= new StringBuilder(decrypt(halfstr1));
        String decrypted2= decrypt(halfstr2);
        int k=0;
        for (int i=1; i<(halfstr1.length()+halfstr2.length());i=i+2){
            decrypted.insert(i,decrypted2.charAt(k));
            k++;
        }
        return decrypted.toString();
    }
    
    
    public void testDecrypt(){
        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
}
