import java.util.*;
import edu.duke.*;
import java.io.File;  
import java.io.FileWriter; 
import java.io.IOException;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder ss = new StringBuilder();
        for (int i=whichSlice; i<message.length();i+=totalSlices){
            char ch = message.charAt(i);
            ss.append(ch);
        }
        return ss.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0; i<klength; i++){
            String enss = sliceString(encrypted,i,klength);
            CaesarCracker ccCrack = new CaesarCracker();
            key[i] = ccCrack.getKey(enss);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for (String words : fr.lines()){
            dictionary.add(words.toLowerCase());//some words in dictionary not all lower case
        }
        return dictionary;
    }
    
    public int countWords(String message,HashSet<String> dictionaries){
        int number=0;
        for (String word : message.split("\\W+")){
            word = word.toLowerCase();
            if (dictionaries.contains(word)){
                number++;
            }
        }
        return number;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int[] wordsCount = new int[100];
        int keylength=0;
        int wordsMax=0;
        String decrypted="";
        for (int klength=1;klength<100;klength++){
            int[] key = tryKeyLength(encrypted,klength,mostCommonCharIn(dictionary)); 
            VigenereCipher vc =new VigenereCipher(key);
            String decryptedTemp = vc.decrypt(encrypted);
            int wordNum = countWords(decryptedTemp,dictionary);
            wordsCount[klength]=wordNum;
            if (wordNum>wordsMax){
                keylength = klength;
                wordsMax = wordNum;
                decrypted = decryptedTemp;
            }
        }
        System.out.println("The keylength = "+keylength);
        System.out.println("The valid words = "+wordsMax);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCounts = new HashMap<Character,Integer>();
        char mostCh='\0';
        for (String word:dictionary){
            for (int i=0; i<word.length(); i++){
                char ch=word.charAt(i);
                if (charCounts.containsKey(ch)){
                    charCounts.put(ch,charCounts.get(ch)+1);
                }
                else charCounts.put(ch,1);
            }
        } //get the number of each char's occurrence
        int mostNum=0;
        for (char key:charCounts.keySet()){
            int num = charCounts.get(key);
            if (num > mostNum){
                mostCh = key;
                mostNum = num;
            }
        }
        return mostCh;
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> language){
        String decrypted="";
        HashMap<String,Integer> validWords = new HashMap<String,Integer>();
        for (String langu: language.keySet()){
            System.out.println("NOW "+langu);
            HashSet<String> dict = language.get(langu);
            String decryptedTemp = breakForLanguage(encrypted,dict);
            int num = countWords(decryptedTemp,dict);
            validWords.put(langu,num);
        }
        int mostNum=0;
        String choseLan="";
        for (String key:validWords.keySet()){
            int num = validWords.get(key);
            if (num > mostNum){
                choseLan = key;
                mostNum = num;
            }
        }
        System.out.println("CHOOSE "+choseLan);
        decrypted = breakForLanguage(encrypted,language.get(choseLan));
        System.out.println(decrypted);
    }
    
    public void breakVigenere1 () {
        System.out.println("NEW-------------------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        FileResource rd = new FileResource("E:/Coursera Java/3.Java - Arrays, Lists, and Structured Data/Project/Week4-Breaking the Vigenère Cipher/VigenereProgram/dictionaries/English");
        HashSet<String> dictionary = readDictionary(rd);
        
        int[] key = tryKeyLength(message,38,'e');
        for (int i=0; i<key.length; i++){
            System.out.println(key[i]);
        }
        VigenereCipher vc =new VigenereCipher(key);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
        int wordsNum = countWords(decrypted,dictionary);
        System.out.println("The number of valid words= "+wordsNum);
    }
    
    public void breakVigenere2 () {
        System.out.println("NEW-------------------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        FileResource rd = new FileResource("E:/Coursera Java/3.Java - Arrays, Lists, and Structured Data/Project/Week4-Breaking the Vigenère Cipher/VigenereProgram/dictionaries/English");
        HashSet<String> dictionary = readDictionary(rd);
        
        String decrypted = breakForLanguage(message,dictionary);
        /*FileWriter writer; //save to a file
        try {
            writer = new FileWriter("./save.txt");
            writer.write(decrypted);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    
    public void breakVigenere3(){
        System.out.println("LOAD DICT-------------------------------------");
        HashMap<String,HashSet<String>> language = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            language.put(f.getName(),readDictionary(fr));
        }
        System.out.println("LOAD DICT DONE");
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        breakForAllLangs(message,language);
    }
}
    

