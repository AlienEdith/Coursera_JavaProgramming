
/**
 * 在这里给出对类 WordLengths 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class WordLengths {
    public void WordLengths(FileResource resource,int[] counts){
        int index=0;
        for (String word : resource.words()){
            StringBuilder wordsb = new StringBuilder(word);
            int wordlength = word.length();
            char first = wordsb.charAt(0);
            char last = wordsb.charAt(wordlength-1);
            if (Character.isLetter(first) && Character.isLetter(last)){
                index = wordlength;
            }
            else if (!Character.isLetter(first) && !Character.isLetter(last)){
                index = wordlength-2;
            }
            else if (!Character.isLetter(first) || !Character.isLetter(last)){
                index = wordlength-1;
            }
            
            if (index >= counts.length){index=counts.length-1;}
            
            counts[index]++;
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        WordLengths(fr,counts);
        int i=0;
        for (i=1; i<31; i++){
            System.out.println(counts[i] +" words of length " + i);
        }
    }
}
